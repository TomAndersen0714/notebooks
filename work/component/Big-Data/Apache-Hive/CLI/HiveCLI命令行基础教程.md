# Hive CLI 命令行基础教程

## Command Line Options

To get help, run "`hive -H`" or "`hive --help`".
Usage (as it is in Hive 0.9.0):

```bash
usage: hive
 -d,--define <key=value>          Variable substitution to apply to Hive
                                  commands. e.g. -d A=B or --define A=B
 -e <quoted-query-string>         SQL from command line
 -f <filename>                    SQL from files
 -H,--help                        Print help information
 -h <hostname>                    Connecting to Hive Server on remote host
    --hiveconf <property=value>   Use value for given property
    --hivevar <key=value>         Variable substitution to apply to hive
                                  commands. e.g. --hivevar A=B
 -i <filename>                    Initialization SQL file
 -p <port>                        Connecting to Hive Server on port number
 -S,--silent                      Silent mode in interactive shell
 -v,--verbose                     Verbose mode (echo executed SQL to the
                                  console)
--database <dbname>      Specify the database to use
```

Note: The variant "`-hiveconf`" is supported as well as "`--hiveconf`".

## hiverc File

The CLI when invoked without the -i option will attempt to load `$HIVE_HOME/bin/.hiverc` and `$HOME/.hiverc` as initialization files.

Hive Shell 在启动时，会自动加载相关的初始化文件 `.hiverc`（类似于，Linux Shell 环境变量），脚本中都是 Hive 命令，常见的是通过 `add jar + create function` 等命令，来初始化 UDF、UDTF 等函数。

## Hive 命令行常用命令

[Hive命令基础教程](work/component/Big-Data/Apache-Hive/Hive命令基础教程.md)

## 参考链接

1. [Apache Hive - LanguageManual](https://cwiki.apache.org/confluence/display/Hive/LanguageManual)
2. [LanguageManual Commands - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Commands)
3. [Apache Hive - LanguageManual Cli](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Cli)