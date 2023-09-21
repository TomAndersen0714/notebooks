# JVM 内存模型


## 堆（Heap）

堆（Heap）内存可以细分为：
1. 年轻代（YoungGen）
	1. 生成区（Eden）
	2. 幸存区（Survivor）：FromSpace、ToSpace
2. 老年代（OldGen）

JVM 启动之后创建，Heap 用来存放 Java 对象的实例，堆内存是垃圾收集器 GC 管理的主要区域。

## 虚拟机栈（VM Stack）

栈是属于线程私有的内存空间，其中虚拟机栈（VM Stack）由多个方法的栈帧（Frame）组成。一个线程会执行一个或嵌套执行多个方法，其中一个方法对应一个栈帧。

栈帧内容包含：局部变量表、操作数栈、动态链接、方法返回地址等信息。

每一次方法调用都会有一个对应的栈帧被压入栈中，每一个方法调用结束后，都会有一个栈帧被弹出。

## 本地方法栈（Native Method Stack）

本地方法栈（Native Method Stack）和虚拟机栈（VM Stack）的功能十分类似，不过一个是存储 Java 方法的栈帧，一个是存储 Native 方法的栈帧，其中 Native 方法主要指的是使用非 Java 语言实现的方法。

## 方法区（Method Area）

在 JVM 规范中，方法区（Method Area）主要用来存储加载后的类信息、常量、静态变量、Class 对象、即时编译器编译生成的机器码等数据，方法区也被称为非堆内存（Non-heap），同时也是线程共享的。

在 Oracle JDK1.7 及之前的 JVM（HotSpot）中，是通过永久代（PermGen）的物理方式来实现 JVM 方法区的；而在 Oracle JDK1.8 中，JVM 放弃了 HotSpot 中永久代（PermGen）的实现方式，而是吸取了 JRockit VM 的技术，使用元空间（MetaSpace）来代替旧版 HotSpot 中的永久代（PermGen），来实现方法区（Method Area）。

## 程序计数器（Program Counter Register）

程序计数器是线程私有的内存空间，用于作为当前线程所执行的字节码的行号指示器，通过它实现代码的流程控制。在多线程的情况下，程序计数器用于记录当前线程执行的位置，当线程被切换回来的时候可以通过程序计数器中的信息获取上次执行的位置，然后继续执行。

## 参考链接
1. [Java SE 8 Edition - 2.5. Run-Time Data Areas](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.5)
2. [微信-阿里云开发者-图解 JVM 内存模型及 JAVA 程序运行原理](https://mp.weixin.qq.com/s/lxdePdWP5UFzA06ceuVUfQ)
3. [CSDN-【JVM】JVM 堆内存 (heap) 详解](https://blog.csdn.net/u011397981/article/details/130714618)