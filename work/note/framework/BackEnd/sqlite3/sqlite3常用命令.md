# sqlite3常用命令





## sqlite3

进入sqlite3命令行模式：`sqlite3`，或者同时直接打开/创建数据库：`sqlite3 DBFileName.db`




## database

打开/创建数据库：`.open DBFileName.db`

查看当前打开的数据库列表：`.database`

添加(附加)数据库: `ATTACH DATABASE 'DBFileName.db' AS database_alias_name`

分离数据库: `DEATTACH DATABASE database_name`



## table

查看所有数据库的所有表: `.tables`

查看表的完整信息: `.schema <table_name>`

创建表: 

```sqlite
CREATE TABLE COMPANY(
   ID INT PRIMARY KEY     NOT NULL,
   NAME           TEXT    NOT NULL,
   AGE            INT     NOT NULL,
   ADDRESS        CHAR(50),
   SALARY         REAL
);
```



删除表: `DROP TABLE database_name.table_name`

