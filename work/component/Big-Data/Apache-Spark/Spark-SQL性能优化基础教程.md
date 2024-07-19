# Spark SQL 性能优化基础教程


## 什么样的 SQL 需要性能优化

[什么样的 SQL 需要性能优化](work/component/Back-End/MySQL/solution/MySQL-SQL查询性能优化教程.md#什么样的%20SQL%20需要性能优化)

## 为什么 SQL 查询会存在性能问题

[为什么 SQL 查询会存在性能问题](work/component/Back-End/MySQL/solution/MySQL-SQL查询性能优化教程.md#为什么%20SQL%20查询会存在性能问题)


## Spark SQL 常见性能问题及诊断方法

### Spark SQL 常见性能问题

[4 Common Reasons for FetchFailed Exception in Apache Spark - DZone](https://dzone.com/articles/four-common-reasons-for-fetchfailed-exception-in-a)

- Out of Heap memory on Executors
- Low Memory Overhead on Executors
- Shuffle block greater than 2 GB
- Network TimeOut.

### Spark SQL 性能问题诊断方法

#### 数据倾斜

[CSDN-诸葛子房-Spark 任务优化分析](https://blog.csdn.net/weixin_43291055/article/details/133770448)
[SparkSql 慢任务诊断案例](https://mp.weixin.qq.com/s/3RrpzO5rPthKfyGX8MvnFw)

#### Broadcast Join Timeout

[Spark braodcast join timeout 300 - yuexiuping - 博客园](https://www.cnblogs.com/yuexiuping/p/15043556.html)

## Spark SQL 常用优化思路和方法

优化 Spark SQL 的目标，一般主要是减少 Spark Application 的空间或时间资源的开销（整体或局部）。

### 减少读取数据量

减少对应任务读取数据量

#### 减少读取行

表筛选条件尽量显式前置。

Case 1：Spark SQL 在 Join 时，会自动下推 `join key is not null` 的条件到执行计划最开始的 table scan 阶段，但如果是 left join，则只会下推 right 表的 join key，而不会下推 left 表的 join key，即无法提前过滤 left 表的无效行。因此可以通过将 left 表的 `join key is not null` 条件下推，以提前减少无效行读取。

Case2：Spark SQL 中在 join 后使用 where 语句时，是先进行 join，然后再执行 where 语句筛选过滤行。因此可以通过将 where 语句下推到 left 表和 right 表的子查询中，以提前减少无效行读取。

#### 减少读取列

按需取列，尽量显示列出对应列字段名，避免使用 `select *`。

Case1：。

### 减少重复读取

Spark SQL 中，针对每个

#### Cache/Uncache Table Table

[CACHE TABLE - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-aux-cache-cache-table.html)
[Spark原理之Cache Table的工作原理及实现自动缓存重复表的思考\_spark cache table-CSDN博客](https://blog.csdn.net/u014445499/article/details/138003052)

CACHE TABLE statement caches contents of a table or output of a query with the given storage level. If a query is cached, then a temp view will be created for this query. This reduces scanning of the original files in future queries.

If storageLevel is not explicitly set using OPTIONS clause, the default storageLevel is set to `MEMORY_AND_DISK`.

使用此语法，可以由用户自定义要缓存的结果集，实际上就是一个临时表（Temporary Table）、物化视图（Materialized View），不过数据存储在 Spark 集群内部，由 Application 所分配的 executors 管理。

```sql
-- cache table
CACHE TABLE testCache OPTIONS ('storageLevel' 'DISK_ONLY') SELECT * FROM testData;

-- uncache table
UNCACHE TABLE [ IF EXISTS ] table_identifier
```

#### Broadcast Join


### 增加读取速度

#### 小文件合并


## 参考链接
1. [京东Spark基于Bloom Filter算法的Runtime Filter Join优化机制 - 脉脉](https://maimai.cn/article/detail?fid=1707795020&efid=dSfxdmyhmG6D8hDYUYvB4Q)