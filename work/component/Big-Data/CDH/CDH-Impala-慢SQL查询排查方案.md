# CDH Impala SQL 慢查询排查方案

## CDH SQL 日志查询页面

![](resources/images/Pasted%20image%2020231113154512.png)

## 排查流程
### 查询执行结果异常的 SQL

```bash
query_state=EXCEPTION
```

### 查询执行较慢的 SQL

PS：逆向思维，使用 OR 逻辑谓语关键字，将各个括号的表达式连接，因为理论上如果这些条件（消耗的时间、内存、CPU 时间，以及读取的 HDFS 字节数等）都无法满足，则表示对应的 SQL 可以视为正常 SQL，故可以使用 OR 连接条件进行查询，排除这一部分。

```bash
(query_duration >= 2058.0 AND query_duration < 14406.0 OR query_duration >= 14406.0) OR (memory_aggregate_peak >= 1.0E9 AND memory_aggregate_peak < 1.2E9 OR memory_aggregate_peak >= 1.2E9) OR (thread_cpu_time >= 10800.0 AND thread_cpu_time < 64800.0 OR thread_cpu_time >= 64800.0 AND thread_cpu_time < 388800.0 OR thread_cpu_time >= 388800.0) OR  (hdfs_bytes_read >= 1.2288E9 AND hdfs_bytes_read < 9.8304E9 OR hdfs_bytes_read >= 9.8304E9)
```

### 查询高 qps 的 SQL

CDH SQL 查询图表配合人工观察定位高 qps SQL

![](resources/images/Pasted%20image%2020231113154641.png)

![](resources/images/Pasted%20image%2020231113154645.png)

或者使用正则表达式，查询包含指定代码段的 SQL

### 查询包含指定代码段的 SQL

```bash
statement RLIKE '.*select 1.*'
```

![](resources/images/Pasted%20image%2020231113154717.png)