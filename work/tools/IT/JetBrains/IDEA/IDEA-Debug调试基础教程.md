# IDEA Debug 调试基础教程

## Breakpoints 的种类

[Breakpoints | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/using-breakpoints.html#breakpoint-types)

### Line breakpoints

Suspend the program upon reaching the line of code where the breakpoint was set. This type of breakpoints can be set on any executable line of code.

当程序到达设置断点的行时，暂停程序。这种类型的断点可以在任何可执行的代码行上进行设置。

### Method breakpoints

Suspend the program upon entering or exiting the specified method or one of its implementations, allowing you to check the method's entry/exit conditions.

在进入、退出指定的方法（包括构造方法 Constructor）或其任何版本的实现方法时，暂停程序，以便检查函数的进入/退出条件。这种类型的断点，需要在方法的声明的首个代码行上进行设置。

### Field watchpoints

Suspend the program when the specified field is read or written to. This allows you to react to interactions with specific instance variables. For example, if at the end of a complicated process you are ending up with an obviously wrong value on one of your fields, setting a field watchpoint may help determine the origin of the fault.

当指定类的字段读取或者写入时，暂停程序，以便检查变量的读写情况。这种类型的断点，需要在类的字段声明的代码行进行设置。

这中断点允许您对特定实例变量的交互做出反应。例如，如果在复杂过程的结束时，您发现一个字段上的值明显不正确，设置字段观察点可能有助于确定故障的根源。

### Exception breakpoints

Suspend the program when Throwable or its subclasses are thrown. They apply globally to the exception condition and do not require a particular source code reference.

当程序抛出 Throwable 的对象时，暂停程序。这种类型的断点，是全局设置的，并不是在某一特定代码行进行设置。

## Breakpoints 的属性

The following policies are available for the breakpoints that suspend program execution:
- `All`: all threads are suspended when any of the threads hits the breakpoint.
- `Thread`: only the thread which hits the breakpoint is suspended.

If you want a policy to be used as the default one, click the Make default button.

IDEA 中断点有两种属性，一种是 `All`，代表任何线程触发断点时，其他所有线程也会一起暂停执行，`Thread` 则代表，只有当前线程会暂停执行。

## Debugger 调试常用功能

https://www.bilibili.com/video/BV1Rm4y1P7j8

### 设置断点（Set Breakpoints）

按照不同的 Debug 场景，选择不同的 Breakpoint 来中断程序。

如，程序抛出异常，则可以通过设置 Exception Breakpoint 异常断点全局定位抛出异常的代码行；如果想观察程序执行过程，则可以在想要设置的代码行处，设置最常见的 Line Breakpoint 行断点，Debug 时按行执行，观察程序。

![](resources/images/Pasted%20image%2020230916203939.png)

![](resources/images/Pasted%20image%2020230916204002.png)

### 方法栈回退（Reset/Delete Frame）

**Debug | Debugger | Reset Frame**

在 Debugger 窗口下的 Frames 是当前程序的方法调用栈，每次都会保存当前的方法调用栈帧（Frame）。

在 Debug 过程中，如果某个方法执行一段后，如果想回退到进入此方法之前，则可以通过 Frames 窗口下选中指定的 Frame，然后点击 Reset Frame 或者 Delete Frame，都可以将对应栈帧 Frame 之后的所有 Frame 删除，程序的当前行会回退到方法栈栈顶的方法入口处。

通过这种方法，可以在调试进某个方法时，适时回退到入口处，重新执行。指的注意的是，已经执行的代码段中，所修改的变量，不会随之回退。

如：Debug 时，某个方法的执行过程未能及时观测到，则可以通过 Reset/Delete Frame 功能，将程序执行代码行回退到某个方法调用前。

或者点击某个 Frame，查看对应 Frame 对应的源码位置，进而理解代码的执行过程。

![](resources/images/Pasted%20image%2020230916203912.png)

### 方法强制返回（Frame Force Return）

在 Debug 过程中，如果某个方法执行一段后，不想再执行，可以直接右键点击 Debugger 中 Frames 窗口中的 Frame，选择“Force Return”直接执行 return 代码，如果方法的声明有返回值，则在执行“Force Return”的同时，还需要提供返回值的表达式。

![](resources/images/Pasted%20image%2020230916203842.png)

### 执行表达式（Evaluate Expression）

Debug 时，可以通过执行表达式功能（Evaluate Expression），来实时执行特定的代码段，并显示对应的执行结果。

如：Debug 时可以通过设置断点等方式，在执行某段代码之前暂停程序，然后执行代码段，来调整程序运行的分支；或者是在提前停止，并设置变量值，来测试后续的代码行。

如：在 Debug 时，可以通过选中指定的代码段，点击右键打开菜单，选中“Evaluate Expression”来执行代码片段。

这个功能可以用于直接探测指定代码的返回值，而不用进入代码内部；也可以用于修改，运行时的变量值，来进行自测。

使用此功能时，需要注意的是，任何代码段都是在程序中实际执行过的，会实际影响到内存中的变量。

![](resources/images/Pasted%20image%2020230916204159.png)

![](resources/images/Pasted%20image%2020230916204248.png)

### 断点静音（Mute Breakpoints）

在某些时候，不需要断点生效时候，可以通过 Mute Breakpoints 功能，将所有的断点静音，使得程序后续调试时不再在断点处中断，而是正常执行。此功能，在调试 Web App 接口时比较常用。

![](resources/images/Pasted%20image%2020240912214635.png)

### 断点条件（Condition）

This option is used to specify a condition that is checked each time the breakpoint is hit. If the condition evaluates to `true`, the selected actions are performed. Otherwise, the breakpoint is ignored.

此选项主要适用于，每次执行到断点处时，判断指定的 Condition 表达式的返回值是否是 true，如果是，则暂停执行，否则跳过当前断点。

![](resources/images/Pasted%20image%2020240912224245.png)

![](resources/images/Pasted%20image%2020240912223953.png)

## 参考链接

1. [Debug code | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/debugging-code.html)
2. [Breakpoints | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/using-breakpoints.html#breakpoint-types)
3. [Alter the program's execution flow | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/altering-the-program-s-execution-flow.html#breakpoint-expressions)
4. [BiliBili-分享 4 个我一直在用的 IDEA Debug 小技巧](https://www.bilibili.com/video/BV1Rm4y1P7j8)