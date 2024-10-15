# Spark 基础教程

## Cluster Overview

[Cluster Mode Overview - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/cluster-overview.html)

## RDD

弹性分布式数据集（Resilient Distributed Datasets）

宽依赖：一个父 RDD 会对应多个子 RDD，即前后 RDD 的依赖关系是发散的。
窄依赖：一个父 RDD 只会对应一个子 RDD，一个子 RDD 可以对应任意多个父 RDD，即前后 RDD 的依赖关系是收敛的。

## Shuffle

Apache Spark 的 shuffle 描述的是数据从 map side task 输出到 reduce side task 输入的这段过程。

在 RDD 的依赖关系中，如果一个父 RDD 中的分区被不只一个子 RDD 中的分区所依赖，则称父子 RDD 之间存在宽依赖。只要有宽依赖的存在，则必定会有 shuffle 过程。通常，重分区的操作 (repartition、coalesce)、各种 ByKey 算子、Join 相关操作 (cogroup、join 等) 都会触发 shuffle 过程。

Shuffle 过程会将 RDD Partition 中数据，按照指定的规则，分配给下游的 RDD partition。

一次 shuffle ，map side 有和 RDD 的分区数相同数量的 task 执行；reduce side 默认取参数 spark. Default. Parallelism 或 spark. Sql. Shuffle. Partitions 的值作为分区数 (若该参数未配置，则取 map side 的最后一个 RDD 的分区数)，分区数决定 reduce side 的 task 的数量。

参数 `spark.default.parallelism` 只有在处理 RDD 时才起作用；参数 `spark.sql.shuffle.partitions` 是对 DataFrame 起作用。

Spark 中每个 Stage 的每个 map/reduce side task 都会有唯一标识：mapId 和 reduceId 。每个 shuffle 过程也有唯一标识：shuffleId 。

## Context

Application: Job =1: n
Job: stage = 1: n
Stage: task = 1: n

Spark Application 中 Job 的 ID 从小到大顺序，即是 Job 在源码中提交的顺序，当 Job 提交之后，源码会继续向后执行，直到遇见下一个 Job。如果下一个 Job 依赖前一个 Job 生成的 RDD，则 Job 会等待前面的 Job 执行完成，否则会直接提交下一个 Job，同一个 Application 中多个 Job 之间可以并行执行。

**Spark Application 中 Job 划分的依据是 Action 算子，Stage 划分的依据是 Shuffle Transform 算子。**

在 Apache Spark 中，应用程序（Spark Application）是由多个作业（Job）组成的，而每个作业又可以细分为多个阶段（Stage），这些阶段进一步被拆分成任务（Task）并行执行。

1. Job 划分的依据是 Action 算子：当应用程序执行到某个 Action 算子时，比如 collect (), save () 或 count () 等，Spark 会创建一个新的 Job。Action 算子是触发计算并将数据从 Spark 环境移出的操作，它们标志着一个具体的计算需求，因此是 Spark 划分 Job 的边界。
2. Stage 划分的依据是宽依赖（Shuffle Transform）算子：在 Job 内部，Stage 的划分依据是 DAG（有向无环图）中的宽依赖关系。当一个 RDD（弹性分布式数据集）的转换操作导致数据需要跨节点重新分布时，就形成了宽依赖，最常见的宽依赖操作是 shuffle，如 groupByKey ()、reduceByKey () 等。为了优化执行效率，Spark 会在宽依赖处切分 Stage，确保 Stage 内的所有任务可以流水线式执行，减少数据的重排次数。因此，Stage 的划分是为了最小化 shuffle 操作，提高整体执行效率。

## Spark Deploy Mode

`deploy-mode` 选项指定的是 spark driver 的运行位置，其中 Cluster 代表在集群中运行 Spark Driver，Client 则代表在本地运行 Spark Driver。

Cluster

Client

## Spark Cluster Manager

`master` 选项指定的是 Spark 集群的地址，Local 代表 Spark Application 提交的是本地运行的 Spark 进程，其他则代表是提交给 Spark 集群。

Standalone
[Spark Standalone Mode - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/spark-standalone.html)

YARN
[Running Spark on YARN - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/running-on-yarn.html)

Mesos
[Running Spark on Mesos - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/running-on-mesos.html)

K8S
[Running Spark on Kubernetes - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/running-on-kubernetes.html)

## 参考链接

1. [Spark documentation archive: Index of /dist/spark/docs](https://archive.apache.org/dist/spark/docs/)
2. [Apache Spark™ - Unified Engine for large-scale data analytics](https://spark.apache.org)
3. [Overview - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/)
4. [微信-五分钟学大数据-Spark SQL 底层执行流程详解](https://mp.weixin.qq.com/s/CWdBLhgUrLxlsavTFhA0rA)
5. [Github - Spark Shuffle 机制](https://paxinla.github.io/posts/2021/02/spark-shuffle-ji-zhi.html)
6. [微信-五分钟学大数据-Spark 面试八股文（上万字面试必备宝典）](https://mp.weixin.qq.com/s/Lx3kWDs_XjhuyibX8dhFMQ)