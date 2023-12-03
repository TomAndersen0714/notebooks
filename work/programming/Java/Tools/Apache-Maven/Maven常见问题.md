# Maven 常见问题

## Maven Plugins

 `ERROR Unresolved plugin: 'org.apache.maven.plugins:maven-assembly-plugin:2.2.1'`

原因 1：
- JetBrain IDEA 中内置的 Maven 是存在 Index BUG 的，不论使用的是官方还是私有 Maven 镜像，都会出现这种问题，即 Mirror 中存在，但 IDEA Maven 就是无法识别并下载。

解决方案 1（未成功）：删除 Maven 本地 repository 中对应 plugin 的版本文件夹，并重启 IDEA。
1. 删除 Maven 本地 repository 中对应 plugin 版本文件夹；
2. IDEA 中点击 `File | Invalidate caches`，清除缓存并重启。

解决方案 2：手动使用官方 Maven 命令行工具下载缺失的 Plugin，并在 IDEA 中重新加载。
1. 下载安装官方 Maven，并使用命令行安装对应的 Maven plugins。命令如下：
```bash

# 
mvn dependency:get Dartifact=ro.isdc.wro4j:wro4j-maven-plugin:1.8.0

# repoUrl 参数指定本次使用的 Repository URL
mvn dependency:get -DrepoUrl=http://repo.maven.apache.org/maven2/ -Dartifact=ro.isdc.wro4j:wro4j-maven-plugin:1.8.0
```
2. 进入菜单页 `File | Settings | Build, Execution, Deployment | Build Tools | Maven`，在 `Local repository` 选项处，点击 Overrride 后，即可重新加载本地 repository，或者直接重新打开 Project。
参考链接：
1. https://stackoverflow.com/a/40308560/13774262

解决方案 3：
1. 手动下载 Maven 3.3.9 及以下版本，代替 IDEA 内置的 Maven。

## Maven version

`ERROR 'parent.version' is either LATEST or RELEASE (both of them are being deprecated)`


原因 1：
1. 当使用 Maven 3.5.2 等版本时，IDEA 在 Load Project 时便会抛出类似信息，如 `[WARNING] 'dependencies.dependency.version' for <groupId>:<artifactId>:jar is either LATEST or RELEASE (both of them are being deprecated)`
2. 当使用高版本的 Maven，解析使用了已废弃特性的 `pom.xml` 文件时，则会抛出 `ERROR`，而非 `WARNING`，导致 Maven 无法正常构建
解决方案：
1. 切换到 Maven 3.3.9 版本以下，进行编译。