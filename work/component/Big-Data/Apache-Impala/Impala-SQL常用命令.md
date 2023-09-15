# Apache Impala常用命令


## Apache Impala通用

### SHOW
1. 查看当前数据库：`select current_database()`
2. 查看库中表：`show tables in <database>`
3. 查看表创建语句：`show create table <table>`
4. 查看库中函数：`show functions in <database>`
5. 查看函数创建语句：`show create function <function>`
6. 查看表结构：`describe <table>`，查看表详细信息：`describe formatted <table>`。
   PS：Table Type字段为“MANAGED_TABLE”则表明当前表为管理表，反之则为外部表
7. 查看分区：`show partitions <table>`
8. 查看range分区：`show range partitions <table>`
9. 查看统计信息
	1. 查看表统计信息：`show table stats <table_name>`
	2. 查看列统计信息：`show column stats <table_name>`
	3. 手动触发统计信息采集：`compute stats <table_name>`。
	4. PS：Impala可以利用分区表的统计信息来加速分区表查询，部分情况下可以自动采集统计信息
10. 刷新表元数据：`refresh <table>`，PS：新建分区之后，需要刷新元数据
11. 刷新UDF：`refresh functions <database>`


### EXPLAIN
1. 查看执行计划：`explain <query_SQL>`
2. 查看上一个执行的SQL性能：`summary`
3. 查看上一个执行的SQL详细执行报告：`profile`


### ALTER
1. 修改表名：`alter table <old_name> rename to <new_name>`
	1. 在高版本Impala中（如：v1.9），修改Impala外部表（external）仅仅会修改Impala表名；对于Impala管理表（managed）会同时修改Impala表名，以及底层Impala创建的Kudu表的表名（即impala::为前缀的Kudu表）
	2. 在低版本Impala中（如：v1.1），修改Impala外部表（external）或管理表（managed）都仅仅是修改Impala表名，并不会去修改Kudu表的表名


## Apache Parquet(HDFS)


### CREATE
1. 创建分区表（默认为管理表）
```mysql
CREATE TABLE IF NOT EXISTS test.partition_test (
    id STRING,
    name STRING
)
PARTITIONED BY (day INT)
STORED AS PARQUET
LOCATION 'hdfs://nameservice1/user/hive/warehouse/test.db/partition_test'
```

2. 根据已有表创建新表
```mysql
CREATE TABLE test.test1
LIKE test.test 
STORED AS PARQUET 
LOCATION 'hdfs://host1:8020/user/hive/warehouse/test.db/test_1'

```

3. 创建外部表
```mysql
CREATE EXTERNAL TABLE IF NOT EXISTS test.partition_test (
    id STRING,
    name STRING
)
PARTITIONED BY (day INT)
STORED AS PARQUET
LOCATION 'hdfs://nameservice1/user/hive/warehouse/test.db/partition_test'
```

### INSERT
1. 数据插入分区表
```mysql
INSERT INTO test.partition_test partition (day=20221102)
VALUES("007","TomAndersen")
```


### ALTER

1. 分区操作
	1. 添加分区：`ALTER TABLE <table> ADD [IF NOT EXISTS] PARTITION (key=value)`
	2. 删除分区：`ALTER TABLE <table> DROP [IF EXISTS] PARTITION (key=value)`
2. 管理表和外部表切换
	1. 管理表转换为外部表：`ALTER TABLE my_table SET TBLPROPERTIES('EXTERNAL'='TRUE')`
	2. 外部表转换为管理表：`ALTER TABLE table_name SET TBLPROPERTIES('EXTERNAL'='FALSE')`
