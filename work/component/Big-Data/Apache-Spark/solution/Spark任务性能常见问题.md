# Spark 任务性能常见问题


## Too slow


**Executor 分配的 Task 数量倾斜**

[数据本地性，引起task分配不均executor执行慢\_excutor的task数分布不均-CSDN博客](https://blog.csdn.net/young_0609/article/details/105755301)
[大叔问题定位分享（19）spark task在executors上分布不均 - 匠人先生 - 博客园](https://www.cnblogs.com/barneywill/p/10152659.html)

存储和计算（即 datanode 和 nodemanager）要么完全分开独立部署，要么完全部署在一起，不要一部分分开部署，一部分部署在一起，如果一定要这样，不要开启数据本地化特性，或者降低等待时间；


## OOM

- **Task 中待处理的数据量倾斜，进而导致 Executor 出现 OOM**

通常是因为存在热点数据，导致数据出现倾斜。
参考：[Spark性能优化之数据倾斜问题解决方法](work/component/Big-Data/Apache-Spark/solution/Spark性能优化之数据倾斜问题解决方法.md)

- **Executor 减少，导致下游 stage 无法 skip，且分配较少的 Task，进而 Executor 出现 OOM**

两次任务的数据量相同，但后者 executor 数据量较少，task 也很少，进而使得 OOM（原因待定）

- **Could not execute broadcast in 900 seconds**