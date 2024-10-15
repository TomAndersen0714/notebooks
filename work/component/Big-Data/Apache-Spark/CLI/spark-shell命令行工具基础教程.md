# spark-shell 命令行工具基础教程

## 简介

Spark-shell 是 Apache Spark 提供的一个交互式命令行工具，它允许用户直接输入 Scala 代码来运行 Spark 操作。

其底层实际上是在交互式运行一个 Spark Application。

Spark-shell 为用户提供了一个即时的、交互式的环境来学习 Spark API、测试 Spark 代码片段、进行数据探索和分析。它在启动时自动创建一个 SparkSession 实例，使用户能够立即开始执行 Spark 任务。

## spark-shell 常用命令

## spark-shell 常见问题

报错信息：
`org.apache.hadoop.security.AccessControlException: Permission denied: user=root, access=WRITE, inode="/user":hdfs:supergroup:drwxr-xr-x`
原因：
Spark-shell 启动时，默认会使用当前登录的系统用户。从报错信息上来看，当前登录 Linux 系统时使用的用户为 `root`，执行操作为 `write`，但当前用户并没有写入对应路径的写入权限，于是报错。
解决方案：
1. 使用 sudo 命令，切换为可访问用户执行 `spark-shell`，如 `sudo -u spark spark-shell`

## 参考链接
1. [Quick Start - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/quick-start.html#interactive-analysis-with-the-spark-shell)
2. [大象教程-Spark Shell 的使用](https://www.hadoopdoc.com/spark/spark-shell)