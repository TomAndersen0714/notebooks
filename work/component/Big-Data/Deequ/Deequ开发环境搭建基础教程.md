# Deequ 开发环境搭建基础教程

## 前言

各组件版本：
1. Maven: `3.6.3`
2. Java: `1.8.0`
3. Deequ: `1.2.2-spark-2.4`
	1. Spark: `2.4.3`
	2. Scala: `2.12.10`，Scala-maven-plugin: `4.4.0`

## 安装 SDK

[Oracle-JDK安装基础教程](work/programming/Java/operation/Oracle-JDK安装基础教程.md)
[Scala安装基础教程](work/programming/Scala/Scala安装基础教程.md)

## 安装 Maven

[Maven安装基础教程](work/programming/Java/tools/Apache-Maven/Maven安装基础教程.md)

## 常见问题

**报错信息：**
- Maven 执行 Compile Lifecycle 时 Plugin 插件 `net.alchim31.maven:scala-maven-plugin:4.4.0` 报错，无法编译成功
**排查原因：**
- Maven 工具使用的 JDK 和 Maven 项目使用的 Scala 语言依赖存在版本兼容问题
**解决方案：**
- [Maven常见问题](work/programming/Java/tools/Apache-Maven/Maven常见问题.md)