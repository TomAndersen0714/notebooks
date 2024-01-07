# SQL 代码格式化工具调研


## 问题

SQL 语言虽然有国际标准，但实际上业内的各个数据库中支持的 SQL 都不尽相同，故希望找一个能尽量兼容各种各样 SQL 语法的格式化工具。


## SQL 格式化模板示例


```sql
select *
from (
    select course_id
    from sgg.course_info
  ) a
left join (
    select course_id
    from sgg.course_info
    where course_id is not null
  ) b
  on a.course_id = b.course_id
left join course_info c
  on a.course_id = c.course_id

```

## 工具调研


### SQLFluff

[GitHub - sqlfluff/sqlfluff: A modular SQL linter and auto-formatter with support for multiple dialects and templated code.](https://github.com/sqlfluff/sqlfluff)
[📜 The SQL Linter for Humans — SQLFluff 2.3.5 documentation](https://docs.sqlfluff.com/en/stable/index.html)

特点：
1. Python 实现

优点：
1. 开源，社区活跃
2. 支持 VSCode 插件
3. 支持多种 SQL dialect
4. 支持代码检查，且支持多种检查规则，可自选
5. 支持多种配置
6. 支持 SQL 模板（jinja2、dbt）

缺点：
1. API 仅支持命令行 Cli、Python 调用
2. 代码检查和格式化速度太慢，不够轻量
3. 检查和格式化 SQL 模板时，需要事先手动配置模板参数 dummy 值


### sql-formatter

[GitHub - sql-formatter-org/sql-formatter: A whitespace formatter for different query languages](https://github.com/sql-formatter-org/sql-formatter)

特点：
1. TypeScript 实现
2. API 支持命令行 Cli、JavaScript 调用

优点：
1. API 支持命令行 Cli、JavaScript 调用，便于 Web 开发
2. 支持多种 SQL dialect
3. 支持格式化 SQL 文件
4. 格式化速度快

缺点：
1. 不支持SQL模板



### clickhouse-format

[clickhouse-format | ClickHouse Docs](https://clickhouse.com/docs/en/operations/utilities/clickhouse-format)
[ClickHouse/programs/format at master · ClickHouse/ClickHouse · GitHub](https://github.com/ClickHouse/ClickHouse/tree/master/programs/format)

特点：
1. C++实现

优点：
1. 官方开源，血统纯正，不担心烂尾

缺点：
1. 不支持格式化 SQL 文件
2. 不支持格式化 SQL 模板，仅支持可直接执行 SQL
3. API 仅支持命令行 Cli、C++调用
4. 仅支持 ClickHouse SQL


### 基于 ML 的代码格式化工具

[GitHub - antlr/codebuff: Language-agnostic pretty-printing through machine learning (uh, like, is this possible? YES, apparently).](https://github.com/antlr/codebuff)




## 参考链接

1. [clickhouse-format | ClickHouse Docs](https://clickhouse.com/docs/en/operations/utilities/clickhouse-format)
2. [GitHub - sqlfluff/sqlfluff: A modular SQL linter and auto-formatter with support for multiple dialects and templated code.](https://github.com/sqlfluff/sqlfluff)
3. [GitHub - treffynnon/sqlstyle.guide: A consistent code style guide for SQL to ensure legible and maintainable projects](https://github.com/treffynnon/sqlstyle.guide)