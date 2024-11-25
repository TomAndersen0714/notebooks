# ClickHouse 慢查询 SQL 排查方案

## 检索筛选慢查询

查询分析系统表 `system.query_log` 筛选慢查询:

ClickHouse Server 会自动搜集记录并采集 SQL 查询日志，定时将其写入到 `system.query_log` 表中，通过查询此表，可以排查和定位系统的慢查询。

### 查询 qps 统计

```sql
SELECT hostname,
    replica,
    hour,
    minute,
    COUNT(1)
FROM clusterAllReplicas('cluster_3s_2r', 'system.query_log')
WHERE toYYYYMMDD(event_date) BETWEEN toYYYYMMDD(toDateTime('{{datetime.start}}')) AND toYYYYMMDD(toDateTime('{{datetime.end}}'))
    AND type = 'QueryStart'
    AND event_time BETWEEN toDateTime('{{datetime.start}}') AND toDateTime('{{datetime.end}}')
GROUP BY hostname() AS hostname,
    getMacro('replica') AS replica,
    toHour(event_time) AS hour,
    toMinute(event_time) AS minute
ORDER BY hostname,
    replica,
    hour,
    minute
```

### 慢查询 sql 查询

```SQL
SELECT
    hostName(),
    *
FROM remote('{{host}}', 'system.query_log')
WHERE toYYYYMMDD(event_date) BETWEEN toYYYYMMDD(toDateTime('{{datetime.start}}')) AND toYYYYMMDD(toDateTime('{{datetime.end}}'))
    AND event_time BETWEEN toDateTime('{{datetime.start}}') AND toDateTime('{{datetime.end}}')
    AND memory_usage >= {{memory_mb_threshold}}*1024*1024
    AND type in [{{type}}]
    AND query ilike '{{query_segment1}}'
ORDER BY {{desc_order_key}} desc
LIMIT {{limit}}
```

## 定位慢查询代码段

### EXPLAIN 分析

[EXPLAIN Statement | ClickHouse Docs](https://clickhouse.com/docs/en/sql-reference/statements/explain)

EXPLAIN SYNTAX: 查看优化后的 SQL 语句，判断是否有明显的代码问题。

EXPLAIN PLAN: 查看 SQL 的执行计划，观察其中的 Indexes 字段，判断是否命中索引。