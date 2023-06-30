# CDH Impala高负载和慢查询SQL排查方案

<img src="images/CDH-Impala%E9%AB%98%E8%B4%9F%E8%BD%BD%E5%92%8C%E6%85%A2%E6%9F%A5%E8%AF%A2SQL%E6%8E%92%E6%9F%A5%E6%96%B9%E6%A1%88/1671783266928-4.png" alt="img" style="zoom:50%;" />



## 一、查询执行结果异常的SQL

```SQL
query_state=EXCEPTION
```



## 二、查询执行较慢的SQL

PS：逆向思维，使用OR逻辑谓语关键字，将各个括号的表达式连接，因为理论上如果这些条件（消耗的时间、内存、CPU时间，以及读取的HDFS字节数等）都无法满足，则表示对应的SQL可以视为正常SQL，故可以使用OR连接条件进行查询，排除这一部分

```Assembly%20language
(query_duration >= 2058.0 AND query_duration < 14406.0 OR query_duration >= 14406.0) OR (memory_aggregate_peak >= 1.0E9 AND memory_aggregate_peak < 1.2E9 OR memory_aggregate_peak >= 1.2E9) OR (thread_cpu_time >= 10800.0 AND thread_cpu_time < 64800.0 OR thread_cpu_time >= 64800.0 AND thread_cpu_time < 388800.0 OR thread_cpu_time >= 388800.0) OR  (hdfs_bytes_read >= 1.2288E9 AND hdfs_bytes_read < 9.8304E9 OR hdfs_bytes_read >= 9.8304E9)
```



## 三、查询qps较高的SQL

**CDH SQL查询图表配合人工观察定位高qps** **SQL**

<img src="images/CDH-Impala%E9%AB%98%E8%B4%9F%E8%BD%BD%E5%92%8C%E6%85%A2%E6%9F%A5%E8%AF%A2SQL%E6%8E%92%E6%9F%A5%E6%96%B9%E6%A1%88/1671783266922-1.png" alt="img" style="zoom:50%;" />



<img src="images/CDH-Impala%E9%AB%98%E8%B4%9F%E8%BD%BD%E5%92%8C%E6%85%A2%E6%9F%A5%E8%AF%A2SQL%E6%8E%92%E6%9F%A5%E6%96%B9%E6%A1%88/1671783266922-2.png" alt="img" style="zoom:50%;" />

**或者使用正则表达式，查询包含指定代码段的SQL**



## 四、查询包含指定代码段的SQL

```SQL
statement RLIKE '.*select 1.*'
```

<img src="images/CDH-Impala%E9%AB%98%E8%B4%9F%E8%BD%BD%E5%92%8C%E6%85%A2%E6%9F%A5%E8%AF%A2SQL%E6%8E%92%E6%9F%A5%E6%96%B9%E6%A1%88/1671783266922-3.png" alt="img" style="zoom:50%;" />