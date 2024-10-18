# Apache Zookeeper命令行常用命令

**zookeeper日志手动清理命令:**

格式: `java -cp lib/zookeeper.jar:lib/slf4j-api-1.6.1.jar:lib/slf4j-log4j12-1.6.1.jar:lib/log4j-1.2.15.jar:conf org.apache.zookeeper.server.PurgeTxnLog <dataLogDir> <dataDir> -n <count>`, 其中dataLogDir和dataDir分别对应的是zookeeper配置文件中的对应配置项

如: 在pulsar容器下执行以下命令来手动清理zookeeper日志: `java -cp lib/org.apache.pulsar-pulsar-zookeeper-2.6.1.jar:lib/org.apache.zookeeper-zookeeper-jute-3.5.7.jar:lib/org.slf4j-slf4j-api-1.7.25.jar:lib/org.apache.logging.log4j-log4j-slf4j-impl-2.10.0.jar:lib/org.apache.logging.log4j-log4j-api-2.10.0.jar:lib/org.apache.logging.log4j-log4j-core-2.10.0.jar:conf org.apache.zookeeper.server.PurgeTxnLog data/standalone/zookeeper data/standalone/zookeeper zookeeper -n 3`