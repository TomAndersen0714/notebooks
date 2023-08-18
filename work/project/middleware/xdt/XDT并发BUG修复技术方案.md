# XDT并发BUG修复技术方案设计


## 需求分析

解决数据库中间件xplat-data-transfer(XDT)在单机中多线程并行处理消息时，需要一种稳定的方案，用于在多线程并行消费消息流时定位各个批次数据的首个到达数据包，并在对应批次数据入库之前，对目标表进行一些定制化的前置动作，如删除分区、清空表等。


原方案采用了全局缓存加锁，通过共享变量来解决线程同步问题。但原方案使用的缓存设计存在BUG：缓存时使用表名作为key，使用batch_id作为value，每张表的缓存空间为1，每次有新的批次ID到达，则会删除前一个ID，并执行数据落盘的前置动作。当同一张表的多批次数据同时到达时，便会出现多线程协作的问题，因此需要重新设计缓存数据结构和算法。


数据库：
同一个分区的数据, 不同批次非交叉到达:
某个批次到达后, 缓存其batch_id, 如果缓存不存在, 则入库,
1. 如果batch_action_beforce_write是drop的话, 则正常执行drop命令; 
2. 如果batch_action_beforce_write是None的话, 则直接写入
3. 同一个分区的数据, 不同批次交叉到达: 某个批次到达后, 缓存其batch_id, 如果缓存不存在, 则入库, 如果某个批次的batch_action_beforce_write是drop的话, 则前一个到达的batch_id会被标记为deprecated；如果某个批次的batch_action_beforce_write是None的话, 则直接缓存写入即可
4. 不同分区的数据, 不同批次交叉到达, 或者非交叉到达: 由于不同分区的数据, 相互之间不会影响, 因此和情况1相同


## 技术方案设计

### 数据库设计

```sql
CREATE TABLE IF NOT EXISTS ddl_task(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	create_time INT NOT NULL,
	update_time INT NOT NULL,
	task_id TEXT NOT NULL,
	task_status TEXT NOT NULL DEFAULT '',
	overwrite BOOLEAN NOT NULL DEFAULT FALSE,
	op_id TEXT NOT NULL,
	op_system TEXT NOT NULL,
	op_type TEXT NOT NULL DEFAULT '',
	op_db TEXT NOT NULL DEFAULT '',
	op_table TEXT NOT NULL DEFAULT '',
	op_partition TEXT NOT NULL DEFAULT ''
);

CREATE INDEX IF NOT EXISTS idx_ddl_task_id ON ddl_task(task_id);
CREATE INDEX IF NOT EXISTS idx_ddl_task_op_id_task_status ON ddl_task(op_id, task_status);
```


### 算法设计

Id：默认自增 ID
Create_time：Task 创建时间
Update_time：Task 更新时间

Task_id：Msg Header 中的 Batch_id
Task_status：Msg Header 中声明的任务一旦到达，则立刻执行，如果执行失败，则 Deprecated；如果 Overwrite=True，且将相同的 Op_id 且 Succeed 的 Task 设置 Task_status=Deprecated
- Succeed，成功，则后续 Header 中的相同 Task 直接跳过， Data 依旧写入 Buffer 
- Deprecated，丢弃，则后续 Header 中相同 Task 直接跳过，Data 也一起丢弃
Overwrite：
- True，则当前 Task 会将相同 op_id 的上一个 Task 的 Task_status 设置为 Deprecated，使其 Task 和对应的同步数据失效
- False，则无视
Op_id：op_system、op_type、op_db、op_table、op_partition的 Hash 值生成的 ID，用于快速检索
Op_system：DDL 执行所在的 System，ClickHouse
Op_type：DDL Task 的操作类型，如 add_range_partition、drop_partition、truncate_table
Op_db：DDL Task 操作的数据库
Op_table：DDL Task 操作的表
Op_partition：DDL Task 操作的分区



消息抵达时，首先查看 Header 中的 DDL Task 描述。

如果声明有 DDL Task，则：
1. 如果 Task ID 之前未执行过，则：
	1. 查看 overwrite 属性：
		1. True，则覆盖上一个相同 op 的 Task，并将其设置为 Deprecated
		2. Flase，则继续
	2. 执行 DDL Task
	3. 添加 DDL Task 执行成功记录到数据库中
2. 如果 Task ID 之前未执行过，则
	1. 查看 Task ID 是否是 Deprecated，如果是，则丢弃当前消息




