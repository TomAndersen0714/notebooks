# ClickHouse分页查询解决方案



## LIMIT OFSET

在分页查询中，"深度翻页/深翻页"问题是指在大数据量的情况下，通过不断地翻页操作来获取后续页数据时所产生的性能问题。

缺点：此方案，在“深度翻页”时候会出现性能问题，因为“深度翻页”每次查询时，都需要进行全局排序，同时扫描并跳过大量的无效数据。


## 参考链接
1. [如何在千万级数据中查询 10W 的数据并排序？都有什么方案？写得太好了！](https://mp.weixin.qq.com/s/luto4gQIrbEFSm6K-_TKWg)
