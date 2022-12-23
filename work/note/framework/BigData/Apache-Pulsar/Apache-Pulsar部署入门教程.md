# Apache Pulsar部署入门教程



## Docker

### StandAlone

```Shell
docker run -d \
--name pulsar-standalone \
-p 6650:6650 -p 8080:8080 \
--mount source=pulsardata,target=/pulsar/data \
--mount source=pulsarconf,target=/pulsar/conf \
registry-vpc.cn-zhangjiakou.aliyuncs.com/xiaoduoai/bigdata-pulsar:2.8.4 bin/pulsar standalone
```