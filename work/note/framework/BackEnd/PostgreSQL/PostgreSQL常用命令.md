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


## SQL命令

[PostgreSQL 14.7 Documentation - SQL Commands](https://www.postgresql.org/docs/14/sql-commands.html)


### ALTER TABLE

https://www.postgresql.org/docs/14/sql-altertable.html


#### Constraint

添加 UNIQUE Constraint
`ALTER TABLE <table_name> ADD CONSTRAINT <constraint_name> UNIQUE (column_name [, ... ])`
添加 PRIMARY KEY Constraint
`ALTER TABLE <table_name> ADD CONSTRAINT <constraint_name> PRIMARY KEY (column_name [, ... ])`
删除 Constraint
`ALTER TABLE <table_name> DROP CONSTRAINT [ IF EXISTS ]  constraint_name [ RESTRICT | CASCADE ]`


## 其他常用命令

查询表结构
```mysql
select column_name,
    data_type,
    character_maximum_length,
    column_default,
    is_nullable
from INFORMATION_SCHEMA.COLUMNS
where table_name = 'sxx.ft_product_mapping_tm';
```

查看数据类型长度
```mysql
SELECT typlen FROM pg_type WHERE oid = pg_typeof(33);
```


## 参考链接

1. [PostgreSQL菜鸟教程](https://www.runoob.com/postgresql/postgresql-tutorial.html)
2. [PostgreSQL 14.7 Documentation](https://www.postgresql.org/docs/14/index.html)
3. [PostgreSQL 14.7 Documentation - SQL Commands](https://www.postgresql.org/docs/14/sql-commands.html)
4. [PostgreSQL 14.7 - PostgreSQL interactive terminal](https://www.postgresql.org/docs/14/app-psql.html)
5. [数据库教程 - PostgreSQL](https://www.sjkjc.com/postgresql/basic/)
