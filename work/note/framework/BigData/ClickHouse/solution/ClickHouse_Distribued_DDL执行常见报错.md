# ClickHouse Distribued DDL执行失败问题排查

## 前言

ClickHouse Server: 20.4.2.9
CentOS: CentOS Linux release 7.6.1810


## 报错示例


### Code: 48

Code: 48. DB::Exception: There was an error on [znzjk-113175-prod-mini-bigdata-bigdata:29000]: Cannot execute replicated DDL query on leader

**原因**：DDL执行时对应副本的Leader执行超时导致报错，而非报错信息指向的节点内部存在问题

PS：对于附加了FINAL关键字的DDL查询，每次执行时都会强制进行合并，因此多次执行可能多次超时

![image-20221108171759644](images/ClickHouse_Distribued_DDL%E6%89%A7%E8%A1%8C%E5%A4%B1%E8%B4%A5%E9%97%AE%E9%A2%98%E6%8E%92%E6%9F%A5/image-20221108171759644.png)



![image-20221108171854006](images/ClickHouse_Distribued_DDL%E6%89%A7%E8%A1%8C%E5%A4%B1%E8%B4%A5%E9%97%AE%E9%A2%98%E6%8E%92%E6%9F%A5/image-20221108171854006.png)




### Code: 159

Code: 159. DB::Exception: Watching task /clickhouse/task_queue/ddl/query-0000974691 is executing longer than distributed_ddl_task_timeout (=180) seconds. There are 1 unfinished hosts (0 of them are currently active), they are going to execute the query in background.

**原因**：DDL执行时超时，可能是DDl队列（FIFO）中存在阻塞，或者当前DDL query执行自身出现问题而超时


## 问题排查

### 方法一：github issue

#### issue检索

https://github.com/ClickHouse/ClickHouse/issues?q=Cannot+execute+replicated+DDL+query+on+leader+

#### 相关issue

https://github.com/ClickHouse/ClickHouse/issues/11884



### 方法二：源码阅读

#### DDLWorker.cpp

```c++
bool DDLWorker::tryExecuteQueryOnLeaderReplica(
    DDLTask & task,
    StoragePtr storage,
    const String & rewritten_query,
    const String & node_path,
    const ZooKeeperPtr & zookeeper)
{
    StorageReplicatedMergeTree * replicated_storage = dynamic_cast<StorageReplicatedMergeTree *>(storage.get());

    /// If we will develop new replicated storage
    if (!replicated_storage)
        throw Exception("Storage type '" + storage->getName() + "' is not supported by distributed DDL", ErrorCodes::NOT_IMPLEMENTED);

    /// Generate unique name for shard node, it will be used to execute the query by only single host
    /// Shard node name has format 'replica_name1,replica_name2,...,replica_nameN'
    /// Where replica_name is 'replica_config_host_name:replica_port'
    auto get_shard_name = [] (const Cluster::Addresses & shard_addresses)
    {
        Strings replica_names;
        for (const Cluster::Address & address : shard_addresses)
            replica_names.emplace_back(address.readableString());
        std::sort(replica_names.begin(), replica_names.end());

        String res;
        for (auto it = replica_names.begin(); it != replica_names.end(); ++it)
            res += *it + (std::next(it) != replica_names.end() ? "," : "");

        return res;
    };

    String shard_node_name = get_shard_name(task.cluster->getShardsAddresses().at(task.host_shard_num));
    String shard_path = node_path + "/shards/" + shard_node_name;
    String is_executed_path = shard_path + "/executed";
    zookeeper->createAncestors(shard_path + "/");

    auto is_already_executed = [&]() -> bool
    {
        String executed_by;
        if (zookeeper->tryGet(is_executed_path, executed_by))
        {
            LOG_DEBUG(log, "Task " << task.entry_name << " has already been executed by leader replica ("
                << executed_by << ") of the same shard.");
            return true;
        }

        return false;
    };

    pcg64 rng(randomSeed());

    auto lock = createSimpleZooKeeperLock(zookeeper, shard_path, "lock", task.host_id_str);
    static const size_t max_tries = 20;
    bool executed_by_leader = false;
    for (size_t num_tries = 0; num_tries < max_tries; ++num_tries)
    {
        if (is_already_executed())
        {
            executed_by_leader = true;
            break;
        }

        StorageReplicatedMergeTree::Status status;
        replicated_storage->getStatus(status);

        /// Leader replica take lock
        if (status.is_leader && lock->tryLock())
        {
            if (is_already_executed())
            {
                executed_by_leader = true;
                break;
            }

            /// If the leader will unexpectedly changed this method will return false
            /// and on the next iteration new leader will take lock
            if (tryExecuteQuery(rewritten_query, task, task.execution_status))
            {
                zookeeper->create(is_executed_path, task.host_id_str, zkutil::CreateMode::Persistent);
                executed_by_leader = true;
                break;
            }

        }

        /// Does nothing if wasn't previously locked
        lock->unlock();
        std::this_thread::sleep_for(std::chrono::milliseconds(std::uniform_int_distribution<int>(0, 1000)(rng)));
    }

    /// Not executed by leader so was not executed at all
    if (!executed_by_leader)
    {
        task.execution_status = ExecutionStatus(ErrorCodes::NOT_IMPLEMENTED,
                                                "Cannot execute replicated DDL query on leader");
        return false;
    }
    return true;
}
```




## 问题总结

主要是由于ClickHouse在执行针对Replicated表执行DDL语句时，采取的是FIFO队列，一旦某个DDL执行速度太慢，就会导致后续的DDL阻塞，进而出现超时。



## 修复版本

https://github.com/ClickHouse/ClickHouse/pull/13450



## 参考链接

https://github.com/ClickHouse/ClickHouse/issues?q=Cannot+execute+replicated+DDL+query+on+leader+

https://github.com/ClickHouse/ClickHouse/issues/11884

https://github.com/ClickHouse/ClickHouse/issues/8282

https://github.com/ClickHouse/ClickHouse/pull/13450