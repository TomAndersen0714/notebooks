# Gradle安装基础教程




## Prerequisites

Gradle至少需要 jdk8 及以上，使用`java -version`检查java版本。

### Compatibility

Gradle和Java的版本兼容表格
https://docs.gradle.org/current/userguide/compatibility.html


## Installing

### Method1: Installing with a package manager

Unix: 
```
sdk install gradle
```

MacOS: 
```
brew install gradle
sudo port install gradle
```

### Method2: Installing manually

#### Step1. Download the latest Gradle distribution

https://gradle.org/releases/
此处直接下载binary-only(bin) 已编译安装包。


#### Step2. Unpack the distribution

创建Gradle路径，并将压缩包解压到对应路径下。

Linux & MacOS users
```bash
❯ mkdir /opt/gradle
❯ unzip -d /opt/gradle gradle-8.1.1-bin.zip
❯ ls /opt/gradle/gradle-8.1.1
LICENSE  NOTICE  bin  README  init.d  lib  media
```

Windows users
使用解压工具，将压缩包内容解压到自定义路径下。


#### Step 3. Configure your system environment

编辑环境变量PATH，将Gradle bin文件夹路径添加到PATH中。

Linux & MacOS users
```bash
export PATH=$PATH:/path/to/gradle-8.1.1/bin
```


Windows users
```
Properties → Advanced System Settings → Environmental Variables → System Variables
```

### Verifying installation

```bash
gradle -v
```

```bash
Welcome to Gradle 8.1.1!

Here are the highlights of this release:
 - Stable configuration cache
 - Experimental Kotlin DSL assignment syntax
 - Building with Java 20

For more details see https://docs.gradle.org/8.1.1/release-notes.html


------------------------------------------------------------
Gradle 8.1.1
------------------------------------------------------------

Build time:   2023-04-21 12:31:26 UTC
Revision:     1cf537a851c635c364a4214885f8b9798051175b

Kotlin:       1.8.10
Groovy:       3.0.15
Ant:          Apache Ant(TM) version 1.10.11 compiled on July 10 2021
JVM:          11.0.15.1 (Oracle Corporation 11.0.15.1+2-LTS-10)
OS:           Windows 11 10.0 amd64
```


## Setting Environment Variable

### GRADLE_USER_HOME

设置环境变量`GRADLE_USER_HOME`，以修改`Gradle user home directory`，避免Gradle Wrapper安装Gradle和Package到默认路径`<home directory of the current user>/.gradle`下，便于后续管理。

如，设置为`$GRADLE_HOME$/.gradle`，即统一放置在Gradle安装路径下，其中`$GRADLE_HOME$`代表Gradle安装路径。

**PS: JetBrains IDE中无法更新Windows System Environment的方案**
1. 安装JetBrains ToolBox，升级和管理IDE
2. 直接重启主机

## Upgrading

https://docs.gradle.org/current/userguide/upgrading_version_7.html


## 参考链接
1. [Github Gradle](https://github.com/gradle/gradle)
2. [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html)
3. [Gradle Installing Gradle](https://docs.gradle.org/current/userguide/installation.html)