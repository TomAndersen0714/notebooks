# Spark JOIN 算法基础教程


## BroadCast Join


Spark SQL 中 BroadCast Join 自动触发的必要条件：
1. Join 的种类必须满足特定条件
	1. 左表 Inner Join, Right Join: `org.apache.spark.sql.execution.SparkStrategies.JoinSelection#canBuildLeft`
	2. 右表 Inner Join, Left Join, Left Semi Join, Left Anti Join:  `org.apache.spark.sql.execution.SparkStrategies.JoinSelection#canBuildRight`
	3. [pyspark - Broadcast join in spark not working for left outer - Stack Overflow](https://stackoverflow.com/questions/62735494/broadcast-join-in-spark-not-working-for-left-outer)
2. 小表为 Hive MetaStore 中存在的表
	1. Note that currently statistics are only supported for Hive Metastore tables where the command `ANALYZE TABLE <tableName> COMPUTE STATISTICS noscan` has been run
	2. [Performance Tuning - Spark 3.5.2 Documentation](https://spark.apache.org/docs/latest/sql-performance-tuning.html)
3. 小表读取并反序列化后的大小（而非表本身的大小）需小于 `spark.sql.autoBroadcastJoinThreshold`
	1. [Why Spark applies a broadcast join for a file with size larger than the autoBroadcastJoinThreshold? - Stack Overflow](https://stackoverflow.com/questions/67225487/why-spark-applies-a-broadcast-join-for-a-file-with-size-larger-than-the-autobroa)

## 参考链接

1. [【Spark的五种Join策略解析】\_spark sql中修改join策略-CSDN博客](https://blog.csdn.net/u012432611/article/details/132824637)
2. [Spark SQL中的broadcast join分析\_spark broadcast join-CSDN博客](https://blog.csdn.net/dabokele/article/details/65963401)
3. [pyspark - Broadcast join in spark not working for left outer - Stack Overflow](https://stackoverflow.com/questions/62735494/broadcast-join-in-spark-not-working-for-left-outer)
4. [Spark\_SparkSQL\_broadcast join不生效问题\_left join使用brodcast 没有生效-CSDN博客](https://blog.csdn.net/u010003835/article/details/132709518)