# Apache Hive SQL 性能优化基础教程


## 什么样的 SQL 需要性能优化


SQL 性能评估维度：
1. 客户端 VS 服务端
2. 服务质量 QoS VS 资源开销
3. 慢查询 SQL VS 高负载查询 SQL 

SQL 性能评估指标：
1. 客户端评估指标：查询响应时间
2. 服务端评估指标：CPU、内存、磁盘 IOPS、磁盘吞吐、网络带宽等

## 为什么 SQL 查询会存在性能问题


从宏观上来说，一般情况下，可以把一个 SQL 查询看做是一个由多个 subtask 组成的一个 task。如果 SQL 执行性能很差，必然是查询的任务总量（$T_n = \sum_{i=1}^{n} task_i$）太大，即要么是 subtask 数量太多，要么是某些 subtask 的执行时间太长，故 SQL 查询的优化方向也是从这两个方面着手。

## 常见性能问题和诊断方法

### 数据倾斜

数据倾斜是指在分布式计算时，大量相同的 key 对应的数据被分发到同一个 reduce 节点中，导致该节点的任务数据量远大于其他节点的平均数据量，运行时间远高于其他节点的平均运行时间，拖累了整体 SQL 查询的执行时间。

### 数据膨胀

数据膨胀是指任务的输出条数/数据量级比输入条数/数据量级大很多，如 100M 的数据作为任务输入，最后输出 1T 的数据。这种情况不仅运行效率会降低，部分任务节点在运行 key 值量级过大时，有可能发生资源不足或失败情况。

## Hive SQL 性能问题诊断方法


### Hive on MapReduce

**数据倾斜问题诊断和定位**

Hive SQL on MapReduce 任务如何诊断和定位数据倾斜
1. 查看 Application Master 或者 JobHistory 页面
	1. JobServer 中点击 Task Type 下的 map 或 reduce，即可跳转到对应的 Task 耗时页面
	2. Application Master 中点击 Map 或 Reduce 对应的数量，即可跳转到对应的 Task 耗时页面
	3. 通过耗时页面倒序，可以看到是否存在数据倾斜
2. 通过 Explain 查看 Hive SQL 对应的执行计划
3. 查看 Application Name 或者 Job Name 中的 stage ID（如 stage-12）
4. 通过 Stage ID 去检索执行计划，即可定位到事故对应执行计划
5. 通过执行计划中引用的表名，检索原始 SQL 代码，然后即可定位到事故代码段


### Hive on Spark


