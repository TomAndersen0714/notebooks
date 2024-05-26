# HiveSQL 常用优化配置


## MapSide Join

Hive Map Side 配置：
`set hive.auto.convert.join=true`


Hive Map Side Join 配置在 0.11.1 版本之后就是默认开启的。
`The default value for hive.auto.convert.join was false in Hive 0.10.0.  Hive 0.11.0 changed the default to true (HIVE-3297). Note that hive-default.xml.template incorrectly gives the default as false in Hive 0.11.0 through 0.13.1.`

[LanguageManual JoinOptimization - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+JoinOptimization)

## CTE 物化

Hive CTE 物化阈值配置：
`set hive.optimize.cte.materialize.threshold=1`

此配置设置的值对应着 CTE 引用的次数，如果 SQL 中 CTE 的“引用次数”超过此次数则会对其进行物化，可以通过 Explain 命令，查看前后执行计划，如果其中包含有 `Move Operator`，则说明执行了物化操作。

在低版本 Hive 中会与 `hive.auto.convert.join` 冲突，出现丢失数据的情况，因此使用此优化特性时，建议关闭 `set hive.auto.convert.join=false`。


坑点：
- [\[HIVE-24606\] Multi-stage materialized CTEs can lose intermediate data - ASF JIRA](https://issues.apache.org/jira/browse/HIVE-24606)

参考链接：
- [Common Table Expression - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/Common+Table+Expression)
- [分享一个 HIVE SQL 性能优化点-使用公共表表达式 CTE 替换临时表-阿里云开发者社区](https://developer.aliyun.com/article/1344897)
- [Hive with语句你所不知道的秘密-CSDN博客](https://blog.csdn.net/godlovedaniel/article/details/115480115)
## 参考链接