3. 修改文件路径Location：`ALTER TABLE test.t1 SET LOCATION 'hdfs://cdh0:8022/user/hive/warehouse/test.db/t1'`
4. 配置Sort By属性，提升查询性能
	1. `ALTER TABLE test.mini_xdrs_log SET TBLPROPERTIES('sort.columns' = 'shop_id, act')`
	2. PS：设置Impala Sort By功能之后，在使用“INSERT INTO”或者“CREATE TABLE AS”时，都会在对应Parquet文件的metadata中保存对应列的统计信息（即Min/Max索引），并在查询时针对对应的列使用这些统计信息，快速过滤不必要的Parquet文件，加速查询过程



### DELETE

1. 清空表：`TRUNCATE <table>`


### LOAD DATA

1. 加载HDFS文件入表：`LOAD DATA INPATH '/path/to/hdfs/directory' [OVERWRITE] INTO TABLE tablename [PARTITION (partcol1=val1, partcol2=val2 ...)]`

## Apache Kudu

### CREATE
1. 创建管理表（managed table）、Hash分区表
```mysql
CREATE TABLE xd_data.web_log_kudu (
    day INT NOT NULL ENCODING AUTO_ENCODING COMPRESSION DEFAULT_COMPRESSION,
    track_id STRING NOT NULL ENCODING AUTO_ENCODING COMPRESSION DEFAULT_COMPRESSION,
    app_id STRING NULL ENCODING AUTO_ENCODING COMPRESSION DEFAULT_COMPRESSION
    PRIMARY KEY (day, track_id)
)
PARTITION BY HASH (day, track_id) PARTITIONS 4 
STORED AS KUDU
TBLPROPERTIES ('kudu.master_addresses'='cdh0:7051,cdh1:7051,cdh2:7051')
```
2. 创建外部表（external table）
```mysql
CREATE EXTERNAL TABLE test.kudu_test_tbl 
STORED AS KUDU 
TBLPROPERTIES ('kudu.master_addresses'='cdh0:7051,cdh1:7051,cdh2:7051', 'kudu.table_name'='kudu_test_table')
```
3. 基于查询结果创建新表
```mysql
CREATE TABLE tmp.test_kudu_source_tbl_1 
PRIMARY KEY (shop_id, snick, day) 
PARTITION BY HASH (shop_id) PARTITIONS 16 
STORED AS KUDU 
TBLPROPERTIES ('kudu.master_addresses'='cdh0:7051,cdh1:7051,cdh2:7051') 
AS SELECT * FROM tmp.test_kudu_source_tbl
```

### ALTER
1. 修改Impala表的Kudu Master地址：`ALTER TABLE <table> SET TBLPROPERTIES('kudu.master_addresses'='host1:7051,host2:7051')`
2. 修改Impala表指向的Kudu表：`ALTER TABLE impala_table_name SET TBLPROPERTIES('kudu.table_name'='kudu_table_name')`
3. RANGE分区操作
	1. 添加分区：
		1. `ALTER TABLE tmp.range_partition_test ADD IF NOT EXISTS RANGE PARTITION 20210802<=VALUES<20210803`
		2. `ALTER TABLE tmp.range_partition_test ADD IF NOT EXISTS RANGE PARTITION VALUE=20210802`
	2. 删除分区：
		1. `ALTER TABLE tmp.range_partition_test DROP IF EXISTS RANGE PARTITION 20210802<=VALUES<20210803`
		2. `ALTER TABLE tmp.range_partition_test drop if exists range partition VALUE=20210802`

### DELETE
1. 删除数据（Impala 2.8 or higher only）
	1. 删除表：`DELETE FROM <table>`，或`DELETE <table>`
	2. 删除指定行：`DELETE FROM <table> WHERE <where_statement>`，PS：注意命中索引
 

## 其他
1) 低版本Kudu（如：1.1）中通过Impala修改Impala和Kudu表名步骤：
	a. 修改Impala表名
	b. 将Impala表改为外部表
	c. 修改Kudu表表名
	d. 修改Impala表的'kudu.table_name'属性
	e. 将Impala改成内部表


## 参考链接
1. [Impala Documentation](https://impala.apache.org/impala-docs.html)