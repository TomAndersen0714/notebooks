# Spark 性能优化之数据倾斜问题解决方案

## 诊断方式

在 Spark Application Master 或 Spark HistoryServer 中查看 Spark UI Web，通过以下方式来判断是否具备数据倾斜的症状：
1. EventTimeLine
2. Executor shuffle read

## 优化方案

### Group By 倾斜时

如果是满足结合律（即因子的顺序不影响结果）的指标，如：sum/max/min 这类，则可以直接水平拆分（热点数据单独处理）、垂直拆分（分步聚合），或者两种拆分方式相互组合。

### Join 倾斜时

大表 Join 小表倾斜

大表 Join 大表倾斜

## 参考链接

1. [Spark性能优化之道——解决Spark数据倾斜（Data Skew）的N种姿势-腾讯云开发者社区-腾讯云](https://cloud.tencent.com/developer/article/1146295)
2. [解决Spark数据倾斜（Data Skew）的N种姿势 | 技术世界 | spark,大数据,集群,消息系统,郭俊 Jason,spark 优化,大数据架构,数据倾斜,技术世界,data skew,spark sql](http://www.jasongj.com/spark/skew/)
3. [spark 数据倾斜优化 - 阿伟宝座 - 博客园](https://www.cnblogs.com/saowei/p/16044630.html)
4. [关于Spark数据倾斜调优看这一篇就够了\_spark 数据倾斜调优-CSDN博客](https://blog.csdn.net/Aaron_ch/article/details/122224043)