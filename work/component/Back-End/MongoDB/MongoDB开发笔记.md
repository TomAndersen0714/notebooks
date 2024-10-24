# MongoDB 开发笔记

## Reading

### Explain

**读取 MongoDB 数据时，需要查看查询的执行计划，以及使用 LIMIT 1 来执行冒烟测试，保证 Mongo 查询的性能，避免提交慢查询**

**MongoDB 慢查询，通常即便客户端断开链接也不会被动结束，需要服务器端手动终结**

MongoDB 拉取数据时，在正式执行之前，必须通过 `cursor.explain`  方法获取对应的执行计划，观察是否命中索引，同时查询要使用 `limit(1)` 避免提交慢查询导致执行时间太久。

其中 explain 的 verbose 参数使用默认值 `queryPlanner`。

### Index Hint

一般情况下，**建议不要使用 Index hint 强制使用索引**，直接使用 Mongo 默认的优化计划，除非你十分了解 Mongo 底层的索引原理

### SecondaryPreferred

**读取 MongoDB 数据时，如果对数据一致性要求不严格，可以将 secondaryPreferred 参数设置为 SECONDARY**
MongoDB 读取 database 和 collection 拉取数据时，可以尝试使用 read_preference 参数，并将其显式设置为 SECONDARY，而非默认的 Primary，即避免直接查询主库，增加主库的负载，保证主要业务流程不受影响，即手动进行资源隔离，即便从库宕机，也不影响主库的正常业务。

## Mongo Client

### PyMongo

**MongoDB Python API 官方文档：**
Pymongo：“ https://pymongo.readthedocs.io/en/stable/api/pymongo/index.html#module-pymongo ”

**PyMongo 读写 datetime 类型字段注意事项：所有不带时区的 datetime 字段，在 Mongo 中读写时都会被视为 UTC 时间**
1. PyMongo 在读取 MongoDB datetime 类型的字段时，默认会返回不带时区信息的 UTC datetime 对象
2. PyMongo 在写入 datetime 类型的字段时，会自动根据其时区转换为通用的时间戳（64bit）进行存储（和对应时区 linux epoch 的相对时间），如果未定义时区（不论是何种开发语言），则默认为 UTC 时间，即时区为 0
3. PyMongo 在筛选 datetime 类型的字段时，也是同样的原理，即如果过滤条件中的 datetime 字段没有规定时区，则视为 UTC 时间，即在转换成时间戳时便按照 UTC 时区进行转换，然后使用对应的时间戳进行过滤