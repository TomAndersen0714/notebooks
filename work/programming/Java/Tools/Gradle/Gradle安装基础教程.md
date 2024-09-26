# Gradle 安装基础教程

## Prerequisites

Gradle 和 Java 的版本兼容表格
https://docs.gradle.org/current/userguide/compatibility.html

Gradle至少需要 jdk8 及以上，使用`java -version`检查java版本。

| Java version | Support for toolchains | Support for running Gradle |
|--------------|------------------------|----------------------------|
| 8            | N/A                    | 2.0                        |
| 9            | N/A                    | 4.3                        |
| 10           | N/A                    | 4.7                        |
| 11           | N/A                    | 5.0                        |
| 12           | N/A                    | 5.4                        |
| 13           | N/A                    | 6.0                        |
| 14           | N/A                    | 6.3                        |
| 15           | 6.7                    | 6.7                        |
| 16           | 7.0                    | 7.0                        |
| 17           | 7.3                    | 7.3                        |
| 18           | 7.5                    | 7.5                        |
| 19           | 7.6                    | 7.6                        |
| 20           | 8.1                    | 8.3                        |
| 21           | 8.4                    | 8.5                        |
| 22           | 8.7                    | 8.8                        |
| 23           | 8.10                   | 8.10                       |
| 24           | N/A                    | N/A                        |

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

## Setting Environment

### GRADLE_USER_HOME

设置环境变量`GRADLE_USER_HOME`，以修改`Gradle user home directory`，避免Gradle Wrapper安装Gradle和Package到默认路径`<home directory of the current user>/.gradle`下，便于后续管理。

如，设置为`$GRADLE_HOME$/.gradle`，即统一放置在Gradle安装路径下，其中`$GRADLE_HOME$`代表Gradle安装路径。

## Upgrading

https://docs.gradle.org/current/userguide/upgrading_version_7.html

## Gradle In IDEA

[【IDEA】配置Gradle与使用 - emdzz - 博客园](https://www.cnblogs.com/mindzone/p/12880854.html)

在 `File | Settings | Build, Execution, Deployment | Build Tools | Gradle` 路径下配置 Gradle:
1. 通过 `Gradle JVM` 选项，选择 Gradle 对应的 JVM 版本。
2. 通过 `Use Gradle From` 选项，配置使用的 Gradle 脚手架，如直接使用已安装的 Gradle，或者使用 Gradle Wrapper 脚本工具。

![](resources/images/Pasted%20image%2020240910233124.png)

## 常见问题

**JetBrains IDE 中无法更新 Windows System Environment 的方案，打开 Gradle 菜单和 Terminal 打印都无法显示正确的环境变量值**

[IDEA常见问题](work/tools/IT/JetBrains/IDEA/IDEA常见问题.md)

## 参考链接

1. [GitHub - gradle/gradle: Adaptable, fast automation for all](https://github.com/gradle/gradle)
2. [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html)
3. [Installing Gradle](https://docs.gradle.org/current/userguide/installation.html)