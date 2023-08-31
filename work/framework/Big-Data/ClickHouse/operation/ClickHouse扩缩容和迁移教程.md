# ClickHouse 扩缩容和迁移教程


## 扩容

https://blog.csdn.net/weixin_37692493/article/details/114003547

### 增加分片


### 增加副本

操作步骤：
1. 在扩容副本节点中修改配置，将集群配置中添加当前副本节点
2. 启动扩容副本节点节点，并创建相关复制表（此时该副本节点查询请求可正常路由选择所有的副本节点，但原副本节点配置文件未刷新，只能路由选择原有副本节点）
3. 原副本节点修改配置文件，将集群配置中添加新增副本节点信息

## 缩容

https://aop.pub/artical/database/clickhouse/cluster-scale/
PS: 缩容时，如果不删除 Zookeeper 中的 Replicate，可能会导致后续的 DDL 阻塞，因为需要等待 Zookeeper 选举出 Leader 节点。

https://clickhouse.com/docs/en/architecture/horizontal-scaling
https://clickhouse.com/company/events/scaling-clickhouse

https://blog.csdn.net/weixin_37692493/article/details/113975249


## 迁移

### 迁移副本

将某个副本节点停止，并将原副本节点上的非 Replicated Engine 的数据库中的表、以及其他数据库中的非 Replicated 表的 metadata 文件复制到新节点上，保存在相同路径下，下线旧节点，并启动新节点。

在新节点复制完成之前（一般不需要等到全部完成，大概等到 70%左右），ClickHouse 仅能提供查询和写入服务，无法执行 Distributed DDL 的命令。


操作步骤：
1. 修改原集群中的 `remote_servers` 配置，将待迁移副本踢出
2. 停止待迁移副本
3. 在新节点上，准备好和待迁移副本相同的配置文件，修改 `remote_servers`，将待迁移副本的相关参数修改为新节点
4. 启动新节点 Server，开启复制。复制开启后一段时间内，整个集群的 Distributed DDL 会阻塞，直到复制完成大部分内容。


Question：
1. Distributed DDL 中是否有相关配置，需要等待所有 Replicated 都执行完成，才能返回结果??？