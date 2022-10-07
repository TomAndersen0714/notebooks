# 基于ClickHouse的近实时数据更新方案

## 前言

众所周知，主攻OLAP场景的数据库引擎一般都会采用某种列式存储格式，以支撑其强大的数据处理性能，从而导致无法兼顾行级事务，以及频繁的数据实时更新操作。如ROLAP中的Hive、Impala、Presto、ClickHouse，以及MOLAP中的Druid、Kylin，等等。

虽然OLAP引擎中不少能够通过挂载外表，接入外部数据库引擎来弥补自身的读、写或存储能力缺陷，但由于对接外部数据库时存在的SQL转译、索引命中、谓词下推、数据序列化、类型转换等等问题的存在，外接数据库必定意味着相比于原生数据库，具备更低的性能和更高的资源开销。如果与外部数据源功能对接得不够完善，那么在大批量数据处理或者较高并发的场景下，常常会变得捉襟见肘，十分尴尬。

ClickHouse作为近几年的主流OLAP型数据库，其本身也是基于MPP(Massively Parallel Processing)架构实现，具备强大的数据并行处理能力，但其最初的版本并不具备数据行级更新的能力，虽然后续迭代的版本中新增了Mutation类操作来支持行级更新和删除，但这种操作是异步、非事务型操作且性能较差，不支持频繁使用，且无法适用于高并发的实时更新场景。

而本文的主要内容是，基于ClickHouse的原生特性，调研和总结近实时数据更新方案。

本文实验中采用的ClickHouse版本为`20.4.2.9`。

## 行级近实时更新

### 1. order by + limit by

PS：为了避免更新频率过高，而导致表数据量膨胀，而使得查询性能下降，建议和ReplacingMergeTree、CollapsingMergeTree等支持后台自动合并的表引擎一起使用，以减少查询时扫描的数据量



### 2. argMax、argMin



### 3. ReplacingMergeTree



PS：Order By表达式必须以Primary Key表达式为前缀，即索引KVs的顺序，必须和数据的存储顺序一致



### 4. (Versioned)CollapsingMergeTree



PS：Order By表达式必须以Primary Key表达式为前缀，即索引KVs的存储顺序，必须和数据的存储顺序严格一致

PS：CollapsingMergeTree在写入时，必须要保证sign等于1的先写入，sign等于-1的后写入，如果写入顺序错误，则合并数据时无法实现正确抵消



### 5. AggregatingMergeTree Or SummingMergeTree

数据立方体



### 6. group by



## 字段级近实时更新：

### 1. Mutation







## 其他方案

### 1. 拉链表





## 参考链接

1. [ClickHouse Document-Alter-mutations](https://clickhouse.com/docs/en/sql-reference/statements/alter/#mutations)
2. [ClickHouse准实时数据更新的新思路](https://cloud.tencent.com/developer/article/1644570)
3. [ClickHouse各种MergeTree的关系与作用](https://mp.weixin.qq.com/s?__biz=MzA4MDIwNTY4MQ==&mid=2247483804&idx=1&sn=b304f7f88d064cc08f87fa5eaafec0b7&chksm=9fa68382a8d10a9440d3ce2a92a04c4a74aeda2d959049f04f1a414c1fb8034b97d9f7243c21&scene=21#wechat_redirect)
4. [在ClickHouse中处理实时更新](https://developer.aliyun.com/article/823894?utm_content=m_1000311820)