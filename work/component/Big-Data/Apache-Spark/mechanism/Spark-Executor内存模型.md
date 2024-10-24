# Spark Executor 内存模型

## 前言

本文主要用于介绍 Spark Executor on YARN 的内存模型。不同版本的 Spark 中，对应的 executor 内存模型也不同，本文中介绍的内存模型对应的 Spark 版本为 v2.4.3 。

## Spark Executor 内存组成

Spark Executor On YARN 的 Memory，主要由 executorMemory、executorMemoryOverhead、pysparkWorkerMemory 三部分组成。

![](resources/images/Pasted%20image%2020240822112520.png)

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

堆内内存 ExecutorMemory:
1. EXECUTOR_MEMORY
	1. `spark.executor.memory`, since 0.7.0

堆外内存 ExecutorMemoryOverhead
1. EXECUTOR_MEMORY_OVERHEAD
	1. `spark.executor.memoryOverhead`, since 2.3.0
2. MEMORY_OVERHEAD_FACTOR * ExecutorMemory
	1. MEMORY_OVERHEAD_FACTOR
		1. `spark.executor.memoryOverheadFactor`, since 3.3.0
		2. Default 0.10
3. MEMORY_OVERHEAD_MIN
	1. Default 384

PysparkWorkerMemory
1. PYSPARK_EXECUTOR_MEMORY
	1. `spark.executor.pyspark.memory`, since 2.4.0

## Spark Executor 内存管理

在 Spark 中不论是堆内 on-heap，或者堆外 off-heap，其内存都主要分为两大类：执行内存（Execution Memory）和存储内存（Storage Memory）。Execution 内存用于计算处理，如 shuffles、joins、sorts 和 aggregations，而 Storage 内存则用于 Cache 缓存和广播 Broadcast 数据集。

Execution 内存和 Storage 内存共享一个统一的区域（M）。当没有使用 Execution 内存时，Storage 内存可以占用所有可用的内存，反之亦然。

在必要时，Execution 内存可以强迫 Storage 区域进行内存回收，直到 Storage 内存的使用量降到某个阈值（R）以下，即 R 是 M 中的用于保存 Storage 的最小内存空间。

虽然有两个相关的配置参数，但一般用户通常不需要调整它们，因为默认值已适用于大多数工作负载：
- `spark.memory.fraction` 表示 M 占用对内的比例，计算公式为 (JVM 堆空间 - 300MiB) 的百分比（默认值为 0.6）。其余的空间（40%）留给用户数据结构、Spark 的内部元数据以及作为安全储备，以防出现稀疏或异常大的记录导致的 OOM（内存溢出）错误。
- `spark.memory.storageFraction` 表示 R 占 M 的比例（默认值为 0.5）。R 是 M 内部的一块存储区域，其内的缓存数据块不会被执行操作驱逐。

![](resources/images/Pasted%20image%2020241024193346.png)

## 参考链接

1. [Spark内存管理之堆内/堆外内存原理详解\_你知道启用off-heap之后,spark有哪些计算环节可以利用到堆外内存-CSDN博客](https://blog.csdn.net/pre_tender/article/details/101517789)
2. [Spark on YARN Executor整体内存理解\_spark executor内存使用情况如何查看-CSDN博客](https://blog.csdn.net/xiaoluobutou/article/details/129416657)
3. [Tuning - Spark 3.5.2 Documentation](https://spark.apache.org/docs/latest/tuning.html#memory-tuning)
4. [Configuration - Spark 3.5.2 Documentation](https://spark.apache.org/docs/latest/configuration.html)
5. [Spark设置executor-memory后，executor显示的内存不符问题 - aminor - 博客园](https://www.cnblogs.com/aminor/p/18152647)