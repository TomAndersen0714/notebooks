# HDFS 性能优化基础教程


## 小文件数量问题

### 问题诊断

#### HDFS

HDFS 中，可以通过查看指定路径下的文件信息及其占用空间大小，对比当前文件大小以及默认文件大小，来判断是否有小文件问题：
```bash
hdfs dfs -du -h <hdfs_path>
```

#### Hive/Spark

Hive 中，可以通过以下 DML 命令，查看指定 Hive 表对应的存储路径下的文件，通过 numFiles、totalSize 计算文件的平均空间大小，并和默认文件大小比较，进而判断是否有小文件问题：
```bash
show partitions <table_name>
describe formatted <table_name>
describe formatted <table_name> partition(<partition_exp>)

如:
describe formatted test_db.test_table partition(ds="2024-01-15")
```


### 修复方案


#### 写时合并

写时使用自定义的写入方式，针对数据进行合并后再执行写入
#### 后台合并

备份
合并
替换

### 治理方案

[HDFS小文件数量治理方案](work/component/Big-Data/Apache-Hadoop/Operation/HDFS小文件数量治理方案.md)