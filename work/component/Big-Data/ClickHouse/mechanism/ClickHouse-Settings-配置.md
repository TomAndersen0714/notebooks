# ClickHouse Settings


## Settings Overview

https://clickhouse.com/docs/en/operations/settings

## Global-level server settings

https://clickhouse.com/docs/en/operations/server-configuration-parameters/settings

### Configuration Files

https://clickhouse.com/docs/en/operations/configuration-files

## User-level Settings

### User Settings

https://clickhouse.com/docs/en/operations/settings/settings-users


### Settings Profiles

https://clickhouse.com/docs/en/operations/settings/settings-profiles

## Query-level settings

### Core Settings

https://clickhouse.com/docs/en/operations/settings/settings

### Restrictions on Query Complexity

https://clickhouse.com/docs/en/operations/settings/query-complexity

**max_execution_time**

Timeout检查只会发生在，每次处理完Block的时候，故在大部分情况下，查询时间都会长于max_execution_time。

```
select sleepEachRow(3) from numbers(100)
settings max_block_size=1, max_execution_time=1
```

https://github.com/ClickHouse/ClickHouse/issues/8505


### Format settings

https://clickhouse.com/docs/en/operations/settings/formats

### Memory overcommit

https://clickhouse.com/docs/en/operations/settings/memory-overcommit

### Permissions for Queries

https://clickhouse.com/docs/en/operations/settings/permissions-for-queries


## 参考链接

1. [Altinity ClickHouse Knowledge Base (KB)](https://kb.altinity.com/)
2. [ClickHouse Documentation](https://clickhouse.com/docs/en/intro)