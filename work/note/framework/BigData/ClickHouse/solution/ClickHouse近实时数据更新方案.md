# ClickHouse近实时数据更新方案

## 前言

众所周知，主攻OLAP场景的数据库引擎一般都会采用某种列式存储格式，以支撑其强大的数据处理性能，当无法同时兼顾行级事务，以及频繁的数据实时更新操作。如ROLAP中的Hive、Impala、Presto、ClickHouse，以及MOLAP中的Druid、Kylin，等等。

虽然OLAP引擎中不少能够通过挂载外部表，接入外部数据库引擎来弥补自身的读、写或存储能力缺陷，但由于对接外部数据库时存在的SQL转译、索引命中、谓词下推、数据序列化、类型转换等等问题的存在，操作外接数据库意味着相比于直接原生数据库，通常表现出更低的性能和更高的资源开销。如果与外部数据源功能兼容得不够完善，那么在大批量数据处理或者较高并发的场景下，常常会变得捉襟见肘，十分尴尬。

ClickHouse作为近几年的主流OLAP型数据库，其本身也是基于MPP(Massively Parallel Processing)架构践行者之一，具备强大的数据并行处理能力，但其最初的版本并不具备数据行级更新的能力，虽然后续迭代的版本中新增了Mutation操作来支持行级更新和删除，但这种操作是异步、非事务型操作且性能较差，不支持频繁使用，无法适用于高并发的实时更新场景。

而本文的主要内容是，仅基于ClickHouse的原生特性，调研和总结近实时数据更新方案。

本文基于ClickHouse版本`20.4.2.9`。

----


## 01.行级近实时更新方案

### 1. ORDER BY + LIMIT BY

**官方文档:**

