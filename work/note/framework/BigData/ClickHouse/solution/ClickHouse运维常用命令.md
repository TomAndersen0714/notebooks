# ClickHouse运维常用命令



## Cache

1. 查看缓存统计指标：`SELECT metric, formatReadableSize(value) FROM system.asynchronous_metrics WHERE metric LIKE '%Cache%'`
2. 清除Mark Cache：`SYSTEM DROP MARK CACHE`
3. 清除UNCOMPRESSED CACHE：`SYSTEM DROP UNCOMPRESSED CACHE`