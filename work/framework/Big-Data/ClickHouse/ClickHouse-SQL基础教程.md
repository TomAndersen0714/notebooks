# ClickHouse SQL 基础教程

https://clickhouse.com/docs/en/sql-reference


## JOIN


LEFT JOIN 时，若右表的 JOIN Key 有空值，则右表空值会匹配该字段所有值，因此 LEFT JOIN 时，右表 JOIN Key Expression 中的字段不应该存在空值。
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



## SQL 优化

[ClickHouse-SQL优化基础教程](work/framework/Big-Data/ClickHouse/ClickHouse-SQL优化基础教程.md)