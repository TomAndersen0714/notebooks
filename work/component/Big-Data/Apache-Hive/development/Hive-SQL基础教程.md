# Hive SQL 基础教程

## Data Type

[LanguageManual Types - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Types)

基于 NULL 值的直接函数计算结果都是 NULL。

## DDL

[LanguageManual DDL - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DDL)

### Create/Drop/Truncate/Alter Table

#### Table

```
DROP TABLE IF EXISTS mammut_user.test_table_1;

CREATE TABLE mammut_user.test_table_1(
    `column1` INT COMMENT '列1',
    `column2` DOUBLE COMMENT '列2',
    `column3` STRING COMMENT '列3',
    `column4` DECIMAL(18,2) COMMENT '列4'
)
COMMENT '测试表'
PARTITIONED BY (
    ds STRING COMMENT '分区1-YYYYMMDD'
)
STORED AS ORC;
```

PS：值得注意的是，Hive Table DDL 语句中的 Partition 和 Store 都是使用的过去式，即 Partitioned 和 Stored，这是 Hive

#### Partition

#### Column

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

1. [LanguageManual LateralView - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+LateralView)
2. [Hive SQL中的 lateral view 与 explode（列转行）以及行转列\_lateral view explode-CSDN博客](https://blog.csdn.net/qq_42374697/article/details/115273726)

`LATERAL VIEW`，创建一个临时 View，并和子查询结果表关联生成笛卡尔积。

`LATERAL VIEW explode, LATERAL VIEW posexplode`，常常被称为，列转行/列合并/行展开（数据行变多），原表数据量增加倍数为 Map 的长度和 Array 的长度，如果有多个 Array 或 Map，则是二次方，即笛卡尔积，此组合方法类似于 ArrayJoin，即 ClickHouse 中的 `groupArray` 和 `arrayJoin`。

`LATERAL VIEW posexplode`，和 `LATERAL VIEW explode` 的区别：
1. `posexplode` 在进行行展开时，需要在当前生成 View 的头部产生额外的一列，保存当前行的行号。
2. `posexplode` 仅支持 Array 类型的输入参数，而 `explode` 支持 Array 和 Map 类型的输入参数。

Example:
```sql
-- explode
select
	col1, col2, col5
from test
lateral view explode(split(col3,','))  b AS col5

-- posexplode
select
    class,
    student_index + 1 as student_index,student_name
from
    default.classinfo
lateral view posexplode(split(student,',')) t as student_index,student_name;
```

### Operators and Functions

[HiveSQL函数基础教程](work/component/Big-Data/Apache-Hive/development/HiveSQL函数基础教程.md)

## Hive SQL 优化

[HiveSQL性能优化基础教程](work/component/Big-Data/Apache-Hive/development/HiveSQL性能优化基础教程.md)

## 参考链接

1. [LanguageManual - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual)