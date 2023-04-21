# MongoDB开发笔记


**MongoDB Python API官方文档：**
pymongo：“https://pymongo.readthedocs.io/en/stable/api/pymongo/index.html#module-pymongo”

**MongoDB的适用场景**
MongoDB单点查询，只要索引能够唯一命中（如：\_id），则qps和查询性能都很强，但是MongoDB并不适合批量查询和聚合，速度会很慢，因为一旦某个查询没有命中索引，查询性能就会直线下降，而且MongoDB的索引构建之后，难以维护。

因此MongoDB通常会搭配ES一起使用，ES充当一级索引引擎，Mongo索引充当二级索引。如存储各个字段到_id的映射关系，充当二级索引，查询到MongoDB ObjectId之后，再去反查MongoDB命中索引，以获取对应的明细数据。

**Mongo索引使用注意事项：**
一般情况下，建议禁止使用hint强制索引，直接使用Mongo默认的优化计划，除非你十分了解Mongo底层的索引原理


**读取MongoDB数据时，如果对数据一致性要求不严格，可以将secondaryPreferred参数设置为secondaryPreferred**
MongoDB读取database和collection拉取数据时，可以尝试使用read_preference参数，并将其显式设置为secondaryPreferred，而非默认的Primary，即避免直接查询主库，增加主库的负载，保证主要业务流程不受影响，即手动进行资源隔离。


**读取MongoDB数据时，需要查看查询的执行计划，保证查询性能，并且测试性能时需要使用LIMIT 1，避免提交慢查询**
MongoDB拉取数据时，在正式执行之前，务必进行先要使用limit和（冒烟测试），并且最好先在Mongo Shell中通过cursor explain获取对应的执行计划，观察是否命中索引。
**PS：MongoDB慢查询，通常即便客户端断开链接也不会被动结束，需要服务器端手动终结**


**MongoDB读写datetime类型字段注意事项：所有不带时区的datetime，在Mongo中读写时都会被视为UTC时间**
1. MongoDB在写入datetime类型的数据时，会自动根据其时区转换为通用的时间戳（64bit）进行存储（和对应时区linux元年的相对时间），如果未定义时区（不论是何种开发语言），则默认为UTC时间，即时区为0。
2. MongoDB在查询datetime类型的字段时也是同样的原理，即如果过滤条件中的datetime字段没有规定时区，则认为是UTC时间，即在转换成时间戳时便按照UTC时区进行转换，然后使用对应的时间戳进行过滤。
