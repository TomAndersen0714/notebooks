# CDH HDFS负载均衡方案

## HDFS Balancers

**官方文档**：[HDFS Balancers | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/admin_hdfs_balancer.html#DummyId)

### 查看HDFS集群磁盘开销情况

```shell
sudo -u hdfs hdfs dfsadmin -report > balance_report.log
```

### 查看HDFS集群DataNode数据块分布情况

**图标库-DataNode-块数据统计-DataNode块总数分布**

![1671778903120-17](resources/images/CDH-HDFS负载均衡方案/1671778903120-17.png)

### 修改 Balancer 相关配置

**PS：避免资源使用过度，降低集群整体服务质量，甚至导致集群服务不可用，尽量选择支持热加载的配置**

1. `dfs.datanode.balance.max.concurrent.moves`

**默认值50，设置为20，修改后需要刷新集群配置（无需重启，但需要刷新配置，可能存在风险，暂时搁置）**

```Visual%20Basic
The property dfs.datanode.balance.max.concurrent.moves sets the maximum number of threads used by the DataNode balancer for pending moves. It is a throttling mechanism to prevent the balancer from taking too many resources from the DataNode and interfering with normal cluster operations. Increasing the value allows the balancing process to complete more quickly, decreasing the value allows rebalancing to complete more slowly, but is less likely to compete for resources with other tasks on the DataNode
```

2. `dfs.balancer.moverThreads`

**Thread pool size for executing block moves. 默认值1000，修改为10（无需重启）**

![img](resources/images/CDH-HDFS负载均衡方案/1671778903112-10.png)

3. `dfs.balancer.dispatcherThreads`

**Thread pool size for dispatching block moves. 默认值1000，修改为10（无需重启）**

![img](resources/images/CDH-HDFS负载均衡方案/1671778903113-11.png)

4. `Excluded Hosts`

**Hosts to exclude from the balancing process. 默认值为空，修改为关键服务所在节点，避免资源竞争（无需重启）**

![img](resources/images/CDH-HDFS负载均衡方案/1671778903113-12.png)

5. `Included Hosts`，**PS：Included Hosts和Excluded Hosts只能二选一**

**Hosts to include in the balancing process (uses all, if none specified). 默认值为空，修改为需要迁移数据的节点**

![1671778903113-13](resources/images/CDH-HDFS负载均衡方案/1671778903113-13.png)

6. **设置Balancer堆内存，由于内存资源充足，可以适当增加内存分配（无需重启）**

![1671778903114-14](resources/images/CDH-HDFS负载均衡方案/1671778903114-14.png)

## 线上操作流程记录

1. **查看HDFS集群DataNode数据块分布情况**

![1671778903120-17](resources/images/CDH-HDFS负载均衡方案/1671778903120-17.png)

2. **查看HDFS集群存储资源使用情况**

```Bash
sudo -u hdfs hdfs dfsadmin -report > hdfs_cluster.log
```

```Bash
WARNING: Use of this script to execute dfsadmin is deprecated.
WARNING: Attempting to execute replacement "hdfs dfsadmin" instead.

Configured Capacity: 233925873565696 (212.75 TB)
Present Capacity: 194364620761222 (176.77 TB)
DFS Remaining: 142016408862242 (129.16 TB)
DFS Used: 52348211898980 (47.61 TB)
DFS Used%: 26.93%
Replicated Blocks:
        Under replicated blocks: 0
        Blocks with corrupt replicas: 0
        Missing blocks: 0
        Missing blocks (with replication factor 1): 0
        Low redundancy blocks with highest priority to recover: 1006612
        Pending deletion blocks: 0
Erasure Coded Block Groups: 
        Low redundancy block groups: 0
        Block groups with corrupt internal blocks: 0
        Missing block groups: 0
        Low redundancy blocks with highest priority to recover: 0
        Pending deletion blocks: 0

-------------------------------------------------
Live datanodes (5):

Name: 10.20.133.149:9866 (zjk-bigdata008)
Hostname: zjk-bigdata008
Rack: /default
Decommission Status : Normal
Configured Capacity: 46785171357696 (42.55 TB)
DFS Used: 17442498123286 (15.86 TB)
Non DFS Used: 8487575546346 (7.72 TB)
DFS Remaining: 18492251082777 (16.82 TB)
DFS Used%: 37.28%
DFS Remaining%: 39.53%
Configured Cache Capacity: 4294967296 (4 GB)
Cache Used: 0 (0 B)
Cache Remaining: 4294967296 (4 GB)
Cache Used%: 0.00%
Cache Remaining%: 100.00%
Xceivers: 5
Last contact: Mon Jun 13 15:51:55 CST 2022
Last Block Report: Mon Jun 13 15:18:08 CST 2022


Name: 10.20.133.176:9866 (jstzjk-133176-prod-tb-bigdata-bigdata)
Hostname: jstzjk-133176-prod-tb-bigdata-bigdata
Rack: /default
Decommission Status : Normal
Configured Capacity: 46785188134912 (42.55 TB)
DFS Used: 62388162998 (58.10 GB)
Non DFS Used: 24216307105 (22.55 GB)
DFS Remaining: 44336217434281 (40.32 TB)
DFS Used%: 0.13%
DFS Remaining%: 94.77%
Configured Cache Capacity: 4294967296 (4 GB)
Cache Used: 0 (0 B)
Cache Remaining: 4294967296 (4 GB)
Cache Used%: 0.00%
Cache Remaining%: 100.00%
Xceivers: 2
Last contact: Mon Jun 13 15:51:54 CST 2022
Last Block Report: Mon Jun 13 14:01:35 CST 2022


Name: 10.20.2.28:9866 (zjk-bigdata006)
Hostname: zjk-bigdata006
Rack: /bigdata1
Decommission Status : Normal
Configured Capacity: 46785171357696 (42.55 TB)
DFS Used: 10989677917236 (10.00 TB)
Non DFS Used: 7785831898060 (7.08 TB)
DFS Remaining: 25646712005572 (23.33 TB)
DFS Used%: 23.49%
DFS Remaining%: 54.82%
Configured Cache Capacity: 4294967296 (4 GB)
Cache Used: 16384 (16 KB)
Cache Remaining: 4294950912 (4.00 GB)
Cache Used%: 0.00%
Cache Remaining%: 100.00%
Xceivers: 7
Last contact: Mon Jun 13 15:51:55 CST 2022
Last Block Report: Mon Jun 13 12:27:33 CST 2022


Name: 10.20.2.29:9866 (zjk-bigdata007)
Hostname: zjk-bigdata007
Rack: /bigdata1
Decommission Status : Normal
Configured Capacity: 46785171357696 (42.55 TB)
DFS Used: 12523060707424 (11.39 TB)
Non DFS Used: 9045297336224 (8.23 TB)
DFS Remaining: 22854447935488 (20.79 TB)
DFS Used%: 26.77%
DFS Remaining%: 48.85%
Configured Cache Capacity: 4294967296 (4 GB)
Cache Used: 0 (0 B)
Cache Remaining: 4294967296 (4 GB)
Cache Used%: 0.00%
Cache Remaining%: 100.00%
Xceivers: 5
Last contact: Mon Jun 13 15:51:55 CST 2022
Last Block Report: Mon Jun 13 13:08:53 CST 2022


Name: 10.20.2.30:9866 (zjk-bigdata005)
Hostname: zjk-bigdata005
Rack: /bigdata1
Decommission Status : Normal
Configured Capacity: 46785171357696 (42.55 TB)
DFS Used: 11330586988036 (10.31 TB)
Non DFS Used: 2404811877574 (2.19 TB)
DFS Remaining: 30686780404124 (27.91 TB)
DFS Used%: 24.22%
DFS Remaining%: 65.59%
Configured Cache Capacity: 4294967296 (4 GB)
Cache Used: 0 (0 B)
Cache Remaining: 4294967296 (4 GB)
Cache Used%: 0.00%
Cache Remaining%: 100.00%
Xceivers: 5
Last contact: Mon Jun 13 15:51:54 CST 2022
Last Block Report: Mon Jun 13 10:24:04 CST 2022
```

3. **按照指定配置运行负载均衡工具，同时监控对应日志（jstzjk-133176-prod-tb-bigdata-bigdata）**

```Fortran
cd /data0/var/log/hadoop-hdfs

tail -f hadoop-cmf-hdfs-BALANCER-jstzjk-133176-prod-tb-bigdata-bigdata.log.out
```

4. **通过观察发现，balancer当前参数的实际迁移速度太慢，故尝试增大相关参数，然后再次执行迁移命令，并监控对应日志（jstzjk-133176-prod-tb-bigdata-bigdata）**

- `dfs.balancer.moverThreads`，默认值为1000，从之前的10修改为100
- `dfs.balancer.dispatcherThreads`，默认值为1000，从之前的10修改为100

![1671778903114-15](resources/images/CDH-HDFS负载均衡方案/1671778903114-15.png)

5. **由于数据传输带宽限制（数据迁移在不影响稳定性前提下，预计需要1000小时左右），重新评估HDFS负载均衡的方案、工作量和必要性后，决定暂时挂起HDFS的负载均衡工作，决定后续再观察集群负载情况**

![1671778903114-16](resources/images/CDH-HDFS负载均衡方案/1671778903114-16.png)

## 参考链接

1. [HDFS Balancers | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/admin_hdfs_balancer.html#DummyId)