# PostgreSQL常用命令

## 前言

由于PostgreSQL客户端版本问题，PostgreSQL某些命令在低版本的客户端上无法执行，故个人建议，如果客户端处于能够直连数据库的网络环境下，可以直接使用`Jetbrain`全家桶下IDE的自带功能`Database`。
利用此功能，直接添加`Postgre Source`，通过UI交互实现数据库的基本管理，并且IDE也支持辅助构建各种SQL命令，提供执行。


## psql命令

[PostgreSQL 14.7 Documentation - psql - PostgreSQL interactive terminal](https://www.postgresql.org/docs/14/app-psql.html)


### connect command

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


### backslash command

`\q`：退出psql终端
`\?`：显示命令帮助信息
`\a`：切换打印内容对齐模式
`\c dbname`：连接指定数据库
`\conninfo`：打印当前连接信息
`\encoding`：打印当前的字符集
`\set`：查看当前psql变量
`\l[+] [ pattern ]`：查看所有databases，`[+]`表示同时打印Size、Description等额外信息
`\dg[+]  [PATTERN]`：查看所有roles
`\dn[S+] [PATTERN]`：查看所有schemas
`\dt[S+] [PATTERN]`：查看所有tables
`\db[+]  [PATTERN]`：查看所有tablespace
`\dv[S+] [PATTERN]`：查看所有views
`\di[S+] [PATTERN]`：查看所有indexes


## SQL命令手册

[PostgreSQL 14.7 Documentation - SQL Commands](https://www.postgresql.org/docs/14/sql-commands.html)


### INDEX

#### CREATE INDEX

https://www.postgresql.org/docs/14/sql-createindex.html


### CONSTRAINT

添加 UNIQUE Constraint：
```SQL
ALTER TABLE <table_name> ADD CONSTRAINT <constraint_name> UNIQUE (column_name [, ... ])
```


添加 PRIMARY KEY Constraint：
```SQL
ALTER TABLE <table_name> ADD CONSTRAINT <constraint_name> PRIMARY KEY (column_name [, ... ])
```
PS：Primary key constraint 创建的同时也会创建名为 tablename_pkey 的 Primary Key。


基于 UNIQUE KEY 添加 PRIMARY KEY Constraint：
```sql
CREATE UNIQUE INDEX CONCURRENTLY dist_id_temp_idx ON distributors (dist_id);

ALTER TABLE distributors DROP CONSTRAINT distributors_pkey,
ADD CONSTRAINT distributors_pkey PRIMARY KEY USING INDEX dist_id_temp_idx;
```


删除 Constraint：
```SQL
ALTER TABLE <table_name> DROP CONSTRAINT [ IF EXISTS ]  constraint_name [ RESTRICT | CASCADE ]
```



## 其他常用命令

information_schema.columns表：查询表结构
https://www.postgresql.org/docs/current/information-schema.html

```mysql
select column_name,
    data_type,
    character_maximum_length,
    column_default,
    is_nullable
from information_schema.columns
where table_name = 'sxx.ft_product_mapping_tm';
```


pg_type表：查看数据类型信息
https://www.postgresql.org/docs/14/catalogs.html
```mysql
-- 查看数据类型长度
SELECT typlen FROM pg_type WHERE oid = pg_typeof(33);
```


pg_indexes表：查看index信息
https://www.postgresql.org/docs/14/catalogs.html
```mysql
SELECT *
FROM pg_indexes
LIMIT 100
```


## 参考链接

1. [PostgreSQL菜鸟教程](https://www.runoob.com/postgresql/postgresql-tutorial.html)
2. [PostgreSQL 14.7 Documentation](https://www.postgresql.org/docs/14/index.html)
3. [PostgreSQL 14.7 Documentation - SQL Commands](https://www.postgresql.org/docs/14/sql-commands.html)
4. [PostgreSQL 14.7 - PostgreSQL interactive terminal](https://www.postgresql.org/docs/14/app-psql.html)
5. [PostgreSQL 14.7 - Catalogs](https://www.postgresql.org/docs/14/catalogs.html)
6. [数据库教程 - PostgreSQL](https://www.sjkjc.com/postgresql/basic/)
