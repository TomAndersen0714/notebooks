# MySQL DML 基础教程

## Insert Into


### on duplicate key update

[MySQL中ON DUPLICATE KEY UPDATE的介绍与使用、批量更新、存在即更新不存在则插入-CSDN博客](https://blog.csdn.net/weixin_49114503/article/details/136479860)

### replace into 和 on duplicate key update 的区别

`Replace into` 与 `on duplicate key update` 在一定程度上都能实现无记录时插入，有记录时更新这个需求。但是强烈推荐使用 on duplicate key update 原因如下：

1. Replace into 在唯一索引冲突时，会删除原记录，然后新增一条记录，如果主键 id 是自增的会导致主键被改变；而 on duplicate key update 在唯一索引冲突时是更新原记录，主键不变。
2. Replace into 唯一索引冲突时会导致主键自增值增加，但由于 binlog 事件记录为 UPDATE 会导致主从环境中表的 AUTO-INCREMENT 值不同，从库执行 UPDATE 事件并不会导致 AUTO-INCREMENT 值增加，所以从库表的 AUTO-INCREMENT 值会小于等于当前表的最新记录主键，当发生主从切换时向新的主库插入记录就会报 duplicate key 错误。

鉴于此，很多使用 REPLACE INTO 的场景，实际上需要的是 `INSERT INTO … ON DUPLICATE KEY UPDATE`（虽然也会增加自增值，但是不会出现从库表的 AUTO-INCREMENT 值会比当前表的最新记录主键小的情况），与 `REPLACE INTO …` 不同，它只是更新重复行上的值，没有删除，也就不会导致原有主键值的变化。


## 参考链接

1. [MySQL中replace into详解、批量更新、不存在插入存在则更新、replace into的坑\_mysql replace into-CSDN博客](https://blog.csdn.net/weixin_49114503/article/details/136765571)
2. [MySQL中ON DUPLICATE KEY UPDATE的介绍与使用、批量更新、存在即更新不存在则插入-CSDN博客](https://blog.csdn.net/weixin_49114503/article/details/136479860)