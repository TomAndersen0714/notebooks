# Spark On YARN 内存模型

——从源码解析 Spark On YARN 内存模型。

## 前言

本文主要内容是介绍在 Spark 2.4.3 版本中 Spark on YARN 集群下的 Driver 和 Executor 的内存模型，以及 Spark 的内存管理机制。

在不同版本的 Spark 中，其对应的 executor 和 driver 的内存模型也不尽相同。

## Driver VS Executor

Driver 是 Spark 应用的主进程，负责管理和协调整个 Spark 应用程序的执行，以及存储 SparkContext、SparkSession 等共享对象，其内存主要由 Driver Memory 和 Driver Memory Overhead 两部分组成：

```scala

// org.apache.spark.deploy.yarn.Client#VerifyClusterResources
    val amMem = amMemory + amMemoryOverhead
    if (amMem > maxMem) {
      throw new IllegalArgumentException(s"Required AM memory ($amMemory" +
        s"+$amMemoryOverhead MB) is above the max threshold ($maxMem MB) of this cluster! " +
        "Please check the values of 'yarn.scheduler.maximum-allocation-mb' and/or " +
        "'yarn.nodemanager.resource.memory-mb'.")
    }
    logInfo("Will allocate AM container, with %d MB memory including %d MB overhead".format(
      amMem,
      amMemoryOverhead))
```

在 Cluster 部署模式下，Driver 进程运行在 YARN 集群的 Application Master（即 Container）中，其阈值分别由配置 `spark.driver.memory` 和 `spark.driver.memoryOverhead` 来控制。

在 Client 部署模式下，Driver 进程运行在 Application 提交命令执行的服务器上，Application Master 只负责管理资源，而不负责 Spark 任务的分发，其阈值分别由配置 `spark.yarn.am.memory`（默认值 512 m）和 `spark.yarn.am.memoryOverhead` 来控制。

若未设置 `spark.yarn.am.memoryOverhead` ，则取 `MEMORY_OVERHEAD_FACTOR * amMemory` 和 `MEMORY_OVERHEAD_MIN` 两者之间的最大值，其中 `MEMORY_OVERHEAD_FACTOR` 默认值为 0.1，`MEMORY_OVERHEAD_MIN` 默认值为 384。

```scala
// org.apache.spark.deploy.yarn.Client

  // AM related configurations
  private val amMemory = if (isClusterMode) {
    sparkConf.get(DRIVER_MEMORY).toInt
  } else {
    sparkConf.get(AM_MEMORY).toInt
  }
  private val amMemoryOverhead = {
    val amMemoryOverheadEntry = if (isClusterMode) DRIVER_MEMORY_OVERHEAD else AM_MEMORY_OVERHEAD
    sparkConf.get(amMemoryOverheadEntry).getOrElse(
      math.max((MEMORY_OVERHEAD_FACTOR * amMemory).toLong, MEMORY_OVERHEAD_MIN)).toInt
  }
  private val amCores = if (isClusterMode) {
    sparkConf.get(DRIVER_CORES)
  } else {
    sparkConf.get(AM_CORES)
  }
```

Executor 是 Spark Task 实际运行的节点，同时负责保存 Task 运行的中间和最终结果。在 YARN 集群中，Executor 的 Memory，主要由 Executor Memory、Executor Memory Overhead 和 Pyspark Worker Memory 三部分组成。

```scala
// org.apache.spark.deploy.yarn.Client

  /**
   * Fail fast if we have requested more resources per container than is available in the cluster.
   */
  private def verifyClusterResources(newAppResponse: GetNewApplicationResponse): Unit = {
    val maxMem = newAppResponse.getMaximumResourceCapability().getMemory()
    logInfo("Verifying our application has not requested more than the maximum " +
      s"memory capability of the cluster ($maxMem MB per container)")
    val executorMem = executorMemory + executorMemoryOverhead + pysparkWorkerMemory
    if (executorMem > maxMem) {
      throw new IllegalArgumentException(s"Required executor memory ($executorMemory), overhead " +
        s"($executorMemoryOverhead MB), and PySpark memory ($pysparkWorkerMemory MB) is above " +
        s"the max threshold ($maxMem MB) of this cluster! Please check the values of " +
        s"'yarn.scheduler.maximum-allocation-mb' and/or 'yarn.nodemanager.resource.memory-mb'.")
    }
```

