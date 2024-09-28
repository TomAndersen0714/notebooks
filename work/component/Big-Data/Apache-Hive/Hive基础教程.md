# Apache Hive 基础教程

## Introduction

Hive 的作用是什么，即 Hive 这个工具是用于解决什么场景下的什么问题？

即基于 HDFS 构建 DBMS，支持通过 SQL API 进行大数据处理应用开发。

## Hive on MapReduce

Hive on MapReduce 中会将 Hive SQL 按照执行计划，拆解为多个 Stage ，针对每个 Stage 启动一个 YARN Application 来执行对应的 MapReduce Job，即一个 Hive SQL on MapReduce，一般对应着多个 YARN Application，即多个 MapReduce Job。

## Hive on Spark

Hive on Spark 相比于 Hive on MR，一般会拆分为更少的 stage，因为 Hive on MR 不支持 reducer 之间进行依赖，并且一个 Hive SQL on Spark，仅对应一个 YARN Application，即一个 Spark Application。

[Hive on Spark - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/Hive+on+Spark)
[Hive On Spark执行计划总结\_hive on spark explain-CSDN博客](https://blog.csdn.net/jiangshouzhuang/article/details/52052398)

## Architecture

### Driver

编译器：将 SQL 解析，并进行语法校验，并生成对应的语法树
解析器：将语法树编译成逻辑计划
优化器：优化逻辑计划
执行器：将逻辑计划翻译成对应的物理计划

## Hive SQL

[Hive-SQL基础教程](work/component/Big-Data/Apache-Hive/development/Hive-SQL基础教程.md)

## Hive CLI

[HiveCLI命令行基础教程](work/component/Big-Data/Apache-Hive/CLI/HiveCLI命令行基础教程.md)

### 参考链接
1. [Apache Hive](https://hive.apache.org/)
2. [Home - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/)
3. [LanguageManual - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual)
4. [Javadocs](https://hive.apache.org/docs/javadocs/)
5. [微信-大数据的其妙冒险-建议收藏 | 大数据面试八股文之 Hive 篇](https://mp.weixin.qq.com/s/1IxDbMs1dSY0zMVveYDxRQ)