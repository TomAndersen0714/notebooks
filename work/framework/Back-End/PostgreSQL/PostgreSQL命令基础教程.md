# PostgreSQL 命令基础教程

## 前言

由于PostgreSQL客户端版本问题，PostgreSQL某些命令在低版本的客户端上无法执行，故个人建议，如果客户端处于能够直连数据库的网络环境下，可以直接使用`Jetbrain`全家桶下IDE的自带功能`Database`。
利用此功能，直接添加`Postgre Source`，通过UI交互实现数据库的基本管理，并且IDE也支持辅助构建各种SQL命令，提供执行。


## Psql cmd

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


### Psql backslash command

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


## SQL DQL

### Functions and Operators

[PostgreSQL常用操作符和函数](work/framework/Back-End/PostgreSQL/PostgreSQL常用操作符和函数.md)


## SQL DDL

[PostgreSQL 14.7 Documentation - SQL Commands](https://www.postgresql.org/docs/14/sql-commands.html)


### ROLE

#### CREATE ROLE

`CREATE ROLE miriam WITH PASSWORD 'jw8s0F4';`

#### ALTER ROLE

https://www.postgresql.org/docs/14/sql-alterrole.html



### DATABASE

`SELECT datname FROM pg_database;`

#### CREATE DATABASE

https://www.postgresql.org/docs/14/sql-createdatabase.html


#### DROP DATABASE

https://www.postgresql.org/docs/14/sql-dropdatabase.html


### SCHEMA

#### CREATE SCHEMA

https://www.postgresql.org/docs/14/sql-createschema.html

#### DROP SCHEMA

https://www.postgresql.org/docs/14/sql-dropschema.html


### TABLE

#### CREATE TABLE

https://www.postgresql.org/docs/14/sql-createtable.html


Example
```sql
CREATE TABLE test (
    id     integer UNIQUE,
    name    varchar(40)
);
```

#### DROP TABLE

https://www.postgresql.org/docs/14/sql-droptable.html


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


## SQL DML


### INSERT

https://www.postgresql.org/docs/current/sql-insert.html




## 其他命令

查看 PostgreSQL 版本
```sql
SELECT version()
```

查看 PostgreSQL 保留字
```sql
SELECT pg_get_keywords();
```


查看 Table 名
```sql
SELECT table_name
FROM information_schema.tables
WHERE table_schema = 'public';
```

查询 Table 结构
https://www.postgresql.org/docs/current/information-schema.html

```mysql
select
	column_name,
    data_type,
    character_maximum_length,
    column_default,
    is_nullable
from information_schema.columns
where table_schema = 'public'
and table_name = 'xqc.test';
```


查看数据类型信息，pg_type 表
https://www.postgresql.org/docs/14/catalogs.html
```mysql
-- 查看数据类型长度
SELECT typlen FROM pg_type WHERE oid = pg_typeof(33);
```


查看 index 信息：pg_indexes 表
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
