# Hive 配置基础教程

## Server 配置

`hive.metastore.uris`
- 设置 Hive Metastore 的 uri 地址
- [AdminManual Metastore Administration - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/AdminManual+Metastore+Administration)

`hive.server2.thrift.bind.host`
- Port number of HiveServer 2 Thrift interface. Can be overridden by setting `$HIVE_SERVER2_THRIFT_PORT`.
- Default Value: `localhost`
- Added In: Hive 0.11.0 with [HIVE-2935](https://issues.apache.org/jira/browse/HIVE-2935)

`hive.server2.thrift.port`
- Bind host on which to run the HiveServer 2 Thrift interface. Can be overridden by setting `$HIVE_SERVER2_THRIFT_BIND_HOST`.
- 此端口，同时也是 HiveServer 2 的 JDBC 端口，支持 JDBC 协议
- Default Value: `10000`
- Added In: Hive 0.11.0 with [HIVE-2935](https://issues.apache.org/jira/browse/HIVE-2935)

## Client 配置

`hive.execution.engine`:
- 设置 Hive 执行引擎
- Chooses execution engine. Options are: `mr` (Map Reduce, default), `tez` (Tez execution, for Hadoop 2 only), or `spark` (Spark execution, for Hive 1.1.0 onward).
- While mr remains the default engine for historical reasons, it is itself a historical engine and is deprecated in the Hive 2 line (HIVE-12300). It may be removed without further warning.

`hive.cli.print.header`
- 设置查询时是否打印列名
- Whether to print the names of the columns in query output.

`hive.compute.query.using.stats`
- 设置进行部分查询时是否直接使用表的统计信息返回结果
- When set to true Hive will answer a few queries like min, max, and count (1) purely using statistics stored in the metastore. For basic statistics collection, set the configuration property `hive.stats.autogather` to true. For more advanced statistics collection, run ANALYZE TABLE queries.
- [Hive中使用count(1)或count()统计行数时结果为0的原因\_hive统计表行数-CSDN博客](https://blog.csdn.net/TomAndersen/article/details/106560747)

`hive.exec.max.dynamic.partitions.pernode`
- 每个 Mapper/Reducer Task 能够动态创建的 Partition 数量
- Maximum number of [dynamic partitions](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DML#LanguageManualDML-DynamicPartitionInserts) allowed to be created in each mapper/reducer node.
- Default Value: `100`
- Added In: Hive 0.6.0

`hive.exec.max.dynamic.partitions`
- Maximum number of [dynamic partitions](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DML#LanguageManualDML-DynamicPartitionInserts) allowed to be created in total.
- Default Value: `1000`
- Added In: Hive 0.6.0

`hive.optimize.cte.materialize.threshold`
- 如果 CTE 的引用次数超出了此参数值，则会将对应的 CTE 进行物化/持久化，以提升 HiveSQL 执行性能。
- If the number of references to a CTE clause exceeds this threshold, Hive will materialize it before executing the main query block, -1 will disable this feature
- Default Value: `-1`
- [分享一个 HIVE SQL 性能优化点-使用公共表表达式 CTE 替换临时表hive 作业的性能优化是一个永恒的话题，其 - 掘金](https://juejin.cn/post/7195024141014499365)
- [Hive with语句你所不知道的秘密-CSDN博客](https://blog.csdn.net/godlovedaniel/article/details/115480115)

`hive.support.quoted.identifiers`
- Hive SQL 的 SELECT 子句可以通过此参数来控制是否支持正则表达式，进而支持查询动态数量的列（`set hive.support.quoted.identifiers=None`）
- Default Value: `column`
- Demo: ```set hive.support.quoted.identifiers=None; select `(user_id)?+.+` from user_action_table;```
- [\[HIVE-6013\] Supporting Quoted Identifiers in Column Names - ASF JIRA]( https://issues.apache.org/jira/browse/HIVE-6013 )
## 参考链接

1. [Configuration Properties - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/hive/configuration+properties)