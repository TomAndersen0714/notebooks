# sqlite3常用命令



## sqlite3

进入sqlite3命令行模式：`sqlite3`，或者同时直接打开/创建数据库：`sqlite3 DBFileName.db`



## database

1. 打开/创建数据库：`.open DBFileName.db`

2. 查看当前打开的数据库列表：`.database`

3. 装载数据库: `ATTACH DATABASE 'DBFileName.db' AS database_alias_name`

4. 卸载数据库: `DEATTACH DATABASE database_name`



## table

1. 查看所有数据库的所有表: `.tables`

2. 查看表的完整信息: `.schema <table_name>`

3. 创建表: 
```sqlite
CREATE TABLE COMPANY(
   ID INT PRIMARY KEY     NOT NULL,
   NAME           TEXT    NOT NULL,
   AGE            INT     NOT NULL,
   ADDRESS        CHAR(50),
   SALARY         REAL
);
```

4. 删除表: `DROP TABLE database_name.table_name`

