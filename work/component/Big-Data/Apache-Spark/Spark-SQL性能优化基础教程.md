# Spark SQL 性能优化基础教程


## 为什么要进行 Spark SQL 性能优化

提升计算资源利用率
提升任务的执行效率，减少任务的执行时间

## 什么样的 Spark SQL 需要性能优化

[什么样的 SQL 需要性能优化](work/component/Back-End/MySQL/solution/MySQL-SQL查询性能优化教程.md#什么样的%20SQL%20需要性能优化)

## Spark SQL 常见性能问题及诊断方法

### Spark SQL 常见性能问题

[大数据技术 - Spark - 《有数中台FAQ》](https://study.sf.163.com/documents/read/service_support/dsc-t-03)
[4 Common Reasons for FetchFailed Exception in Apache Spark - DZone](https://dzone.com/articles/four-common-reasons-for-fetchfailed-exception-in-a)

1. OOM 内存不足
2. Data Skew 数据倾斜
3. Timeout 执行超时

#### OOM 内存不足

#### Task 数据倾斜

[CSDN-诸葛子房-Spark 任务优化分析](https://blog.csdn.net/weixin_43291055/article/details/133770448)
[SparkSql 慢任务诊断案例](https://mp.weixin.qq.com/s/3RrpzO5rPthKfyGX8MvnFw)
#### Time out 任务超时

[Spark braodcast join timeout 300 - yuexiuping - 博客园](https://www.cnblogs.com/yuexiuping/p/15043556.html)

### Spark SQL 常用诊断方法

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

Case3：Spark SQL 使用 Order By+Limit 语句查询 TopN 时，优化器会对 partition 中的数据进行局部排序 local sort 并局部筛选 local limit，减少后续读取数据量，然后再去执行全局排序和筛选 global limit。


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

##### Bloom Filter 过滤

[京东Spark基于Bloom Filter算法的Runtime Filter Join优化机制 - 脉脉](https://maimai.cn/article/detail?fid=1707795020&efid=dSfxdmyhmG6D8hDYUYvB4Q)

当大表 Inner Join/Right Join 小表（即以小表为主表）时，若小表的数据量太大而无法通过 Broadcast 广播给所有 Executor 时（即无法使用 BroadcastJoin），则可以考虑根据基于小表构建 BloomFilter 文件，并广播给 Executor，用于提前过滤大表的数据。

进而减少后续大表在执行 SortMerge Join 的 Exchange（Shuffle） 和 Sort 阶段时，输入的数据量，提升任务整体性能。

#### 减少读取列

SQL Select 语句中按需取列，显式列出对应列字段名，尽量避免使用 `SELECT *`，即减少对应 Task 的读取数据量。

Case1：当表中存在几十个字段，但实际上当前查询只需要几个字段时，减少查询使用字段，能大量减少查询时生成的 RDD 大小。

#### 减少重复读取

Spark SQL 中，默认情况下，针对同一个 CTE、View 或 Table 的多次重复查询，会重复触发数据读取和 RDD 的生成，为了避免重复查询，应尽量缓存重复数据，减少重复读，以提升任务整体性能。

##### Cache Table

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

##### Broadcast Join

[Spark-JOIN算法基础教程](work/component/Big-Data/Apache-Spark/mechanism/Spark-JOIN算法基础教程.md)

通过配置并触发 Broadcast Join 算法，避免 Sort-merge Join 时的 Shuffle 阶段，减少数据 IO，提升性能，进而可以避免 Shuffle（Exchange）阶段可能存在的数据倾斜 Data Skew。

Broadcast Join 支持自动触发，但是必须得是**非 left/full outer join 的主表**，以及必须是实体表，即**已缓存或者创建过的表**。如果需要 Broadcast Join 的表是主表，则可以先针对非主表执行 inner join 来执行 inner join，即自动触发 Broadcast Join，以低代价提前过滤数据。

Broadcast Join 自动触发，相关配置：

| Property Name                        | Default          | Meaning                                                                                                                                                                                                                                                                                                                                                  | Since Version |
| ------------------------------------ | ---------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------- |
| spark.sql.autoBroadcastJoinThreshold | 10485760 (10 MB) | Configures the maximum size in bytes for a table that will be broadcast to all worker nodes when performing a join. By setting this value to -1, broadcasting can be disabled. <br><br>Note that currently statistics are only supported for Hive Metastore tables where the command `ANALYZE TABLE <tableName> COMPUTE STATISTICS noscan` has been run. | 1.1.0         |
| spark.sql.broadcastTimeout           | 300              | Timeout in seconds for the broadcast wait time in broadcast joins                                                                                                                                                                                                                                                                                        | 1.3.0         |

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

对于执行时间超长、OOM 这类任务，可以根据具体的运算逻辑，将对应的任务垂直拆分为多个任务，分步处理，避免单任务处理的数据量太大、逻辑过于复杂，导致任务执行时出现 OOM、超时等报错而执行失败。

对于 Group By 聚合类数据倾斜的任务，可以尝试通过加盐（salting）、加字段的方式进行分步聚合。

对于 Inner Join 筛选类数据倾斜的任务，如果小表不满足 BroadcastJoin 条件，则可以尝试通过大表加盐（salting）+小表膨胀（scaling）的方式

```sql
SELECT
    t1.key
FROM (
    SELECT
        concat(floor(rand()*10), key) AS new_key
    FROM t1
) t1
INNER JOIN (
    SELECT concat(prefix, key) as new_key
    FROM t2
    LATERAL VIEW explode(array(1,2,3,4,5,6,7,8,9,10)) t AS prefix
) t2
on t1.new_key = t2.new_key
```

##### 水平拆分

对于 OOM、执行时间长的任务，可以尝试将其水平拆解为多个执行过程相同的任务，减少单个任务的数据量。

对于数据倾斜的任务，可以通过 Hash 取模的方式进行随机抽样，并统计 Key 的 count 值，以获取存在数据倾斜的 Key，并针对这些数据进行任务水平拆分。针对数据倾斜的少量 Key 采取 Broadcast Join 等方式，以避免 Shuffle 数据倾斜。

## 参考链接

1. [Spark性能优化指南——基础篇 - 美团技术团队](https://tech.meituan.com/2016/04/29/spark-tuning-basic.html)
2. [Spark性能优化指南——高级篇 - 美团技术团队](https://tech.meituan.com/2016/05/12/spark-tuning-pro.html)
3. [京东Spark基于Bloom Filter算法的Runtime Filter Join优化机制 - 脉脉](https://maimai.cn/article/detail?fid=1707795020&efid=dSfxdmyhmG6D8hDYUYvB4Q)
4. [Spark排错与优化 - linhaifeng - 博客园](https://www.cnblogs.com/linhaifeng/p/16245352.html)
5. [spark 数据倾斜优化 - 阿伟宝座 - 博客园](https://www.cnblogs.com/saowei/p/16044630.html#spark-%E6%95%B0%E6%8D%AE%E5%80%BE%E6%96%9C%E4%BC%98%E5%8C%96)