# JVM 性能优化教程


## 性能问题排查方法


## 性能问题优化方法


[Java命令行工具基础教程](work/programming/Java/CLI/Java命令行工具基础教程.md)


默认情况下，当堆中可用内存小于 40%时，堆内存会开始增加，一直增加到-Xmx 的大小。当堆中可用内存大于 70%时，堆内存会开始减少，一直减小到-Xms 的大小。

-Xms：堆内存的最小值。
-Xmx：堆内存的最大值。
-Xmn：新生代内存的最大值，包括 Eden 区和两个 Survivor 区的总和，配置写法如：-Xmn1024k，-Xmn1024m，-Xmn1g 等。


## 参考链接
1. [微信-JavaGuide-面试官：你这 JVM 调优，回答的很有问题呀！！](https://mp.weixin.qq.com/s/pOOiLl3j0-0Hqhz3sPzxOQ)