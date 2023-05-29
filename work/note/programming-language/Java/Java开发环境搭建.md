# Java开发环境搭建

## 安装JDK

[Oracle_JDK安装教程](work/note/programming-language/Java/Feature/Java-junior/Oracle_JDK安装教程.md)


## JetBrain IDEA

### 安装IDEA


### 配置JDK

File | Settings | Project Structure | Platform Settings | SDKs | Add New SDK

将之前安装的Java对应的JAVA_HOME路径，作为SDK的路径，将SDK添加到IDEA中。


### 安装插件

#### .ignore

用于交互式生成.ignore文件

#### translation

一个JetBrain上的翻译插件，支持各大主流翻译工具，建议使用有道翻译


### 修改配置


#### Build

Version: IntelliJ IDEA 2022.3.2 (Ultimate Edition)

File | Settings | Build, Execution, Deployment | Build Tools

取消勾选，Reload project after changes in the build scripts，避免每次Build脚本变动，或者打开新项目时，都自动构建，无法手动控制。

https://stackoverflow.com/questions/43192504/how-do-i-disable-or-enable-gradle-maven-auto-import-for-an-intellij-idea-proje/43192764#43192764


## VSCode




## 参考链接
1. 