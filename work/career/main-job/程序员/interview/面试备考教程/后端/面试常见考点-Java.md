# 面试常见考点-Java

## Java核心概念

### JVM

- JVM 内存模型中主要分为哪几个部分？
[JVM内存模型](work/programming/Java/mechanism/JVM内存模型.md)

- JVM 类加载过程
[JVM类加载机制](work/programming/Java/mechanism/JVM类加载机制.md)

### Java Collection

- ArrayList 和 LinkedList 的区别是什么？

- Hashmap 的底层实现原理？

[Java-HashMap基础教程](work/programming/Java/programming/Intermediate/Collections/Java-HashMap基础教程.md)

JDk1.8，中 HashMap 是通过数组（Hash 分桶）+链表、红黑树的方式实现的。

https://cloud.tencent.com/developer/article/1830825

HashMap 中有两种节点 Node，一种 Node 是可以构成链表的普通 Node 也是一种 Linked Node，一种 Node 是可以构成树的 TreeNode。

当 Put 方法执行时，某个 Linked Node 组成的链表长度大于 HashMap 类设置的阈值时，Put 方法会调用相应的 Node 链表转换方法，将该 Node 对应的链表转换为 TreeNode 树，反之，当 TreeNode 树的 Node 数量小于某个阈值时，TreeNode 树也会退化为 Linked Node 链表。

- HashMap 中 Get 和 Put 方法的时间复杂度？

如果在执行 Get 和 Put 方法时，未出现 Hash 取余时冲突，则时间复杂度都是 $O(1)$，

如果在执行 Get 和 Put 方法时，出现了 Hash 取余时冲突，则在 HashMap 的数组的 Node 链表中操作的时间复杂度为 $O(n)$，在 TreeNode 树中操作的时间复杂度为 $O(log_{2}{n})$。

- ConcurrentHashMap

在 JDK1.7 中，ConcurrentHashMap 是将所有的 Entry 集合切分为 Segment 对象进行存储，针对每个 Segment 进行上锁。而自 JDK1.8 开始，ConcurrentHashMap 放弃了 Segment 的实现方式，而是直接采用和 HashEntry 类似的数据结构和算法，通过 Synchronized 关键字来处理并发场景下的互斥问题。

https://blog.csdn.net/Elroy1230/article/details/123681027

### Java Concurrency

- Java 中线程状态有哪些？是怎么变化流转的？
[Javat-Thread线程的生命周期](work/programming/Java/programming/Advanced/Concurrency/Javat-Thread线程的生命周期.md)

## 参考链接
1. [BiliBili-【面试精选】美团大佬带你一周刷完 Java 面试八股文，比啃书效果好多了！](https://www.bilibili.com/video/BV1eD4y1w7Rp)
2. [BiliBili-秋招 Java 开发第一场面试试试水呢](https://www.bilibili.com/video/BV1B14y1B73v)
3. [Interview Guide 大厂面试真题](https://top.interviewguide.cn/)