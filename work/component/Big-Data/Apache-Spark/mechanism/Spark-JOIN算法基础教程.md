# Spark JOIN 算法基础教程


## BroadCast Join


Spark SQL 中 BoardCast Join 自动触发的必要条件：
1. 小表为非 Left Join (Left Outer Join)。[pyspark - Broadcast join in spark not working for left outer - Stack Overflow](https://stackoverflow.com/questions/62735494/broadcast-join-in-spark-not-working-for-left-outer)
2. 小表为 Hive MetaStore 中存在的表。`Note that currently statistics are only supported for Hive Metastore tables where the command ANALYZE TABLE <tableName> COMPUTE STATISTICS noscan has been run` 。



## 参考链接

1. [【Spark的五种Join策略解析】\_spark sql中修改join策略-CSDN博客](https://blog.csdn.net/u012432611/article/details/132824637)
2. [Spark SQL中的broadcast join分析\_spark broadcast join-CSDN博客](https://blog.csdn.net/dabokele/article/details/65963401)
3. [pyspark - Broadcast join in spark not working for left outer - Stack Overflow](https://stackoverflow.com/questions/62735494/broadcast-join-in-spark-not-working-for-left-outer)
4. [Spark\_SparkSQL\_broadcast join不生效问题\_left join使用brodcast 没有生效-CSDN博客](https://blog.csdn.net/u010003835/article/details/132709518)