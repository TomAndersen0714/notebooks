# Spark Application 配置基础教程


## Overview


[Cluster Mode Overview - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/cluster-overview.html)
[Configuration - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/configuration.html)
## Spark Configuration

[Configuration - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/configuration.html)

### Spark Application 常用配置


Spark Configuration:

```
# RDD 生成时默认的 partition 数量
spark.default.parallelism
```

## Spark On YARN Configuration 

[Running Spark on YARN - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/running-on-yarn.html#spark-properties)


### Spark On YARN 常用配置

Spark Resources Configuration:

```
spark.executor.instances
spark.executor.cores
```


## Spark SQL Configuration


[Configuration - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/configuration.html#spark-sql)

### Spark SQL 常用配置

`spark.sql.shuffle.partitions` : 
1. The default number of partitions to use when shuffling data for joins or aggregations. Spark SQL
2. Default: 200