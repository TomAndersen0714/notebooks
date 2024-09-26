# Gradle 命令行基础教程

PS：由于 gradle wrapper 实际上是使用指定的 Gradle 版本执行 gradle 命令，因此 `gradlew` 命令行的使用方式和 `gradle` 基本完全一致。

## 语法

```
USAGE: gradle [option...] [task...]
```

## 基础命令

```
# 查看 gradle 版本信息
gradle --version
# 打印帮助信息
gradle --help
```

Gradle 命令行执行任务时，会优先执行当前 Project 中定于的 Default Tasks，即 `build.gradle(.kts)` 中 `defaultTasks` 显式声明的任务。如果没有定义 `defaultTasks`，则默认是执行 Gradle 内置的同名任务，如 `help`。

## 内置任务（Built-in Tasks）

Init：初始化当前路径路径为 Gradle 项目
```bash
gradle init
```

Wrapper：使用 Gradle 构建 Gradle Wrapper 脚本
```bash
gradle wrapper
```

Help：打印帮助信息
```
gradle help
```

Projects：打印当前目录下下所有的 Project
```
gradle projects
gradle -q projects
```

Tasks：打印当前 Project 下所有的 Task
```
gradle tasks
gradle tasks --all
```

Dependencies：解析当前项目的依赖，并下载缺少的依赖
```
gradle dependencies
```

Run:
- The `run` task tells Gradle to execute the `main` method in the class assigned to the `mainClass` property in `build.gradle(.kts)` file.
```
gradle run
```

## 参考链接

1. [Gradle-Command-Line Interface](https://docs.gradle.org/current/userguide/command_line_interface.html)