# Spark Application 配置基础教程


## Overview

[Cluster Mode Overview - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/cluster-overview.html)
[Configuration - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/configuration.html)

## Spark On YARN Configuration

[Running Spark on YARN - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/running-on-yarn.html#spark-properties)


### Spark On YARN 常用配置

Spark Resources Configuration:

| Property Name            | Default | Meaning | Since Version |
| ------------------------ | ------- | ------- | ------------- |
| spark.executor.instances |         |         | 1.1.0         |
| spark.executor.cores     |         |         | 1.1.0         |

## Spark Application Configuration

[Configuration - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/configuration.html)

### Spark Application 常用配置

| Property Name             | Default                                                                                                                                                                                                                                                                                                                                                                                         | Meaning                                                                                                                        | Since Version |
| ------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------ | ------------- |
| spark.default.parallelism | For distributed shuffle operations like reduceByKey and join, the largest number of partitions in a parent RDD. <br><br>For operations like parallelize with no parent RDDs, it depends on the cluster manager: <br><br>- Local mode: number of cores on the local machine <br>- Mesos fine grained mode: 8 <br>- Others: total number of cores on all executor nodes or 2, whichever is larger | Default number of partitions in RDDs returned by transformations like join, reduceByKey, and parallelize when not set by user. | 0.5.0         |

## Spark SQL Configuration

[Configuration - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/configuration.html#spark-sql)

### Spark SQL 常用配置

| Property Name                | Default | Meaning                                                                                                                                                                                                               | Since Version |
| ---------------------------- | ------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------- |
| spark.sql.shuffle.partitions | 200     | The default number of partitions to use when shuffling data for joins or aggregations. Note: For structured streaming, this configuration cannot be changed between query restarts from the same checkpoint location. | 1.1.0 <br>     |