# Apache Pulsar常用命令



## pulsar command-line tools

[官方链接](https://pulsar.apache.org/tools/)

 

## pulsar-client

[官方链接](https://pulsar.apache.org/tools/pulsar-client/)



## pulsar-admin

[官方链接](https://pulsar.apache.org/tools/pulsar-admin/)

1. Pulsar创建tenant: 

     `bin/pulsar-admin tenants create bigdata`

2. Pulsar创建namespaces: 

     `bin/pulsar-admin namespaces create bigdata/data_cross`

3. Pulsar创建partitioned topics: 

     `bin/pulsar-admin topics create-partitioned-topic persistent://bigdata/data_cross/mini_send_tb --partitions 4`

4. Pulsar查看指定namespaces下的topic: 

     `bin/pulsar-admin topics list bigdata/data_cross`



**Pulsar Standalone的容器环境下手动清理snapshot和log:** 

- zookeeper手动清理日志命令格式: 

  `java -cp lib/zookeeper.jar:lib/slf4j-api-1.6.1.jar:lib/slf4j-log4j12-1.6.1.jar:lib/log4j-1.2.15.jar:conf org.apache.zookeeper.server.PurgeTxnLog <dataLogDir> <dataDir> -n <count>`, 其中dataLogDir和dataDir分别对应的是zookeeper配置文件中的对应配置项

  如: 在pulsar容器下执行以下命令来手动清理zookeeper日志: `java -cp lib/org.apache.pulsar-pulsar-zookeeper-2.6.1.jar:lib/org.apache.zookeeper-zookeeper-jute-3.5.7.jar:lib/org.slf4j-slf4j-api-1.7.25.jar:lib/org.apache.logging.log4j-log4j-slf4j-impl-2.10.0.jar:lib/org.apache.logging.log4j-log4j-api-2.10.0.jar:lib/org.apache.logging.log4j-log4j-core-2.10.0.jar:conf org.apache.zookeeper.server.PurgeTxnLog data/standalone/zookeeper data/standalone/zookeeper zookeeper -n 3`