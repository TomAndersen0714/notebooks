# Spark 任务性能常见问题


**Executor 分配的 Task 数量倾斜**

[数据本地性，引起task分配不均executor执行慢\_excutor的task数分布不均-CSDN博客](https://blog.csdn.net/young_0609/article/details/105755301)
[大叔问题定位分享（19）spark task在executors上分布不均 - 匠人先生 - 博客园](https://www.cnblogs.com/barneywill/p/10152659.html)

存储和计算（即 datanode 和 nodemanager）要么完全分开独立部署，要么完全部署在一起，不要一部分分开部署，一部分部署在一起，如果一定要这样，不要开启数据本地化特性；


**Task 的数据量倾斜**

