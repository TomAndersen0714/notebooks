# Apache Hive SQL 性能优化教程


## 什么样的 SQL 需要性能优化

[MySQL-SQL查询性能优化教程](work/component/Back-End/MySQL/solution/MySQL-SQL查询性能优化教程.md)


SQL 性能评估维度：
1. 客户端 VS 服务端
2. 服务质量 QoS VS 资源开销
3. 慢查询 SQL VS 高负载查询 SQL 


SQL 性能评估指标： 
1. 客户端评估指标：查询响应时间
2. 服务端评估指标：CPU、内存、磁盘 IOPS、磁盘吞吐、网络带宽等


## 为什么 SQL 查询会存在性能问题


从宏观上来说，一般情况下，可以把一个 SQL 查询看做是一个由多个 subtask 组成的一个 task。如果 SQL 执行性能很差，必然是查询的任务总量（$T_n = \sum_{i=1}^{n} task_i$）太大，即要么是 subtask 数量太多，要么是某些 subtask 的执行时间太长，故 SQL 查询的优化方向也是从这两个方面着手。



## Hive SQL 常见性能问题


### 数据倾斜


### 数据膨胀


## Hive SQL 性能问题排查定位方法



## Hive SQL 性能问题常见优化方法


### Map 端优化

#### 按需取值



### Join 方法优化

`JOIN[shuffle]`


### Reduce 端优化


#### 空值处理


#### 排序优化

Order By、Sort By、Distribute By、Cluster By

Sort By、Order By
Distribute By、Cluster By

https://blog.csdn.net/lianghecai52171314/article/details/104658201


## Hive On Spark


[Cloudera Enterprise 6.3.x Documentation - Tuning Apache Hive on Spark in CDH](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/admin_hos_tuning.html#hos_tuning)
[【尚硅谷大数据技术 Hive On Spark 调优（离线数仓项目实战）-哔哩哔哩】](https://b23.tv/f2mPHla)

## 参考链接
1. [微信-大数据技术团队-Hive SQL 高级进阶 10 大技巧](https://mp.weixin.qq.com/s/AKXXfbGBqndv6Fe1yjHryA)
2. [微信-数据仓库与Python大数据-Hive | 4 万字性能调优全面总结（推荐收藏）](https://mp.weixin.qq.com/s/9BCFrUqtDsrf7w8ipRow0Q)
3. [微信-阿里云云栖号-大数据 SQL 数据倾斜与数据膨胀的优化与经验总结](https://mp.weixin.qq.com/s/0N0ZFFIZtQLp7CBBWuh_pQ)
4. [【尚硅谷大数据技术 Hive On Spark 调优（离线数仓项目实战）-哔哩哔哩】](https://b23.tv/f2mPHla)

