# MySQL SQL 基础教程


## Query

[MySQL 中各 SQL 子句的逻辑执行顺序](https://blog.csdn.net/TomAndersen/article/details/105881084)

### Scalar Subquery

https://dev.mysql.com/doc/refman/8.0/en/scalar-subqueries.html

标量子查询

### DUAL table

DUAL is a kind of dummy table, which is purely for the convenience of people who require that all SELECT statements should have FROM and possibly other clauses. MySQL may ignore the clauses. MySQL does not require FROM DUAL if no tables are referenced.

```SQL
SELECT 1+1 FROM DUAL
```


## Functions

### Window function

https://dev.mysql.com/doc/refman/8.0/en/window-functions.html


## 参考链接
1. [MySQL 中各 SQL 子句的逻辑执行顺序](https://blog.csdn.net/TomAndersen/article/details/105881084)