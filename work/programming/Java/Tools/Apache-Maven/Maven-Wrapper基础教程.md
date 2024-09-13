# Maven Wrapper 基础教程

Maven Wrapper 是一个用于确保项目使用特定版本的 Maven 进行构建的工具。它由一组脚本和配置文件组成，允许开发者在没有预先安装 Maven 的情况下构建项目。此脚本会为用户自动下载安装特定版本 Maven，并使用该 Maven 来编译当前项目。

从 SpringBoot 2.3.0 版本开始，Spring 在线脚手架工具（Spring Initializr）开始默认包含 Maven Wrapper。

## Maven Wrapper 的组成部分

1. `mvnw` 和 `mvnw.cmd`：
    - `mvnw` 是用于 Unix/Linux/macOS 系统的 Shell 脚本。
    - `mvnw.cmd` 是用于 Windows 系统的批处理脚本。
2. `/.mvn/wrapper/maven-wrapper.properties`：
	- 配置文件，指定要使用的 Maven 版本和下载 URL。
3. `/.mvn/wrapper/maven-wrapper.jar`：
    - 一个可执行的 JAR 文件，包含 Maven Wrapper 的实现。

## 添加和使用 Maven Wrapper

```shell
# Add maven wrapper to project
mvn wrapper:wrapper

# Linux
./mvnw clean install

# Windows
mvnw.cmd clean install
```

## 参考链接

1. [Apache Maven Wrapper – Maven Wrapper](https://maven.apache.org/wrapper/index.html)
2. [Spring Initializr](https://start.spring.io/)