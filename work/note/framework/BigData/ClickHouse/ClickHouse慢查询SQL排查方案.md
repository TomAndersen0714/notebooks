# ClickHouse慢查询SQL排查方案

## system.query_log

ClickHouse内部会自动搜集查询日志，并写入到system.query_log表中，通过查询此表，可以排查和分析慢查询SQL



### 查询qps统计

```sql
WITH (
    SELECT toDateTime('{{datetime.start}}')
) AS start_datetime,
(
    SELECT toDateTime('{{datetime.end}}')
) AS end_datetime
SELECT hostname,
    replica,
    hour,
    minute,
    COUNT(1)
FROM clusterAllReplicas('cluster_3s_2r', 'system.query_log')
WHERE toYYYYMMDD(event_date) BETWEEN toYYYYMMDD(start_datetime) AND toYYYYMMDD(end_datetime)
    AND type = 'QueryStart'
    AND event_time BETWEEN start_datetime AND end_datetime
GROUP BY hostname() AS hostname,
    getMacro('replica') AS replica,
    toHour(event_time) AS hour,
    toMinute(event_time) AS minute
ORDER BY hostname,
    replica,
    hour,
    minute
```



### 慢查询sql查询

```SQL
WITH (
    SELECT toDateTime('{{datetime.start}}')
) AS start_datetime,
(
    SELECT toDateTime('{{datetime.end}}')
) AS end_datetime
select
    hostName(),
    *
from remote('{{host}}', 'system.query_log')
where toYYYYMMDD(event_date) BETWEEN toYYYYMMDD(start_datetime) AND toYYYYMMDD(end_datetime)
    and event_time BETWEEN start_datetime AND end_datetime
    and memory_usage >= {{memory_mb_threshold}}*1024*1024
    and type in [{{type}}]
    and query ilike '{{query_segment1}}'
order by {{desc_order_key}} desc
limit {{limit}}
```