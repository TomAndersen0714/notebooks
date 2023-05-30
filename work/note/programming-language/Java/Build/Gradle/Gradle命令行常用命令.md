# Gradle命令行常用命令


## Syntax

```
USAGE: gradle [option...] [task...]
```


## Built-in Tasks

projects
tasks

打印当前Project下的所有Sub-project
```
gradle projects
gradle -q projects
```


## 常用命令

打印帮助信息
```
gradle --help
```


执行指定Task
```
gradle [task...]

gradle task1 task2
```


## 参考链接
1. [Gradle-Command-Line Interface](https://docs.gradle.org/current/userguide/command_line_interface.html)