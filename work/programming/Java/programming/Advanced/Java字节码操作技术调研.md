# Java字节码操作相关技术调研


## 字节码增强技术（ByteCode Enhancement）

简单理解，字节码增强技术（ByteCode Enhancement）就是一类对现有字节码进行修改或者动态生成全新字节码文件的技术，在不影响源代码的前提下，使得新生的字节码文件能够满足各种定制的需求。

PS：初步推测，ByteCode Enhancement关键词来源于Hibernate框架

## ASM

ASM是一个通用的Java字节码操作和分析框架，它可以用于修改已有的class文件，也可以以二进制的形式直接动态生成class文件。ASM提供了字节码转换的和分析的常用算法，利用这些算法可以自行构建复杂的转换和代码分析工具。尽管ASM提供了和Java字节码框架类似的功能，但其更加注重性能，因为ASM被设计尽可能小而快，并适用于在动态系统中使用（当然，可以以静态的方式使用，如编译器中）。

ASM在OpenJDK、Groovy编译器、Kotlin编译器、Gradle等项目中都有被使用。

https://blog.csdn.net/dadiyang/article/details/86413697

https://asm.ow2.io/



## cglib



## Spring





## 参考链接

1. [美团技术团队-字节码增强技术探索](https://tech.meituan.com/2019/09/05/java-bytecode-enhancement.html)
2. [Java字节码技术(二)字节码增强之ASM、JavaAssist、Agent、Instrumentation](https://blog.csdn.net/hosaos/article/details/102931887)

