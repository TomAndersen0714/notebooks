# Spark SQL 查询性能优化教程


## 什么样的 SQL 需要性能优化

[什么样的 SQL 需要性能优化](work/component/Back-End/MySQL/solution/MySQL-SQL查询性能优化教程.md#什么样的%20SQL%20需要性能优化)

## 为什么 SQL 查询会存在性能问题

[为什么 SQL 查询会存在性能问题](work/component/Back-End/MySQL/solution/MySQL-SQL查询性能优化教程.md#为什么%20SQL%20查询会存在性能问题)

## Spark SQL 常用优化方法

### Cache/Uncache Table Table

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


## Spark SQL 谓词下推


Spark SQL 在 JOIN 时，会自动进行谓词下推，对于 JOIN Key，则会自动下推 Join key is not null 的条件在 table scan 的阶段，但如果是 left join，则只会下推 right 表，而不会下推 left 表。

## 参考链接
1. [京东Spark基于Bloom Filter算法的Runtime Filter Join优化机制 - 脉脉](https://maimai.cn/article/detail?fid=1707795020&efid=dSfxdmyhmG6D8hDYUYvB4Q)