# ClickHouse AggregatingMergeTree 聚合合并树

AggregatingMergeTree 继承自 MergeTree 引擎，修改了默认的数据合并逻辑。在同一个 data part 中，ClickHouse 会将所有行按照 sorting key 进行合并，相同 sorting key 的行（同一个 data part 中）会被聚合为一行数据，用于存储 aggregate functions 计算结果的中间状态（state），即 AggregateFunction 类型的字段。

`AggregateFunction` 数据类型的字段，存储的是聚合函数的一种中间状态，此中间状态是具备可加性的，而非最终结果，通常用于构建 OLAP Cube，以支持各种粒度下的聚合操作。

在查询 AggregatingMergeTree 表时，需要使用转换函数，才能查询到聚合后的结果，但由于 MergeTree 的合并时机无法确定，故一般只是局部聚合，而非全局聚合，即 AggregatingMergeTree 默认只是提供一种数据的预聚合能力，可以减少后续聚合查询时的扫描数据量。

PS：MergeTree 并不能保证具有相同 primary key (或 sorting key) 的行被写入到同一个 data part，故整个表中的数据并不一定是完全合并的状态。想要实现完全合并，通常需要执行 `OPTIMIZE FINAL` 语句。

> [VersionedCollapsingMergeTree#Selecting Data](https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/versionedcollapsingmergetree/#selecting-data): ClickHouse does not guarantee that all of the rows with the same primary key will be in the same resulting data part or even on the same physical server. This is true both for writing the data and for subsequent merging of the data parts.

## Creating a Table

```sql
CREATE TABLE [IF NOT EXISTS] [db.]table_name [ON CLUSTER cluster]
(
    name1 [type1] [DEFAULT|MATERIALIZED|ALIAS expr1],
    name2 [type2] [DEFAULT|MATERIALIZED|ALIAS expr2],
    ...
) ENGINE = AggregatingMergeTree()
[PARTITION BY expr]
[ORDER BY expr]
[SAMPLE BY expr]
[TTL expr]
[SETTINGS name=value, ...]
```

## SELECT and INSERT

`INSERT` 插入数据时，需要使用对应的聚合函数（即 Aggregate Function）并附带 `State` 后缀，如：sumState、uniqState等。
`SELECT` 查询数据时，需要使用 `GROUP BY` 子句，以及对应的聚合函数，并附带 `Merge` 后缀，如：sumMerge、uniqMerge等。

ClickHouse 的 `AggregateFunction` 类型的数据，虽然是使用了特殊的二进制表示，不可读，但同样支持文件等形式的导出和导入。

## Example

```sql

-- DROP TABLE test.visits
CREATE TABLE test.visits
(
    StartDate DateTime64,
    CounterID UInt64,
    Sign Int32,
    UserID Int32
) ENGINE = MergeTree ORDER BY (StartDate, CounterID);


-- DROP TABLE test.mv_visits
CREATE MATERIALIZED VIEW test.mv_visits
(
    StartDate DateTime64,
    CounterID UInt64,
    Visits AggregateFunction(sum, Int32),
    Users AggregateFunction(uniq, Int32)
)
ENGINE = AggregatingMergeTree() ORDER BY (StartDate, CounterID)
AS SELECT
    StartDate,
    CounterID,
    sumState(Sign) AS Visits,
    uniqState(UserID) AS Users
FROM test.visits
GROUP BY StartDate, CounterID;

-- INSERT
INSERT INTO test.visits (StartDate, CounterID, Sign, UserID) VALUES (1667446031, 1, 3, 4);
INSERT INTO test.visits (StartDate, CounterID, Sign, UserID) VALUES (1667446031, 1, 6, 3);


-- SELECT
SELECT
    StartDate,
    sumMerge(Visits) AS Visits,
    uniqMerge(Users) AS Users
FROM test.mv_visits
GROUP BY StartDate
ORDER BY StartDate;

```

## 参考链接

1. https://clickhouse.com/docs/en/sql-reference/data-types/aggregatefunction
2. https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference
3. https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/aggregatingmergetree