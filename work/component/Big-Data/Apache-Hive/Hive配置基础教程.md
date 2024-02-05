# Hive 配置基础教程


设置 Hive 执行引擎：
`hive.execution.engine`

Chooses execution engine. Options are: `mr` (Map Reduce, default), `tez` (Tez execution, for Hadoop 2 only), or `spark` (Spark execution, for Hive 1.1.0 onward).

While mr remains the default engine for historical reasons, it is itself a historical engine and is deprecated in the Hive 2 line (HIVE-12300). It may be removed without further warning.


设置查询时是否打印列名：
`hive.cli.print.header`
Whether to print the names of the columns in query output.

设置进行部分查询时是否直接使用表的统计信息返回结果：
`hive.compute.query.using.stats`

When set to true Hive will answer a few queries like min, max, and count (1) purely using statistics stored in the metastore. For basic statistics collection, set the configuration property `hive.stats.autogather` to true. For more advanced statistics collection, run ANALYZE TABLE queries.

[Hive中使用count(1)或count()统计行数时结果为0的原因\_hive统计表行数-CSDN博客](https://blog.csdn.net/TomAndersen/article/details/106560747)

## 参考链接
1. [Configuration Properties - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/hive/configuration+properties)