- [ClickHouse - LIMIT BY Clause](https://clickhouse.com/docs/en/sql-reference/statements/select/limit-by/)

**使用注意事项:**  

1. 为了避免数据覆盖更新的频率过高，导致表数据量膨胀系数过大，进而使得查询性能下降，建议和ReplacingMergeTree、CollapsingMergeTree等支持后台自动合并的表引擎一起使用，以尽量减少过期的无效数据存储
2. 从测试结果来看，LIMIT BY子句在合并数据时内存开销与数据量的正相关系数较大，不适合大数据量的合并去重操作



### 2. GROUP BY + argMax

**官方文档:**

- [ClickHouse - aggregate function - argMax](https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/argmax/)
- [ClickHouse - aggregate function - argMin](https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/argmin/
  )


### 3. ReplacingMergeTree + FINAL(+ PREWHERE)

**官方文档:**

- [ClickHouse - ReplacingMergeTree](https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/replacingmergetree)

**使用注意事项:**

1. Order By表达式必须以Primary Key表达式为前缀(即索引KVs的存储顺序，必须和数据的存储顺序严格对应)

2. 单纯使用此类表引擎支撑数据更新时，其数据更新的实时性取决于数据合并的频率，而ClickHouse默认的合并策略是无法预估合并时机的，合并间隔可达到小时级别。类似于ReplacingMergeTree表引擎这种基于MergeTree的数据合并策略来实现数据更新的组件，单独使用时无法保证数据不出现重复，通常还需要搭配特定的查询才可以实现数据最终读一致性。

3. ReplacingMergeTree只能消除将要合并到同一个data part文件夹下的数据记录，但ClickHouse默认的合并策略无法保证Primary Key相同的数据会被写入到同一个data part中，即使这些数据都存储在同一个ClickHouse服务器上，并且就算后续进行过合并也无法保证这一点。通常需要手动执行`OPTIMIZE...FINAL`来触发强制合并后，才将相同Primary Key的数据强制合并到同一个data part中。

> [VersionedCollapsingMergeTree#Selecting Data](https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/versionedcollapsingmergetree/#selecting-data): ClickHouse does not guarantee that all of the rows with the same primary key will be in the same resulting data part or even on the same physical server. This is true both for writing the data and for subsequent merging of the data parts.

4. SELECT查询中使用`FINAL`关键字时，将不会自动开启PREWHERE子句的优化功能，虽然ClickHouse提供了`optimize_move_to_prewhere`和`optimize_move_to_prewhere_if_final`配置来控制这一行为，但在本文实验采用的ClickHouse(20.4.2.9)中，这一参数并未起到任何实际作用，只能通过显式使用`PREWHERE`子句来声明筛选条件，以实现非主键字段的谓词下推，提升查询效率。([PREWHERE Clause](https://clickhouse.com/docs/en/sql-reference/statements/select/prewhere/))。
   PS: 如果PREWHERE只能用于筛选只读字段，如果筛选可修改字段，会将旧数据一起查询出来



### 4. (Versioned)CollapsingMergeTree + FINAL(+ PREWHERE)

**官方文档:**

- [ClickHouse - CollapsingMergeTree](https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/collapsingmergetree/)

- [ClickHouse - VersionedCollapsingMergeTree](https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/versionedcollapsingmergetree)

**使用注意事项:**

1. CollapsingMergeTree在写入时，对于数据的写入顺序有严格要求，必须要保证sign等于1的记录先写入，sign等于-1的记录后写入，才能实现数据删除。如果写入顺序错误，则合并数据时无法实现抵消，旧的数据将一直存在，只能在后续查询时显式剔除。
2. CollapsingMergeTree表引擎在删除数据时，只需要写入Primary Key表达式中的所有字段，以及值为-1的标识字段sign即可，不必将整行中全部字段全都写入。
3. FINAL关键字存在的性能问题，以及ClickHouse生成data part时存在的数据合并问题，同ReplacingMergeTree。



## 02.字段级近实时更新方案: 

### 5. Mutation

**官方文档:**

- [ClickHouse - mutations](https://clickhouse.com/docs/en/sql-reference/statements/alter/#mutations)

**使用注意事项:**

1. Mutation操作是很重的异步操作，尤其是在分布式语句中执行时，且此类操作的数据一致性能力很弱，不建议在实际生产环境中频繁使用
2. 提交Mutation查询时，其筛选条件(filter expression)不能过于复杂，否则可能造成Mutation操作过度消耗集群资源，可以通过[system.mutations](https://clickhouse.com/docs/en/operations/system-tables/mutations/#system_tables-mutations)表的`is_done`字段来判断mutation操作的执行状态。如果Mutation操作影响正常使用，可以使用[KILL MUTATION](https://clickhouse.com/docs/en/sql-reference/statements/kill/#kill-mutation)操作来终止Mutation的执行。

---

## 03.简单性能测试

ClickHouse版本：`20.4.2.9`


### 测试过程

#### 方案1: ORDER BY + LIMIT BY

**测试表**

```sql
-- DROP TABLE IF EXISTS update_test_limit_by
CREATE TABLE update_test_limit_by
(
   `user_id` String,
   `score` String,
   `update_time` DateTime
)
ENGINE = MergeTree()
ORDER BY `user_id`
```

**写入测试数据**

```sql
INSERT INTO TABLE update_test_limit_by(user_id, score)
WITH(
 SELECT ['A','B','C','D','E','F','G']
)AS dict
SELECT
   number AS user_id,
   dict[number%7+1] AS score
FROM numbers(30000000)
```

**写入更新数据**

```sql
INSERT INTO TABLE update_test_limit_by(user_id, score, update_time)
WITH(
 SELECT ['AA','BB','CC','DD','EE','FF','GG']
)AS dict
SELECT
   number AS user_id,
   dict[number%7+1] AS score,
   now() AS update_time
FROM numbers(5000000)
```

**查询数据更新结果**

```sql
SELECT
   *
FROM update_test_limit_by
WHERE user_id = '2'
ORDER BY update_time DESC
LIMIT 1 BY user_id
```

**性能测试**

```sql
-- 性能测试用例
SELECT COUNT(1)
FROM (
   SELECT *
   FROM update_test_limit_by
   ORDER BY update_time
   LIMIT 1 BY user_id
)

-- 性能测试结果
MemoryTracker: Peak memory usage (for query): 2.56 GiB
1 rows in set. Elapsed: 11.920 sec. Processed 35.00 million rows, 717.78 MB (2.94 million rows/s., 60.22 MB/s.)

MemoryTracker: Peak memory usage (for query): 2.56 GiB.
1 rows in set. Elapsed: 13.272 sec. Processed 35.00 million rows, 717.78 MB (2.64 million rows/s., 54.08 MB/s.)

MemoryTracker: Peak memory usage (for query): 2.56 GiB.
1 rows in set. Elapsed: 12.043 sec. Processed 35.00 million rows, 717.78 MB (2.91 million rows/s., 59.60 MB/s.)
```





#### 方案2: GROUP BY + argMax

**测试表**

```sql
-- DROP TABLE IF EXISTS update_test_group_by
CREATE TABLE update_test_group_by
(
   `user_id` String,
   `score` String,
   `update_time` DateTime
)
ENGINE = MergeTree()
ORDER BY `user_id`
```



**写入测试数据**

```sql
INSERT INTO TABLE update_test_group_by(user_id, score)
WITH(
 SELECT ['A','B','C','D','E','F','G']
)AS dict
SELECT
   number AS user_id,
   dict[number%7+1] AS score
FROM numbers(30000000)
```



**写入更新数据**

```sql
INSERT INTO TABLE update_test_group_by(user_id, score, update_time)
WITH(
 SELECT ['AA','BB','CC','DD','EE','FF','GG']
)AS dict
SELECT
   number AS user_id,
   dict[number%7+1] AS score,
   now() AS update_time
FROM numbers(5000000)
```



**查询数据更新结果**

```sql
SELECT
   user_id,
   argMax(score, update_time) AS score,
   max(update_time) AS utime
FROM update_test_group_by
WHERE user_id = '200'
GROUP BY user_id
```



**性能测试**

```sql
-- 性能测试用例
SELECT COUNT(1)
FROM (
   SELECT
	   user_id,
	   argMax(score, update_time) AS score,
	   max(update_time) AS utime
   FROM update_test_group_by
   GROUP BY user_id
)

-- 性能测试结果
MemoryTracker: Peak memory usage (for query): 1.68 GiB.
1 rows in set. Elapsed: 5.595 sec. Processed 35.00 million rows, 717.78 MB (6.26 million rows/s., 128.29 MB/s.)

MemoryTracker: Peak memory usage (for query): 1.27 GiB.
1 rows in set. Elapsed: 5.182 sec. Processed 35.00 million rows, 717.78 MB (6.75 million rows/s., 138.51 MB/s.)

MemoryTracker: Peak memory usage (for query): 1.27 GiB.
1 rows in set. Elapsed: 5.238 sec. Processed 35.00 million rows, 717.78 MB (6.68 million rows/s., 137.03 MB/s.)
```


#### 方案3: ReplacingMergeTree + FINAL

**测试表**

```sql
-- DROP TABLE IF EXISTS update_test_replacing
CREATE TABLE update_test_replacing
(
   `user_id` String,
   `score` String,
   `update_time` DateTime
)
ENGINE = ReplacingMergeTree(update_time)
ORDER BY `user_id`
```

**写入测试数据**

```sql
INSERT INTO TABLE update_test_replacing(user_id, score)
WITH(
 SELECT ['A','B','C','D','E','F','G']
)AS dict
SELECT
   number AS user_id,
   dict[number%7+1] AS score
FROM numbers(30000000)
```

**写入更新数据**

```sql
INSERT INTO TABLE update_test_replacing(user_id, score, update_time)
WITH(
 SELECT ['AA','BB','CC','DD','EE','FF','GG']
)AS dict
SELECT
   number AS user_id,
   dict[number%7+1] AS score,
   now() AS update_time
FROM numbers(5000000)
```

**查询数据更新结果**

```sql
SELECT
*
FROM update_test_replacing FINAL
WHERE user_id = '2'
```

**性能测试**

```sql
-- 性能测试用例
SELECT COUNT(1)
FROM update_test_replacing FINAL

-- 性能测试结果
MemoryTracker: Peak memory usage (for query): 34.56 MiB.
1 rows in set. Elapsed: 1.783 sec. Processed 35.00 million rows, 717.78 MB (19.63 million rows/s., 402.62 MB/s.)

MemoryTracker: Peak memory usage (for query): 34.69 MiB.
1 rows in set. Elapsed: 1.777 sec. Processed 35.00 million rows, 717.78 MB (19.70 million rows/s., 404.03 MB/s.)

MemoryTracker: Peak memory usage (for query): 38.12 MiB
1 rows in set. Elapsed: 1.684 sec. Processed 35.00 million rows, 717.78 MB (20.79 million rows/s., 426.27 MB/s.)
```



#### 方案4: CollapsingMergeTree + FINAL

**测试表**

```sql
-- DROP TABLE IF EXISTS update_test_collapsing
CREATE TABLE update_test_collapsing
(
   `user_id` String,
   `score` String,
   `update_time` DateTime,
   `sign` Int8
)
ENGINE = CollapsingMergeTree(sign)
ORDER BY `user_id`
```

**写入测试数据**

```sql
INSERT INTO TABLE update_test_collapsing(user_id, score, sign)
WITH(
 SELECT ['A','B','C','D','E','F','G']
)AS dict
SELECT
   number AS user_id,
   dict[number%7+1] AS score,
   1 AS sign
FROM numbers(30000000)
```

**写入更新数据**

```sql
-- 删除旧数据
INSERT INTO TABLE update_test_collapsing(user_id, score, sign)
WITH(
 SELECT ['A','B','C','D','E','F','G']
)AS dict
SELECT
   number AS user_id,
   dict[number%7+1] AS score,
   -1 AS sign
FROM numbers(5000000)

-- 查看删除结果
SELECT COUNT(1) FROM update_test_collapsing FINAL

-- 写入新数据
INSERT INTO TABLE update_test_collapsing(user_id, score, update_time, sign)
WITH(
 SELECT ['A','B','C','D','E','F','G']
)AS dict
SELECT
   number AS user_id,
   dict[number%7+1] AS score,
   now() AS update_time,
   1 AS sign
FROM numbers(5000000)

-- 查看数据更新结果
SELECT COUNT(1) FROM update_test_collapsing FINAL
SELECT * FROM update_test_collapsing FINAL WHERE user_id = '2'


```

**性能测试**

```sql
-- 性能测试用例
SELECT COUNT(1)
FROM update_test_collapsing FINAL

-- 性能测试结果
MemoryTracker: Peak memory usage (for query): 22.15 MiB.
1 rows in set. Elapsed: 2.012 sec. Processed 40.00 million rows, 696.67 MB (19.88 million rows/s., 346.30 MB/s.)

MemoryTracker: Peak memory usage (for query): 22.13 MiB.
1 rows in set. Elapsed: 2.224 sec. Processed 40.00 million rows, 696.67 MB (17.99 million rows/s., 313.27 MB/s.)

MemoryTracker: Peak memory usage (for query): 22.13 MiB.
1 rows in set. Elapsed: 2.104 sec. Processed 40.00 million rows, 696.67 MB (19.01 million rows/s., 331.08 MB/s.)
```


### 性能测试结果汇总

- **方案1(ORDER BY + LIMIT BY):**


```
MemoryTracker: Peak memory usage (for query): 2.56 GiB
1 rows in set. Elapsed: 11.920 sec. Processed 35.00 million rows, 717.78 MB (2.94 million rows/s., 60.22 MB/s.)

MemoryTracker: Peak memory usage (for query): 2.56 GiB.
1 rows in set. Elapsed: 13.272 sec. Processed 35.00 million rows, 717.78 MB (2.64 million rows/s., 54.08 MB/s.)

MemoryTracker: Peak memory usage (for query): 2.56 GiB.
1 rows in set. Elapsed: 12.043 sec. Processed 35.00 million rows, 717.78 MB (2.91 million rows/s., 59.60 MB/s.)
```

- **方案2(GROUP BY + argMax):**

```
MemoryTracker: Peak memory usage (for query): 1.68 GiB.
1 rows in set. Elapsed: 5.595 sec. Processed 35.00 million rows, 717.78 MB (6.26 million rows/s., 128.29 MB/s.)

MemoryTracker: Peak memory usage (for query): 1.27 GiB.
1 rows in set. Elapsed: 5.182 sec. Processed 35.00 million rows, 717.78 MB (6.75 million rows/s., 138.51 MB/s.)

MemoryTracker: Peak memory usage (for query): 1.27 GiB.
1 rows in set. Elapsed: 5.238 sec. Processed 35.00 million rows, 717.78 MB (6.68 million rows/s., 137.03 MB/s.)
```


- **方案3(ReplacingMergeTree + FINAL(+ PREWHERE)):**

```
MemoryTracker: Peak memory usage (for query): 34.56 MiB.
1 rows in set. Elapsed: 1.783 sec. Processed 35.00 million rows, 717.78 MB (19.63 million rows/s., 402.62 MB/s.)

MemoryTracker: Peak memory usage (for query): 34.69 MiB.
1 rows in set. Elapsed: 1.777 sec. Processed 35.00 million rows, 717.78 MB (19.70 million rows/s., 404.03 MB/s.)

MemoryTracker: Peak memory usage (for query): 38.12 MiB
1 rows in set. Elapsed: 1.684 sec. Processed 35.00 million rows, 717.78 MB (20.79 million rows/s., 426.27 MB/s.)
```

- **方案4((Versioned)CollapsingMergeTree + FINAL(+ PREWHERE)):**

```
MemoryTracker: Peak memory usage (for query): 22.15 MiB.
1 rows in set. Elapsed: 2.012 sec. Processed 40.00 million rows, 696.67 MB (19.88 million rows/s., 346.30 MB/s.)

MemoryTracker: Peak memory usage (for query): 22.13 MiB.
1 rows in set. Elapsed: 2.224 sec. Processed 40.00 million rows, 696.67 MB (17.99 million rows/s., 313.27 MB/s.)

MemoryTracker: Peak memory usage (for query): 22.13 MiB.
1 rows in set. Elapsed: 2.104 sec. Processed 40.00 million rows, 696.67 MB (19.01 million rows/s., 331.08 MB/s.)
```

## 04.使用参考建议

1. **查询结果数据量较小，不支持更改表引擎，不改SQL主体，选择方案1(ORDER BY + LIMIT BY)**
2. **查询结果数据量中等，不支持更改表引擎，修改主体SQL，选择方案2(GROUP BY + argMax)**
3. **支持修改表引擎，优先选择方案3(ReplacingMergeTree + FINAL(+ PREWHERE))，其次有仅删除类需求，选择方案4((Versioned)CollapsingMergeTree + FINAL(+ PREWHERE))**
4. **临时手动修改表数据，但非生产环境使用，选择方案5(Mutation)**





## 05.参考链接

1. [ClickHouse Document-Alter-mutations](https://clickhouse.com/docs/en/sql-reference/statements/alter/#mutations)
2. [ClickHouse的秘密基地-ClickHouse准实时数据更新的新思路](https://cloud.tencent.com/developer/article/1644570)
3. [ClickHouse的秘密基地-ClickHouse各种MergeTree的关系与作用](https://mp.weixin.qq.com/s?__biz=MzA4MDIwNTY4MQ==&mid=2247483804&idx=1&sn=b304f7f88d064cc08f87fa5eaafec0b7&chksm=9fa68382a8d10a9440d3ce2a92a04c4a74aeda2d959049f04f1a414c1fb8034b97d9f7243c21&scene=21#wechat_redirect)
4. [阿里云开发者社区-在ClickHouse中处理实时更新](https://developer.aliyun.com/article/823894?utm_content=m_1000311820)
5. [墨天轮-Clickhouse 数据合并初探](https://www.modb.pro/db/124574)
6. [CSDN-MergeTree分区目录合并详细过程](https://blog.csdn.net/Night_ZW/article/details/112845382)