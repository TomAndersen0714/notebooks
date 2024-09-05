# HiveSQL DML 操作


## Insert

### Standard syntax 标准语法

```sql
INSERT OVERWRITE TABLE tablename1 [PARTITION (partcol1=val1, partcol2=val2 ...) [IF NOT EXISTS]] select_statement1 FROM from_statement;

INSERT INTO TABLE tablename1 [PARTITION (partcol1=val1, partcol2=val2 ...)] select_statement1 FROM from_statement;
```


Demo:

```SQL

```

### Hive extension (multiple inserts) 同源多次写入

```sql
-- Hive extension (multiple inserts):
FROM from_statement
INSERT OVERWRITE TABLE tablename1 [PARTITION (partcol1=val1, partcol2=val2 ...) [IF NOT EXISTS]] select_statement1
[INSERT OVERWRITE TABLE tablename2 [PARTITION ... [IF NOT EXISTS]] select_statement2]
[INSERT INTO TABLE tablename2 [PARTITION ...] select_statement2] ...;


FROM from_statement
INSERT INTO TABLE tablename1 [PARTITION (partcol1=val1, partcol2=val2 ...)] select_statement1
[INSERT INTO TABLE tablename2 [PARTITION ...] select_statement2]
[INSERT OVERWRITE TABLE tablename2 [PARTITION ... [IF NOT EXISTS]] select_statement2] ...;
```

Demo:

```SQL

```

### Dynamic Partition Inserts 动态分区写入


[LanguageManual DML - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DML#LanguageManualDML-DynamicPartitionInserts)

在使用动态分区写入功能时，用户可以在 PARTITION 子句中仅指定部分分区列的具体字面量，而其他分区值从 SELECT 查询中获取，其中这部分未明确指定具体字面量的分区列，被称为动态分区（dynamic partition），反之则被称为静态分区（static partition）。

其中动态分区，必须是 SELECT 子句列名清单末尾的连续列名，并且与其在 PARTITION 子句中的声明顺序完全相同。


在以下的例子中，country 分区，会自动引用 SELECT 子句中最后一列，来动态填充二级分区对应的值。
```sql
FROM page_view_stg pvs
INSERT OVERWRITE TABLE page_view PARTITION(dt='2008-06-08', country)
       SELECT pvs.viewTime, pvs.userid, pvs.page_url, pvs.referrer_url, null, null, pvs.ip, pvs.cnt
```

Demo:

```SQL

```

## Load





## 参考链接
1. [LanguageManual DML - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DML)