# 如何阅读 Deequ 源码

## 常用工具

### IDEA Scala Plugin

[IDEA如何查看Scala隐式转换和隐式参数的调用](work/programming/Scala/solution/IDEA如何查看Scala隐式转换和隐式参数的调用.md)

在 Scala 中存在着各种各样的语法糖 syntactic sugar，比如，方法调用时参数列表为空时连括号都可以省略、`_` 符号在集合迭代过程中的当前元素、各种运算符重载、隐式转换函数、隐式参数等等等等。

其五花八门的独特语法，对于一个习惯了 Java 口味的程序猿来说，Scala 的味道尝起来未免显得过于“齁甜”了，因此使用 Scala Plugin 此类工具，来展示 Scala 方法的实际调用过程，以及方法的返回值类型，就显得很有必要，可以帮忙大幅减少代码阅读的时间成本，正如“编程语言”一词中，前半段只是定语，后半段才是核心。

使用 IDEA `Scala` 插件的 X-Ray 功能，即可显示编辑器当前文件中的隐式调用（如：隐式参数、隐式转化函数等等），`File | Settings | Languages & Frameworks | Scala | X-Ray Mode`。此功能在 Scala 插件 [2023.3.16](https://plugins.jetbrains.com/plugin/1347-scala/versions/stable/446506) 版本之后开始支持，其兼容的 IDEA 版本，也是 `2023.3` 版本之后。

Scala 插件的 `X-Ray` 功能的按钮在 Scala 源文件编辑器右上角，`X-Ray` 功能触发的默认快捷键为，双击 Ctrl 并保持按压，此功能的配置路径在`File | Settings | Languages & Frameworks | Scala | X-Ray Mode`：

![](resources/images/demonstrate.gif)

### IDEA Diagram

IDEA Diagram 是 IDEA 中自带的核心功能之一，可以自动解析当前项目的类结构生成对应的 UML 类图（Class Diagram），支持各种离线文件格式的导出导入，支持导出为 DSL 绘图语言（如：Mermaid、PlantUML 等等），可以帮助开发者从代码结构的视角辅助阅读代码。

PS：IDEA Diagram 对于非 Java 语言并不完全支持。

![](resources/images/demonstrate%201.gif)

### IDEA Sequence Plugin

IDEA Sequence Plugin 是 IDEA 社区的一个可以自动解析当前方法的执行过程，并生成序列图（Sequence Diagram）的插件，可以帮助开发者从代码执行流程的角度，辅助代码阅读，支持导出为 DSL 绘图语言（如：Mermaid、PlantUML 等等），其中导出等高级功能需要使用收费的高级版。

![](resources/images/demonstrate%202.gif)

## 输入

由于 Deequ 官方在开源社区中的文档内容很少，因此对于代码的结构和执行流程，目前大部分只能通过阅读源码来获取和理解。

建议最开始先从 Deequ 中最基础的 Basic Example 开始，即先去了解 Deequ 的基础执行流程，了解基础执行流程中的常用类、常用方法，进行发散，逐步了解其他的同类，以及更加复杂的功能，搭配各种图形工具辅助理解和记录。

如果想要熟悉 Deequ 项目功能的执行流程，则建议从最简单的 Basic Example 开始。

如果想要熟悉 Deequ 项目代码的整体结构，直接看抽象程度最高的基类、抽象类、接口，通过其抽象方法的入参和返回参数，就能大致推测和理解该接口的功能和职责。

以下是个人实际在 Deequ 源码阅读过程中最常用的两个方法。

### 使用断点和调用栈回退进行调试

在 IDEA 等 IDE 中，支持设置行执行断点、方法调用断点、属性读写断点、异常抛出断点、条件断点等等，合理利用这些断点来进行 Debug，理解代码流程的执行逻辑。

[Breakpoints | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/using-breakpoints.html#breakpoint-types)

此外可以在 Debugger 菜单中通过点击、回退方法栈 Frame，来查看和控制 JVM 程序的执行过程，进而了解代码的执行过程。

![](resources/images/Pasted%20image%2020230916203912.png)

### 使用图形工具辅助阅读

具体工具及使用方式，如前文所述。

### 使用AI大模型充当知识库

除了自己源码阅读外，可以通过 ChatGPT、Claude 等大模型，来充当或者搭建自己的项目代码知识库，辅助阅读和理解项目源码。

具体操作方面，个人建议是使用微软的 Github Copilot，支持以插件的形式嵌入 IDE，帮助诊断、理解、生成代码，并且近期也更新了几个适用于编程开发领域的大模型（Claude 3.5 Sonnet）的 preview 版本，月租价格是 3~4 杯奶茶。

## 理解并记录

好记性不如烂笔头，读项目源码时，需要做好笔记，避免陷入记忆-遗忘-记忆的循环。

源码注释：
自己拉取对应的框架、组件的源码，在本地创建自己的分支 branch ，或者 fork 生成自己的项目，并通过增加注释的方式来做笔记，当需要更新源码时，获取分支后直接 rebase ，或者先 reset，然后 cherry pick 相关的 commit 的方式来维护 comment 注释内容。

这个方式的缺点是，代码变动时，注释维护起来会有些费劲儿。

源码摘抄写文档：
针对关键模块，摘抄相关源码，同自己的笔记一起写成文档。

常用通过图形工具辅助记录：
一图胜千言

## 输出

通过发布博客、写文档等方式，逐步沉淀和输出自己的所见所得，可以实现教学相长、查漏补缺。当然具体输出的方式和内容，这一点必定是见仁见智。

## 参考链接

1. [Debug code | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/debugging-code.html)
2. [Breakpoints | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/using-breakpoints.html#breakpoint-types)
3. [Alter the program's execution flow | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/altering-the-program-s-execution-flow.html#breakpoint-expressions)
4. [BiliBili-分享 4 个我一直在用的 IDEA Debug 小技巧](https://www.bilibili.com/video/BV1Rm4y1P7j8)
5. [IDEA-Debug调试基础教程](work/tools/IT/JetBrains/IDEA/IDEA-Debug调试基础教程.md)
6. [源码阅读学习基础教程](work/methodology/Software-Engineering/源码阅读学习基础教程.md)