Executor Memory：对应配置参数为 `spark.executor.memory`，默认值为 1 g。

Executor Memory Overhead：优先取配置参数 `spark.executor.memoryOverhead`，若此配置值为空，则取 `MEMORY_OVERHEAD_FACTOR * executorMemory`，和 `MEMORY_OVERHEAD_MIN` 两者之间的最大值，其中 `MEMORY_OVERHEAD_FACTOR` 默认值为 0.10，`MEMORY_OVERHEAD_MIN` 默认值为 384 MB。

PySpark Executor Memory：如果是 PySpark 上提交的 Application，则还需要申请 PySpark Executor Memory 用于运行 PySpark Executor，其对应配置参数为 `spark.executor.pyspark.memory`，默认值为 0。

其中 YARN Container 内存分配相关配置参数如下:
- `yarn.nodemanager.resource.memory-mb`: YARN 中 NM 节点最多可以分配给 Container 的总物理内存大小
- `yarn.scheduler.maximum-allocation-mb`：YARN 中 NM 节点给每个 Container 分配的内存上限
- [yarn-default.xml](https://hadoop.apache.org/docs/stable/hadoop-yarn/hadoop-yarn-common/yarn-default.xml)

## Heap Memory VS Off-Heap Memory

Off-heap Memory 指的是非 JVM 内存空间，即不由 JVM 控制，而是由 Java 程序自行申请、使用和释放的系统内存，可以用于避免 JVM GC 操作，以及 IO 时堆内外内存的频繁拷贝，对当前程序的性能产生的负面影响，在 JDK 1.8 中一般是通过 `sun.misc` 包中提供的 API 进行 off-heap 内存操作。

Off-heap Memory 受到 `spark.executor.memoryOverhead` 和 `spark.driver.memoryOverhead` 参数控制，同样占用 YARN Container 内存资源的一部分。

```scala
// org.apache.spark.deploy.yarn.YarnAllocator

  // Resource capability requested for each executors
  private[yarn] val resource = Resource.newInstance(
    executorMemory + memoryOverhead + pysparkWorkerMemory,
    executorCores)
```

## Storage Memory VS Execution Memory

在 Spark 中不论是堆内内存 on-heap memory，或者非堆内存 off-heap memory，其内存都主要分为两大类，执行内存（Execution Memory）和存储内存（Storage Memory）。其中 Execution 内存用于存储计算结果，如 shuffles/joins/sorts/aggregations 等，而 Storage 内存则用于 Cache 缓存并共享数据。

```scala
// org.apache.spark.memory.MemoryManager

  // -- Methods related to memory allocation policies and bookkeeping ------------------------------

  @GuardedBy("this")
  protected val onHeapStorageMemoryPool = new StorageMemoryPool(this, MemoryMode.ON_HEAP)
  @GuardedBy("this")
  protected val offHeapStorageMemoryPool = new StorageMemoryPool(this, MemoryMode.OFF_HEAP)
  @GuardedBy("this")
  protected val onHeapExecutionMemoryPool = new ExecutionMemoryPool(this, MemoryMode.ON_HEAP)
  @GuardedBy("this")
  protected val offHeapExecutionMemoryPool = new ExecutionMemoryPool(this, MemoryMode.OFF_HEAP)

  onHeapStorageMemoryPool.incrementPoolSize(onHeapStorageMemory)
  onHeapExecutionMemoryPool.incrementPoolSize(onHeapExecutionMemory)

  protected[this] val maxOffHeapMemory = conf.get(MEMORY_OFFHEAP_SIZE)
  protected[this] val offHeapStorageMemory =
    (maxOffHeapMemory * conf.getDouble("spark.memory.storageFraction", 0.5)).toLong

  offHeapExecutionMemoryPool.incrementPoolSize(maxOffHeapMemory - offHeapStorageMemory)
  offHeapStorageMemoryPool.incrementPoolSize(offHeapStorageMemory)
```

Spark 为 Storage 内存和 Execution 内存的管理提供了统一的接口 MemoryManager，同一个 Executor 内的任务都调用这个接口的方法来申请或释放内存，目前支持两种内存管理方式：静态内存管理（Static Memory Manager）和统一内存管理（Unified Memory Manager）。

![](resources/images/Pasted%20image%2020241025092600.png)

在 Spark 1.6 以前默认采用的是静态内存管理 StaticMemoryManager 的方式；而在 Spark 1.6 以后，默认采用的是统一内存管理 UnifiedMemoryManager 的方式，可以通过配置 `spark.memory.useLegacyMode` 来控制是否启用传统的静态内存管理方式 StaticMemoryManager。

```scala
// org.apache.spark.SparkEnv

    val useLegacyMemoryManager = conf.getBoolean("spark.memory.useLegacyMode", false)
    val memoryManager: MemoryManager =
      if (useLegacyMemoryManager) {
        new StaticMemoryManager(conf, numUsableCores)
      } else {
        UnifiedMemoryManager(conf, numUsableCores)
      }

```

Unified MemoryManager 为 Execution 内存和 Storage 内存分配了一片共享内存区域（M)，当 Spark Application 没有使用 Execution 内存时，Storage 内存可以使用 M 中的所有可用的内存，反之亦然。

