# Spark Application 性能优化基础教程


## 常见性能问题的症状、原因及其解决方案

[MySQL-SQL查询性能优化教程](work/component/Back-End/MySQL/solution/MySQL-SQL查询性能优化教程.md)


### 客户侧的慢查询

任务执行耗时太长，客户侧无法忍受，需要加速整个任务的执行流程。

#### 问题原因

任务量的角度：
原因 1：整个任务的任务量过大
原因 2：


资源开销的角度：
原因 1：客户侧资源实际利用率低。即有资源，但没有使用或者使用不当。
1. 客户侧提交任务中的算法问题
原因 2：服务侧资源总量有限。即没有资源，巧妇难为无米之炊
1. 服务侧资源总量有限，限制了客户侧的性能

#### 解决方法


### 服务侧的高负载查询

服务侧

#### 问题原因

任务量的角度


资源开销的角度

#### 解决方法


## Spark 任务常见性能问题、诊断及解决方法

### 常见性能问题

[4 Common Reasons for FetchFailed Exception in Apache Spark - DZone](https://dzone.com/articles/four-common-reasons-for-fetchfailed-exception-in-a)
- Out of Heap memory on Executors
- Low Memory Overhead on Executors
- Shuffle block greater than 2 GB
- Network TimeOut.

### 常用诊断方法

[CSDN-诸葛子房-Spark 任务优化分析](https://blog.csdn.net/weixin_43291055/article/details/133770448)

[SparkSql 慢任务诊断案例](https://mp.weixin.qq.com/s/3RrpzO5rPthKfyGX8MvnFw)


### 慢查询

极个别任务量太大
1. 数据倾斜

[Spark性能优化之数据倾斜问题解决方法](work/component/Big-Data/Apache-Spark/solution/Spark性能优化之数据倾斜问题解决方法.md)


任务量太大
1. 小文件数量太多


### 高负载查询

OOM：
1. OOM 常见报错日志，如 `Container Killed by Yarn For Exceeding Memory`

解决方案：
1. Spark SQL 先主动 shuffle，然后 aggregation，会减少 partial aggregation 的 memory 开销
2. 增加 `spark.sql.shuffle.partitions` 数值


### Adaptive Query Execution

[Performance Tuning - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-performance-tuning.html#adaptive-query-execution)

## Spark SQL 性能优化

[Spark SQL Guide - Performance Tuning - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/sql-performance-tuning.html)

[Spark-SQL性能优化基础教程](work/component/Big-Data/Apache-Spark/Spark-SQL性能优化基础教程.md)

## 参考链接
1. [Performance Tuning - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/sql-performance-tuning.html)
2. [Tuning - Spark 3.5.0 Documentation](https://spark.apache.org/docs/latest/tuning.html#tuning-spark)
3. [Spark性能优化指南——基础篇 - 美团技术团队](https://tech.meituan.com/2016/04/29/spark-tuning-basic.html)
4. [Spark性能优化指南——高级篇 - 美团技术团队](https://tech.meituan.com/2016/05/12/spark-tuning-pro.html)
5. [Spark数据倾斜问题分析和解决-CSDN博客](https://blog.csdn.net/weixin_43291055/article/details/133770448)
6. [Spark性能优化指南-高级篇](https://mp.weixin.qq.com/s/gqBlYim7JYjAXW3CYssLBA)