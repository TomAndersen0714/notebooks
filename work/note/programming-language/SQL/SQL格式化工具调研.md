# SQL格式化工具调研


## 问题



## 工具调研


### SQLFluff

https://github.com/sqlfluff/sqlfluff
https://docs.sqlfluff.com/en/stable/index.html

特点：
1. Python实现

优点：
1. 开源，社区活跃
2. 支持VSCode插件
3. 支持多种SQL dialect
4. 支持代码检查，且支持多种检查规则，可自选
5. 支持多种配置
6. 支持SQL模板（jinja2、dbt）

缺点：
1. API仅支持命令行Cli、Python调用
2. 代码检查和格式化速度太慢，不够轻量
3. 检查和格式化SQL模板时，需要事先手动配置模板参数dummy值


### sql-formatter

https://github.com/sql-formatter-org/sql-formatter

特点：
1. TypeScript实现
2. API支持命令行Cli、JavaScript调用

优点：
1. API支持命令行Cli、JavaScript调用，便于Web开发
2. 支持多种SQL dialect
3. 支持格式化SQL文件
4. 格式化速度快

缺点：
1. 不支持SQL模板



### clickhouse-format

https://clickhouse.com/docs/en/operations/utilities/clickhouse-format
https://github.com/ClickHouse/ClickHouse/tree/master/programs/format

特点：
1. C++实现

优点：
1. 官方开源，血统纯正，不担心烂尾

缺点：
1. 不支持格式化SQL文件
2. 不支持格式化SQL模板，仅支持可直接执行SQL
3. API仅支持命令行Cli、C++调用
4. 仅支持ClickHouse SQL



## 参考链接

1. [clickhouse-format](https://clickhouse.com/docs/en/operations/utilities/clickhouse-format)
2. [sqlfluff](https://github.com/sqlfluff/sqlfluff)