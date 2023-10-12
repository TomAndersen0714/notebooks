# Spark 基础教程


## Cluster Overview

https://spark.apache.org/docs/3.2.0/cluster-overview.html


## RDD

弹性分布式数据集（Resilient Distributed Datasets）

宽依赖：一个父 RDD 会对应多个子 RDD，即前后 RDD 的依赖关系是发散的。
窄依赖：一个父 RDD 只会对应一个子 RDD，一个子 RDD 可以对应任意多个父 RDD，即前后 RDD 的依赖关系是收敛的。

## Shuffle

Apache Spark 的 shuffle 描述的是数据从 map side task 输出到 reduce side task 输入的这段过程。

在 RDD 的依赖关系中，如果一个父 RDD 中的分区被不只一个子 RDD 中的分区所依赖，则称父子 RDD 之间存在宽依赖。只要有宽依赖的存在，则必定会有 shuffle 过程。通常，重分区的操作 (repartition、coalesce)、各种 ByKey 算子、Join 相关操作 (cogroup、join 等) 都会触发 shuffle 过程。

一次 shuffle ，map side 有和 RDD 的分区数相同数量的 task 执行；reduce side 默认取参数 spark. Default. Parallelism 或 spark. Sql. Shuffle. Partitions 的值作为分区数 (若该参数未配置，则取 map side 的最后一个 RDD 的分区数)，分区数决定 reduce side 的 task 的数量。

参数 spark. Default. Parallelism 只有在处理 RDD 时才起作用；参数 spark. Sql. Shuffle. Partitions 是对 DataFrame 起作用。

Spark 中每个 Stage 的每个 map/reduce side task 都会有唯一标识：mapId 和 reduceId 。每个 shuffle 过程也有唯一标识：shuffleId 。



## 参考链接
1. [Apache Spark](https://spark.apache.org/)
2. [微信-五分钟学大数据-Spark SQL 底层执行流程详解](https://mp.weixin.qq.com/s/CWdBLhgUrLxlsavTFhA0rA)
3. [Github - Spark Shuffle 机制](https://paxinla.github.io/posts/2021/02/spark-shuffle-ji-zhi.html)
4. [微信-五分钟学大数据-Spark 面试八股文（上万字面试必备宝典）](https://mp.weixin.qq.com/s/Lx3kWDs_XjhuyibX8dhFMQ)
5. 