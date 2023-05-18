# ClickHouse Settings


## Restrictions on Query Complexity
https://clickhouse.com/docs/en/operations/settings/query-complexity


## Client

### max_execution_time

Timeout检查只会发生在，每次处理完Block的时候，故在大部分情况下，查询时间都会长于max_execution_time。

```
select sleepEachRow(3) from numbers(100)
settings max_block_size=1, max_execution_time=1
```

https://github.com/ClickHouse/ClickHouse/issues/8505





## 参考链接
1. 