# YARN 基础教程


## YARN 应用的运行机制


1. Client: submit YARN application to Resource Manager
2. Resource Manager: allocate a container in Node Manager
3. Node Manager: launch a container
4. Node Manager: request more containers in heartbeat to Resource Manager
5. Node Manager: launch other containers

## YARN 中的资源调度机制

FIFO：先入先出队列，无法保证整体服务质量。
Capacity：多个先入先出队列，实现资源隔离，无法保证集群的资源利用率。
Fair：根据当前正在运行的任务，动态分配计算资源，可以在资源利用率和服务质量之间实现平衡。PS：是否可能出现同时提交任务过多，而使得集群资源平均值很小，导致所有任务都执行很慢。

## 参考链接
1.  [Hadoop Documents Archive](https://hadoop.apache.org/docs/)