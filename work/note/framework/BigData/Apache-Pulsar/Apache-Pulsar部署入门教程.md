# Apache Pulsar部署入门教程



## Docker部署

官方链接: [Set up a standalone Pulsar in Docker](https://pulsar.apache.org/docs/2.10.x/getting-started-docker/)

```Shell
docker run -d \
--name pulsar-standalone \
-p 6650:6650  \
-p 8080:8080 \
--mount source=pulsardata,target=/pulsar/data \
--mount source=pulsarconf,target=/pulsar/conf \
apachepulsar/pulsar:2.8.4 \
bin/pulsar standalone
```





## 本地部署

官方链接: [Set up a standalone Pulsar locally](https://pulsar.apache.org/docs/2.10.x/getting-started-standalone/)





## 参考链接

1. [Set up a standalone Pulsar in Docker](https://pulsar.apache.org/docs/2.10.x/getting-started-docker/)
2. [Set up a standalone Pulsar locally](https://pulsar.apache.org/docs/2.10.x/getting-started-standalone/)