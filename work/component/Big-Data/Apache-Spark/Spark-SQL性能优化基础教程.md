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

### Spark SQL 常见性能问题诊断方法

#### OOM 内存不足


#### Data Skew 数据倾斜

[CSDN-诸葛子房-Spark 任务优化分析](https://blog.csdn.net/weixin_43291055/article/details/133770448)
[SparkSql 慢任务诊断案例](https://mp.weixin.qq.com/s/3RrpzO5rPthKfyGX8MvnFw)


#### Time out 超时

等待资源启动超时
执行过程中超时
[Spark braodcast join timeout 300 - yuexiuping - 博客园](https://www.cnblogs.com/yuexiuping/p/15043556.html)

## Spark SQL 常用优化思路和方法

优化 Spark SQL 的目标，一般主要是减少 Spark Application 的空间或时间资源的开销（整体或局部）。

### 减少读取数据量

减少对应 Stage 的读取数据量。

#### 减少读取行

Where 语句下推、前置。

Case 1：Spark SQL 在 Join 时，会自动下推 `Join key is not null` 的条件到执行计划最开始的 table scan 阶段，但如果是 left join，则只会下推 right 表的 join key，而不会下推 left 表的 join key，即无法提前过滤 left 表的无效行。因此可以通过手动将 left 表的 `Join key is not null` 条件下推，以提前减少无效行读取。

Case2：Spark SQL 中在 Join 后使用 Where 语句时，是先进行 Join，然后再执行 Where 语句筛选和过滤行。因此可以通过手动将 Where 语句下推到 left 表和 right 表的子查询中，以提前减少无效行的读取。

Case3：Spark SQL 使用 Order By+Limit 语句查询 TopN 时，优化器会对 partition 中的数据进行局部排序 local sort 并局部筛选 local limit，减少后续读取数据量，然后再去执行全局排序 global limit。


```sql
scala> val myDF = Seq(83, 90, 40, 94, 12, 70, 56, 70, 28, 91).toDF("number")
scala> myDF.orderBy("number").limit(3).explain(true)

== Parsed Logical Plan ==
GlobalLimit 3
+- LocalLimit 3
   +- Sort [number#3 ASC NULLS FIRST], true
      +- Project [value#1 AS number#3]
         +- LocalRelation [value#1]

== Analyzed Logical Plan ==
number: int
GlobalLimit 3
+- LocalLimit 3
   +- Sort [number#3 ASC NULLS FIRST], true
      +- Project [value#1 AS number#3]
         +- LocalRelation [value#1]

== Optimized Logical Plan ==
GlobalLimit 3
+- LocalLimit 3
   +- Sort [number#3 ASC NULLS FIRST], true
      +- LocalRelation [number#3]

== Physical Plan ==
TakeOrderedAndProject(limit=3, orderBy=[number#3 ASC NULLS FIRST], output=[number#3])
+- LocalTableScan [number#3]
```

#### 减少读取列

SQL Select 语句中按需取列，显式列出对应列字段名，尽量避免使用 `SELECT *`。

Case1：当表中

### 减少重复读取

Spark SQL 中，默认情况下，针对同一个 CTE、View 或 Table 的多次重复查询，会重复触发数据读取和 RDD 的生成。

#### Cache Table

[CACHE TABLE - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-aux-cache-cache-table.html)
[Spark原理之Cache Table的工作原理及实现自动缓存重复表的思考\_spark cache table-CSDN博客](https://blog.csdn.net/u014445499/article/details/138003052)

>CACHE TABLE statement caches contents of a table or output of a query with the given storage level. If a query is cached, then a temp view will be created for this query. This reduces scanning of the original files in future queries.
>
>If storageLevel is not explicitly set using OPTIONS clause, the default storageLevel is set to `MEMORY_AND_DISK`.

Spark cache table ，类似临时表（Temporary Table）、物化视图（Materialized View），其数据 cache 在 Spark executor 中，支持快速重复读。

```sql
-- cache table
CACHE [ LAZY ] TABLE table_identifier [ OPTIONS ( 'storageLevel' [ = ] value ) ]
[ [ AS ] query ]
-- examples:
CACHE TABLE t2;
CACHE TABLE t1 OPTIONS ('storageLevel' 'MEMORY_AND_DISK')
AS SELECT * FROM testData;

-- uncache table
UNCACHE TABLE [ IF EXISTS ] table_identifier;

-- clear cache
CLEAR CACHE;
```

#### Broadcast Join

通过配置并触发 Broadcast Join 算法，避免 Sort-merge Join 时的 Shuffle 阶段，减少数据 IO，提升性能。

Broadcast Join 相关配置，自动触发：

| Property Name                        | Default          | Meaning                                                                                                                                                                                                                                                                                                                                          | Since Version |
| ------------------------------------ | ---------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------- |
| spark.sql.autoBroadcastJoinThreshold | 10485760 (10 MB) | Configures the maximum size in bytes for a table that will be broadcast to all worker nodes when performing a join. By setting this value to -1, broadcasting can be disabled. Note that currently statistics are only supported for Hive Metastore tables where the command ANALYZE TABLE `<tableName>` COMPUTE STATISTICS noscan has been run. | 1.1.0         |
| spark.sql.broadcastTimeout           | 300              | Timeout in seconds for the broadcast wait time in broadcast joins                                                                                                                                                                                                                                                                                | 1.3.0         |

Broadcast Join Hint，手动触发：

[Performance Tuning - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/sql-performance-tuning.html#join-strategy-hints-for-sql-queries)
[Hints - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select-hints.html#join-hints)

>Suggests that Spark use broadcast join. The join side with the hint will be broadcast regardless of `autoBroadcastJoinThreshold`. If both sides of the join have the broadcast hints, the one with the smaller size (based on stats) will be broadcast. 
>
>The aliases for BROADCAST are BROADCASTJOIN and MAPJOIN.

```sql
-- Join Hints for broadcast join
SELECT /*+ BROADCAST(t1) */ * FROM t1 INNER JOIN t2 ON t1.key = t2.key;
SELECT /*+ BROADCASTJOIN (t1) */ * FROM t1 left JOIN t2 ON t1.key = t2.key;
SELECT /*+ MAPJOIN(t2) */ * FROM t1 right JOIN t2 ON t1.key = t2.key;
```

### 增加读取速度

#### 小文件合并

##### Distribute by

[DISTRIBUTE BY Clause - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select-distribute-by.html)

```sql
INSERT INTO t1
SELECT * FROM t2
DISTEIBUTE BY ceiling(rand()*${files_num})
```

##### Partition Hints

[Partitioning Hints - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-qry-select-hints.html#partitioning-hints)

```sql
SELECT /*+ COALESCE(3) */ * FROM t;
SELECT /*+ REPARTITION(3) */ * FROM t;
SELECT /*+ REPARTITION(c) */ * FROM t;
SELECT /*+ REPARTITION(3, c) */ * FROM t;
SELECT /*+ REPARTITION_BY_RANGE(c) */ * FROM t;
SELECT /*+ REPARTITION_BY_RANGE(3, c) */ * FROM t;
SELECT /*+ REBALANCE */ * FROM t;
SELECT /*+ REBALANCE(3) */ * FROM t;
SELECT /*+ REBALANCE(c) */ * FROM t;
SELECT /*+ REBALANCE(3, c) */ * FROM t;

-- multiple partitioning hints
EXPLAIN EXTENDED SELECT /*+ REPARTITION(100), COALESCE(500), REPARTITION_BY_RANGE(3, c) */ * FROM t;

== Parsed Logical Plan ==
'UnresolvedHint REPARTITION, [100]
+- 'UnresolvedHint COALESCE, [500]
   +- 'UnresolvedHint REPARTITION_BY_RANGE, [3, 'c]
      +- 'Project [*]
         +- 'UnresolvedRelation [t]

== Analyzed Logical Plan ==
name: string, c: int
Repartition 100, true
+- Repartition 500, false
   +- RepartitionByExpression [c#30 ASC NULLS FIRST], 3
      +- Project [name#29, c#30]
         +- SubqueryAlias spark_catalog.default.t
            +- Relation[name#29,c#30] parquet

== Optimized Logical Plan ==
Repartition 100, true
+- Relation[name#29,c#30] parquet

== Physical Plan ==
Exchange RoundRobinPartitioning(100), false, [id=#121]
+- *(1) ColumnarToRow
   +- FileScan parquet default.t[name#29,c#30] Batched: true, DataFilters: [], Format: Parquet,
      Location: CatalogFileIndex[file:/spark/spark-warehouse/t], PartitionFilters: [],
      PushedFilters: [], ReadSchema: struct<name:string>
```

### 数据均匀分布


#### 增加 partition 数量

增加 Spark SQL Shuffle 阶段生成的 partition 数量

| Property Name                | Default | Meaning                                                                                                                                                                                                               | Since Version |
| ---------------------------- | ------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------- |
| spark.sql.shuffle.partitions | 200     | The default number of partitions to use when shuffling data for joins or aggregations. Note: For structured streaming, this configuration cannot be changed between query restarts from the same checkpoint location. | 1.1.0 <br>    |

## 参考链接

1. [京东Spark基于Bloom Filter算法的Runtime Filter Join优化机制 - 脉脉](https://maimai.cn/article/detail?fid=1707795020&efid=dSfxdmyhmG6D8hDYUYvB4Q)