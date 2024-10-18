# Maven 常见问题

## Maven Dependency

**问题描述:**
 - Maven Build 时报错： `ERROR Unresolved plugin: 'org.apache.maven.plugins:maven-assembly-plugin:2.2.1'`
 - IDAE 中 pom.xml 文件报错： `Plugin 'org.apache.maven.plugins:maven-source-plugin:2.2.1' not found`

**排查结果**：
1. **问题原因*：
	- Maven 配置文件，或者命令行中的配置，指向的 Repository 中确实不存在对应的 jar 包
	- **解决方案**：
		- 仔细排查 `settings.xml` 文件中的 `mirrors` 等参数，访问 Repository 网页，确定是否是配置路径等错误，配置正确的 `mirrors` 参数。
2. **问题原因**：
	- JetBrain IDEA 中内置的 Maven 是存在 Index BUG 的，不论使用的是官方还是私有 Maven 镜像，都会出现这种问题，即 Mirror 中存在，但 IDEA Maven 就是无法识别并下载。
	- **解决方案（未成功）**：
		- 删除 Maven 本地 repository 中对应 plugin 的版本文件夹，并重启 IDEA。
		- IDEA 中点击 `File | Invalidate caches`，清除缓存并重启。
	- **解决方案（成功）**：手动使用官方 Maven 命令行工具下载缺失的 Plugin，并在 IDEA 中重新加载。
		- 命令行下载 Dependency：下载安装官方 Maven，并使用命令行安装对应的 Maven plugins。命令如下：`mvn dependency:get -Dartifact="org.apache.maven.plugins:maven-javadoc-plugin:2.9.1" -DrepoUrl=http://repo.maven.apache.org/maven2/`
		- 重新加载项目：进入菜单页 `File | Settings | Build, Execution, Deployment | Build Tools | Maven`，在 `Local repository` 选项处，点击 Overrride 后，或者直接重新打开 Project，即可重新加载本地 repository。
	- **参考链接**：
		- [java - Maven plugins can not be found in IntelliJ - Stack Overflow](https://stackoverflow.com/a/40308560/13774262)
		- [Troubleshooting common Maven issues | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/troubleshooting-common-maven-issues.html#klwtar_1)
	- **解决方案**：
		- 手动下载 Maven 3.3.9 及以下版本，代替 IDEA 内置的 Maven。

## Maven Version

**问题描述**：
- Maven Build 时报错：`ERROR 'parent.version' is either LATEST or RELEASE (both of them are being deprecated)`
**排查结果**：
1. **问题原因**：
	- 当使用 Maven 3.5.2 等版本时，IDEA 在 Load Project 时便会抛出类似信息，如 `[WARNING] 'dependencies.dependency.version' for <groupId>:<artifactId>:jar is either LATEST or RELEASE (both of them are being deprecated)`
	- 当使用高版本的 Maven，解析使用了已废弃特性的 `pom.xml` 文件时，则会抛出 `ERROR`，而非 `WARNING`，导致 Maven 无法正常构建
	- **解决方案：
		- 切换到 Maven 3.3.9 版本以下，进行编译。

## Maven Run

**问题描述**：
- Maven Build 编译通过后，点击 Run Main 方法时，却抛出异常 `ClassNotFoundException`
**问题原因**：
- Maven 项目的配置文件 pom.xml 中对应的 Dependency 中的 scope 被命名为了除 `compile/runtime` 之外的其他 scope（比如 `provided`），其对应的 Dependency 依赖在编译时是不会一起打包的，这种场景适用于当前项目对应的依赖属于独立维护和部署的情况。
**解决方案**：
- 在 IDEA Run Configuration 中，设置 `Modify options - Add dependencies with 'provided' scope to classpath`，这样就能避免 Maven 项目中 scope 为 provided 的 dependency，因为打包时未被引入，而在 IDEA 中直接运行时出现 `ClassNotFoundException` 的异常。

## Maven Compile

**问题描述**：
- Maven 执行 compile 时，只编译了 scala 代码，但是并未编译 java 代码
**问题原因**：
- 因为 Maven pom.xml 文件中插件的配置存在问题
**解决方案**：
1. Maven pom.xml 文件中删除 `sourceDirectory` 和 `testSourceDirectory` 相关配置，避免强制声明源代码和测试代码路径，而是让 Maven 插件采用默认路径
```xml
<sourceDirectory>src/main/scala</sourceDirectory>  
<testSourceDirectory>src/test/scala</testSourceDirectory>
```

## JDK Compatibility

Maven 选用 JDK 的策略：
1. 如果配置了 `JAVA_HOME` 环境变量，则 Maven 会优先使用此变量指向的 JDK
2. 否则，如果直接查找 `PATH` 环境变量，则 Maven 会使用其中指定的 JDK 版本
3. 可以通过 `mvn -v` 命令查看其执行时，使用的 JDK 版本

**问题描述**
- IDEA 中使用 Maven 编译时报错 `Failed to execute goal net.alchim31.maven:scala-maven-plugin:4.4.0:compile (scala-compile-first) on project deequ: Execution scala-compile-first of goal net.alchim31.maven:scala-maven-plugin:4.4.0:compile failed`
**问题原因**：
- JDK 版本和 Scala 版本不兼容时，在编译时便会抛出此错误
	- 在 IDEA 中使用外部的 Maven 、在命令行使用 Maven 时，Maven 会默认使用 `JAVA_HOME` 环境变量指向的 JDK，其次会使用 `PATH` 环境变量指向的 JDK
	- 在 IDEA 中使用内置的 Maven 时，Maven 默认会使用当前 Project Structure 中指定的 JDK 版本
**解决方案**：
- 目标是使得 Maven 使用的 JDK 版本和 Scala 版本兼容
	- 更换项目 Maven 使用的 Java SDK 版本
		- 如果使用的是内置 Maven，即在 `FILE | Project Structure | Project Settings | Project` 中选择和当前版本 Scala 兼容的 JDK 作为项目 SDK。
		- 如果使用的是非内置的 Maven，则需要修改环境变量 `JAVA_HOME` 环境变量指向需要的 JDK 版本，以及 `PATH` 环境变量指向的 JDK
	- 更换项目 Scala SDK 版本
		- 在 pom.xml 文件中修改 Maven 项目的 Scala 依赖，即 `org.scala-lang:scala-library`，将其切换为适配 Maven 使用的 JDK 的版本
**参考链接**：
- [Scala安装基础教程](work/programming/Scala/Scala安装基础教程.md)