不同的是，Execution 内存分配的优先级要高于 Storage 内存，当 M 无法再为 Execution 分配内存时，则会尝试释放和回收 Storage 内存，直到 Storage 内存的占用量下降到阈值（R）。

其中内存区域 M 和阈值 R 的相关配置参数如下（但官方并不建议调整，因为其默认值已适用于大多数应用场景）：
- `spark.memory.fraction`，表示内存区域 M 占用堆内存 Heap Memory 的比例，计算公式为 (JVM Heap Memory-300 MiB) 的百分比（默认值为 0.6，即 60%），而其余（40%）的堆内存空间主要用于存储用户数据结构、Spark 的内部元数据，以及作为安全储备，以防出现异常记录而导致出现 OOM 报错。此参数越小，约容易出现 Spill 溢出写盘和 Cache 缓存失效。
- `spark.memory.storageFraction`，表示内存区域 R 占用 M 的比例（默认值为 0.5）。R 是 M 内部的一块存储区域，其内存空间会优先分配给 Storage，不会因为 Execution 竞争内存资源而被强制回收。

![](resources/images/Pasted%20image%2020241025024419.png)

## 参考链接

1. [Decoding Memory in Spark — Parameters that are often confused | by Sohom Majumdar | Walmart Global Tech Blog | Medium](https://medium.com/walmartglobaltech/decoding-memory-in-spark-parameters-that-are-often-confused-c11be7488a24)
2. [Difference between "spark.yarn.executor.memoryOverhead" and "spark.memory.offHeap.size" - Stack Overflow](https://stackoverflow.com/questions/58666517/difference-between-spark-yarn-executor-memoryoverhead-and-spark-memory-offhea/61723456#61723456)
3. [Spark内存管理之堆内/堆外内存原理详解\_你知道启用off-heap之后,spark有哪些计算环节可以利用到堆外内存-CSDN博客](https://blog.csdn.net/pre_tender/article/details/101517789)
4. [Spark on YARN Executor整体内存理解\_spark executor内存使用情况如何查看-CSDN博客](https://blog.csdn.net/xiaoluobutou/article/details/129416657)
5. [Java魔法类：Unsafe应用解析 - 美团技术团队](https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html)
6. [On-Heap and Off-Heap Memory](https://docs.oracle.com/en/java/javase/23/core/heap-and-heap-memory.html)
7. [Tuning - Spark 3.5.2 Documentation](https://spark.apache.org/docs/latest/tuning.html#memory-tuning)
8. [Configuration - Spark 3.5.2 Documentation](https://spark.apache.org/docs/latest/configuration.html)
9. [Spark设置executor-memory后，executor显示的内存不符问题 - aminor - 博客园](https://www.cnblogs.com/aminor/p/18152647)