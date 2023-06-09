# ClickHouse运维常见问题

## ClickHouse中Atomic数据库的坑点

**版本：22.1.2.2**

**坑点1：Atomic数据库是不支持自动复制数据库及其表结构的**。在进行副本迁移或扩容时，Atomic数据库以及其中的表结构并不会自动复制到新节点上，需要手动迁移其metadata到新副本，重启新副本后才会开始加载其元数据，最后才会根据已经加载的元数据，去复制其中Replicated表的数据到新节点上
https://github.com/ClickHouse/ClickHouse/issues/12135

**坑点2：Atomic数据库删除Replicated表时，暂时只是标记删除，在此期间无法使用相同的ZK路径创建Replicated表**。 在执行删除时，需要使用`SYNC`或`NO DELAY`关键词作为后缀，表示同步删除zookeeper路径，否则就需要等待`database_atomic_delay_before_drop_table_sec`对应的时长

**坑点3：在Atomic数据库中创建表时，存储路径是基于UUID生成的。** https://clickhouse.com/docs/en/engines/database-engines/atomic/#table-uuid



## ClickHouse Server内存占用不释放

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



## Clickhouse KILL QUERY hangs forever

If you are using a recent CH release (21.12+), then the KILL flag will be checked after each block is processed (on older releases it might never be checked).
https://stackoverflow.com/questions/72364629/clickhouse-kill-query-hangs-forever


## ClickHouse IN Subquery无法命中Primary Key索引

**错误版本：20.4.2.9**
ClickHouse IN Operator+Subquery无法命中Primary Key索引

**相关Issue：**
Index not used for IN operator with literals
https://github.com/ClickHouse/ClickHouse/issues/10574

**Sample：**
```sql
SELECT *
FROM ods.xdrs_logs_all
WHERE day = 20230517
    AND shop_id IN (
        SELECT '62416239773256001c03f83b' AS shop_id
    )
LIMIT 100
```


**解决方案：**
**方案一**：如果子查询是常量查询，则可以直接将IN Subquery再嵌套一层子查询，在新的子查询中将索引字段检索出来

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


**方案二**：如果子查询不是常量查询，则可以在外部查询中使用`PREWHERE`来替换原来的`WHERE`子句，提升查询性能。
https://clickhouse.com/docs/en/sql-reference/statements/select/prewhere

PS：虽然性能提升十分明显，但是在执行日志中，依旧显示性能开销较大，初步判断可能是这些性能指标的计算存在误差

```sql
SELECT *
FROM ods.xdrs_logs_all
PREWHERE day = 20230517
AND shop_id GLOBAL IN (
    SELECT shop_id
    FROM (
        SELECT shop_id
        FROM xqc_dim.xqc_shop_all
        WHERE day = 20230608
        AND shop_id = '61616faa112fa5000dcc7fba'
    )
)
LIMIT 100
```

**方案三**：升级ClickHouse版本


## 错误日志

### Code: 48

**错误日志：**
Code: 48. DB::Exception: There was an error on [znzjk-113175-prod-mini-bigdata-bigdata:29000]: Cannot execute replicated DDL query on leader.

**版本：20.4.2.9**

**原因解析1**：
- **报错对应节点本身存在错误**。最常见的就是对应节点表结构存在问题，无法执行对应的DDL语句。

**原因解析2：**
- **可能是Distribued DDL执行超时，出现死锁**。在执行DDL时，ClickHouse Server将对应的查询添加到了zookeeper的Distribued DDL队列中，但是cluster中的某个副本节点执行报错，或者超时，导致该Leader节点对应的其他Slave副本节点报错，因而需要去对应报错节点的Leader节点上查看具体的报错信息。

**解决方案**：
1. 可以通过clusterAllReplicas函数查询system.query表，获取整个集群的query日志，定位问题节点。


### Code: 62

**错误日志：**
Application: Caught exception while loading metadata: Code: 62, e.displayText() = DB::Exception: Incorrect user[:password]@host:port#default_database format detached

**版本：22.1.2.2**

**原因解析**：

