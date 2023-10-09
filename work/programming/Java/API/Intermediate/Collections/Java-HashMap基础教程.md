# Java HashMap 基础教程


## HashMap 的实现原理

https://cloud.tencent.com/developer/article/1830825

JDK1.8 中，HashMap 是通过数组（Hash 分桶），加上链表或红黑树的数据结构来实现的。

HashMap 中有两种结点 Node，一种 Node 是可以用于构成链表的 Linked Node，一种 Node 是可以构成树的 Tree Node。

在执行 HashMap Put 方法时，若某个 Linked Node 组成的链表长度大于 HashMap 类中设置的阈值，Put 方法会调用相应的 Node 链表转换方法，将该 Node 对应的链表转换为 TreeNode 树的方式进行存储，并且会将 TreeNode 树构建和维护成一个红黑树（Red-Black Tree）。反之，当 TreeNode 红黑树的 Node 数量小于某个阈值时，TreeNode 树也会被退化为 Linked Node 链表。

JDK1.7 中，HashMap 在 Put 方法中，主要是通过头插法进行 Linked Node 链表的构建，这种实现方式在多线程并发场景下，可能会导致链表成环，进而导致程序陷入死循环。在 JDK1.8 中，主要是通过尾插法进行 Linked Node 链表的构建，这种实现方式在多线程并发环境下，最多也只是导致结点覆盖，而不会出现死循环。而且在 Java 多线程并发的环境下，可以使用 ConcurrentHashMap 代替 HashMap。

## HashMap 中 Get 和 Put 方法的时间复杂度


如果在执行 Get 和 Put 方法时，未出现 Hash 取余时冲突，则时间复杂度都是 $O(1)$。

如果在执行 Get 和 Put 方法时，出现了 Hash 取余时冲突，则在 HashMap 的数组的 Node 链表中执行对应操作的时间复杂度为 $O(n)$，在 TreeNode 树中执行对应操作的时间复杂度为 $O(log_{2}{n})$。