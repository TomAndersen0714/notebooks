# PostgreSQL常见问题





## 1. PostgreSQL命令行执行`\d table_name`命令查看表结构时报错



### 报错信息

```postgresql
postgres=# \d field_meta
ERROR:  column c.relhasoids does not exist
LINE 1: ..., c.relhasindex, c.relhasrules, c.relhastriggers, c.relhasoi...
```

### 问题原因

由于PostgreSQL在Postgre12之后，table的OID不再作为一个特殊列，因此显示`relhasoids`列不存在

### 解决方案

使用Postgre12以上的客户端进行链接，如：

1. （推荐）使用JetBrain等内嵌Postgre客户端的工具，直接使用更高版本的Postgre客户端，并直接进行连接
2. 升级本地的Postgre客户端

### 参考链接

1. [stackoverflow - How to fix "ERROR: column c.relhasoids does not exist" in Postgres?](https://stackoverflow.com/questions/58461178/how-to-fix-error-column-c-relhasoids-does-not-exist-in-postgres)