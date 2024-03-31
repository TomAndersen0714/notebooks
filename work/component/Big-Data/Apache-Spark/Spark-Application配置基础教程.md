# Spark Application 配置基础教程


## Overview

[Configuration - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/configuration.html)
[Cluster Mode Overview - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/cluster-overview.html)

## Spark Configuration On YARN

[Running Spark on YARN - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/running-on-yarn.html#spark-properties)

## Spark Configuration

[Configuration - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/configuration.html)

## Spark Application 常用配置参数


Spark Resources Configuration:

```
spark.executor.instances
spark.executor.cores
```


Spark Configuration:

```
# RDD 生成时默认的 partition 数量
spark.default.parallelism

# Spark SQL shuffle 分区数量
spark.sql.shuffle.partitions
```