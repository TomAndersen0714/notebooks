# ClickHouse Bitmap位图

## ClickHouse Bitmap
RoaringBitmap


## 应用场景

1. 用户画像
2. 人群圈选
3. AB测试
4. UV统计


## Bitmap相关的函数

### Bitmap Functions
1. https://clickhouse.com/docs/en/sql-reference/functions/bitmap-functions


### Bitmap Aggregate Functions
1. groupBitmap: https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/groupbitmap
2. groupBitmapAnd: https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/groupbitmapand
3. groupBitmapOr: https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/groupbitmapor
4. groupBitmapXor: https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/groupbitmapxor

```sql
SELECT *
FROM system.functions
WHERE name LIKE '%groupBitmap%'
```


## Example

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
```


## 参考链接
1. https://clickhouse.com/docs/en/sql-reference/data-types/aggregatefunction
2. https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/groupbitmap
3. https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference/groupbitmapand
4. https://clickhouse.com/docs/en/sql-reference/functions/bitmap-functions
5. [技术干货 | ClickHouse 在十亿级用户画像平台的应用实践](https://maimai.cn/article/detail?fid=1666603389&efid=FJ9ko6oJOUycWo_q5WdZDg)