# ClickHouse数据导入导出解决方案


## ClickHouse 支持的数据格式以及对应数据类型

https://clickhouse.com/docs/en/sql-reference/formats
https://clickhouse.com/docs/en/interfaces/formats


### Arrow
https://clickhouse.com/docs/en/interfaces/formats#data-format-arrow


### Avro


### Parquet


### JSON


## ClickHouse 数据导入导出方法


### 与其他 Source 之间

#### Table function, Table Engine

如 MySQL、MongoDB、HDFS 等

使用对应的 Table function、Table Engine，实现导入导出

Clickhouse-local 除了支持本地文件读写之外，也支持使用 ClickHouse 的 Table Function、Table Engine 等。

### 与其他 ClickHouse Server 之间

#### remote, remoteSecure

### 基于文件

#### File Table Function

https://clickhouse.com/docs/en/sql-reference/table-functions/file

查看当前ClickHouse版本是否支持`file`表函数
```SQL
SELECT * FROM system.functions WHERE name like '%file%'
```


#### INFILE and OUTFILE clause

INSERT INTO ... FROM INFILE
https://clickhouse.com/docs/en/sql-reference/statements/insert-into#inserting-data-from-a-file


SELECT ... INTO OUTFILE
https://clickhouse.com/docs/en/sql-reference/statements/select/into-outfile


#### clickHouse-local

https://clickhouse.com/docs/en/operations/utilities/clickhouse-local#download-clickhouse-local

### 基于标准输入输出流

https://clickhouse.com/docs/en/integrations/data-ingestion/insert-local-files

#### docker

Example
```bash
docker exec -i 96 clickhouse-client --port=19000 --query="
	select * tmp.table 
	FORMAT Parquet
" > /tmp/export.parq

docker exec -i 96 clickhouse-client --port=19000 --query="
	INSERT INTO tmp.table
	FORMAT Parquet
" < /tmp/export.parq
```

PS：切记通过容器终端导出时，不要加上“-t”参数，此命令会输出一些固定文本到标准输出中，污染数据文件，破坏文件格式


### 基于第三方 ETL 工具

Airbyte，SeaTunnel，Spark，Flink，xdt-core 等


## 示例

https://clickhouse.com/docs/knowledgebase/file-export
https://clickhouse.com/docs/en/integrations/data-formats/sql


## 参考链接
1. [ClickHouse Documentation - Migrating Data into ClickHouse](https://clickhouse.com/docs/en/integrations/migration)
2. [ClickHouse Documentation - Clients and Drivers - clickhouse-local](https://clickhouse.com/docs/en/operations/utilities/clickhouse-local#download-clickhouse-local)