# Linux OOM Killer 基础教程



## OOM Killer 运行机制


### 检查可用内存


### 判定 OOM 状态


### 选择牺牲者进程

到了这一阶段，OOM Killer 会从一众程序中按照一定规则计算和选取一个进程杀死并释放内存，以保证系统能够继续运行。选择进程时会遵循如下原则：

- 尽量选择内存占用较多的进程（通常容易命中我们的服务进程）
- 尽量通过杀死最少的进程来达到目标
- 尽量不杀死系统和内核运行需要的进程
- 如果有进程处于 SIGKILL 或者退出动作中，优先选择以加快内存释放

OOM Killer 通过一套尽心设计的算法计算每个进程的 `oom_score`，按照分数高低选择应该杀死哪个进程。

具体进程的 oom_score 可以通过命令 `cat /proc/[pid]/oom_score` 来查看。

在我的 ubuntu 上，系统进程的 oom_score 通常是 0，chrome 中内存占用较大页面进程在 300 左右。

### 杀死牺牲者

选出牺牲者进程后，OOM Killer 会发送 `信号杀死牺牲者，即使这个程序本身没有任何问题`。

如果内存依然不足，OOM Killer 会重复上面的步骤，继续选择并杀死新的进程，直到释放足够的内存位置，颇有为了拯救世界宁愿杀尽天下人的冷血范。

## 参考链接
1. https://www.wang7x.com/2021-01-26-linux-oom-killer/
2. [Oracle-How to Configure the Linux Out-of-Memory Killer](https://www.oracle.com/technical-resources/articles/it-infrastructure/dev-oom-killer.html)
3. [The Linux Kernel Archives - Chapter 13  Out Of Memory Management](https://www.kernel.org/doc/gorman/html/understand/understand016.html)

