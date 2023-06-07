# ClickHouse数据导入导出解决方案



## 支持的数据格式以及对应数据类型

https://clickhouse.com/docs/en/sql-reference/formats
https://clickhouse.com/docs/en/interfaces/formats


### Arrow
https://clickhouse.com/docs/en/interfaces/formats#data-format-arrow


### Avro


### Parquet


### JSON



### 其他


## 导入导出方法


### 基于标准输入输出流

https://clickhouse.com/docs/en/integrations/data-ingestion/insert-local-files




#### docker

Example
```bash
docker exec -i 96 clickhouse-client --port=19000 --query="select * tmp.table FORMAT Parquet" > /tmp/export.parq

docker exec -i 96 clickhouse-client --port=19000 --query="INSERT INTO tmp.table FORMAT Parquet" < /tmp/export.parq
```

PS：切记通过容器终端导出时，不要加上“-t”参数，此命令会输出一些固定文本到标准输出中，污染数据文件，破坏文件格式


### 基于文件


#### Table Function - File

https://clickhouse.com/docs/en/sql-reference/table-functions/file

查看当前ClickHouse版本是否支持`file`表函数
```SQL
SELECT * FROM system.functions WHERE name like '%file%'
```


#### INFILE and OUTFILE

INSERT INTO ... FROM INFILE
https://clickhouse.com/docs/en/sql-reference/statements/insert-into#inserting-data-from-a-file


SELECT ... INTO OUTFILE
https://clickhouse.com/docs/en/sql-reference/statements/select/into-outfile


## 示例

https://clickhouse.com/docs/knowledgebase/file-export
https://clickhouse.com/docs/en/integrations/data-formats/sql


## 参考链接
