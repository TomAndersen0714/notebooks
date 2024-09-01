# Bloom Filter布隆过滤器


布隆过滤器 Bloom Filter 是一种空间利用率极高的数据结构，可以在一定精度误差（false positive）的范围内，快速判断某个元素是否属于某个集合，且只会返回可能属于（false positive or true positive），或者一定不属于（true negative），这两种结果。

布隆过滤器 Bloom Filter，一般通过 Hash 函数，加上Bit 位数组的方式来实现，即空间换取时间，需要进行初始化。


布隆过滤器适用于对空间敏感且能容忍一定误报率的应用场景，比如网页爬虫的 URL 去重、数据库查询优化、垃圾邮件过滤等。


## 参考链接

1. [Bloom filter - Wikipedia](https://en.wikipedia.org/wiki/Bloom_filter)