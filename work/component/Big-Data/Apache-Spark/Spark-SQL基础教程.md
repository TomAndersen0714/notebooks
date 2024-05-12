# Spark SQL 基础教程


[Spark SQL and DataFrames - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-programming-guide.html)

## SQL Reference

[SQL Syntax - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax.html)


### DDL
#### TABLE

#### VIEW


Views are based on the result-set of an SQL query. `CREATE VIEW` constructs a virtual table that has no physical data therefore other operations like `ALTER VIEW` and `DROP VIEW` only change metadata.

Spark 中视图是无法存储

#### FUNCTION

[Functions - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-functions.html)

### DQL

[SELECT - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select.html)

#### SORT BY

[SORT BY Clause - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select-sortby.html)

#### DISTRIBUTE BY

[DISTRIBUTE BY Clause - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select-distribute-by.html)

#### CLUSTER BY

[CLUSTER BY Clause - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select-clusterby.html)

#### ORDER BY

[ORDER BY Clause - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select-orderby.html)

#### Hints

[Hints - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select-hints.html)
#### JOIN

[JOIN - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select-join.html)

#### Lateral View

[LATERAL VIEW Clause - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select-lateral-view.html)

`LATERAL VIEW`，创建一个临时 View，并和子查询数据集关联，并生成笛卡尔积。

#### Explain

[EXPLAIN - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-explain.html)

### Auxiliary Statements

#### Cache Table

[CACHE TABLE - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-aux-cache-cache-table.html)
[Spark原理之Cache Table的工作原理及实现自动缓存重复表的思考\_spark cache table-CSDN博客](https://blog.csdn.net/u014445499/article/details/138003052)

CACHE TABLE statement caches contents of a table or output of a query with the given storage level. If a query is cached, then a temp view will be created for this query. This reduces scanning of the original files in future queries.

If storageLevel is not explicitly set using OPTIONS clause, the default storageLevel is set to `MEMORY_AND_DISK`.

使用此语法，可以由用户自定义要缓存的结果集，实际上就是一个临时表（Temporary Table）、物化视图（Materialized View），不过数据存储在 Spark 集群内部，由 Application 所分配的 executors 管理。


## Performance Tuning

[Performance Tuning - Spark SQL - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-performance-tuning.html)


### Adaptive Query Execution

Adaptive Query Execution (AQE) is an optimization technique in Spark SQL that makes use of the runtime statistics to choose the most efficient query execution plan, which is enabled by default since Apache Spark 3.2.0. Spark SQL can turn on and off AQE by spark. Sql. Adaptive. Enabled as an umbrella configuration. As of Spark 3.0, there are three major features in AQE: including coalescing post-shuffle partitions, converting sort-merge join to broadcast join, and skew join optimization.



## Client

[spark-sql基础教程](work/component/Big-Data/Apache-Spark/CLI/spark-sql基础教程.md)

## 参考链接
1. [Spark SQL and DataFrames - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-programming-guide.html)
2. [微信-字节跳动数据平台-观点｜SparkSQL 在企业级数仓建设的优势](https://mp.weixin.qq.com/s/CLr6KfdzKhtfaT89NHv_8g)
