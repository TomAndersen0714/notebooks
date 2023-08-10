# ClickHouse SQL 基础教程

https://clickhouse.com/docs/en/sql-reference


## JOIN


LEFT JOIN 时，如果右表的 JOIN Key 有空值，则右表空值默认会匹配该字段所有值。
```sql
SELECT *
FROM (
    SELECT 'a' AS a
) AS a
LEFT JOIN (
    SELECT '' AS a
    UNION ALL
    SELECT 'b' AS a
)
USING(a)
```


```sql
SELECT *
FROM (
    SELECT '' AS a
) AS a
LEFT JOIN (
    SELECT 'a' AS a
    UNION ALL
    SELECT 'b' AS a
)
USING(a)
```