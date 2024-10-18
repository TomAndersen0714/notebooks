# Zookeeper 安装基础教程

## 拉取 ZK 镜像

PS：由于本次平台搭建所在的线上环境无法直接拉取 Docker 公共仓库的 image，因此需要在线下拉取然后导出导入的方式实现 image 的加载

```bash
# 线下pull镜像
docker pull zookeeper:3.7.0
# 保存镜像为tar文件
docker save zookeeper: 3.7.0 -o zookeeper-3.7.0-image.tar
# 压缩镜像文件
zip zookeeper-3.7.0-image.tar.zip zookeeper-3.7.0-image.tar
# 上传镜像文件到线上环境
...
# 解压镜像文件
unzip zookeeper-3.7.0-image.tar.zip
# 从文件中加载镜像
docker load -i zookeeper-3.7.0-image.tar
```

## 配置 ZK 集群

### 节点列表

|                   host                    |      ip       |  container  |
|:-----------------------------------------:|:-------------:|:-----------:|
| znzjk-133213-prod-mini-bigdata-clickhouse | 10.22.133.213 | zookeeper_1 |
| znzjk-133214-prod-mini-bigdata-clickhouse | 10.22.133.214 | zookeeper_2 |
|    znzjk-133215-prod-mini-bigdata-cdh     | 10.22.133.215 | zookeeper_3 |

### ZK1

#### 创建容器数据卷对应宿主机路径（root 用户）

```bash
#!/bin/bash
mkdir -p /data/zookeeper/conf/ /etc/zookeeper/
```

#### 创建配置文件 zoo.cfg

`在/data/zookeeper/conf/路径下创建配置文件zoo.cfg，内容同上，同时需要修改读写权限`

```bash
vim /data/zookeeper/conf/zoo.cfg
chmod 666 /data/zookeeper/conf/zoo.cfg
```

```xml
# The number of milliseconds of each tick
tickTime=2000
# The number of ticks that the initial 
# synchronization phase can take
initLimit=10
# The number of ticks that can pass between 
# sending a request and getting an acknowledgement
syncLimit=5
# the directory where the snapshot is stored.
# do not use /tmp for storage, /tmp here is just 
# example sakes.
#dataDir=/tmp/zookeeper
dataDir=/data

# dataLogDir : (No Java system property) This option will direct the machine to write the transaction log to the dataLogDir
# rather than the dataDir. This allows a dedicated log device to be used, and helps avoid competition between logging and snapshots.
dataLogDir=/datalog

# the port at which the clients will connect
clientPort=2181

# The port the embedded Jetty server listens on. Defaults to 8080
admin.serverPort=8081

# the maximum number of client connections.
# increase this if you need to handle more clients
#maxClientCnxns=60
#
# Be sure to read the maintenance section of the 
# administrator guide before turning on autopurge.
#
# http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
#
# The number of snapshots to retain in dataDir
autopurge.snapRetainCount=5
# Purge task interval in hours
# Set to "0" to disable auto purge feature
autopurge.purgeInterval=24

# The zookeeper cluster setting
#server.<myid>=<host>:<port1>:<prot2>
server.1=znzjk-133213-prod-mini-bigdata-clickhouse:2888:3888
server.2=znzjk-133214-prod-mini-bigdata-clickhouse:2888:3888
server.3=znzjk-133215-prod-mini-bigdata-cdh:2888:3888
```

#### 创建日志配置文件log4j.properties

`在/data/zookeeper/conf/路径下创建配置文件log4j.properties，同时需要修改读写权限`

```bash
vim /data/zookeeper/conf/log4j.properties
chmod 666 /data/zookeeper/conf/log4j.properties
```

```xml
log4j.rootLogger=INFO,INFO_LOG,ERROR_LOG
log4j.appender.INFO_LOG=org.apache.log4j.RollingFileAppender 
log4j.appender.INFO_LOG.File=/logs/zookeeper.log
log4j.appender.INFO_LOG.Encoding=UTF-8
log4j.appender.INFO_LOG.MaxFileSize=16MB
log4j.appender.INFO_LOG.MaxBackupIndex=8
log4j.appender.INFO_LOG.layout=org.apache.log4j.PatternLayout 
log4j.appender.INFO_LOG.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} [%t] [%c] [%p] - %m%n

log4j.appender.ERROR_LOG = org.apache.log4j.RollingFileAppender
log4j.appender.ERROR_LOG.Threshold=WARN
log4j.appender.ERROR_LOG.File=/logs/zookeeper.error.log
log4j.appender.ERROR_LOG.Encoding=UTF-8
log4j.appender.ERROR_LOG.MaxFileSize=16MB
log4j.appender.ERROR_LOG.MaxBackupIndex=8
log4j.appender.ERROR_LOG.layout=org.apache.log4j.PatternLayout 
log4j.appender.ERROR_LOG.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} [%t] [%c] [%p] - %m%n
```

#### 创建 zk 容器

PS：本次创建 Docker 容器，更好地实现隔离，使用的是 Docker 的默认网络模式，即网桥，因此需要手动设置域名映射（`--add-host=<host>:<ip>`）
PS：由于本次实验中某节点上已使用 2181 端口，因此本次实验中，Docker ZK 容器的 Client 端口 2181 端口映射为主机的 2182 端口

```bash
vim /etc/zookeeper/zk1_start.sh
chmod 744 /etc/zookeeper/zk1_start.sh
/etc/zookeeper/zk1_start.sh
```

