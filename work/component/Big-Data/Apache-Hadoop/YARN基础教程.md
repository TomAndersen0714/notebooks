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


## Hadoop Job vs YARN Application

In terms of YARN, the programs that are being run on a cluster are called applications. In terms of MapReduce they are called jobs. So, if you are running MapReduce on YARN, job and application are the same thing (if you take a close look, job ids and application ids are the same).

在 Hadoop MR1 中，当时还并未引入 YARN，所有的提交的程序被称为 Job，而历史日志服务器也被称为 Job History Server，而当 YARN 出现之后，所有提交的程序就被称为了 Application，但 Hadoop MapReduce 中

[Difference between job, application, task, task attempt logs in Hadoop, Oozie - Stack Overflow](https://stackoverflow.com/a/35152984/13774262)

## 参考链接
1.  [Hadoop Documents Archive](https://hadoop.apache.org/docs/)