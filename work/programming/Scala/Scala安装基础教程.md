# Scala 安装基础教程

## 安装前置依赖-JDK

Scala 和 JDK 版本兼容性检查：
[JDK Compatibility | Scala Documentation](https://docs.scala-lang.org/overviews/jdk-compatibility/overview.html#scala-compatibility-table)

| JDK      | 3      | 2.13     | 2.12    | 2.11    |
|----------|--------|----------|---------|---------|
| 23 (ea)  | 3.3.5* | 2.13.15* | 2.12.20 |         |
| 22       | 3.3.4* | 2.13.13  | 2.12.19 |         |
| 21 (LTS) | 3.3.1  | 2.13.11  | 2.12.18 |         |
| 17 (LTS) | 3.0.0  | 2.13.6   | 2.12.15 |         |
| 11 (LTS) | 3.0.0  | 2.13.0   | 2.12.4  | 2.11.12 |
| 8 (LTS)  | 3.0.0  | 2.13.0   | 2.12.0  | 2.11.0  |

JDK 安装：
[Oracle-JDK安装基础教程](work/programming/Java/operation/Oracle-JDK安装基础教程.md)

## 下载安装

下载对应系统的 Scala 压缩包，并解压到分配的安装路径下。

[All Available Versions | The Scala Programming Language](https://www.scala-lang.org/download/all.html)

## 配置

配置环境变量 `SCALA_HOME`，指向 Scala 安装路径目录，将 `$SCALA_HOME/bin` 添加到环境变量 `PATH` 中，或者直接添加 bin 绝对路径到 `PATH` 变量中。

验证安装：`scala -version`。

## 参考链接

1. [The Scala Programming Language](https://www.scala-lang.org/)
2. [GitHub - scala/scala: Scala 2 compiler and standard library. Bugs at https://github.com/scala/bug; Scala 3 at https://github.com/scala/scala3](https://github.com/scala/scala)
3. [GitHub - scala/scala3: The Scala 3 compiler, also known as Dotty.](https://github.com/scala/scala3)