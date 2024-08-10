# Spark Web UI 基础教程


## SQL

Spark Web UI 的 SQL 一栏中，展示了 Spark 执行计划对应的 SQL 视图，如果 Spark Application 中执行了 SQL，则会直接展示对应的 SQL 的实际执行计划在该页面。

同时 SQL 的实际执行计划最后的节点，也会显示当前 SQL query 每次执行的结果，如：写入操作，则执行计划的日志会显示写入的文件数和数据量。


## Spark Web UI 中如何定位执行计划对应的代码

在进行 Spark Application（主要是 Spark SQL 的情况） 性能优化和问题排查时，经常需要通过 Spark Web SQL 页面查看对应 application 的执行计划，并确定事故点对应的代码片段：

1. 通过 Stage 页面中的 DAG visualization，在 SQL 页面实际执行计划图形中的出现的相对位置，进而得出其在原始 SQL 中的相对位置，确定对应的 SQL 代码段
2. 通过 Stage 页面中 Exchange 的 coordinator id，在 SQL 页面实际执行计划图形中的位置，从而得出对应 DAG visualization 在整体执行计划中的出现的相对位置，进而得出其在原始 SQL 中的相对位置，确定对应的 SQL 代码段

## 参考链接
1. [《Spark官方文档》Web UI -学习笔记\_spark ui 显示的 资源使用-CSDN博客](https://blog.csdn.net/weixin_43161811/article/details/123407165)
2. [Spark UI - kris12 - 博客园](https://www.cnblogs.com/shengyang17/p/17713897.html)
3. [SparkUI超详细解释（3）——Storage\_spark ui storge-CSDN博客](https://blog.csdn.net/2301_77818583/article/details/130393146)
4. [Web UI - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/web-ui.html)