[Cloudera Enterprise 6.3.x Documentation - Tuning Apache Hive on Spark in CDH](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/admin_hos_tuning.html#hos_tuning)
[【尚硅谷大数据技术 Hive On Spark 调优（离线数仓项目实战）-哔哩哔哩】](https://b23.tv/f2mPHla)

## Hive SQL 优化常用配置

[HiveSQL性能优化常用配置](work/component/Big-Data/Apache-Hive/Hive-SQL/HiveSQL性能优化常用配置.md)


## Hive SQL 常见性能问题优化方法


### 数据倾斜问题

#### Map 端优化

##### 列裁剪-按需取值

按需取值，列裁剪。减少使用 `select * from table` 语句，过多选择无用列会增加数据在集群上传输的 IO 开销。

##### 行裁剪-谓词下推

谓词下推，提前过滤

##### 空值处理

部分实例发生长尾效应，很大程度上由于 null 值，空值导致，使得 Reduce 时含有脏值的数据被分发到同一台机器中。

针对这种问题 SQL，首先确认包含无效值的数据源表是否可以在 Map 阶段直接过滤掉这些异常数据；如果后续 SQL 逻辑仍然需要这些数据，可以通过将空值转变成随机值，既不影响关联也可以避免聚集。

##### 小文件合并读取

提交 SQL 时，设置相应 InputFormat 参数，使用特定的 InputFormat 类来进行文件读取，如 CombineInputFormat，将小文件读取合并后再创建对应的 Mapper 处理数据，即减少 Map 数量。

##### 随机数据分布

方法一：
在 Map 阶段时，可以使用随机分布函数 `distribute by rand()`，控制 Map 端输出结果的分发策略，即 Map 端如何拆分数据给 Reduce 端（默认基于针对列使用 Hash 算法的计算结果），打乱数据分布，至少不会在 Map 端就开始出现数据倾斜。

方法二：
在 Map 阶段时，针对 Reduce Key 增加随机数前缀（加盐），进行初步聚合之后，再去前缀（去盐），最后再进行聚合。

#### Join 端优化


##### 小表缓存


通过将需要 join 的小表分发至 map 端内存中，将 Join 操作提前至 map 端执行，避免因分发 key 值不均匀引发的长尾效应，复杂度从 `M*N` 降至约为 `M`，从而提高执行效率。

ODPS SQL 与 Hive SQL 使用 map join（`set hive.auto.convert.join=true`），Spark 中则可以使用 broadcast 广播变量。

The default value for `hive.auto.convert.join` was false in Hive 0.10.0.  Hive 0.11.0 changed the default to true (HIVE-3297). 

##### 大表手动分桶

手动 Hash 分区，将大表转换为对应的小表，分批进行 JOIN。

#### Reduce 端优化

Order by：全局排序，最后会使用单个 Reducer 来实现全局排序，当表数据量过大时，便会出现数据倾斜，产生数据瓶颈；

Sort by：局部排序，支持使用多个 Reducer 来，确保 Reducer 任务内结果有序，不保证全局有序；

Distribute by：控制 map 的输出在 reducer 是如何划分的，支持指定分区键，并依据分区键将数据划分到不同的 Reducer 中，distribute by 必须要写在 sort by 之前；

Cluster by：根据指定的字段进行分桶，并在桶内进行排序，可以认为 cluster by 是 distribute by+sort by，但仅支持升序排序。


https://blog.csdn.net/lianghecai52171314/article/details/104658201

##### 按需选择局部排序

如果需求中，要求结果是局部排序，如：按照部门排序，则可以使用 ` Distribute by` 加上 `Sort by`，而避免使用 `Order By` 全局排序（只会使用单个 Reducer 来处理最终结果），造成 Reducer 数据倾斜。

##### 全局排序转换为局部排序

对于需要全局排序的整型字段，可以通过截取整型的高位数值作为分桶的标识，然后计算每个桶中的数据量，并按照桶的编号进行排序累加，获得桶中整型数据的偏移量，避免了全局排序的数据倾斜问题。

然后再按照桶编号进行分区排序，在每个桶中计算，整型在桶中的排序后的序号，最后根据桶的全局偏移量和桶中排序序号，求和得到整型字段的全局排序序号。


##### Distinct VS Group By

[知乎- hive的distinct与group by的区别是什么？](https://www.zhihu.com/question/328860878)

简单来说，两者在性能方面并没有区别，具体可通过 explain 查看 distinct 语句，观察 Hive 是否有将 Distinct 的执行计划优化为 group by operator。

### 数据膨胀问题


#### 避免笛卡尔积


#### JOIN KEY 基数校验

关注 Join Key 的基数，Key 值的基数越少（即 distinct 数量少），越有可能造成数据爆炸情况。如用户表下的性别列、交易事务表下的省市列等。

#### 复杂查询拆解


## 参考链接
1. [微信-大数据技术团队-Hive SQL 高级进阶 10 大技巧](https://mp.weixin.qq.com/s/AKXXfbGBqndv6Fe1yjHryA)
2. [微信-数据仓库与Python大数据-Hive | 4 万字性能调优全面总结（推荐收藏）](https://mp.weixin.qq.com/s/9BCFrUqtDsrf7w8ipRow0Q)
3. [微信-阿里云云栖号-大数据 SQL 数据倾斜与数据膨胀的优化与经验总结](https://mp.weixin.qq.com/s/0N0ZFFIZtQLp7CBBWuh_pQ)
4. [【尚硅谷大数据技术 Hive On Spark 调优（离线数仓项目实战）-哔哩哔哩】](https://b23.tv/f2mPHla)
5. [MySQL-SQL查询性能优化教程](work/component/Back-End/MySQL/solution/MySQL-SQL查询性能优化教程.md)
6. [Apache Hive - LanguageManual JoinOptimization](https://cwiki.apache.org/confluence/display/hive/languagemanual+joinoptimization)

