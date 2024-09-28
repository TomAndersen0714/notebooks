# HiveSQL 性能优化常用配置

## Map Join

[LanguageManual JoinOptimization - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+JoinOptimization)

Hive Map Side 配置：
`set hive.auto.convert.join=true`
`set hive.mapjoin.smalltable.filesize=25000000`

Hive Map Join 配置在 0.11.1 版本之后就是默认开启的。
[Configuration Properties - Apache Hive - Apache Software Foundation - ConfigurationProperties-hive.auto.convert.join](https://cwiki.apache.org/confluence/display/Hive/Configuration+Properties#ConfigurationProperties-hive.auto.convert.join)

Hive Map Join 小表阈值：
`hive.smalltable.filesize` or `hive.mapjoin.smalltable.filesize`
- The threshold (in bytes) for the input file size of the small tables; if the file size is smaller than this threshold, it will try to convert the common join into map join.
- Default Value: `25000000`
- `hive.smalltable.filesize` : added In: Hive 0.7.0 with HIVE-1642 (replaced by `hive.mapjoin.smalltable.filesize` in Hive 0.8.1)
- `hive.mapjoin.smalltable.filesize` : added In: Hive 0.8.1 with HIVE-2499

## CTE Materialize 物化

Hive CTE 物化阈值配置：
`set hive.optimize.cte.materialize.threshold=1`

此配置设置的值对应着 CTE 引用的次数，如果 SQL 中 CTE 的“引用次数”超过此次数则会对其进行物化，可以通过 Explain 命令，查看前后执行计划，如果其中包含有 `Move Operator`，则说明执行了物化操作。

注意事项：
- 在低版本 Hive 中会与 Map join 特性 `hive.auto.convert.join` 冲突，出现丢失数据的情况，因此使用此优化特性时，建议关闭 `set hive.auto.convert.join=false`。
- [\[HIVE-24606\] Multi-stage materialized CTEs can lose intermediate data - ASF JIRA](https://issues.apache.org/jira/browse/HIVE-24606)

参考链接：
- [Common Table Expression - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/Common+Table+Expression)
- [分享一个 HIVE SQL 性能优化点-使用公共表表达式 CTE 替换临时表-阿里云开发者社区](https://developer.aliyun.com/article/1344897)
- [Hive with语句你所不知道的秘密-CSDN博客](https://blog.csdn.net/godlovedaniel/article/details/115480115)

## 参考链接

1. [Hive配置基础教程](work/component/Big-Data/Apache-Hive/Hive配置基础教程.md)