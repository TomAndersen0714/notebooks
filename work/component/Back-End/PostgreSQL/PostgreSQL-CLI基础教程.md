# PostgreSQL-CLI 基础教程


## 前言

由于 PostgreSQL psql 客户端版本问题，PostgreSQL 某些命令在低版本的 psql 客户端上无法执行。

个人建议，如果客户端处于能够直连数据库的网络环境下，可以直接使用 Jetbrain 全家桶下 IDE 的自带功能 `Database`，或者使用 `DataGrip `，通过 UI 交互实现数据库的基本管理，并且 IDE 也支持辅助或交互式构建各种 SQL 命令，大幅降低开发门槛。

## psql cmd

[PostgreSQL 14.7 Documentation - psql - PostgreSQL interactive terminal](https://www.postgresql.org/docs/14/app-psql.html)


### Connect command

常用格式
```postgresql
psql -h host -p port -U db_user db_name
psql postgresql://db_user:db_passwd@host:port/db_name
```

示例
```postgresql
psql -h 10.248.32.3 -p 5432 -U qc_object qc_object
psql postgresql://qc_object:qcobjadmin@10.248.32.3:5432/qc_object
psql postgresql://sxx:sxxdosngzimv@10.248.32.3:5432/postgres
psql postgresql://airflow_user:airflow_pass@10.0.2.2:5432/airflow_db
```


### psql backslash command

`\q` ：退出 psql 终端
`\?` ：显示命令帮助信息
`\a` ：切换打印内容对齐模式

`\c dbname` ：连接指定数据库
`\conninfo` ：打印当前连接信息
`\encoding` ：打印当前的字符集
`\set` ：查看当前 psql 变量

`\dg[+]  [PATTERN]` ：查看所有 roles
`\l[+]  [PATTERN]` ：查看所有 database，`[+]` 表示同时打印 Size、Description 等额外信息
`\db[+]  [PATTERN]` ：查看所有 tablespace

`\dn[S+] [PATTERN]` ：查看所有 schema
`\dt[S+] [PATTERN]` ：查看所有 table
`\dv[S+] [PATTERN]` ：查看所有 view
`\di[S+] [PATTERN]` ：查看所有 index



## 常见问题

### psql 查看表结构时报错

Psql 执行 `\d table_name` 命令查看表结构时报错

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