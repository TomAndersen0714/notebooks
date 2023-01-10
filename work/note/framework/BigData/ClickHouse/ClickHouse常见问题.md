# ClickHouse常见问题

## 1. ClickHouse在atomic中使用replicated表的坑点

https://github.com/ClickHouse/ClickHouse/issues/12135





## 2. ClickHouse Server内存不释放

**原因解析**：

https://github.com/ClickHouse/ClickHouse/issues/12563

**解决方案**：

`SYSTEM DROP MARK CACHE`

`SYSTEM DROP UNCOMPRESSED CACHE `

**官方链接**：https://clickhouse.com/docs/en/sql-reference/statements/system/





## 3. Code: 342. DB::Exception: **Existing table metadata in ZooKeeper differs in partition key expression**. Stored in ZooKeeper: day, platform, local: day. (METADATA_MISMATCH) (version 22.1.2.2 (official build))

**原因解析**：

1. 出现此错误的主要原因在于ClickHouse分布式DDL语句默认是采用异步执行的，zookeeper的metadata也是如此，因此如果在提交Distributed DDL之后，立刻再去修改Schema就会导致Zookeeper和本地的metadata不一致，因为zk的数据还未执行更新，解决方案就是在DDL语句添加SYNC后缀，使得其采用Synchronize同步的方式来执行DDL
2. 在ClickHouse release v20.10.3.30, 2020-10-28版本之后，默认的Database Engine被设置成了Atomic。在删除Atomic数据库的表时，如果没有使用SYNC后缀，则一般需要等待database_atomic_delay_before_drop_table_sec时间之后，才会真正执行删除。在Table未被真正删除之前，zookeeper中的元数据依旧存在，因此这期间创建不同结构的表时，则会出现此错误

**解决方案**：

1. `DROP TABLE xqc_dim.xqc_shop_local ON CLUSTER cluster_3s_2r SYNC;`

2. `DROP TABLE xqc_dim.xqc_shop_local ON CLUSTER cluster_3s_2r NO DALAY`



## 4. Exception: Code: 48. DB::Exception: **There was an error on [znzjk-113175-prod-mini-bigdata-bigdata:29000]: Cannot execute replicated DDL query on leader**.

**原因解析**：

在执行DDL时，ClickHouse Server查询添加到了zookeeper的Distribued DDL队列中，但是cluster中的某个副本节点执行报错，或者超时，导致对应Server节点报错，返回给Query分发节点时，则会统一显示此错误

**解决方案1（已验证，无效）**：

`TRUNCATE TABLE xqc_dim.xqc_shop_local ON CLUSTER cluster_3s_2r SYNC;`

`TRUNCATE TABLE xqc_dim.xqc_shop_local ON CLUSTER cluster_3s_2r NO DALAY`

**解决方案2（已验证，无效）：**

`TRUNCATE TABLE IF EXISTS xqc_dim.xqc_shop_local ON CLUSTER cluster_3s_2r SETTINGS replication_alter_partitions_sync = 2;`

**PS**：**此错误仅仅表示对应节点执行失败，具体的报错信息，需要去对应节点的日志上进行检索和排查。**



## 5. Application: Caught exception while loading metadata: Code: 62, e.displayText() = DB::Exception: Incorrect user[:password]@host:port#default_database format detached

**原因解析**：

此问题主要原因在于，distribued表的数据目录下`/etc/clickhouse/data`，`/var/lib/clickhouse/data`中存在detach目录和format_version.txt文件

**解决方案**：

将Distribued表的数据路径下的detach目录和format_version.txt文件后重启即可

**参考链接**：

https://github.com/ClickHouse/ClickHouse/issues/7005