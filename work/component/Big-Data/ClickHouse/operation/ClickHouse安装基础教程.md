# ClickHouse 安装基础教程


## 拉取 CH 镜像

PS：由于本次平台搭建所在的线上环境无法直接拉取 Docker 公共仓库的 image，因此需要在线下拉取然后导出导入的方式实现 image 的加载

```shell
# 线下pull镜像
docker pull yandex/clickhouse-server:21.8.3
# 保存镜像为tar文件
docker save yandex/clickhouse-server:21.8.3 -o clickhouse-server-21.8.3-image.tar
# 压缩镜像文件
zip clickhouse-server-21.8.3-image.tar.zip clickhouse-server-21.8.3-image.tar
# 上传镜像文件到线上环境
...
# 解压镜像文件
unzip clickhouse-server-21.8.3-image.tar.zip
# 从文件中加载镜像
docker load -i clickhouse-server-21.8.3-image.tar
# 恢复镜像标签
docker tag <image_id> yandex/clickhouse-server:21.8.3
```


## 配置 CH 集群

### 节点列表


| host                                      | ip            | CH       |          |
|-------------------------------------------|---------------|----------|----------|
| znzjk-133213-prod-mini-bigdata-clickhouse | 10.22.133.213 | ch-s1-r1 | ch-s2-r2 |
| znzjk-133214-prod-mini-bigdata-clickhouse | 10.22.133.214 | ch-s2-r1 | ch-s1-r2 |


### Zookeeper 集群

PS：由于本次实验中，某个 ZK 节点上的 2181 端口已经占用，因此本次实验采用的是 2182 端口，其中 CH 的配置文件 config. Xml 中 ZK 的客户端端口也作出了相应调整

| host                                      | ip            | ZK client port |
| ----------------------------------------- | ------------- | -------------- |
| znzjk-133213-prod-mini-bigdata-clickhouse | 10.22.133.213 | 2182           |
| znzjk-133214-prod-mini-bigdata-clickhouse | 10.22.133.214 | 2182           |
| znzjk-133215-prod-mini-bigdata-cdh        | 10.22.133.215 | 2182           | 


### ch-s1-r1