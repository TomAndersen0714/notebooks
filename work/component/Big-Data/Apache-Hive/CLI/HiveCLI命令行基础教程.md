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

## 常用子命令

[LanguageManual Commands - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Commands)

| Command                                                                                                                    | Description                                                                                                                                                                                                                                                                                                                                                                                                               |
| -------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| quit  <br>exit                                                                                                             | Use quit or exit to leave the interactive shell.                                                                                                                                                                                                                                                                                                                                                                          |
| reset                                                                                                                      | Resets the configuration to the default values (as of Hive 0.10: see [HIVE-3202](https://issues.apache.org/jira/browse/HIVE-3202)).                                                                                                                                                                                                                                                                                       |
| `set <key>=<value>`                                                                                                        | Sets the value of a particular configuration variable (key).  <br>**Note:** If you misspell the variable name, the CLI will not show an error.                                                                                                                                                                                                                                                                            |
| set                                                                                                                        | Prints a list of configuration variables that are overridden by the user or Hive.                                                                                                                                                                                                                                                                                                                                         |
| set -v                                                                                                                     | Prints all Hadoop and Hive configuration variables.                                                                                                                                                                                                                                                                                                                                                                       |
| `add FILE[S] <filepath> <filepath>*`  <br>`add JAR[S] <filepath> <filepath>* ` <br>`add ARCHIVE[S] <filepath> <filepath>*` | Adds one or more files, jars, or archives to the list of resources in the distributed cache. See [Hive Resources](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Cli#LanguageManualCli-HiveResources) below for more information.                                                                                                                                                                        |
| `add FILE[S] `<ivyurl>` <ivyurl>*  `<br>`add JAR[S] <ivyurl> <ivyurl>*  `<br>`add ARCHIVE[S] <ivyurl> <ivyurl>*`           | As of [Hive 1.2.0](https://issues.apache.org/jira/browse/HIVE-9664), adds one or more files, jars or archives to the list of resources in the distributed cache using an [Ivy](http://ant.apache.org/ivy/) URL of the form ivy://group:module:version?query_string. See [Hive Resources](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Cli#LanguageManualCli-HiveResources) below for more information. |
| list FILE[S]  <br>list JAR[S]  <br>list ARCHIVE[S]                                                                         | Lists the resources already added to the distributed cache. See [Hive Resources](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Cli#LanguageManualCli-HiveResources) below for more information.                                                                                                                                                                                                         |
| `list FILE[S] <filepath>*  `<br>`list JAR[S] <filepath>*  `<br>`list ARCHIVE[S] <filepath>*`                               | Checks whether the given resources are already added to the distributed cache or not. See [Hive Resources](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Cli#LanguageManualCli-HiveResources) below for more information.                                                                                                                                                                               |
| `delete FILE[S] `<filepath>`*  `<br>`delete JAR[S] `<filepath>`*  `<br>`delete ARCHIVE[S] `<filepath>`*`                   | Removes the resource(s) from the distributed cache.                                                                                                                                                                                                                                                                                                                                                                       |
| `delete FILE[S] <ivyurl> <ivyurl>*  `<br>`Delete JAR[S] <ivyurl> <ivyurl>*`  <br>`delete ARCHIVE[S]  <ivyurl> <ivyurl>*`   | As of [Hive 1.2.0](https://issues.apache.org/jira/browse/HIVE-9664), removes the resource(s) which were added using the <ivyurl> from the distributed cache. See [Hive Resources](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Cli#LanguageManualCli-HiveResources) below for more information.                                                                                                        |
| `! <command>`                                                                                                              | Executes a shell command from the Hive shell.                                                                                                                                                                                                                                                                                                                                                                             |
| `dfs <dfs command>`                                                                                                        | Executes a dfs command from the Hive shell.                                                                                                                                                                                                                                                                                                                                                                               |
| `<query string>`                                                                                                           | Executes a Hive query and prints results to standard output.                                                                                                                                                                                                                                                                                                                                                              |
| `source <filepath>`                                                                                                        | Executes a script file inside the CLI.                                                                                                                                                                                                                                                                                                                                                                                    |
## 参考链接

1. [Apache Hive - LanguageManual](https://cwiki.apache.org/confluence/display/Hive/LanguageManual)
2. [LanguageManual Commands - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Commands)
3. [Apache Hive - LanguageManual Cli](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Cli)