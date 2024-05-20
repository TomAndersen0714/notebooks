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

### Function


## DML

[LanguageManual DML - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DML)

## DQL

[LanguageManual Select - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Select)


### JOIN

[LanguageManual Joins - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Joins)


#### Inner Join


#### Left Semi Join

`Left Semi Join` 其实功能上等价于 MySQL 中 Where 子句中常用的 Exist 或 In 子句，并且支持多列值同时判断。

`Left Semi Join` 和 `Inner Join` 的主要区别在于，当右表的 Join Key 不唯一时，`Inner Join` 会使得数据膨胀，而 `Left Semi Join` 最多只会保留左表数据，而不会使得数据集膨胀，相当于 `Left Semi Join` 等价于先对右表的 Join Key 去重后再进行 Inner Join，即 `Group By/Distinct + Inner Join`。

### Lateral View

[LanguageManual LateralView - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+LateralView)


[Hive SQL中的 lateral view 与 explode（列转行）以及行转列\_lateral view explode-CSDN博客](https://blog.csdn.net/qq_42374697/article/details/115273726)

`LATERAL VIEW`，创建一个临时 View，并和子查询结果表关联生成笛卡尔积。

`LATERAL VIEW explode`，常常被称为，列转行/列合并/行展开（数据行变多），原表数据量增加倍数为 Map 的长度和 Array 的长度，如果有多个 Array 或 Map，则是二次方，即笛卡尔积，此组合方法类似于 ArrayJoin，即 ClickHouse 中的 `groupArray` 和 `arrayJoin`。 

`LATERAL VIEW posexplode`，和 `LATERAL VIEW explode` 的区别在于：
1. `posexplode` 在进行行展开时，需要在当前生成 View 的头部产生额外的一列，保存当前行的行号。
2. `posexplode` 仅支持 Array 类型的输入参数，而 `explode` 支持 Array 和 Map 类型的输入参数。

### Operators and Functions

[LanguageManual UDF - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+UDF)

#### Operators

`a <=> b` : 支持判断 null 值的等于符号。

#### Functions

[HiveSQL常用函数](work/component/Big-Data/Apache-Hive/Hive-SQL/HiveSQL常用函数.md)

Normal user-defined functions, such as concat (), take in a single input row and output a single output row.

第三方 Hive UDF 仓库：
[GitHub - jeromebanks/brickhouse: Hive UDF's for the data warehouse](https://github.com/jeromebanks/brickhouse)
[GitHub - brndnmtthws/facebook-hive-udfs: Facebook's Hive UDFs](https://github.com/brndnmtthws/facebook-hive-udfs)
[GitHub - aaronshan/hive-third-functions: Some useful custom hive udf functions, especial array, json, math, string functions.](https://github.com/aaronshan/hive-third-functions)

#### Table-Generating Functions (UDTF)

Table-generating functions transform a single input row to multiple output rows.

UDTF 支持接收单行结果，并展开为多行，但是在 SELECT 语句中使用 UDTF 时，能且仅能使用一个 UDTF 函数，无法使用其他的 Expression。
#### Aggregate Functions (UDAF)



## Hive SQL 优化

[HiveSQL性能优化基础教程](work/component/Big-Data/Apache-Hive/Hive-SQL/HiveSQL性能优化基础教程.md)

## 参考链接
1. [Hive SQL LanguageManual](https://cwiki.apache.org/confluence/display/Hive/LanguageManual)
