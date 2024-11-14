# ClickHouse 数据冷备方案

简单来说，就是利用 ClickHouse 的自身特性，增加额外的 OSS 或者 HDFS 路径，作为存储磁盘，同时修改表 DDL，增加 TTL 到期迁移数据到指定 OSS 或 HDFS 磁盘的配置。

即将热数据，迁移到低成本的磁盘中存储，如固态硬盘 SSD 数据迁移到机械硬盘 HDD。

## 参考链接

1. [冷热数据分层存储--分析型云数据库 ClickHouse-帮助文档-京东云](https://docs.jdcloud.com/cn/jchdb/hot_on_coldstorage)
2. [如何使用HDFS进行数据冷热分离\_开源大数据平台 E-MapReduce(EMR)-阿里云帮助中心](https://help.aliyun.com/zh/emr/emr-on-ecs/user-guide/separate-hot-and-cold-data-by-using-hdfs?spm=a2c4g.11186623.help-menu-28066.d_2_1_9_4_0.2dd51492FbJaeH&scm=20140722.H_425428._.OR_help-V_1)
3. [如何使用OSS进行数据的冷热分离\_开源大数据平台 E-MapReduce(EMR)-阿里云帮助中心](https://help.aliyun.com/zh/emr/emr-on-ecs/user-guide/separate-hot-and-cold-data-by-using-oss?spm=a2c4g.11186623.help-menu-28066.d_2_1_9_4_1.5cd77746hfYFaS&scm=20140722.H_425429._.OR_help-V_1)
4. [ClickHouse冷热数据分离存储 - 云数据库 ClickHouse - 阿里云](https://www.alibabacloud.com/help/zh/clickhouse/user-guide/tiered-storage-of-hot-data-and-cold-data)