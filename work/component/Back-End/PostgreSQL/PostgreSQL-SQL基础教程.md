# PostgreSQL SQL 基础教程


## SQL DQL


### Functions and Operators

[PostgreSQL-SQL常用函数和操作符](work/component/Back-End/PostgreSQL/PostgreSQL-SQL常用函数和操作符.md)


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
