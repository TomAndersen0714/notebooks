# Spark SQL 性能优化基础教程


## 为什么要进行 Spark SQL 性能优化

提升计算资源利用率
提升任务的执行效率，减少任务的执行时间
## Spark SQL 常见性能问题及诊断方法

#### 常见问题

1. OOM 内存不足
2. Data Skew 数据倾斜
3. Timeout 执行超时
#### 查看日志

OOM 报错常见日志：
- `org.apache.spark.shuffle.FetchFailedException: Failure while fetching StreamChunkId`
- `org.apache.spark.shuffle.FetchFailedException: Failed to connect to xxx`
- `org.apache.spark.shuffle.MetadataFetchFailedException: Missing an output location for shuffle xxx`
- `ERROR cluster.YarnShceduler: Lost executor xx on xx` 
- `ExecutorLostFailure (executor xxx exited caused by one of the running tasks) Reason: Container killed by YARN for exceeding memory limits`

Broadcast Join Timeout 报错常见日志：
- `org.apache.spark.SparkException: Could not execute broadcast in 300 secs`
- `java.util.concurrent.TimeoutException: Futures timed out after [300 seconds]`

RPC Timeout 报错常见日志：
- `org.apache.spark.rpc.RpcTimeoutException: Cannot receive any reply in 120 seconds`
#### 查看 Spark UI

Spark UI | Stages | Details for Stage | Tasks

**OOM** ：Task 失败，并抛出相应的错误日志
![](resources/images/Pasted%20image%2020240722104343.png)

**数据倾斜**：某个 Task 的 Duration 和 Records 会远大于其他 Task
![](resources/images/Pasted%20image%2020240722101654.png)

## Spark SQL 常用优化思路和方法

### 减少读取数据量

通过减少 Task 读取的数据量，提升整体性能，以避免出现 OOM 和超时问题。
#### 减少读取行

##### Where 过滤前置

Case 1：Spark SQL 在 Join 时，会自动下推 `Join key is not null` 的条件到执行计划最开始的 table scan 阶段，但如果是 left join，则只会下推 right 表的 join key，而不会下推 left 表的 join key，即无法提前过滤 left 表的无效行。可以通过手动将 left 表的 `Join key is not null` 条件下推，以提前减少无效行读取。

Case2：Spark SQL 中在 Join 后使用 Where 语句时，是先进行 Join，然后再执行 Where 语句筛选和过滤行。可以通过手动将 Where 语句下推到 left 表和 right 表的子查询中，以提前减少无效行的读取。

##### Bloom Filter 过滤前置

当大表 Inner Join/Right Join 小表（即以小表为主表）时，若小表的数据量太大而无法通过 Broadcast 广播给所有 Executor 时（即无法使用 BroadcastJoin），则可以考虑根据基于小表构建 BloomFilter 文件，并广播给 Executor，用于提前过滤大表的数据。

进而减少后续大表在执行 SortMerge Join 的 Exchange（Shuffle） 和 Sort 阶段时，输入的数据量，提升任务整体性能。

#### 减少读取列

SQL Select 语句中按需取列，显式列出对应列字段名，尽量避免使用 `SELECT *`，即减少对应 Task 的读取数据量。

### 减少重复读取

Spark SQL 中，默认情况下，针对同一个 CTE、View 或 Table 的多次重复查询，会重复触发数据读取和 RDD 的生成，为了避免重复查询，应尽量缓存重复数据，减少重复读，以提升任务整体性能。

#### Cache Table

[CACHE TABLE - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/sql-ref-syntax-aux-cache-cache-table.html)

>CACHE TABLE statement caches contents of a table or output of a query with the given storage level. If a query is cached, then a temp view will be created for this query. This reduces scanning of the original files in future queries.
>
>If storageLevel is not explicitly set using OPTIONS clause, the default storageLevel is set to `MEMORY_AND_DISK`.

Spark cache table ，类似 RDBMS 中的常见概念，如临时表（Temporary Table）、物化视图（Materialized View）等，其数据 cache 在 Spark executor 上，支持快速重复读。

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

通过调整相关参数，或者使用 Hint，来触发 Broadcast Join 算法，避免 SortMerge Join 时的 Exchange（Shuffle） 阶段，减少数据 IO，提升性能，同时避免 Shuffle 阶段可能的数据倾斜。

Broadcast Join 相关配置，自动触发：

| Property Name                        | Default          | Meaning                                                                                                                                                                                                                                                                                                                                          | Since Version |
| ------------------------------------ | ---------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------- |
| spark.sql.autoBroadcastJoinThreshold | 10485760 (10 MB) | Configures the maximum size in bytes for a table that will be broadcast to all worker nodes when performing a join. By setting this value to -1, broadcasting can be disabled. Note that currently statistics are only supported for Hive Metastore tables where the command ANALYZE TABLE `<tableName>` COMPUTE STATISTICS noscan has been run. | 1.1.0         |
| spark.sql.broadcastTimeout           | 300              | Timeout in seconds for the broadcast wait time in broadcast joins                                                                                                                                                                                                                                                                                | 1.3.0         |

Broadcast Join Hint，手动触发：

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

### 合理划分任务数据量

#### 调整 partition 数量

1. 使用 Partition Hints：同上
2. 调整 spark.sql.shuffle.partitions ：调整 Spark SQL Shuffle 阶段生成的 partition 数 ` spark.sql.shuffle.partitions`，使得每个 Task 处理的数据量的分布合理，避免出现 OOM。

| Property Name                | Default | Meaning                                                                                                                                                                                                               | Since Version |
| ---------------------------- | ------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------- |
| spark.sql.shuffle.partitions | 200     | The default number of partitions to use when shuffling data for joins or aggregations. Note: For structured streaming, this configuration cannot be changed between query restarts from the same checkpoint location. | 1.1.0 <br>    |

#### 任务拆分

Spark SQL 的执行计划通常为自顶向下的树形结构，对于存在性能问题的任务，可以尝试将其进行水平，或者垂直拆分，尤其是存在数据倾斜的任务。
##### 垂直拆分

对于执行时间超长、OOM、数据倾斜这类任务，可以根据具体的运算逻辑，将对应的任务垂直拆分为多个任务，分步处理，避免单任务处理的数据量太大、逻辑过于复杂，导致任务无法执行通过。
##### 水平拆分

对于数据倾斜的任务，可以通过 Hash 取模的方式进行随机抽样，并统计 Key 的 count 值，以获取存在数据倾斜的 Key，并针对这些数据进行任务水平拆分。其中数据倾斜的少量 Key 采取 Broadcast Join 等方式，以避免 Shuffle 数据倾斜。

## 参考链接

1. [Spark性能优化指南——基础篇 - 美团技术团队](https://tech.meituan.com/2016/04/29/spark-tuning-basic.html)
2. [Spark性能优化指南——高级篇 - 美团技术团队](https://tech.meituan.com/2016/05/12/spark-tuning-pro.html)
3. [京东Spark基于Bloom Filter算法的Runtime Filter Join优化机制 - 脉脉](https://maimai.cn/article/detail?fid=1707795020&efid=dSfxdmyhmG6D8hDYUYvB4Q)