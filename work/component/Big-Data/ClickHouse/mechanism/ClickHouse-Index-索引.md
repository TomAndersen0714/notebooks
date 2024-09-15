# ClickHouse MergeTree Index

## 前言

本文主要介绍 MergeTree 表引擎相关的索引功能，及其基础原理。

## 索引类型

[Understanding ClickHouse Data Skipping Indexes | ClickHouse Docs](https://clickhouse.com/docs/en/optimize/skipping-indexes)

### Primary Index 主键索引

[MergeTree | ClickHouse Docs#selecting-a-primary-key](https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/mergetree#selecting-a-primary-key)

### Data Skipping Index 二级索引

[MergeTree | ClickHouse Docs#Data Skipping Indexes](https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/mergetree#table_engine-mergetree-data_skipping-indexes)
[Understanding ClickHouse Data Skipping Indexes | ClickHouse Docs](https://clickhouse.com/docs/en/optimize/skipping-indexes)

## 索引的原理

[MergeTree | ClickHouse Docs | primary-keys-and-indexes-in-queries](https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/mergetree#primary-keys-and-indexes-in-queries)

## 索引支持的函数(Functions support)

[MergeTree functions-support | ClickHouse Docs](https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/mergetree/#functions-support)

## 参考链接
1. [Understanding ClickHouse Data Skipping Indexes | ClickHouse Docs](https://clickhouse.com/docs/en/optimize/skipping-indexes)