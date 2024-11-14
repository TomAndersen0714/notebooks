# Gradle Wrapper 基础教程

https://docs.gradle.org/current/userguide/gradle_wrapper.html

Gradle 官方推荐使用 Gradle Wrapper 来辅助执行 Build。Wrapper 是一个脚本（`gradlew(.bat)`），完全支持 Gradle 的各种命令。

但不同的是 Wrapper 在执行 Gradle Task 之前会检查是否存在特定版本的 Gradle，如果指定的 Gradle 不在 `Gradle User Home Directory` 路径下，则会下载指定版本的 Gradle 到 `Gradle User Home Directory`，并使用此版本 Gradle 执行 Task。

因此，使用 Wrapper 脚本，可以不需要事先手动全局安装 Gradle，可以直接通过调用该脚本来执行 Gradle Task，如构建 Project 等。Wrapper 脚本（`gradlew(.bat)`）通常和 Project 是强绑定的，不同的 Project 对应的 Wrapper 脚本通常也不同。

![](resources/images/Pasted%20image%2020230530221731.png)

**使用 Gradle Wrapper 的优势，主要有以下几点**：
1. 版本兼容：在特定版本的 Gradle 下编译 Project，使得 Build 过程更加可靠。由于 Gradle Wrapper 将 Gradle 的版本与 Project 一起绑定，可以确保 Project 在不同的开发环境中使用相同的 Gradle 版本执行 Build，从而避免由于 Gradle 版本不一致而导致的构建问题。
2. 环境隔离：使用 Gradle Wrapper，项目可以独立管理自己的 Gradle 版本和插件版本，不会与其他项目的配置发生冲突，实现了环境的隔离。
3. 简化 Build 环境配置：Gradle Wrapper 允许项目将 Gradle 的版本和配置文件捆绑在一起，使得每个项目都有自己独立的构建环境，不依赖于全局的 Gradle 安装。这样可以简化项目的配置和迁移，并且确保每个开发人员使用的是相同的 Gradle 版本。

**PS：解耦可以使系统更灵活，聚合可以使系统更简单，切忌教条主义。**

## Create the Gradle Wrapper

在一个 Gradle 新项目中，可以通过全局已安装的 Gradle 工具来执行 Gradle 内置的 Wrapper Task，给当前的 Project 构建 Wrapper 脚本

```bash
gradle wrapper
```

其中生成的 `gradle/wrapper/gradle-wrapper.properties` 会存储当前 project 使用的 Gradle 版本信息，后续在其他环境下使用 Gradle Wrapper 时，则会自动校验 Gradle 此版本是否存在，不存在则下载。例如：

```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-8.1.1-bin.zip
```

## Using the Gradle Wrapper

在 Gradle 项目中，使用 Gradle Wrapper 脚本，可以代替全局安装的 Gradle 来执行 Gradle Task

Windows:
```bash
gradlew.bat build
```

Unix:
```
gradlew build
```

Gradle Wrapper in IDEA:
- 在 IDEA 中首次打开创建有 Gradle Wrapper 脚本 `gradlew.bat/gradlew` 的项目时，都应该先执行对应的脚本，即直接调用 `gradlew.bat/gradlew`，来下载对应的 Gradle，否则 IDEA 中内置的 Gradle 插件，无法正确 Load 对应的项目。

## Upgrading the Gradle Wrapper

Gradle Wrapper 支持通过执行 Gradle Task 升级 Wrapper 版本。

```bash
# Example: Upgrading the Wrapper to the latest version
./gradlew wrapper --gradle-version latest

# Example: Upgrading the Wrapper to a specific version
./gradlew wrapper --gradle-version 8.1.1
```