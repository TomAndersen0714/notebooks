# PostgreSQL常用命令

## 前言

由于PostgreSQL客户端版本问题，PostgreSQL某些命令在低版本的客户端上无法执行，个人建议，如果是在能够直连数据库的网络环境下，建议使用Jetbrain IDE的自带功能Database，直接添加Postgre Source，通过UI去管理数据库，并且IDEA也能够自动生成各种SQL命令


## psql命令行命令

[psql - PostgreSQL interactive terminal](https://www.postgresql.org/docs/14/app-psql.html)



## SQL命令

### Constraints(约束)

添加约束
`ALTER TABLE <table_name> ADD CONSTRAINT <constraint_name> <constraint_type> (<columns...>)`


删除约束
`ALTER TABLE <table_name> DROP CONSTRAINT <constraint_name>`


## 其他命令

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
