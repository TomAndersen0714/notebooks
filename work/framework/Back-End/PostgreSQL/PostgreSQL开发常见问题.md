# PostgreSQL 开发常见问题

## psql 查看表结构时报错

psql 执行 `\d table_name` 命令查看表结构时报错

**报错信息**

```postgresql
postgres=# \d field_meta
ERROR:  column c.relhasoids does not exist
LINE 1: ..., c.relhasindex, c.relhasrules, c.relhastriggers, c.relhasoi...
```

**问题原因**

由于 PostgreSQL 在 Postgre12 之后，table 不在具备默认特殊列 OID，因此显示 `relhasoids` 列不存在，使用低版本 `psql` 客户端工具连接高版本 PostgreSQL 时就会报此错误。

**解决方案**

使用 Postgre12 以上的 psql 客户端进行连接，如：
1. （推荐）使用 JetBrain 等内嵌 Postgre 客户端的工具，直接使用更高版本的 Postgre 客户端，并直接进行连接
2. 升级本地的 Postgre psql 客户端


## 跨数据库查询时报错

跨数据库查询时候，报错`ERROR:  cross-database references are not implemented`

PostgreSQL 在设计时采用了更为严格的隔离性和安全性策略，**默认不支持跨数据库查询**。默认情况下，每个 PostgreSQL 数据库是相互隔离的，每个数据库都有自己的独立用户和权限体系。这种隔离性确保了数据库之间的数据安全性和稳定性，但也限制了跨数据库查询的能力。

PostgreSQL 提供了 dblink 扩展，允许通过外部函数在一个数据库中执行跨数据库查询。这种方式可以实现跨数据库查询，但需要额外的配置和权限设置，以及在查询性能上可能存在一些限制。

因此在使用 PostgreSQL 时，同一个业务线的表，建议存储在同一个 database 中，通过 schema 来组织和管理不同业务模块的 table。

**参考链接：**
https://stackoverflow.com/questions/51784903/cross-database-references-are-not-implemented


## PostgreSQL 无法直接添加 NOT NULL 字段

在 RDMBS 中一般都是无法直接添加具有 NOT NULL Constraint 的字段，因为此类 Constraint 的字段添加时必须要带有默认值（即 Default Expression），否则 PostgreSQL 会默认使用 NULL 值来填充新增字段，但这又与新增字段的 NOT NULL Constraint 属性冲突，故而报错。

解决方案 1：
添加 NOT NULL 字段同时，带上 Default Expression，缺点是当表比较大时，DDL 执行时锁表时间比较长。

解决方案 2：
先添加普通字段，然后通过 UPDATE 的方式，手动设置默认值，避免 DDL 执行时锁表时间过长，然后再给字段添加 Default Expression，保证新增行的对应字段有默认值。

## 参考链接

1. [stackoverflow - How to fix "ERROR: column c.relhasoids does not exist" in Postgres?](https://stackoverflow.com/questions/58461178/how-to-fix-error-column-c-relhasoids-does-not-exist-in-postgres)