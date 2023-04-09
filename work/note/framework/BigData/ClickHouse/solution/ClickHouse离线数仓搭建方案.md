# ClickHouse离线数仓搭建方案

## 方案一：Buffer+Distribued+ReplicatedMergeTree

internal_replication=true，insert_distributed_sync=false

可以利用副本之间互相复制，实现单次仅写入一个副本，避免写入时出现单点瓶颈，同时兼顾高可用，和高写入性能

写：Buffer
读：Distribued
存：ReplicatedMergeTree
算：ClickHouse