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


## Hive SQL 常见性能问题和排查方法


### 数据倾斜

数据倾斜是指在分布式计算时，大量相同的 key 被分发到同一个 reduce 节点中。针对某个 key 值的数据量比较多，会导致该节点的任务数据量远大于其他节点的平均数据量，运行时间远高于其他节点的平均运行时间，拖累了整体 SQL 执行时间。

### 数据膨胀

数据膨胀是指任务的输出条数/数据量级比输入条数/数据量级大很多，如 100M 的数据作为任务输入，最后输出 1T 的数据。这种情况不仅运行效率会降低，部分任务节点在运行 key 值量级过大时，有可能发生资源不足或失败情况。

## Hive SQL 性能问题常见优化方法

### 数据倾斜问题
#### Map 端优化

##### 小文件合并读取

提交 SQL 时，设置相应 InputFormat 参数，如 CombineInputFormat，将小文件读取合并后再创建对应的 Mapper 进行处理。

##### 列裁剪

按需取值，列裁剪

##### 谓词下推

谓词下推，提前过滤

##### 数据重分布

在 Map 阶段做聚合时，使用随机分布函数 `distribute by rand()`，控制 Map 端输出结果的分发，即 map 端如何拆分数据给 reduce 端（默认 hash 算法），打乱数据分布，至少不会在 Map 端发生数据倾斜。

##### 空值处理

部分实例发生长尾效应，很大程度上由于 null 值，空值导致，使得 Reduce 时含有脏值的数据被分发到同一台机器中。

针对这种问题 SQL，首先确认包含无效值的数据源表是否可以在 Map 阶段直接过滤掉这些异常数据；如果后续 SQL 逻辑仍然需要这些数据，可以通过将空值转变成随机值，既不影响关联也可以避免聚集。

#### Join 方法优化

`JOIN[shuffle]`

##### 大表 JOIN 小表


通过将需要 join 的小表分发至 map 端内存中，将 Join 操作提前至 map 端执行，避免因分发 key 值不均匀引发的长尾效应，复杂度从 `（M*N）` 降至 `（M+N）`，从而提高执行效率。ODPS SQL 与 Hive SQL 使用 mapjoin，SPARK 使用 broadcast。



#### Reduce 端优化

##### 全局排序优化

Order by 为全局排序，当表数据量过大时，性能可能会出现瓶颈；

Sort by 为局部排序，确保 Reduce 任务内结果有序，全局排序不保证；

Distribute by 按照指定字段进行 Hash 分片，把数据划分到不同的 Reducer 中；

CLUSTER BY：根据指定的字段进行分桶，并在桶内进行排序，可以认为 cluster by 是 distribute by+sort by。

对于排序而言，尝试用 distribute by+sort by 确保 reduce 中结果有序，最后在全局有序。

https://blog.csdn.net/lianghecai52171314/article/details/104658201



### 数据膨胀问题


#### 避免笛卡尔积


#### 关联 key 区基数校验


#### 聚合操作误用



## Hive On Spark


[Cloudera Enterprise 6.3.x Documentation - Tuning Apache Hive on Spark in CDH](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/admin_hos_tuning.html#hos_tuning)
[【尚硅谷大数据技术 Hive On Spark 调优（离线数仓项目实战）-哔哩哔哩】](https://b23.tv/f2mPHla)

## 参考链接
1. [微信-大数据技术团队-Hive SQL 高级进阶 10 大技巧](https://mp.weixin.qq.com/s/AKXXfbGBqndv6Fe1yjHryA)
2. [微信-数据仓库与Python大数据-Hive | 4 万字性能调优全面总结（推荐收藏）](https://mp.weixin.qq.com/s/9BCFrUqtDsrf7w8ipRow0Q)
3. [微信-阿里云云栖号-大数据 SQL 数据倾斜与数据膨胀的优化与经验总结](https://mp.weixin.qq.com/s/0N0ZFFIZtQLp7CBBWuh_pQ)
4. [【尚硅谷大数据技术 Hive On Spark 调优（离线数仓项目实战）-哔哩哔哩】](https://b23.tv/f2mPHla)

