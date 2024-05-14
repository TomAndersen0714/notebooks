# HiveSQL 常用优化参数


## CTE

Hive CTE 物化阈值配置：
`set hive.optimize.cte.materialize.threshold`
可以通过 Explain 命令，查看执行计划，如果其中包含有 `Move Operator`，则说明执行了物化操作。

[Hive with语句你所不知道的秘密-CSDN博客](https://blog.csdn.net/godlovedaniel/article/details/115480115)


## 参考链接
