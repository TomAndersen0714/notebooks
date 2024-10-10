# IDEA 如何配置 Gradle

## Configure a Gradle version for a project

在 Gradle 配置页面中，可以指定使用的 Gradle 路径，如果是 Gradle Wrapper (Gradlew) 来管理的项目，则需要设置成 `Wrapper`，这也是 Gradle 项目最佳实践的方式，否则则需要使用本地安装的 Gradle，即 `Local installation`。

同时在 Gradle JVM 中，也能控制 Gradle 执行时，使用的 JDK 版本，但不建议在此处调整，而应该让其维持默认值 `Project SDK`，即引用 Project 级别的 JDK 版本，需要调整时统一在 `Project Structure` 中做全局调整。

![](resources/images/Pasted%20image%2020241010084541.png)

![project structure](resources/images/Pasted%20image%2020241010084441.png)
## 参考链接

1. [Gradle | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/gradle.html)