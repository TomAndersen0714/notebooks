# Spark 性能优化之数据倾斜问题解决方案

## 诊断方式

在 Spark Application Master 或 Spark HistoryServer 中查看 Spark UI Web，通过以下指标来判断是否具备数据倾斜的症状：

1. EventTimeLine
2. Executor shuffle read

## 优化方案

### Group By 倾斜

如果是满足结合律（即表达式的因子顺序交换不影响结果）的指标，如：sum/max/min 这类，则可以采取以下三种方式：

1. 垂直拆分：增加聚合字段，减小聚合粒度，从细到粗，分步聚合
	1. 如 `Group By department`，改为 `Group By department, user` 等
	2. 如果没有合适的字段，也可以自行生成新字段，如 `floor(random(5))`
2. 水平拆分：热点数据手动统计并采样，热点数据单独处理
3. 两种拆分方式相互组合

### Join 倾斜

1. 小表 Join 小表倾斜
	- 针对小表，调整当前 Application 的 Broadcast 参数，适当放大阈值，触发 Broadcast Join，避免出现 Shuffle 倾斜
2. 大表 Join 小表倾斜
	1. 针对小表，调整当前 Application 的 Broadcast 参数，适当放大阈值，触发 Broadcast Join，避免出现 Shuffle 倾斜
	2. 减小读取数据量，将大表转换为小表
3. 大表 Join 大表倾斜

## 参考链接

1. [Spark-SQL性能优化基础教程](work/component/Big-Data/Apache-Spark/library/SparkSQL/Spark-SQL性能优化基础教程.md)
2. [Spark性能优化之道——解决Spark数据倾斜（Data Skew）的N种姿势-腾讯云开发者社区-腾讯云](https://cloud.tencent.com/developer/article/1146295)
3. [解决Spark数据倾斜（Data Skew）的N种姿势 | 技术世界 | spark,大数据,集群,消息系统,郭俊 Jason,spark 优化,大数据架构,数据倾斜,技术世界,data skew,spark sql](http://www.jasongj.com/spark/skew/)
4. [spark 数据倾斜优化 - 阿伟宝座 - 博客园](https://www.cnblogs.com/saowei/p/16044630.html)
5. [关于Spark数据倾斜调优看这一篇就够了\_spark 数据倾斜调优-CSDN博客](https://blog.csdn.net/Aaron_ch/article/details/122224043)