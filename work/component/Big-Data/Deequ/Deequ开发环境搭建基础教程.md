# Deequ 开发环境搭建基础教程


## 前言

各组件版本：
1. Java: `1.8.0`
2. Scala: `2.13.3`
3. Deequ: `1.2.2-spark-2.4`
4. Spark: `2.4.3`

## 安装 SDK

[Oracle-JDK安装基础教程](work/programming/Java/Operation/Oracle-JDK安装基础教程.md)
[Scala安装基础教程](work/programming/Scala/Scala安装基础教程.md)

## 安装 Maven

[Maven安装基础教程](work/programming/Java/Tools/Apache-Maven/Maven安装基础教程.md)


## 常见问题

**Maven Compile 时 Plugin 插件 `net.alchim31.maven:scala-maven-plugin:4.4.0` 报错**

原因排查：
- 检查 Maven 实际执行的 mvn 命令中使用的 JDK 版本，是否兼容 Scala
解决方案：
- 如果是在 IDEA 中，则在 `Project Structure | Project Settings | Project` 下调整项目的 SDK 为指定的 Java 版本
- 如果是在 Maven 中，则在使用 Maven 命令行时，更换编译时使用的 JDK 路径，使用项目指定的 Java 版本

原因排查：
- 如果是报错 `Cannot run program "javac"`，则需要检查 Maven 实际执行的 mvn 命令中使用的 JDK，是否有配置好 Maven 依赖的 `JAVA_HOME` 环境变量
解决方案：
- 如果没有配置好 `JAVA_HOME` 环境变量