1. 此问题直接原因在于，distribued表的数据目录下`/etc/clickhouse/data`，`/var/lib/clickhouse/data`中存在detach目录和format_version.txt文件。
2. 此问题的根本原因暂时未知。

**解决方案**：

将Distribued表的数据路径下的detach目录和format_version.txt文件后重启即可
https://github.com/ClickHouse/ClickHouse/issues/7005


### Code: 100

**错误日志：**
Code: 100. DB::Exception: Received from localhost:19000. DB::Exception: Unknown packet from server: While executing SourceFromInputStream.

**版本：20.4.2.9**

**相关Issues：**
https://github.com/ClickHouse/ClickHouse/issues/10574
https://github.com/ClickHouse/ClickHouse/issues/14833

**问题描述：**

**20.4.2.9版本使用`IN Subquery`子查询时，日志打印信息以及system.query都表明查询未命中Primary Key**，且查询性能和直接使用非子查询字面量差距很大。同时Server端出现报错日志，但Client端返回了正常结果。

触发Example：
```SQL
SELECT *
FROM ods.xdrs_logs_all
WHERE day BETWEEN toYYYYMMDD(toDate('2023-05-17')) AND toYYYYMMDD(toDate('2023-05-18'))
AND shop_id GLOBAL IN (
    SELECT '62416239773256001c03f83b' AS a
)
LIMIT 100
```

执行日志：
```
2023.06.07 15:46:17.798896 [ 69194 ] {5b76a4f2-fb28-48e9-bd07-5d9fb2808280} <Debug> ods.xdrs_logs_bak_local (SelectExecutor): Key condition: unknown, unknown, and, unknown, and, unknown, unknown, and, and

2023.06.07 15:46:17.798941 [ 69194 ] {5b76a4f2-fb28-48e9-bd07-5d9fb2808280} <Debug> ods.xdrs_logs_bak_local (SelectExecutor): MinMax index condition: (column 0 in [20230517, +inf)), (column 0 in (-inf, 20230518]), and, unknown, and, (column 0 in [20230517, +inf)), (column 0 in (-inf, 20230518]), and, and

2023.06.07 15:46:17.798981 [ 69194 ] {5b76a4f2-fb28-48e9-bd07-5d9fb2808280} <Debug> ods.xdrs_logs_bak_local (SelectExecutor): Selected 6 parts by date, 6 parts by key, 7183 marks to read from 6 ranges
```

**原因解析：**
原因暂时未知

**解决方案：**
方案一：将IN Subquery再嵌套一层子查询，在新的子查询中将索引字段检索出来
方案二：升级ClickHouse版本



### Code: 342

错误日志：Code: 342. DB::Exception: Existing table metadata in ZooKeeper differs in partition key expression. Stored in ZooKeeper: day, platform, local: day. (METADATA_MISMATCH) 

**版本：22.1.2.2**

**原因解析**：

1. 在ClickHouse release v20.10.3.30, 2020-10-28版本之后，默认的Database Engine被设置成了Atomic。在删除Atomic数据库的表时，默认会先进行标记删除，但zookeeper上节点路径依旧存在，需要等待`database_atomic_delay_before_drop_table_sec`时间之后，才会真正去。在Table未被真正删除之前，zookeeper中的元数据依旧存在，因此这期间创建不同结构的表时，则会抛出此错误。
   https://github.com/ClickHouse/ClickHouse/issues/12135
2. [官方链接](https://clickhouse.com/docs/en/engines/database-engines/atomic/#drop-detach-table)

**解决方案1**：
`DROP TABLE xqc_dim.xqc_shop_local ON CLUSTER cluster_3s_2r SYNC;`
**解决方案2**：
`DROP TABLE xqc_dim.xqc_shop_local ON CLUSTER cluster_3s_2r NO DELAY;`


### Code: 524

错误日志：Code: 524, e.displayText() = DB::Exception: Trying to ALTER RENAME key sid column which is a part of key expression (version 21.8.14.5 (official build)).


Columns specified in the key expression of the table (either with ORDER BY or PRIMARY KEY) cannot be renamed. Trying to change these columns will produce SQL Error [524].

https://clickhouse.com/docs/en/sql-reference/statements/alter/column#rename-column