# Spark Application 常见性能问题


## Too slow


**Executor 分配的 Task 数量倾斜**

[数据本地性，引起task分配不均executor执行慢\_excutor的task数分布不均-CSDN博客](https://blog.csdn.net/young_0609/article/details/105755301)
[大叔问题定位分享（19）spark task在executors上分布不均 - 匠人先生 - 博客园](https://www.cnblogs.com/barneywill/p/10152659.html)

存储和计算（即 datanode 和 nodemanager）要么完全分开独立部署，要么完全部署在一起，不要一部分分开部署，一部分部署在一起，如果一定要这样，不要开启数据本地化特性，或者降低等待时间；

减小 `spark.locality.wait` 参数，实现 Task 的本地性等级快速降级。

## Timeout

- **Could not execute broadcast in 900 seconds**

增大 `spark.sql,broadcastTimeout` 参数，使得此参数的值大于 broadcast join 的时间开销即可，避免等待 broadcast 的时候 timeout。

## OOM

- **Task 中待处理的数据量倾斜，进而导致 Executor 出现 OOM**

通常是因为存在热点数据，导致数据出现倾斜。

参考：[Spark性能优化之数据倾斜问题解决方法](work/component/Big-Data/Apache-Spark/solution/Spark性能优化之数据倾斜问题解决方法.md)


- **Executor 减少，导致下游 stage 无法 skip，且分配较少的 Task，进而 Executor 出现 OOM**

两次任务的数据量相同，但后者 executor 数据量较少，task 也很少，进而出现 OOM（原因待定）。

1. 增大，`spark.scheduler.maxRegisteredResourcesWaitingTime`，增加等待分配 Executor 的时间，尽量避免提前启动 Job。
2. 增大，`spark.scheduler.minRegisteredResourcesRatio`，增大最小启动资源比例。
3. `spark.dynamicAllocation.enabled`???

