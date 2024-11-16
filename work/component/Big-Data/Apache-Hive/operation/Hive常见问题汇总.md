# Hive 常见问题汇总

**Hive 分区表新增字段问题：**

Hive Partitioned Table 增加字段后，默认情况下无法更新已有分区的元数据信息，当针对这些历史分区进行 insert into 写入时，新增字段便会显示未 null 值。

**解决方案：**
解决方案 1：
增加字段时，使用 `CASCADE` 关键字，可以同时刷新历史分区的元数据
```sql
ALTER TABLE default.test_table ADD columns (column1 string,column2 string) CASCADE; 
```
解决方案 2：
写入分区前，重建分区，即 `insert overwrite` 或者 `drop partition`。
解决方案 3：
刷新整表的元数据。

**参考链接：**
[Adding new columns to an already partitioned Hive ... - Cloudera Community - 245636](https://community.cloudera.com/t5/Community-Articles/Adding-new-columns-to-an-already-partitioned-Hive-table/ta-p/245636)

## 参考链接

1. [Adding new columns to an already partitioned Hive ... - Cloudera Community - 245636](https://community.cloudera.com/t5/Community-Articles/Adding-new-columns-to-an-already-partitioned-Hive-table/ta-p/245636)