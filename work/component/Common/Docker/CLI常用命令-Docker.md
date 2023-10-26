# Docker CLI常用命令


## Container

### View

#### docker info

#### docker ps

#### docker top

#### docker stats

#### docker port


### Manage

#### docker login

#### docker exec


### Modify

#### docker update


### Create

#### docker run


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

## Image

### View

#### docker images


## Network


### View

#### docker network ls

#### Docker network inspect


### Create

#### docker network create


## 其他

1. root赋予指定用户docker组权限
```
sudo usermod -aG docker <username>
```

## 参考链接
1. [Docker - Use the Docker command line](https://docs.docker.com/engine/reference/commandline/cli/)