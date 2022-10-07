# 基于ClickHouse的近实时数据更新方案

## 前言

总所周知，OLAP引擎一般不支持事务，以及无法实现实时更新



ClickHouse version：20.4.2.9

## 行级近实时更新

### 1. Order By + Limit By

PS：为了避免更新频率过高，导致的表数据量过大，建议和ReplacingMergeTree、CollapsingMergeTree等支持自动合并的表引擎一起使用，后台自动依据主键合并，减少查询时扫描的数据量



### 2. argMax、argMin



### 3. ReplacingMergeTree



PS：Order By表达式必须以Primary Key表达式为前缀，即索引KVs的顺序，必须和数据的存储顺序一致



### 4. (Versioned)CollapsingMergeTree



PS：Order By表达式必须以Primary Key表达式为前缀，即索引KVs的顺序，必须和数据的存储顺序一致

PS：CollapsingMergeTree在写入时，必须要保证sign等于1的先写入，sign等于-1的后写入，如果写入顺序错误，则合并数据时无法实现正确抵消



### 5. AggregatingMergeTree Or SummingMergeTree

数据立方体



## 字段级近实时更新：

### 1. Mutation





## 其他方案

### 1. Group By

标准SQL





## 参考链接

1. [ClickHouse准实时数据更新的新思路](https://cloud.tencent.com/developer/article/1644570)
2. [ClickHouse各种MergeTree的关系与作用](https://mp.weixin.qq.com/s?__biz=MzA4MDIwNTY4MQ==&mid=2247483804&idx=1&sn=b304f7f88d064cc08f87fa5eaafec0b7&chksm=9fa68382a8d10a9440d3ce2a92a04c4a74aeda2d959049f04f1a414c1fb8034b97d9f7243c21&scene=21#wechat_redirect)