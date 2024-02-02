# Spark SQL 基础教程


[Spark SQL and DataFrames - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-programming-guide.html)

## SQL Reference

[SQL Syntax - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax.html)


### DDL
#### TABLE

#### VIEW

#### FUNCTION


### DQL

[SELECT - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select.html)

#### Function

[Functions - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-functions.html)

#### Lateral View

[LATERAL VIEW Clause - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select-lateral-view.html)
## Performance Tuning

[Performance Tuning - Spark SQL - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-performance-tuning.html)


### Adaptive Query Execution

Adaptive Query Execution (AQE) is an optimization technique in Spark SQL that makes use of the runtime statistics to choose the most efficient query execution plan, which is enabled by default since Apache Spark 3.2.0. Spark SQL can turn on and off AQE by spark. Sql. Adaptive. Enabled as an umbrella configuration. As of Spark 3.0, there are three major features in AQE: including coalescing post-shuffle partitions, converting sort-merge join to broadcast join, and skew join optimization.



## Client

[spark-sql基础教程](work/component/Big-Data/Apache-Spark/CLI/spark-sql基础教程.md)

## 参考链接
1. [Spark SQL and DataFrames - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-programming-guide.html)
2. [微信-字节跳动数据平台-观点｜SparkSQL 在企业级数仓建设的优势](https://mp.weixin.qq.com/s/CLr6KfdzKhtfaT89NHv_8g)
