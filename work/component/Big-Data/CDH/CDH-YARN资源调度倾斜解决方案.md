# CDH-YARN资源调度倾斜解决方案

## 问题

在cdh 6.3.x版本中，提交spark任务，发现yarn分配的container集中于几台节点，其它节点没有分配。这显然会导致个别机器负载过高，影响集群稳定性。

## 原因

由于`yarn.scheduler.fair.assignmultiple`配置为true，导致单次YARN任务运行时所需资源都在同一个NM节点上分配。

## 相关配置

1. `yarn.scheduler.fair.assignmultiple`
	1. Whether to allow multiple container assignments in one heartbeat. Defaults to false。
	2. 这个配置项决定了是否在一次心跳分配请求中分配多个 container，在 CDH 中默认为 true，在 yarn 原生中默认为 false 的。如果为 true，任务量较少时（比如：单个任务）会发生资源倾斜，但是当要运行多个小任务时，该配置可以避免多次请求，提升吞吐量。
2. `yarn.scheduler.fair.dynamic.max.assign`.
	1. During node heartbeat, the ResourceManager will allocate up to half the available resources on a node. Only valid if yarn. Scheduler. Fair. Assignmultiple is set to true。
	2. 在 `yarn.scheduler.fair.assignmultiple` 为 true 时才能生效，用于控制 RM 是否可以动态使用 NN 节点的容器资源，最多至该节点容器资源的一半。
3. `yarn.scheduler.fair.maxassign`.
	1. Limit the number of containers allocated by the ResourceManager with each node heartbeat. -1 is equivalent to unlimited。Only valid if yarn. Scheduler. Fair. Assignmultiple is true and yarn. Scheduler. Fair. Dynamic. Max. Assign is false。
	2. 当 `yarn.scheduler.fair.assignmultiple` 为 true，且 `yarn.scheduler.fair.dynamic.max.assign` 为 false 时才能生效，用于控制每个 NN 节点单次可分配的容器数量。
4. `yarn.scheduler.minimum-allocation-mb`，容器可以请求的最小物理内存量（以 MiB 为单位）。
5. `yarn.scheduler.maximum-allocation-mb`，可为容器请求的最大物理内存数量（以 MiB 为单位）。

## 解决方法

### 方法 1
设置 CDH YARN 的配置 `yarn.scheduler.fair.assignmultiple` 为 false，重启 yarn 集群生效，这样分配的资源就不会发生倾斜了，会均匀分配到集群的多个节点中。当任务数较多时，整体吞吐率会下降。

### 方案 2
设置 CDH YARN 的配置 `yarn.scheduler.maximum-allocation-mb`，或者 CDH NodeManager 的配置 `yarn.nodemanager.resource.memory-mb`，通过降低可分配资源，降低资源调度倾斜带来的影响。

## 参考链接
1. https://blog.51cto.com/ikeguang/3054842
2. https://stackoverflow.com/questions/43826703/difference-between-yarn-scheduler-maximum-allocation-mb-and-yarn-nodemanager