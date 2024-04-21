# Bitmap位图

## 简介

Bitmap适合稠密序列
RoaringBitmap支持稀疏，也支持稠密序列


### 优点

数据压缩
自带去重
集合间运算速度快
存在性判断速度快


### 缺点

存储数据类型仅为无符号整数



### 应用场景

1. 用户画像
2. 人群圈选
3. AB测试
4. 精准去重计数


## RoaringBitmap

Roaring Bitmap，又叫做咆哮位图。

用于解决普通 Bitmap 存储大整数时，出现的 Bitmap 过大的问题，当 Bitmap 过大时，一次性加载到内存，可能会导内存开销过大。


## Bloom Filter

只会返回两个结果，可能在（存在假阳性的可能），或者一定不在

[Bloom-Filter-布隆过滤器](learning/subjects/ComputerScience/DataStructuresAndAlgorithm/Data-Structures/Advanced/Bloom-Filter-布隆过滤器.md)


### 应用场景

1. BloomFilter索引
2. 


## 参考链接
1. [BitMap及其在ClickHouse中的应用](https://zhuanlan.zhihu.com/p/480345952)