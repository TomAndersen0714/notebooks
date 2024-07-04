# spark-sql  基础教程

## 简介

Spark-sql 命令行工具是 Apache Spark 的一个组件，它允许用户直接在命令行中执行 SQL 语句，而无需编写额外的代码。

其底层实际上是在交互式运行一个 Spark Application。

这个工具特别适合进行快速的数据探索和测试 SQL 查询，它提供了一个简单而强大的方式来与 Spark SQL 交互。

## 常用选项

```shell
CLI options:
 -d,--define <key=value>          Variable substitution to apply to Hive
                                  commands. e.g. -d A=B or --define A=B
    --database <databasename>     Specify the database to use
 -e <quoted-query-string>         SQL from command line
 -f <filename>                    SQL from files, 设置 Spark SQL 读取并执行的 SQL 文件名，SQL 文件中支持分号（semicolon）分割多个 SQL
 -H,--help                        Print help information
    --hiveconf <property=value>   Use value for given property
    --hivevar <key=value>         Variable substitution to apply to Hive
                                  commands. e.g. --hivevar A=B
 -i <filename>                    Initialization SQL file
 -S,--silent                      Silent mode in interactive shell
 -v,--verbose                     Verbose mode (echo executed SQL to the
                                  console)
 --queue`                         设置 Spark Application 提交队列
```

## 常用特殊 shell 命令

| Command                  | Description                                                       |
| ------------------------ | ----------------------------------------------------------------- |
| `quit or exit`           | Exits the interactive shell.                                      |
| `!<command>`             | Executes a shell command from the Spark SQL CLI shell.            |
| `dfs <HDFS dfs command>` | Executes a HDFS dfs command from the Spark SQL CLI shell.         |
| `<query string>`         | Executes a Spark SQL query and prints results to standard output. |
| `source <filepath>`      | Executes a script file inside the CLI.                            |

## 参考链接

1. [Spark SQL CLI - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/sql-distributed-sql-engine-spark-sql-cli.html#spark-sql-cli)
2. [Spark-SQL基础教程](work/component/Big-Data/Apache-Spark/Spark-SQL基础教程.md)