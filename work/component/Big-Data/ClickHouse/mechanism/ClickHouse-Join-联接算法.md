# ClickHouse Join 联接算法


## 常见的 JOIN 算法

[JOIN算法](learning/subjects/Computer/Data-Structures-and-Algorithm/Algorithms/Intermediate/JOIN算法.md)

## ClickHouse JOIN Clause
https://clickhouse.com/docs/en/sql-reference/statements/select/join#memory-limitations

> By default, ClickHouse uses the hash join algorithm. ClickHouse takes the right_table and creates a hash table for it in RAM. If join_algorithm = 'auto' is enabled, then after some threshold of memory consumption, ClickHouse falls back to merge join algorithm. For JOIN algorithms description see the join_algorithm setting.


## ClickHouse Join Algorithm
https://clickhouse.com/docs/en/operations/settings/settings#settings-join_algorithm

Default

Hash

Grace_hash

Parallel_hash

Partial_merge

Direct

Auto

Full_sorting_merge

Prefer_partial_merge

## 参考链接
1. [Wiki-JOIN]( https://en.wikipedia.org/wiki/Join_ (SQL))