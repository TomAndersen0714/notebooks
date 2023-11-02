# ClickHouse 基础教程


## What Is ClickHouse?


https://clickhouse.com/docs/en/intro

## Quick Start

https://clickhouse.com/docs/en/getting-started/quick-start


## Overview

https://clickhouse.com/docs/en/development/architecture

优点：
无共享、多主结构：每个节点上的角色相同，都支持对外提供服务，每个节点上仅访问自己的本地资源，避免了集群单点故障问题
数据分片与副本集群：数据通过分片+副本集群的方式，进行数据分布式存储，实现了较高的数据存储能力，同时保障了集群高可用
向量化执行引擎：通过 CPU 的 SIMD 指令，实现了底层的数据并行处理，实现了较高的数据处理和计算性能

缺点：
强依赖 Zookeeper

与 HDFS、YARN 的对比：
1. 各个节点角色相同，每个节点都能提供服务
2. 不存在单点故障问题，集群可靠性较强

Demo
https://play.clickhouse.com/play?user=play

[Getting started with ClickHouse? Here are 13 "Deadly Sins" and how to avoid them](https://clickhouse.com/blog/common-getting-started-issues-with-clickhouse)


Data Type
https://clickhouse.com/docs/en/sql-reference/data-types


## 参考链接
1. [Altinity ClickHouse Knowledge Base (KB)](https://kb.altinity.com/)
2. [ClickHouse Documentation](https://clickhouse.com/docs/en/intro)
3. [ClickHouse SQL官方文档（中文版）](https://clickhouse.com/docs/zh/sql-reference/statements/#)
4. [ClickHouse 全面学习指南](https://mp.weixin.qq.com/s/Q37TQwdCSjwtLX5BHSI0Ww)