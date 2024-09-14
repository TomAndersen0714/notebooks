# Gradle 命令行基础教程

PS：由于 gradle wrapper 实际上是使用指定的 Gradle 版本执行 gradle 命令，因此 `gradlew` 命令行的使用方式和 `gradle` 基本完全一致。

## Syntax

```
USAGE: gradle [option...] [task...]
```


## 基础命令

打印帮助信息
```
gradle --help
```

执行当前 Project 的 Default Tasks，即 `build.gradle` 中显式声明的 `defaultTasks` 对象。如果没有定义 `defaultTasks`，则默认是执行内置的 `help` 任务
```
gradle
gradle -q
```


`gradle [task...]`: 执行当前 Project 的指定 Task
```
gradle task1 task2
```


## 内置任务（Built-in Tasks）

Help：打印帮助信息
```
gradle help
```

Projects：打印当前 Project 下所有的 Project
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

## 参考链接

1. [Gradle-Command-Line Interface](https://docs.gradle.org/current/userguide/command_line_interface.html)