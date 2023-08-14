# ClickHouse 开发基础教程


[Getting started with ClickHouse? Here are 13 "Deadly Sins" and how to avoid them](https://clickhouse.com/blog/common-getting-started-issues-with-clickhouse)


## ClickHouse SQL




## ClickHouse Client

### Python

Clickhouse_driver

Read：如果写入的 Python Datetime 对象带有分区信息，则会按照对应时区转换为时间戳来进行写入；如果写入的 Python Datetime 对象不带分区信息，则会将其视为服务器本地时间 Local Datetime，并转换服务器时区对应的时间戳，来执行写入过程。

Write：如果 ClickHouse Datetime 数据类型的字段，没有设置时区信息，则会使用 ClickHouse Server 配置中的时区信息，并将对应的时间戳转换为本地时间。


### Java

JDBC

[使用 ClickHouse JDBC 官方驱动，踩坑无数](https://blog.csdn.net/lisu061714112/article/details/128088578)