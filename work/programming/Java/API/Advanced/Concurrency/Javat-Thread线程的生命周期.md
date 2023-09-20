# Java Thread 线程的生命周期


## Java Thread 的六个状态

A thread can be in only one state at a given point in time. These states are virtual machine states which do not reflect any operating system thread states.

A thread state. A thread can be in one of the following states:
1. `NEW` A thread that has not yet started is in this state.
2. `RUNNABLE` A thread executing in the Java virtual machine is in this state.
3. `BLOCKED` A thread that is blocked waiting for a monitor lock is in this state.
4. `WAITING` A thread that is waiting indefinitely for another thread to perform a particular action is in this state.
5. `TIMED_WAITING` A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.
6. `TERMINATED` A thread that has exited is in this state.

