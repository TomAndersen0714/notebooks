# Spark Job 性能优化基础教程


## Spark Job 性能问题的宏观原因

原因 1：客户侧资源实际利用率低。即有资源，但没有使用或者使用不当。
1. 客户侧提交任务中的算法问题

原因 2：服务侧资源总量有限。即没有资源，巧妇难为无米之炊
1. 服务侧资源总量有限，限制了客户侧的性能


## 性能分析

[CSDN-诸葛子房-Spark 任务优化分析](https://blog.csdn.net/weixin_43291055/article/details/133770448)

[SparkSql 慢任务诊断案例](https://mp.weixin.qq.com/s/3RrpzO5rPthKfyGX8MvnFw)

## Adaptive Query Execution


## 参考链接
1. [Performance Tuning - Spark 3.2.0 Documentation](https://spark.apache.org/docs/3.2.0/sql-performance-tuning.html)
2. [Spark性能优化指南——基础篇 - 美团技术团队](https://tech.meituan.com/2016/04/29/spark-tuning-basic.html)
3. [Spark性能优化指南——高级篇 - 美团技术团队](https://tech.meituan.com/2016/05/12/spark-tuning-pro.html)
4. [Spark数据倾斜问题分析和解决-CSDN博客](https://blog.csdn.net/weixin_43291055/article/details/133770448)