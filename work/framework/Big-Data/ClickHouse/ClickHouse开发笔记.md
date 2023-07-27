# ClickHouse 开发笔记


## ClickHouse Python API


### Clickhouse_driver

**对于 ClickHouse Datetime 字段读写时的默认时区问题**

在写入时：
如果写入的 Python Datetime 对象带有分区信息，则会按照对应时区转换为时间戳来进行写入；如果写入的 Python Datetime 对象不带分区信息，则会将其视为服务器本地时间 Local Datetime，并转换服务器时区对应的时间戳，来执行写入过程。

在读取时：
如果 ClickHouse Datetime 数据类型的字段，没有设置时区信息，则会使用 ClickHouse Server 配置中的时区信息，并将对应的时间戳转换为本地时间。



## ClickHouse DDL


ClickHouse Truncate