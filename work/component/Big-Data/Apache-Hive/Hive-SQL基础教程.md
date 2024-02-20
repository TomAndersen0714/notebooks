# Hive SQL 基础教程


## Data Type

[LanguageManual Types - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Types)

基于 NULL 值的直接函数计算结果都是 NULL。

## DDL

[LanguageManual DDL - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DDL)


### Show

[Show - LanguageManual DDL - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DDL#LanguageManualDDL-Show)

```sql
SHOW PARTITIONS table_name;
```


### Describe

[Describe - LanguageManual DDL - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DDL#LanguageManualDDL-Describe)

```sql
DESCRIBE [EXTENDED|FORMATTED] [db_name.]table_name [column_name] PARTITION partition_spec;
```


## DML

[LanguageManual DML - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DML)

## DQL


### Lateral View

[LanguageManual LateralView - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+LateralView)


[Hive SQL中的 lateral view 与 explode（列转行）以及行转列\_lateral view explode-CSDN博客](https://blog.csdn.net/qq_42374697/article/details/115273726)

`LATERAL VIEW`，创建一个临时 View，并和子查询结果表关联生成笛卡尔积。
`LATERAL VIEW explode`，列转行（行展开），即 ClickHouse 中的 `arrayJoin`。 
`LATERAL VIEW posexplode`，和 `LATERAL VIEW explode` 的区别在于
1. `posexplode` 在进行行展开时，需要在当前生成 View 的头部产生额外的一列，保存当前行的行号。
2. `posexplode` 仅支持 Array 类型的输入参数，而 `explode` 支持 Array 和 Map 类型的输入参数。


### Operators and Functions

[LanguageManual UDF - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+UDF)

[HiveSQL常用函数和运算符](work/component/Big-Data/Apache-Hive/Hive-SQL/HiveSQL常用函数和运算符.md)

#### Operators

#### Functions

Normal user-defined functions, such as concat (), take in a single input row and output a single output row.

#### Table-Generating Functions (UDTF)

Table-generating functions transform a single input row to multiple output rows.

UDTF 支持接收单行结果，并展开为多行，但是在 SELECT 语句中使用 UDTF 时，能且仅能使用一个 UDTF 函数，无法使用其他的 Expression。
#### Aggregate Functions (UDAF)



## Hive SQL 查询性能优化

[HiveSQL查询性能优化教程](work/component/Big-Data/Apache-Hive/Hive-SQL/HiveSQL查询性能优化教程.md)

## 参考链接
1. [Hive SQL LanguageManual](https://cwiki.apache.org/confluence/display/Hive/LanguageManual)
