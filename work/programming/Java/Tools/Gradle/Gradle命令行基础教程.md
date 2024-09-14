# Gradle 命令行基础教程

PS：由于gradle wrapper实际上是使用指定的Gradle版本执行gradle命令，因此`gradlew`命令行的使用方式和`gradle`基本完全一致。

## Syntax

```
USAGE: gradle [option...] [task...]
```


## 常用命令

打印帮助信息
```
gradle --help
```

执行当前Project的Default Tasks，即`build.gradle`中显式声明的`defaultTasks`对象。如果没有定义`defaultTasks`，则默认是执行内置的`help`任务
```
gradle
gradle -q
```


`gradle [task...]`: 执行当前Project的指定Task
```
gradle task1 task2
```


## Built-in Tasks

help：打印帮助信息
```
gradle help
```

projects：打印当前Project下所有的Project
```
gradle projects
gradle -q projects
```

tasks：打印当前Project下所有的Task
```
gradle tasks
gradle tasks --all
```


## 参考链接
1. [Gradle-Command-Line Interface](https://docs.gradle.org/current/userguide/command_line_interface.html)