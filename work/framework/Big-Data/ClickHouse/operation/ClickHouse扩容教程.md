# ClickHouse 扩容教程

## 扩容

https://blog.csdn.net/weixin_37692493/article/details/114003547

## 缩容

https://aop.pub/artical/database/clickhouse/cluster-scale/
PS: 缩容时，如果不删除 Zookeeper 中的 Replicate，可能会导致后续的 DDL 阻塞，因为需要等待 Zookeeper 选举出 Leader 节点。

https://clickhouse.com/docs/en/architecture/horizontal-scaling
https://clickhouse.com/company/events/scaling-clickhouse

https://blog.csdn.net/weixin_37692493/article/details/113975249


