# PostgreSQL常见问题


## psql 执行 `\d table_name` 命令查看表结构时报错


**报错信息**

```postgresql
postgres=# \d field_meta
ERROR:  column c.relhasoids does not exist
LINE 1: ..., c.relhasindex, c.relhasrules, c.relhastriggers, c.relhasoi...
```

**问题原因**

由于 PostgreSQL 在 Postgre12 之后，table 不在具备默认特殊列 OID，因此显示 `relhasoids` 列不存在，使用低版本 `psql` 客户端工具连接高版本 PostgreSQL 时就会报此错误。

**解决方案**

使用 Postgre12 以上的客户端进行链接，如：
1. （推荐）使用 JetBrain 等内嵌 Postgre 客户端的工具，直接使用更高版本的 Postgre 客户端，并直接进行连接
2. 升级本地的 Postgre psql 客户端


## 参考链接

1. [stackoverflow - How to fix "ERROR: column c.relhasoids does not exist" in Postgres?](https://stackoverflow.com/questions/58461178/how-to-fix-error-column-c-relhasoids-does-not-exist-in-postgres)