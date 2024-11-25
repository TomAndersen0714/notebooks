# ClickHouse SQL 性能优化教程

## 前言

SQL 性能评估维度：
1. 客户端 VS 服务端
2. 服务质量 QoS VS 资源开销
3. 慢查询 SQL VS 高负载查询 SQL

SQL 性能评估指标：
1. 客户端评估指标：查询响应时间
2. 服务端评估指标：CPU、内存、磁盘 IOPS、磁盘吞吐、网络带宽等

为什么 SQL 查询会存在性能问题:
- 从宏观上来说，一般情况下，可以把一个 SQL 查询看做是一个由多个 subtask 组成的一个 task。如果 SQL 执行性能很差，必然是查询的任务总量（$T_n = \sum_{i=1}^{n} task_i$）太大，即要么是 subtask 总数量太多，要么是某些 subtask 的执行时间太长，故 SQL 查询的优化方向也是从这两个方面着手。

[MySQL-SQL查询性能优化教程](work/component/Back-End/MySQL/solution/MySQL-SQL查询性能优化教程.md)

## ClickHouse SQL 慢查询诊断

先制定慢查询标准，即性能指标满足什么样的条件就算慢查询。

检索和排查慢查询，即按照制定的标准筛选出满足条件的慢查询。
[ClickHouse慢查询SQL排查方案](work/component/Big-Data/ClickHouse/operation/ClickHouse慢查询SQL排查方案.md)

使用系统工具，如 Explain、日志等，定位慢查询的性能瓶颈，设计优化方案。

## ClickHouse SQL 慢查询性能优化常用方案

[微信-ClickHouse的秘密基地-ClickHouse SQL 的十项优化技巧](https://mp.weixin.qq.com/s/lOxCyms__qviTGhb9H1-Pw)

GLOBAL IN
GLOBAL JOIN