# ClickHouse View视图

View视图是一种特殊的数据库对象，其本身实际上并不存储数据，仅提供数据查询服务。每次查询View时，都会以相同的方式转换查询请求，并将其转交给真正的数据表执行查询。

故从另一方面来将，View实际上只是存储了一个SQL语句，用于替换查询此View的SQL请求中的FROM子句。

## Normal View

### Syntax

```sql
CREATE [OR REPLACE] VIEW [IF NOT EXISTS] [db.]table_name [ON CLUSTER cluster_name]
AS
SELECT ...
```


### Example

```sql
-- DROP VIEW IF EXISTS test.number_view
CREATE VIEW test.number_view AS
SELECT number AS a
FROM numbers(10);


-- SELECT
SELECT a FROM test.number_view;


-- SELECT
SELECT a
FROM (
    SELECT number AS a
    FROM numbers(10)
);
```


## Parameterized View

Parameterized View是一种特殊的Normal View，在创建View时支持附带额外参数，使得在查询View时，就像是使用ClickHouse Table Function（如：remote、cluster等函数）一样，可以传递相应的参数，转换并执行对应的查询。

### Syntax
```sql
CREATE VIEW view
AS 
SELECT * FROM <TABLE>
WHERE Column1={column1:datatype1} and Column2={column2:datatype2} ...
```

### Example

21.8.14.5版本不支持，暂无示例


## 参考链接
1. https://clickhouse.com/docs/en/sql-reference/statements/create/view