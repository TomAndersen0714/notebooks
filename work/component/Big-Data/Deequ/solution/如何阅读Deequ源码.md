# 如何阅读 Deequ 源码

## 工具

IDEA Diagram: 从结构视角阅读代码
IDEA Sequence Plugin: 从流程视角阅读代码

Scala Plugin: [IDEA如何查看Scala隐式转换和隐式参数的调用](work/programming/Scala/solution/IDEA如何查看Scala隐式转换和隐式参数的调用.md)

在 Scala 中存在着各种各样的语法糖 syntactic sugar，比如，方法调用时参数列表为空时连括号都可以省略、`_` 符号在集合迭代过程中的当前元素、各种运算符重载、隐式转换函数、隐式参数等等等等。

其五花八门的独特语法，对于一个习惯了 Java 口味的程序猿来说，其味道尝起来未免显得过于“齁甜”了，因此使用 Scala Plugin 此类工具，来展示 Scala 方法的实际调用过程，以及方法的返回值类型，就显得很有必要，正如“编程语言”一词中，前半段只是定语，后半段才是核心。

## 输入

如果想知道流程，则从 BasicExample Main 开始。

如果想要熟悉结构，直接看抽象程度最高的基类、或者说接口，就能大致理解该模块的功能和职责。

建议先从流程开始，逐步接触经常用的几个类，然后再去了解各个类的结构。

## 理解和记录

[源码阅读学习基础教程](work/methodology/Software-Engineering/源码阅读学习基础教程.md)

## 输出

教学相长、查漏补缺