PS：使用 Docker 容器的网桥模式创建集群，缺点在于 hosts 无法实现动态增加，需要事先固定好

```bash
#!/bin/bash
docker run -d \
--name=zookeeper_1 \
--hostname=znzjk-133213-prod-mini-bigdata-clickhouse-zookeeper_1  \
--env=ZOO_MY_ID=1 \
--volume=/data/zookeeper/data/:/data/ \
--volume=/data/zookeeper/datalog/:/datalog/ \
--volume=/data/zookeeper/logs/:/logs/ \
--volume=/data/zookeeper/conf/:/conf/ \
--add-host=znzjk-133213-prod-mini-bigdata-clickhouse:10.22.133.213 \
--add-host=znzjk-133214-prod-mini-bigdata-clickhouse:10.22.133.214 \
--add-host=znzjk-133215-prod-mini-bigdata-cdh:10.22.133.215 \
--env=JVMFLAGS="-Xms1024m -Xmx2048m" \
--env=ZOO_LOG4J_PROP="INFO,ROLLINGFILE" \
--restart=always \
-p 2182:2181 \
-p 2888:2888 \
-p 3888:3888 \
-p 8081:8081 \
zookeeper:3.7.0
```

### ZK2

#### 创建容器数据卷对应宿主机路径

同上
#### 创建配置文件

同上
#### 创建日志配置文件

同上

#### 创建 zk 容器

```bash
vim /etc/zookeeper/zk2_start.sh
chmod 744 /etc/zookeeper/zk2_start.sh
/etc/zookeeper/zk2_start.sh
```

```bash
#!/bin/bash
docker run -d \
--name=zookeeper_2 \
--hostname=znzjk-133214-prod-mini-bigdata-clickhouse-zookeeper_2  \
--env=ZOO_MY_ID=2 \
--volume=/data/zookeeper/data/:/data/ \
--volume=/data/zookeeper/datalog/:/datalog/ \
--volume=/data/zookeeper/logs/:/logs/ \
--volume=/data/zookeeper/conf/:/conf/ \
--add-host=znzjk-133213-prod-mini-bigdata-clickhouse:10.22.133.213 \
--add-host=znzjk-133214-prod-mini-bigdata-clickhouse:10.22.133.214 \
--add-host=znzjk-133215-prod-mini-bigdata-cdh:10.22.133.215 \
--env=JVMFLAGS="-Xms1024m -Xmx2048m" \
--env=ZOO_LOG4J_PROP="INFO,ROLLINGFILE" \
--restart=always \
-p 2182:2181 \
-p 2888:2888 \
-p 3888:3888 \
-p 8081:8081 \
zookeeper:3.7.0
```

### ZK3

#### 创建容器数据卷对应宿主机路径

同上
#### 创建配置文件

同上
#### 创建日志配置文件

同上

#### 创建 zk 容器

```bash
vim /etc/zookeeper/zk3_start.sh
chmod 744 /etc/zookeeper/zk3_start.sh
/etc/zookeeper/zk3_start.sh
```

```bash
#!/bin/bash
docker run -d \
--name=zookeeper_3 \
--hostname=znzjk-133215-prod-mini-bigdata-cdh-zookeeper_3 \
--env=ZOO_MY_ID=3 \
--volume=/data/zookeeper/data/:/data/ \
--volume=/data/zookeeper/datalog/:/datalog/ \
--volume=/data/zookeeper/logs/:/logs/ \
--volume=/data/zookeeper/conf/:/conf/ \
--add-host=znzjk-133213-prod-mini-bigdata-clickhouse:10.22.133.213 \
--add-host=znzjk-133214-prod-mini-bigdata-clickhouse:10.22.133.214 \
--add-host=znzjk-133215-prod-mini-bigdata-cdh:10.22.133.215 \
--env=JVMFLAGS="-Xms1024m -Xmx2048m" \
--env=ZOO_LOG4J_PROP="INFO,ROLLINGFILE" \
--restart=always \
-p 2182:2181 \
-p 2888:2888 \
-p 3888:3888 \
-p 8081:8081 \
zookeeper:3.7.0
```

## 检查 zkServer 集群状态

```bash
# 获取zkServer状态(角色)
docker exec -it zookeeper_1 /apache-zookeeper-3.7.0-bin/bin/zkServer.sh status
docker exec -it zookeeper_2 /apache-zookeeper-3.7.0-bin/bin/zkServer.sh status
docker exec -it zookeeper_3 /apache-zookeeper-3.7.0-bin/bin/zkServer.sh status

# demo
$ docker exec -it 7c1b979d0570 /apache-zookeeper-3.7.0-bin/bin/zkServer.sh status
ZooKeeper JMX enabled by default
Using config: /conf/zoo.cfg
Client port found: 2181. Client address: localhost. Client SSL: false.
Mode: follower
```

## 常见问题

Zookeeper Server 必须按照 myId 从小到大的顺序启动，如果顺序错乱则会抛出类似信息：`[org.apache.zookeeper.server.quorum.QuorumCnxManager] [INFO] - Have smaller server identifier, so dropping the connection: (myId:1 --> sid:2)`

- 解决方案 1：找到 leader 节点，重启 leader 节点的 zookeeper
- 解决方案 2：按照 my_id 顺序依次重启所有 zookeeper

## End