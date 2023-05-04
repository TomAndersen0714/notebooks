# XDT并发BUG修复技术方案设计

## 需求分析

解决数据库中间件xplat-data-transfer(XDT)在单机中多线程并行处理消息时，需要一种稳定的方案，用于在多线程并行消费消息流时定位各个批次数据的首个到达数据包，并在对应批次数据入库之前，对目标表进行一些定制化的前置动作，如删除分区、清空表等。



原方案采用了全局缓存加锁，通过共享变量来解决线程同步问题。但原方案使用的缓存设计存在BUG：缓存时使用表名作为key，使用batch_id作为value，每张表的缓存空间为1，每次有新的批次ID到达，则会删除前一个ID，并执行数据落盘的前置动作。当同一张表的多批次数据同时到达时，便会出现多线程协作的问题，因此需要重新设计缓存数据结构和算法。



缓存：

同一个分区的数据, 不同批次非交叉到达:
某个批次到达后, 缓存其batch_id, 如果缓存不存在, 则入库,
1. 如果batch_action_beforce_write是drop的话, 则正常执行drop命令; 

1. 如果batch_action_beforce_write是None的话, 则直接写入




1. 同一个分区的数据, 不同批次交叉到达: 某个批次到达后, 缓存其batch_id, 如果缓存不存在, 则入库, 如果某个批次的batch_action_beforce_write是drop的话, 则前一个到达的batch_id会被标记为deprecated；如果某个批次的batch_action_beforce_write是None的话, 则直接缓存写入即可

2. 不同分区的数据, 不同批次交叉到达, 或者非交叉到达: 由于不同分区的数据, 相互之间不会影响, 因此和情况1相同



缓存表1: 

done cache: (table_partition_id, batch_id)

缓存表2: 

deprecated cache: (table_partition_id, batch_id)



数据库：

数据库表1: done cache (id, table_partition_id, batch_id)

数据库表2: deprecated cache(id, table_partition_id, batch_id)

## 方案调研

常见的缓存替换策略(cache replacement policy), 或缓存替换算法(cache replacement algorithms)

https://en.wikipedia.org/wiki/Cache_replacement_policies



Redis的8种缓存淘汰策略(key eviction policy)

https://redis.io/docs/manual/eviction/





## 方案设计与实现



### 方案一：传统的LRU算法

Hash Map，Doubly Linked List。HashMap用于查询，Doubly Linked List用于增删改

缺点：传统的LRU算法需要指定capacity，如果capacity小于任务数时，则会导致程序出现BUG



### 方案二：改进后的LRU算法

#### 访问模式分析

基于对数据访问模式的分析，可以对算法的参数或者某些行为进行定制优化。而在XDT的使用场景中，数据是按批次进行同步的，因此数据的访问在时间上会呈现很强的连贯性，且等到数据稳定后一般将永远不再访问。多批次数据可能交替到达，同一批次数据通常连续到达。

#### 算法改进





## 缓存的易失性问题





## 性能测试

和传统的LRU算法对比

内存、cpu











