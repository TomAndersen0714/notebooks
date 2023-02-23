# CDH-YARN资源调度倾斜解决方案

## 问题

在cdh 6.3.x版本中，提交spark任务，发现yarn分配的container集中于几台节点，其它节点没有分配。这显然会导致个别机器负载过高，影响集群稳定性。

## 原因

由于`yarn.scheduler.fair.assignmultiple`配置为true，导致单次YARN任务运行时所需资源都在同一个NM节点上分配。

## 相关配置

1. **yarn.scheduler.fair.assignmultiple**. Whether to allow multiple container assignments in one heartbeat. Defaults to false。
   这个配置项决定了是否在一次心跳分配请求中分配多个containe，在CDH中默认为true，在yarn原生中默认为false的。如果为true，任务量较少时（比如：单个任务）会发生资源倾斜，但是当要运行多个小任务时，该配置可以避免多次请求，提升吞吐量。
2. **yarn.scheduler.fair.dynamic.max.assign**. During node heartbeat, the ResourceManager will allocate up to half the available resources on a node. Only valid if yarn.scheduler.fair.assignmultiple is set to true。
   在`yarn.scheduler.fair.assignmultiple`为true时才能生效，用于控制RM是否可以动态使用NN节点的容器资源，最多至该节点容器资源的一半。
3. **yarn.scheduler.fair.maxassign**. Limit the number of containers allocated by the ResourceManager with each node heartbeat. -1 is equivalent to unlimited。Only valid if yarn.scheduler.fair.assignmultiple is true and yarn.scheduler.fair.dynamic.max.assign is false。
   当`yarn.scheduler.fair.assignmultiple`为true，且`yarn.scheduler.fair.dynamic.max.assign`为false时才能生效，用于控制每个NN节点单次可分配的容器数量。
4. yarn.scheduler.minimum-allocation-mb
   容器可以请求的最小物理内存量（以 MiB 为单位）
5. yarn.scheduler.maximum-allocation-mb
   可为容器请求的最大物理内存数量（以 MiB 为单位）


## 解决办法

### 方案1. 
设置CDH YARN的配置`yarn.scheduler.fair.assignmultiple`为false，重启yarn集群生效，这样分配的资源就不会发生倾斜了，会均匀分配到集群的多个节点中。当任务数较多时，整体吞吐率会下降

### 方案2.
设置CDH YARN的配置`yarn.scheduler.maximum-allocation-mb`，或者CDH NodeManager的配置`yarn.nodemanager.resource.memory-mb`，通过降低可分配资源，降低资源调度倾斜带来的影响。


## 参考链接
1. https://blog.51cto.com/ikeguang/3054842
2. https://stackoverflow.com/questions/43826703/difference-between-yarn-scheduler-maximum-allocation-mb-and-yarn-nodemanager