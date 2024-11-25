# Hive 常见问题汇总

## Hive 表元数据问题

Hive Partitioned Table 增加字段后，默认情况下无法更新已有分区的元数据信息，当针对这些历史分区进行 `insert into` 写入时，新增字段便会显示为 null 值。

**解决方案：**

方案 1：写入历史分区前，先使用 `drop partition` 删除历史分区。

方案 2（不推荐）：增加字段时，使用 `CASCADE` 关键字，可以同时刷新历史分区的元数据
```sql
ALTER TABLE default.test_table ADD columns (column1 string,column2 string) CASCADE; 
```

方案 3（不推荐）：刷新整表所有分区的元数据。

**参考链接：**
1. [Adding new columns to an already partitioned Hive ... - Cloudera Community - 245636](https://community.cloudera.com/t5/Community-Articles/Adding-new-columns-to-an-already-partitioned-Hive-table/ta-p/245636)
2. [Hive分区表增加字段新增字段为NULL解决方案\_hive 分区空的扩展字段-CSDN博客](https://blog.csdn.net/weixin_40983094/article/details/121303745)