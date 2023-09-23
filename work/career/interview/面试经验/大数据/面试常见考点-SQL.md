# 面试常见考点 SQL


## 相关概念

开窗函数 RANK、DENSE_RANK、ROW_NUMBER、PERCENT_RANK 四者的区别？

LateralView

Window define

## MySQL SQL


### 查询性能优化方法

- 有一个 sql 突然执行很慢可能有什么原因？
从宏观上来说，一般情况下，可以把一个 SQL 查询看做是一个由多个 subtask 组成的一个 task。如果 SQL 执行速度较慢，必然是任务总量太大，即要么是 subtask 数量太多，要么是某些 subtask 的执行时间太长。估优化方向也是从这两个方面着手。


[MySQL-SQL查询性能优化教程](work/component/Back-End/MySQL/solution/MySQL-SQL查询性能优化教程.md)

### 场景题


## Hive SQL


### 查询性能调优方法

大表 JOIN 大表，某个 Key 出现数据倾斜该怎么办？如果 Key 未知怎么办？

有一个 sql 突然执行很慢可能有什么原因？

[Apache-HiveSQL查询性能优化教程](work/component/Big-Data/Apache-Hive/Apache-Hive-SQL/Apache-HiveSQL查询性能优化教程.md)


### 场景题

最大在线人数

留存计算

转化计算

连续登录

留存

相互关注

同时在线

## Spark SQL


### 性能调优方法

[Spark-SQL查询性能优化教程](work/component/Big-Data/Apache-Spark/Spark-SQL查询性能优化教程.md)


## ClickHouse SQL

[ClickHouse-SQL查询性能优化教程](work/component/Big-Data/ClickHouse/ClickHouse-SQL查询性能优化教程.md)

[ClickHouse慢查询SQL排查方案](work/component/Big-Data/ClickHouse/operation/ClickHouse慢查询SQL排查方案.md)


## 参考链接
1. [飞书文档-语兴-踏踏实实练 SQL](https://oxtwry26ao.feishu.cn/mindnotes/bmncnCxiGnEedT4I8hTHMAwGXtg#mindmap)
