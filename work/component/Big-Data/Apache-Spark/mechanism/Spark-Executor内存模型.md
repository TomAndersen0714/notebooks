# Spark Executor 内存模型


## 前言

本文主要用于介绍 Spark Executor on YARN 的内存模型。

不同版本的 Spark 中，对应的 executor 内存模型也不同，本文中介绍的内存模型对应的 Spark 版本为 v2.4.3 。

## Spark Executor 内存组成


`org.apache.spark.deploy.yarn.Client#verifyClusterResources` 源码摘录

```scala
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

    // We could add checks to make sure the entire cluster has enough resources but that involves
    // getting all the node reports and computing ourselves.
  }
```


ExecutorMemory:
1. EXECUTOR_MEMORY
	1. `spark.executor.memory`, since 0.7.0

ExecutorMemoryOverhead
1. EXECUTOR_MEMORY_OVERHEAD
	1. `spark.executor.memoryOverhead`, since 2.3.0
2. MEMORY_OVERHEAD_FACTOR * ExecutorMemory
	1. `spark.executor.memoryOverheadFactor`, since 3.3.0
	2. Default 0.10
3. MEMORY_OVERHEAD_MIN
	1. Default 384

PysparkWorkerMemory
1. PYSPARK_EXECUTOR_MEMORY
	1. `spark.executor.pyspark.memory`, since 2.4.0

![](resources/images/Pasted%20image%2020240822112520.png)

## Spark Executor 内存管理

在 Spark 中，内存使用主要分为两大类：执行内存和存储内存。执行内存用于计算处理，如 shuffles（洗牌）、joins（连接）、sorts（排序）和 aggregations（聚合）；而存储内存则用于缓存和在集群中传播内部数据。在 Spark 中，execution 执行内存和 storage 存储内存共享一个统一的区域（M）。当没有使用执行内存时，存储内存可以占用所有可用的内存，反之亦然。在必要时，执行内存可以驱逐存储内存，但只到存储内存的使用量降到某个阈值（R）以下。换句话说，R 描述了 M 中的一个子区域，在该区域内，缓存的数据块不会被驱逐。由于实现机制的复杂性，存储内存不能驱逐执行内存。

此设计确保了几项理想的特性。首先，不利用缓存的应用可以使用全部内存空间进行执行操作，避免不必要的磁盘溢出。其次，利用缓存的应用可以预留一定的存储空间（R），确保其数据块不会被驱逐。最后，这种方法为多种工作负载提供了合理的即插即用性能，而无需用户深入了解内部内存划分的细节。

尽管有两个相关的配置参数，但典型用户通常不需要调整它们，因为默认值已适用于大多数工作负载：

- `spark.memory.fraction` 表示 M 占用的比例，计算公式为 (JVM 堆空间 - 300MiB) 的百分比（默认值为 0.6）。其余的空间（40%）留给用户数据结构、Spark 的内部元数据以及作为安全储备，以防出现稀疏或异常大的记录导致的 OOM（内存溢出）错误。
- `spark.memory.storageFraction` 表示 R 占 M 的比例（默认值为 0.5）。R 是 M 内部的一块存储区域，其内的缓存数据块不会被执行操作驱逐。

`spark.memory.fraction` 的值应该设置在能够在 JVM 的老年代或“终身代”中舒适适应这部分堆空间。

## 参考链接

1. [Spark内存管理之堆内/堆外内存原理详解\_你知道启用off-heap之后,spark有哪些计算环节可以利用到堆外内存-CSDN博客](https://blog.csdn.net/pre_tender/article/details/101517789)
2. [Spark on YARN Executor整体内存理解\_spark executor内存使用情况如何查看-CSDN博客](https://blog.csdn.net/xiaoluobutou/article/details/129416657)
3. [Tuning - Spark 3.5.2 Documentation](https://spark.apache.org/docs/latest/tuning.html#memory-tuning)
4. [Configuration - Spark 3.5.2 Documentation](https://spark.apache.org/docs/latest/configuration.html)
5. [Spark设置executor-memory后，executor显示的内存不符问题 - aminor - 博客园](https://www.cnblogs.com/aminor/p/18152647)