# Java ConcurrentHashMap


在 JDK1.7 中，ConcurrentHashMap 是将所有的 Entry 集合切分为 Segment 对象进行存储，针对每个 Segment 创建对应的锁来保证操作的原子性。

而自 JDK1.8 开始，ConcurrentHashMap 放弃了 Segment 的实现方式，而是直接采用和 HashMap Entry  类似的数据结构和算法，并且通过 Synchronized 关键字来保证操作的原子性。



参考链接