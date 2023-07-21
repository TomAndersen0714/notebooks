# ClickHouse Materialized View 物化视图

在ClickHouse中，Materialized View 实际上是一种**针对 INSERT 事件的触发器（`Trigger`）**，且**只增不删**，每当向源表中插入数据时，依赖此源表的Materialized View 便会通过其创建时声明的 SELECT 查询，将本次插入的数据进行转换，并将转换后的结果数据写入到 Materialized View 声明的目标表中。

使用 Materialized View 物化视图，能够在数据 INSERT 时对其进行近实时处理，相当于一种数据的预处理，通常可以用于提升后续的SELECT查询性能，如：通过预聚合源表的数据并写入目标表，减少ClickHouse在查询目标表时需要扫描的数据量，以及运算量。

Materialized View 物化视图实际上本身也并不存储数据，如果未指定目标表，创建Materialized View 物化视图的同时也会在同数据库下创建一张隐藏表，用于存储转换后的数据。

## Syntax

```sql
CREATE MATERIALIZED VIEW [IF NOT EXISTS] [db.]table_name [ON CLUSTER] 
[TO [db.]name] 
[ENGINE = engine] [POPULATE] 
AS 
SELECT ...
```

1. 如果创建 Materialized View 时，未指定`TO [db].[table]`，则必须在创建时声明`ENGINE`，即表明无目标表，使用 Materialized View 来存储转换后的数据（实际上是通过创建了一个对应库下的内部表`.inner.<VIEW_NAME>`的方式，来实现存储）。
2. 如果创建时，指定了`TO [db].[table]`，则表明 Materialized View 转换后的数据需要存储入到对应的表中，且声明时禁止使用`POPULATE`选项，来重刷源表的历史数据。
3. `POPULATE`选项，用于控制是否在创建 Materialized View 的同时，使用源表（SELECT查询结果）的数据，来初始化视图的数据，进行填充。


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

-- DROP TABLE IF EXISTS test.sink_mv
CREATE MATERIALIZED VIEW test.sink_mv
(
    `sign` String,
    `uuid` String
)
ENGINE = MergeTree()
ORDER BY sign
AS
SELECT *
FROM test.mv_source;


-- INSERT INTO
INSERT INTO test.mv_source(`sign`, `uuid`)
VALUES ('a', '001');

INSERT INTO test.mv_source(`sign`, `uuid`)
VALUES ('b', '002'), ('c', '003');


-- SELECT
SELECT *
FROM test.sink_mv;
```


## 应用场景

1. Materialized View 天然适合于各种数据实时处理的应用场景，可以用于构建“实时数据仓库”，但由于 ClickHouse Server qps 负载有限的问题，因此主要适合于构建“准实时数仓”，实时性不算强。


## 注意事项

1. 由于 Table Schema 在开发和生产环境中经常会发生变化，如果不同时修改对应的 Materialized View 物化视图，则默认情况下会导致 INSERT 数据阻塞，除非将 `materialized_views_ignore_errors` 参数设置为 `true`。
2. Materialized View 支持定义 SELECT 语句中使用 JOIN，但在实际开发和生产环境中，需要注意 JOIN 的性能问题，避免因 qps 太高，而导致资源开销太大。


## 参考链接
1. https://clickhouse.com/docs/en/engines/table-engines/special/materializedview
2. https://clickhouse.com/docs/en/sql-reference/statements/create/view#materialized-view
3. https://clickhouse.com/docs/en/guides/developer/cascading-materialized-views
4. https://clickhouse.com/blog/using-materialized-views-in-clickhouse