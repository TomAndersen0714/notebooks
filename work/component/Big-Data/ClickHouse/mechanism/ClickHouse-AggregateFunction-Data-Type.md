# ClickHouse AggregateFunction Data Type

`AggregateFunction`数据类型的字段，存储的是聚合函数的一种中间状态，而非最终结果，此中间状态是具备可加性的，即可以直接点查计算最终结果，也可以使用GROUP BY按照任意粒度（即，任意维度的组合）进行聚合，即除了支持点查外，还支持OLAP Cube中除了下钻（Drill Down）的任意操作。

如，使用`AggregateFunction(uniq, String)`数据类型时，通过`uniqState`函数写入uniq聚合函数的中间状态，构建OLAP Cube，而每个聚合函数计算的中间状态，后续都能按照任意粒度进行聚合，可以保证聚合结果的正确性。

## 应用场景

1. 通过预计算的方式，为某些不可加性指标（如：UV、Mean等）提前计算出一个具备可加性、可复用的中间值，加速后续查询


## Syntax

`AggregateFunction(name, types_of_arguments…)`

`name` - Name of the aggregate function. If the function is parametric, specify its parameters too.
`types_of_arguments` - Types of the aggregate function arguments.

生成`AggregateFunction`类型的字段，需要使用附带`State`后缀的聚合函数，如“uniqState”，而查看`AggregateFunction`类型字段的最终结果，需要使用附带`Merge`后缀的聚合函数，如“uniqMerge”。


## Example
```sql
-- DROP TABLE IF EXISTS test.mv_source
CREATE TABLE test.mv_source
(
    `sign` String,
    `uuid` String
)
ENGINE = MergeTree()
ORDER BY sign;


-- DROP TABLE IF EXISTS test.mv_sink_stat
CREATE TABLE test.mv_sink_stat
(
    `sign` String,
    `uuid_uniq_state` AggregateFunction(uniq, String)
)
ENGINE = MergeTree()
ORDER BY sign;


-- DROP TABLE IF EXISTS test.mv
CREATE MATERIALIZED VIEW test.mv
TO test.mv_sink_stat
AS
SELECT
    sign,
    uniqState(uuid) AS uuid_uniq_state
FROM test.mv_source
GROUP BY sign;


-- INSERT INTO
INSERT INTO test.mv_source(`sign`, `uuid`)
VALUES ('a', '001');

INSERT INTO test.mv_source(`sign`, `uuid`)
VALUES ('a', '001'), ('a', '002'), ('c', '003');


-- SELECT
SELECT
    sign,
    uniqMerge(uuid_uniq_state)
FROM test.mv_sink_stat
GROUP BY sign;
```


## 参考链接
1. https://clickhouse.com/docs/en/sql-reference/data-types/aggregatefunction
2. https://clickhouse.com/docs/en/sql-reference/aggregate-functions/reference
