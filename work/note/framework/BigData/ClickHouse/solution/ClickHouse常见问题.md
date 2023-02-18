# ClickHouse常见问题

## 1. ClickHouse中Atomic数据库的坑点

**版本：22.1.2.2**

**坑点1：Atomic数据库是不支持自动复制数据库及其表结构的**。在进行副本迁移或扩容时，Atomic数据库以及其中的表结构并不会自动复制到新节点上，需要手动迁移其metadata到新副本，重启新副本后才会开始加载其元数据，最后才会根据已经加载的元数据，去复制其中Replicated表的数据到新节点上
https://github.com/ClickHouse/ClickHouse/issues/12135

**坑点2：Atomic数据库删除Replicated表时，暂时只是标记删除，在此期间无法使用相同的ZK路径创建Replicated表**。 在执行删除时，需要使用`SYNC`或`NO DELAY`关键词作为后缀，表示同步删除zookeeper路径，否则就需要等待`database_atomic_delay_before_drop_table_sec`对应的时长

**坑点3：在Atomic数据库中创建表时，存储路径是基于UUID生成的。** https://clickhouse.com/docs/en/engines/database-engines/atomic/#table-uuid



## 2. ClickHouse Server内存开销不释放

**版本：20.4.2.9**

**原因解析**：

ClickHouse版本较低，Server的Cache占用的内存开销可能会超出配置的限制，并且不及时回收和释放
https://github.com/ClickHouse/ClickHouse/issues/12563

**解决方案**：

1. `SYSTEM DROP MARK CACHE`
2. `SYSTEM DROP UNCOMPRESSED CACHE `
3. https://clickhouse.com/docs/en/sql-reference/statements/system/



## 3. Code: 342

**报错：Code: 342. DB::Exception: Existing table metadata in ZooKeeper differs in partition key expression. Stored in ZooKeeper: day, platform, local: day. (METADATA_MISMATCH)** 

**版本：22.1.2.2**

**原因解析**：

1. 在ClickHouse release v20.10.3.30, 2020-10-28版本之后，默认的Database Engine被设置成了Atomic。在删除Atomic数据库的表时，默认会先进行标记删除，但zookeeper上节点路径依旧存在，需要等待`database_atomic_delay_before_drop_table_sec`时间之后，才会真正去。在Table未被真正删除之前，zookeeper中的元数据依旧存在，因此这期间创建不同结构的表时，则会抛出此错误。
   https://github.com/ClickHouse/ClickHouse/issues/12135
2. [官方链接](https://clickhouse.com/docs/en/engines/database-engines/atomic/#drop-detach-table)

**解决方案1**：
`DROP TABLE xqc_dim.xqc_shop_local ON CLUSTER cluster_3s_2r SYNC;`
**解决方案2**：
`DROP TABLE xqc_dim.xqc_shop_local ON CLUSTER cluster_3s_2r NO DELAY;`


## 4. Code: 48

**报错：Code: 48. DB::Exception: There was an error on [znzjk-113175-prod-mini-bigdata-bigdata:29000]: Cannot execute replicated DDL query on leader.**

**版本：20.4.2.9**

**原因解析1**：
- **报错对应节点本身存在错误**。最常见的就是对应节点表结构存在问题，无法执行对应的DDL语句。

**原因解析2：**
- **可能是Distribued DDL执行超时，出现死锁**。在执行DDL时，ClickHouse Server将对应的查询添加到了zookeeper的Distribued DDL队列中，但是cluster中的某个副本节点执行报错，或者超时，导致该Leader节点对应的其他Slave副本节点报错，因而需要去对应报错节点的Leader节点上查看具体的报错信息。

**解决方案**：
1. 可以通过clusterAllReplicas函数查询system.query表，获取整个集群的query日志，定位问题节点。


## 5. Code: 62

**报错：Application: Caught exception while loading metadata: Code: 62, e.displayText() = DB::Exception: Incorrect user[:password]@host:port#default_database format detached**

**版本：22.1.2.2**

**原因解析**：

1. 此问题直接原因在于，distribued表的数据目录下`/etc/clickhouse/data`，`/var/lib/clickhouse/data`中存在detach目录和format_version.txt文件。
2. 此问题的根本原因暂时未知。

**解决方案**：

将Distribued表的数据路径下的detach目录和format_version.txt文件后重启即可
https://github.com/ClickHouse/ClickHouse/issues/7005