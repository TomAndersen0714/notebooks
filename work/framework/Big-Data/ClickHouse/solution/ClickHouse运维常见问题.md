# ClickHouse运维常见问题


## 运维问题

### ClickHouse Distributed DDL 执行太慢，甚至超时

参考 `Code: 159` 小节相关内容。


### ClickHouse Server 内存不释放

**版本：20.4.2.9**

**原因解析**：

ClickHouse版本较低，Server的Cache占用的内存开销可能会超出配置的限制，并且不及时回收和释放
https://github.com/ClickHouse/ClickHouse/issues/12563

可以通过system.asynchronous_metrics表查看cache相关的性能指标统计情况：`SELECT metric, formatReadableSize(value) FROM system.asynchronous_metrics WHERE metric LIKE '%Cache%'`

[mark_cache_size](https://clickhouse.com/docs/en/operations/server-configuration-parameters/settings/#server-mark-cache-size)
[use_uncompressed_cache](https://clickhouse.com/docs/en/operations/settings/settings/#setting-use_uncompressed_cache)
[uncompressed_cache_size](https://clickhouse.com/docs/en/operations/server-configuration-parameters/settings/#server-settings-uncompressed_cache_size)
[SYSTEM Statements](https://clickhouse.com/docs/en/sql-reference/statements/system/)

**解决方案**：

1. `SELECT metric, formatReadableSize(value) FROM system.asynchronous_metrics WHERE metric LIKE '%Cache%'`
2. `SYSTEM DROP MARK CACHE`
3. `SYSTEM DROP UNCOMPRESSED CACHE`


### ClickHouse ReplacingMergeTree 无法命中索引

**推测原因 1：**
查询 ReplacingMergeTree 表时，如果使用的 `FINAL` 关键字，则默认无法使用 ` PREWHERE ` 优化机制，即无法命中主键索引，需要显示使用 ` PREWHERE ` 子句。

https://clickhouse.com/docs/en/sql-reference/statements/select/prewhere#controlling-prewhere-manually

### ClickHouse 查询 Kill 后仍在执行（Killed query hangs forever）

**推测原因：**
If you are using a recent CH release (21.12+), then the KILL flag will be checked after each block is processed (on older releases it might never be checked).
https://stackoverflow.com/questions/72364629/clickhouse-kill-query-hangs-forever


### ClickHouse IN Subquery 无法命中 Primary Key 索引

**版本：20.4.2.9**

**相关Issue：**
Index not used for IN operator with literals
https://github.com/ClickHouse/ClickHouse/issues/10574

**Demo：**
```sql
SELECT *
FROM ods.xdrs_logs_all
WHERE day = 20230517
AND shop_id IN (
	SELECT '62416239773256001c03f83b' AS shop_id
)
LIMIT 100
```

**解决方案 1：**
如果子查询是常量查询，则可以直接将IN Subquery再嵌套一层子查询，在新的子查询中将索引字段检索出来

```sql
SELECT *
FROM ods.xdrs_logs_all
WHERE day = 20230517
AND shop_id IN (
	SELECT shop_id
	FROM (
		SELECT '62416239773256001c03f83b' AS shop_id
	)
)
LIMIT 100
```

**解决方案 2：**
如果子查询不是常量查询，则可以在外部查询中使用`PREWHERE`来替换原来的`WHERE`子句，提升查询性能。
https://clickhouse.com/docs/en/sql-reference/statements/select/prewhere

PS：虽然性能提升十分明显，但是在执行日志中，依旧显示性能开销较大，初步判断可能是这些性能指标的计算存在误差

```sql
SELECT *
FROM ods.xdrs_logs_all
PREWHERE day = 20230517
AND shop_id IN (
	SELECT '62416239773256001c03f83b' AS shop_id
)
LIMIT 100
```

**解决方案 3：**
升级 ClickHouse 版本，ClickHouse 于 v20.5 之后修复了此类问题


### ClickHouse Atomic Database 的坑点

**版本：22.1.2.2**

**坑点1：Atomic数据库是不支持自动复制数据库及其表结构的**。在进行副本迁移或扩容时，Atomic数据库以及其中的表结构并不会自动复制到新节点上，需要手动迁移其metadata到新副本，重启新副本后才会开始加载其元数据，最后才会根据已经加载的元数据，去复制其中Replicated表的数据到新节点上
https://github.com/ClickHouse/ClickHouse/issues/12135

**坑点2：Atomic数据库删除Replicated表时，暂时只是标记删除，在此期间无法使用相同的ZK路径创建Replicated表**。 在执行删除时，需要使用`SYNC`或`NO DELAY`关键词作为后缀，表示同步删除zookeeper路径，否则就需要等待`database_atomic_delay_before_drop_table_sec`对应的时长

**坑点3：在Atomic数据库中创建表时，存储路径是基于UUID生成的。** https://clickhouse.com/docs/en/engines/database-engines/atomic/#table-uuid



## 错误日志问题

### Code: 48

**版本：20.4.2.9**

**错误日志：**
```
Code: 48. DB::Exception: There was an error on [znzjk-113175-prod-mini-bigdata-bigdata:29000]: Cannot execute replicated DDL query on leader.
```

**原因解析1**：
- **报错对应节点本身存在错误**。最常见的就是对应节点表结构存在问题，无法执行对应的DDL语句。

**原因解析2：**
- **可能是Distribued DDL执行超时，出现死锁**。在执行DDL时，ClickHouse Server将对应的查询添加到了zookeeper的Distribued DDL队列中，但是cluster中的某个副本节点执行报错，或者超时，导致该Leader节点对应的其他Slave副本节点报错，因而需要去对应报错节点的Leader节点上查看具体的报错信息。

**解决方案**：
1. 可以通过clusterAllReplicas函数查询system.query表，获取整个集群的query日志，定位问题节点。


### Code: 62

**版本：22.1.2.2**

**错误日志：**
```
Application: Caught exception while loading metadata: Code: 62, e.displayText() = DB::Exception: Incorrect user[:password]@host:port#default_database format detached
```

**原因解析**：

1. 此问题直接原因在于，distribued表的数据目录下`/etc/clickhouse/data`，`/var/lib/clickhouse/data`中存在detach目录和format_version.txt文件。
2. 此问题的根本原因暂时未知。

**解决方案**：

将Distribued表的数据路径下的detach目录和format_version.txt文件后重启即可
https://github.com/ClickHouse/ClickHouse/issues/7005


### Code: 100

**版本：20.4.2.9**

**错误日志：**
```
Code: 100. DB::Exception: Received from localhost:19000. DB::Exception: Unknown packet from server: While executing SourceFromInputStream.
```

**相关Issues：**
https://github.com/ClickHouse/ClickHouse/issues/10574
https://github.com/ClickHouse/ClickHouse/issues/14833

**问题描述：**

**20.4.2.9 版本使用 `IN Subquery` 子查询时，日志打印信息以及 system. Query 都表明查询未命中 Primary Key**，且查询性能和直接使用非子查询字面量差距很大。同时 Server 端出现报错日志，但 Client 端返回了正常结果。

触发案例：
```SQL
SELECT *
FROM ods.xdrs_logs_all
WHERE day BETWEEN toYYYYMMDD(toDate('2023-05-17')) AND toYYYYMMDD(toDate('2023-05-18'))
AND shop_id GLOBAL IN (
    SELECT '62416239773256001c03f83b' AS a
)
LIMIT 100
```

查询执行日志：
```
2023.06.07 15:46:17.798896 [ 69194 ] {5b76a4f2-fb28-48e9-bd07-5d9fb2808280} <Debug> ods.xdrs_logs_bak_local (SelectExecutor): Key condition: unknown, unknown, and, unknown, and, unknown, unknown, and, and

2023.06.07 15:46:17.798941 [ 69194 ] {5b76a4f2-fb28-48e9-bd07-5d9fb2808280} <Debug> ods.xdrs_logs_bak_local (SelectExecutor): MinMax index condition: (column 0 in [20230517, +inf)), (column 0 in (-inf, 20230518]), and, unknown, and, (column 0 in [20230517, +inf)), (column 0 in (-inf, 20230518]), and, and

2023.06.07 15:46:17.798981 [ 69194 ] {5b76a4f2-fb28-48e9-bd07-5d9fb2808280} <Debug> ods.xdrs_logs_bak_local (SelectExecutor): Selected 6 parts by date, 6 parts by key, 7183 marks to read from 6 ranges
```

**推测原因：**
可能是 `IN Subquery` 查询时，

**解决方案：**
方案一：将 IN Subquery 再嵌套一层子查询，在新的子查询中将索引字段检索出来
方案二：升级 ClickHouse 版本至于 v20.5 之后


### Code: 159

**版本：22.1.2.2**

**错误日志：**
```
clickhouse_driver.errors.ServerException: Code: 159.
DB::Exception: Watching task /clickhouse/task_queue/ddl_4/query-0000351328 is executing longer than distributed_ddl_task_timeout (=180) seconds. There are 4 unfinished hosts (4 of them are currently active), they are going to execute the query in background. Stack trace:

0. DB::Exception::Exception(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, int, bool) @ 0x8fe8d5a in /usr/bin/clickhouse
1. DB::Exception::Exception<std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> >&, long&, unsigned long&, unsigned long&>(int, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> >&, long&, unsigned long&, unsigned long&) @ 0x10814984 in /usr/bin/clickhouse
2. DB::DDLQueryStatusInputStream::readImpl() @ 0x1081406e in /usr/bin/clickhouse
3. DB::IBlockInputStream::read() @ 0xfe50f07 in /usr/bin/clickhouse
4. DB::AsynchronousBlockInputStream::calculate() @ 0xfe4cd61 in /usr/bin/clickhouse
5. ? @ 0xfe4d617 in /usr/bin/clickhouse
6. ThreadPoolImpl<ThreadFromGlobalPool>::worker(std::__1::__list_iterator<ThreadFromGlobalPool, void*>) @ 0x902c6f8 in /usr/bin/clickhouse
7. ThreadFromGlobalPool::ThreadFromGlobalPool<void ThreadPoolImpl<ThreadFromGlobalPool>::scheduleImpl<void>(std::__1::function<void ()>, int, std::__1::optional<unsigned long>)::'lambda0'()>(void&&, void ThreadPoolImpl<ThreadFromGlobalPool>::scheduleImpl<void>(std::__1::function<void ()>, int, std::__1::optional<unsigned long>)::'lambda0'()&&...)::'lambda'()::operator()() @ 0x902e29f in /usr/bin/clickhouse
8. ThreadPoolImpl<std::__1::thread>::worker(std::__1::__list_iterator<std::__1::thread, void*>) @ 0x90299df in /usr/bin/clickhouse
9. ? @ 0x902d2c3 in /usr/bin/clickhouse
10. start_thread @ 0x9609 in /usr/lib/x86_64-linux-gnu/libpthread-2.31.so
11. __clone @ 0x122293 in /usr/lib/x86_64-linux-gnu/libc-2.31.so
```

**关联 issue：**
https://github.com/ClickHouse/ClickHouse/issues/3322
https://github.com/ClickHouse/ClickHouse/issues/6267
https://github.com/ClickHouse/ClickHouse/issues/11779
https://github.com/ClickHouse/ClickHouse/issues/39250

**推测原因 1：**
DDL 查询对应的表的 block_numbers 太多，导致对应表的 Distributed DDL 执行速度太慢。

ClickHouse 在针对 Partitioned Replicated Table 执行 Truncate On Cluster 时，会查询 Zookeeper 中所有的 block_numbers。而 Partitioned Replicated Table 在写入数据后，便会在 Zookeeper 的 block_numbers 路径下增加 child znode，用来存储 Partition 信息。

Zookeeper block_numbers 路径下的 Znode，在 ClickHouse 创建后便不会主动删除，即便是执行 Truncate 或者 Drop Partition 操作。进而随着分区数量的不断增加，Partitioned Replicated Table DDL 执行速度也越来越慢，最终超时。

排查 SQL 示例：
```sql
SELECT *
FROM system.zookeeper
WHERE path = '/clickhouse/ods/tables/01_02/xdqc_dialog_local/block_numbers/'
```

虽然社区已经有人提交了相关 issue，希望社区支持类似特性，但并未获得社区支持，社区给出的理由是，如果在 Truncate 时删除 znode，会导致在 Truncate 和 Insert 操作并发时，可能出现数据一致性问题。[issue-11779](https://github.com/ClickHouse/ClickHouse/issues/11779)

**解决方案 1-1：**
重建 Partitioned Replicated Table 表，ClickHouse 便会自动删除 Zookeeper 中 `/<path-to-table>/block_numbers/` 下的 Znode。

**解决方案 1-2：**
修改技术方案，禁止针对 Partitioned Replicated Table 表进行 Truncate 操作，避免分区 block_numbers 不断积累。目前社区，针对这部分的设计，还未有特定的解决方案。

**推测原因 2：**
在对应语句之前，某些 DDL 语句执行较慢，导致后续提交的 DDL 出现超时。

由于 Distributed DDL 队列是个并行度为 1 的 FIFO 队列，当某些 Distributed DDL 执行较慢时，则会增加后续的 DDL 执行耗时，甚至超时，因此建议查询 DDL Queue 中的记录，依次进行排查。
```sql
SELECT *
FROM system.zookeeper
WHERE path = '/clickhouse/task_queue/ddl'
ORDER BY ctime DESC


SELECT *
FROM system.distributed_ddl_queue
WHERE toYYYYMMDD(query_start_time) = toYYYYMMDD(today())
LIMIT 10
```

**推测原因 3：**
在执行 Distributed DDL 语句时，无法获取正确其他节点信息，因而出现等待超时。

如果日志内容显示 `(0 of them are currently active)`，这类信息，则表明 ClickHouse Cluster 的配置可能存在问题。


### Code: 342

**版本：22.1.2.2**

**错误日志：**
```
Code: 342. DB::Exception: Existing table metadata in ZooKeeper differs in partition key expression. Stored in ZooKeeper: day, platform, local: day. (METADATA_MISMATCH) 
```

**推测原因 1**：
1. 在 ClickHouse release v20.10.3.30, 2020-10-28 版本之后，默认的 Database Engine 被设置成了 Atomic。在删除 Atomic 数据库的表时，默认会先进行标记删除，但 zookeeper 上节点路径依旧存在，需要等待 `database_atomic_delay_before_drop_table_sec` 时间之后，才会真正去。在 Table 未被真正删除之前，zookeeper 中的元数据依旧存在，因此这期间创建不同结构的表时，则会抛出此错误。 
   https://github.com/ClickHouse/ClickHouse/issues/12135
2. [官方链接](https://clickhouse.com/docs/en/engines/database-engines/atomic/#drop-detach-table)

**解决方案 1-1**：
`DROP TABLE xqc_dim.xqc_shop_local ON CLUSTER cluster_3s_2r SYNC;`
**解决方案 1-2**：
`DROP TABLE xqc_dim.xqc_shop_local ON CLUSTER cluster_3s_2r NO DELAY;`


### Code: 524

**版本：22.1.2.2**

**错误日志：**
```
Code: 524, e.displayText () = DB::Exception: Trying to ALTER RENAME key sid column which is a part of key expression (version 21.8.14.5 (official build)).
```

Columns specified in the key expression of the table (either with ORDER BY or PRIMARY KEY) cannot be renamed. Trying to change these columns will produce SQL Error [524].
https://clickhouse.com/docs/en/sql-reference/statements/alter/column#rename-column

**原因解析：**
ClickHouse 不支持修改 Primary Key 对应的列名。


### Code: 999

**版本：21.3.13.9**

```
ALTER TABLE cost.app_kpi_base_data_realtime_local
    RENAME COLUMN delivery_system_type TO jd_jx

Query id: 1bef652e-25dc-4d81-b852-cf6dd35a5b6f

[A03-R28-I24-238-1761453.JD.LOCAL] 2022.07.14 18:15:02.503362 [ 33161 ] {1bef652e-25dc-4d81-b852-cf6dd35a5b6f} <Debug> executeQuery: (from [::ffff:172.28.176.22]:58924, using production parser) alter table cost.app_kpi_base_data_realtime_local RENAME COLUMN delivery_system_type To jd_jx;




Timeout exceeded while receiving data from server. Waited for 300 seconds, timeout is 300 seconds.
Cancelling query.


[A03-R28-I24-238-1761453.JD.LOCAL] 2022.07.14 18:15:02.503490 [ 33161 ] {1bef652e-25dc-4d81-b852-cf6dd35a5b6f} <Trace> ContextAccess (default): Access granted: ALTER RENAME COLUMN(delivery_system_type) ON cost.app_kpi_base_data_realtime_local
[A03-R28-I24-238-1761453.JD.LOCAL] 2022.07.14 18:34:00.882859 [ 33161 ] {1bef652e-25dc-4d81-b852-cf6dd35a5b6f} <Error> executeQuery: Code: 999, e.displayText() = Coordination::Exception: Connection loss (version 21.3.13.9 (official build)) (from [::ffff:172.28.176.22]:58924) (in query: alter table cost.app_kpi_base_data_realtime_local RENAME COLUMN delivery_system_type To jd_jx;), Stack trace (when copying this message, always include the lines below):

0. Coordination::Exception::Exception(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, Coordination::Error, int) @ 0xf9c17f3 in /data0/jdolap/clickhouse/lib/clickhouse
1. Coordination::Exception::Exception(Coordination::Error) @ 0xf9c1cbe in /data0/jdolap/clickhouse/lib/clickhouse
2. zkutil::ZooKeeper::tryMulti(std::__1::vector<std::__1::shared_ptr<Coordination::Request>, std::__1::allocator<std::__1::shared_ptr<Coordination::Request> > > const&, std::__1::vector<std::__1::shared_ptr<Coordination::Response>, std::__1::allocator<std::__1::shared_ptr<Coordination::Response> > >&) @ 0xf9c9842 in /data0/jdolap/clickhouse/lib/clickhouse
3. DB::EphemeralLocksInAllPartitions::EphemeralLocksInAllPartitions(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, zkutil::ZooKeeper&) @ 0xf35aa61 in /data0/jdolap/clickhouse/lib/clickhouse
4. DB::StorageReplicatedMergeTree::allocateBlockNumbersInAffectedPartitions(DB::MutationCommands const&, DB::Context const&, std::__1::shared_ptr<zkutil::ZooKeeper> const&) const @ 0xf1e72b6 in /data0/jdolap/clickhouse/lib/clickhouse
5. DB::StorageReplicatedMergeTree::alter(DB::AlterCommands const&, DB::Context const&, std::__1::shared_ptr<DB::RWLockImpl::LockHolderImpl>&) @ 0xf1eb0e8 in /data0/jdolap/clickhouse/lib/clickhouse
6. DB::InterpreterAlterQuery::execute() @ 0xe9d9090 in /data0/jdolap/clickhouse/lib/clickhouse
7. ? @ 0xeef43b2 in /data0/jdolap/clickhouse/lib/clickhouse
8. DB::executeQuery(std::__1::basic_string<char, std::__1::char_traits<char>, std::__1::allocator<char> > const&, DB::Context&, bool, DB::QueryProcessingStage::Enum, bool) @ 0xeef2cf3 in /data0/jdolap/clickhouse/lib/clickhouse
9. DB::TCPHandler::runImpl() @ 0xf683d3d in /data0/jdolap/clickhouse/lib/clickhouse
10. DB::TCPHandler::run() @ 0xf6962d9 in /data0/jdolap/clickhouse/lib/clickhouse
11. Poco::Net::TCPServerConnection::start() @ 0x11d4bb0f in /data0/jdolap/clickhouse/lib/clickhouse
12. Poco::Net::TCPServerDispatcher::run() @ 0x11d4d521 in /data0/jdolap/clickhouse/lib/clickhouse
13. Poco::PooledThread::run() @ 0x11e83c49 in /data0/jdolap/clickhouse/lib/clickhouse
14. Poco::ThreadImpl::runnableEntry(void*) @ 0x11e7faaa in /data0/jdolap/clickhouse/lib/clickhouse
15. start_thread @ 0x7dc5 in /usr/lib64/libpthread-2.17.so
16. clone @ 0xf621d in /usr/lib64/libc-2.17.so

Query was cancelled.

0 rows in set. Elapsed: 1138.391 sec.

Received exception from server (version 21.3.13):
Code: 999. DB::Exception: Received from 11.127.24.238:9600. DB::Exception: Connection loss. (KEEPER_EXCEPTION)
```