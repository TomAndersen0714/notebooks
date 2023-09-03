# Apache Zookeeper 的优缺点



## 优点

### Apache Zookeeper 提供了强一致性服务



## 缺点


### Apache Zookeeper 存在单点故障问题


Apache Kafka、ClickHouse 等等大数据分布式组件，都开始逐步脱离对 Zookeeper 的依赖。

Apache Zookeeper 存在单点故障问题。因为 Apache Zookeeper 虽然是分布式应用，但其架构设计中，每次只有某单个节点会被选举成为 Leader，而其他节点则为 Follower。这个 Leader 服务，负责协调所有的写操作和数据变更，且是单节点服务，如果它发生故障，整个 ZooKeeper 集群将无法执行写操作，从而导致出现单点故障问题。

随着 Apache Zookeeper 读写负载不断增加，Apache Zookeeper 的单节点 Leader 会首逐渐成为性能瓶颈，进而需要随着负载的增加，而不断进行纵向扩展（scale up）。