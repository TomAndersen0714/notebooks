# ClickHouse 实时数仓搭建方案

## 实时任务


### 实时处理



### 实时查询


## 方案一：Materialized View

Buffer+Distribued+ReplicatedMergeTree+MaterializedView+ReplicatedMergeTree

internal_replication=true

待验证：待确定MaterializedView是否能够捕获到ReplicatedMergeTree副本之间的复制动作，如果捕获到了则方案无效，会出现数据重复，如果无法捕获，则方案正确

PS：如果要删除或者修正，则需要以增代删


## 方案二：Projection

和方案一的 Materialized View 类似，也是利用预计算的思路来提高查询效率。

Projection 本质上也是一种索引，能够加速特定模式下的查询。



## 参考链接
1. https://github.com/ClickHouse/ClickHouse/issues/8999
2. [基于ClickHouse的百亿级广告平台实时数仓构建实战](https://www.modb.pro/db/391882)
