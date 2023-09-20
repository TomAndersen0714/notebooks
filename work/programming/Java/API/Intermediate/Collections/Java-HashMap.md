# Java-HashMap


## HashMap 的实现原理

https://cloud.tencent.com/developer/article/1830825

JDK1.8 中，HashMap 是通过数组（Hash 分桶），加上链表或红黑树的数据结构来实现的。

HashMap 中有两种结点 Node，一种 Node 是可以用于构成链表的 Linked Node，一种 Node 是可以构成树的 Tree Node。

当 Put 方法执行时，某个 Linked Node 组成的链表长度大于 HashMap 类中设置的阈值时，Put 方法会调用相应的 Node 链表转换方法，将该 Node 对应的链表转换为 TreeNode 树的方式进行存储，并且会将 TreeNode 树构建和维护成一个红黑树（Red-Black Tree）。反之，当 TreeNode 红黑树的 Node 数量小于某个阈值时，TreeNode 树也会退化为 Linked Node 链表。


## HashMap 中 Get 和 Put 方法的时间复杂度


如果在执行 Get 和 Put 方法时，未出现 Hash 取余时冲突，则时间复杂度都是 $O(1)$。

如果在执行 Get 和 Put 方法时，出现了 Hash 取余时冲突，则在 HashMap 的数组的 Node 链表中执行对应操作的时间复杂度为 $O(n)$，在 TreeNode 树中执行对应操作的时间复杂度为 $O(log_{2}{n})$。