# JVM GC 垃圾回收


Yong GC（Minor GC）：
1. Eden 区满、Survivor 区满时触发
2. 通常采用复制清除算法

Full GC（Major GC）：
1. 老年代空间不足、内存碎片过多、Minor GC 次数过多时触发。
2. 通常采用标记清除算法