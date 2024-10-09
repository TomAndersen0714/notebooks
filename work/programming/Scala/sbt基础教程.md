# SBT 基础教程

SBT 是 Scala 项目的标准构建工具，但使用 Maven 来构建 Scala 项目也是完全可行的，特别是在团队已经熟悉 Maven 或项目需要与 Java 生态系统紧密集成的情况下。

通过在 Maven 项目的 pom.xml 文件中引入 plugin 插件 `net.alchim31.maven:scala-maven-plugin:4.5.4` 就可以通过 Maven 来管理 Scala 项目的依赖和生命周期。

同理在 Gradle 中也可以引入对应的插件，来管理 Scala 项目的依赖和生命周期。

因此实际开发过程中，非特定情况下，并不推荐额外使用 sbt 工具来构建 Scala 项目。

## 参考链接

1. [GitHub - sbt/sbt: sbt, the interactive build tool](https://github.com/sbt/sbt)