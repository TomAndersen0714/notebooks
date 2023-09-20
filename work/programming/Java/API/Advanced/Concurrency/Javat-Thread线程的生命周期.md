# Java Thread 线程的生命周期


## Java Thread 线程对象的六个状态

A thread can be in only one state at a given point in time. These states are virtual machine states which do not reflect any operating system thread states.

A thread state. A thread can be in one of the following states:
1. `NEW` A thread that has not yet started is in this state.
2. `RUNNABLE` A thread executing in the Java virtual machine is in this state.
3. `BLOCKED` A thread that is blocked waiting for a monitor lock is in this state.
4. `WAITING` A thread that is waiting indefinitely for another thread to perform a particular action is in this state.
5. `TIMED_WAITING` A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.
6. `TERMINATED` A thread that has exited is in this state.


## Java Thread 线程状态的流转


Thread 创建完成时，则为 `NEW`，调用完 `start()` 方法后，则进入 `RUNABLE` 状态，并在 JVM 中开始执行。

当 Thread 遇到阻塞操作时（如：`Synchronized` 关键字、`Lock.lock` 方法等），则会进入 `BLOCKED` 状态，直到当前 Thread 获得了对应的 Lock，