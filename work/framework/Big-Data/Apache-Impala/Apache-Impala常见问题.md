# Apache Impala常见报错



## 报错信息

1. **RemoteException: Operation category READ is not supported in state standby. Visit https://s.apache.org/sbnn-error** ：
	1. **报错原因**：
		1. Impala的HDFS Location指向的NameNode节点状态为standby，无法提供数据查询服务。一般是由于HDFS HA环境下，NameNode active节点发生切换，但Impala表的元数据中Location指向的是固定节点
	2. **解决方案**：
		1. 检查对应数据表的Location是否设置成了支持HA的URL，如果没有则需要进行修改。如果表的数量较少，可以通过在Impala上执行SQL来修改，如“ALTER TABLE test.t1 SET LOCATION 'hdfs://nameservice1/user/hive/warehouse/test.db/t1'”；如果表的数量角度，则需要去Hive对应的MetaStore数据库中修改，将对应的Location字段进行替换。[参考链接](https://www.cnblogs.com/wuning/p/11698961.html?ivk_sa=1024320u)
2. **Cloudera Manager页面Impala告警“There are 0 (Beeswax pool) 64 (Hive Server 2 pool) active client”**
	1. **报错原因**：
		1. Impala的HiveServer2连接池中连接数耗尽，导致无法新建客户端连接，提交查询
	2. **解决方案**：
		1. Go to CM -> Impala -> Charts Library（图表库） -> check "Active Frontend API connection（活动的前端API连接）"，查看connection连接的使用情况，排查异常活动。如果是连接数一直很高，可以尝试增加“fe_service_threads（Impala Daemon Max Client Connections）”参数，增加连接池大小，或者设置Impala Daemon的“idle_session_timeout”参数，回收超时的闲置连接。[参考链接](https://community.cloudera.com/t5/Support-Questions/Impala-Concurrent-Client-Connections/m-p/298038)
3. **Impala Kudu创建表时候报错“CAUSED BY: NonRecoverableException: the requested number of tablet replicas is over the maximum permitted at creation time (180), additional tablets may be added by adding range partitions to the table post-creation”**
	1. **报错原因**：Impala中创建Kudu引擎表时，显式指定的分区数量过多
	2. **解决方案**：减少建表时，显式指定的分区数量
