# Apache Pulsar常用命令


## pulsar command-line tools

[官方链接](https://pulsar.apache.org/tools/)

## pulsar-admin

[官方链接](https://pulsar.apache.org/tools/pulsar-admin/)

### Tenant

1. Pulsar 创建 tenant: `bin/pulsar-admin tenants create <tenant>`，如"bin/pulsar-admin tenants create bigdata"

### Namespace

1. Pulsar 在 tenant 下创建 namespace: `bin/pulsar-admin namespaces create <tenant/namespace>`，如"bin/pulsar-admin namespaces create bigdata/data_cross"
2. Pulsar 查看指定 namespaces 下的 topic: `bin/pulsar-admin topics list <tenant>/<namespace>`，如"bin/pulsar-admin topics list bigdata/data_cross"

### Topics

1. Pulsar 在 namespace 下创建 partitioned and persistent topics: `bin/pulsar-admin topics create-partitioned-topic persistent://<tenant>/<namespace>/<topic> --partitions <partition_count>`，如"bin/pulsar-admin topics create-partitioned-topic persistent://bigdata/data_cross/mini_send_tb --partitions 4"


## pulsar-client

[官方链接](https://pulsar.apache.org/tools/pulsar-client/)


## 其他

1. **Pulsar Standalone的容器中手动清理snapshot和log:** 
- zookeeper手动清理日志命令格式: 
  `java -cp lib/zookeeper.jar:lib/slf4j-api-1.6.1.jar:lib/slf4j-log4j12-1.6.1.jar:lib/log4j-1.2.15.jar:conf org.apache.zookeeper.server.PurgeTxnLog <dataLogDir> <dataDir> -n <count>`
  其中 dataLogDir 和 dataDir 分别对应的是 zookeeper 配置文件中的对应配置项，count 参数表示留下的日志文件数量
- 如: 在 pulsar 容器下执行以下命令来手动清理 zookeeper 日志: `java -cp lib/org.apache.pulsar-pulsar-zookeeper-2.6.1.jar:lib/org.apache.zookeeper-zookeeper-jute-3.5.7.jar:lib/org.slf4j-slf4j-api-1.7.25.jar:lib/org.apache.logging.log4j-log4j-slf4j-impl-2.10.0.jar:lib/org.apache.logging.log4j-log4j-api-2.10.0.jar:lib/org.apache.logging.log4j-log4j-core-2.10.0.jar:conf org.apache.zookeeper.server.PurgeTxnLog data/standalone/zookeeper data/standalone/zookeeper zookeeper -n 3`


## 参考链接

1. [Apache Pulsar - Manage partitioned topics](https://pulsar.apache.org/docs/2.11.x/admin-api-topics/#manage-partitioned-topics)