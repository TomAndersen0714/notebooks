# ClickHouse Bitmap位图


## ClickHouse Bitmap

### 简介

ClickHouse中，`Bitmap Object`通常有两种构建方式：
1. 一种是使用`groupBitmap`聚合函数附加`State`后缀，即`groupBitmapState`将任意个无符号整型聚合后，生成对应的Bitmap对象；
2. 二是使用`bitmapBuild`函数，将输入的无符号整型数组，转换后生成对应的Bitmap对象。

ClickHouse中Bitmap对象的基数（即不同非符号整数数量）小于等于32时，实际上存储时使用的是`Set`对象，当基数大于32时，则使用的是`RoaringBitmap`对象，这也是为什么ClickHouse低基数的bitmap会比高基数的bitmap速度要快。


### 应用场景

1. 用户画像
2. 人群圈选
3. AB测试
4. 精准去重计数


## Bitmap相关的函数

### Bitmap Functions
https://clickhouse.com/docs/en/sql-reference/functions/bitmap-functions

Bitmap Function主要用于处理单个或两个Bitmap对象的运算。


#### bitmapBuild

将无符号整型数组，转换为对应的Bitmap对象。PS：bitmap中重复记录只会记录一次。

```sql
SELECT bitmapBuild([1, 2, 3, 4, 5]) AS res, toTypeName(res);
```

#### bitmapToArray

将bitmap对象，转换为无符号整型数组的形式。

```sql
SELECT bitmapToArray(bitmapBuild([1, 2, 3, 4, 5])) AS res;
```


### Bitmap Aggregate Functions

```sql
SELECT *
FROM system.functions
WHERE name LIKE '%groupBitmap%'
```


#### groupBitmap
https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/groupbitmap

输入任意数量`UInt*`类型的无符号整数，生成对应的bitmap对象后，仅返回bitmap的基数。

如果附加`State`后缀，则返回对应的Bitmap对象，即`AggregateFunction(groupBitmap, UInt*)`类型的数值。
如果附加`Merge`后缀，则是输入任意数量Bitmap对象，返回对应聚合后的bitmap基数（UInt64类型）。


#### groupBitmapAnd
https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/groupbitmapand

输入任意数量`AggregateFunction(groupBitmap, UInt*)`类型数值，执行AND与运算，最后返回UInt64类型的bitmap基数。

如果附加`State`后缀，则返回AND与运算后对应的Bitmap对象结果，即`AggregateFunction(groupBitmap, UInt*)`类型的数值。


#### groupBitmapOr
https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/groupbitmapor


#### groupBitmapXor
https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/groupbitmapxor



## Example and Benchmark

主机配置
```
Intel(R) Xeon(R) Silver 4110 CPU @ 2.10GHz
Intel(R) Xeon(R) Silver 4110 CPU @ 2.10GHz

16GiB DIMM DDR4 Synchronous Registered (Buffered) 2666 MHz (0.4 ns)
16GiB DIMM DDR4 Synchronous Registered (Buffered) 2666 MHz (0.4 ns)
32GiB DIMM DDR4 Synchronous Registered (Buffered) 2666 MHz (0.4 ns)
```

Benchmark
```sql
-- DROP TABLE IF EXISTS test.visits;
CREATE TABLE test.visits
(
    StartDate UInt32,
    Sign String,
    UserID UInt32
)
ENGINE = MergeTree()
PARTITION BY StartDate
ORDER BY Sign;


-- DROP TABLE IF EXISTS test.dws_mv_visits;
CREATE TABLE test.dws_mv_visits
(
    StartDate UInt32,
    Sign String,
    VisitsBitMap AggregateFunction(groupBitmap, UInt64),
    Count UInt32
)
ENGINE = MergeTree()
PARTITION BY StartDate
ORDER BY Sign;


-- TRUNCATE TABLE test.visits
INSERT INTO test.visits (StartDate, Sign, UserID)
SELECT
    1667446031 AS StartDate,
    '1' Sign,
    number
FROM numbers(400000)

UNION ALL
SELECT
    1667446032 AS StartDate,
    '2' Sign,
    number
FROM numbers(400000)

UNION ALL
SELECT
    1667446033 AS StartDate,
    '3' Sign,
    number
FROM numbers(400000)

UNION ALL
SELECT
    1667446034 AS StartDate,
    '4' Sign,
    number
FROM numbers(400000);

-- TRUNCATE TABLE test.dws_mv_visits
INSERT INTO test.dws_mv_visits
SELECT
    StartDate,
    Sign,
    groupBitmapState(toUInt64(UserID)) AS Visits,
    COUNT(1) AS Count
FROM test.visits
GROUP BY StartDate, Sign;

-- SELECT
-- 1 rows in set. Elapsed: 0.009 sec.
SELECT
    groupBitmapMerge(VisitsBitMap)
FROM test.dws_mv_visits;

SELECT
    groupBitmapAnd(VisitsBitMap)
FROM test.dws_mv_visits;

-- SELECT
-- 1 rows in set. Elapsed: 0.064 sec. Processed 1.60 million rows, 6.40 MB (25.05 million rows/s., 100.22 MB/s.)
SELECT COUNT(DISTINCT UserID) FROM test.visits;

SELECT uniqExact(UserID) FROM test.visits;
```

小结：在160-40w数据量的情况下，使用groupBitmapMerge去重，耗时约为uniqExact的7分之1。


## 常见问题

### 问题1: groupBitmapOr等函数计算结果和实际不符

github issue: groupBitmapOr result incorrect in distribued table #31335
在低版本中groupBitmapOr、groupBitmapAnd在查询Distribued表时，计算结果不正确
https://github.com/ClickHouse/ClickHouse/issues/31335
https://github.com/ClickHouse/ClickHouse/pull/32529

**解决方案**：
不要针对Distribued表直接查询并使用对应的聚合函数，需要将Distribued表中所需的字段以子查询的形式查询出来，如`SELECT * FROM test.distribued_all`，然后在再使用bitmap相关的聚合函数进行聚合。

个人推测是因为在直接查询分布式Distribued表时，查询会被转换为对应的本地表查询，而在将本地表的查询结果汇总时，低版本ClickHouse中并没有针对此问题进行特殊处理，进而导致查询结果与实际不符。



## 参考链接
1. https://clickhouse.com/docs/en/sql-reference/data-types/aggregatefunction
2. https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/groupbitmap
3. https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/groupbitmapand
4. https://clickhouse.com/docs/en/sql-reference/functions/bitmap-functions
5. [技术干货 | ClickHouse 在十亿级用户画像平台的应用实践](https://maimai.cn/article/detail?fid=1666603389&efid=FJ9ko6oJOUycWo_q5WdZDg)
6. [ClickHouse Meetup-在苏宁用户画像场景的实践](https://mp.weixin.qq.com/s/sLFD5llh8YaECtqsNjSbjQ)