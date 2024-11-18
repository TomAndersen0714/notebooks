# Oracle JDK安装基础教程

## 前言

- JDK(Java Development kit)，即Java开发工具包，JDK中包含有JRE，JRE(Java Runtime Environment)，即Java运行环境，JRE中包含有JVM(Java Virtual Machine)，即Java虚拟机，用于解决Java跨平台开发的兼容性问题，实现了一次开发多处运行
- Oracle JDK是市场占有率最高的JDK发行版，也就是最常说的JDK，与其他的发行版一样，也是起源于Open JDK
- Oracle JDK的长期支持版本（LTS）目前有JDK8、JDK11、JDK17，其中Oracle JDK17使用的是Oracle NFTC协议，可以限时免费商用，时间截止到2024年9月，共计3年，推荐安装使用Oracle JDK 17
- 除了Oracle JDK之外，还有其他的一些比较流行的JDK发行版，可供Java开发人员选择，如阿里的Dragonwell、华为的毕昇、腾讯的Kona等
- Oracle JDK官方下载地址：https://www.oracle.com/java/technologies/downloads/archive/
- Oracle JDK官方安装教程：https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html
- Oracle Java SE帮助中心：https://docs.oracle.com/en/java/javase/index.html
- Oracle Java SE & JDK 11 API在线文档：https://docs.oracle.com/en/java/javase/11/docs/api/index.html

## JDK on Linux

参考链接：[Installing the 64-Bit JDK 17 on Linux Platforms](https://docs.oracle.com/en/java/javase/17/install/installation-jdk-linux-platforms.html#GUID-ADC9C14A-5F51-4C32-802C-9639A947317F)

## JDK On Windows

参考链接：[Installation of the JDK on Microsoft Windows Platforms](https://docs.oracle.com/en/java/javase/17/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA)

## JDK On macOS

参考链接： [Installation of the JDK on macOS](https://docs.oracle.com/en/java/javase/17/install/installation-jdk-macos.html)

## 配置环境变量

Linux:
1. 增加 `JAVA_HOME` 环境变量，将变量指向 Java 安装路径。
2. 修改 `Path` 环境变量，将 `$JAVA_HOME/bin` 文件夹添加到 Path 中。

Windows:
1. 同上

## 验证安装

```bash
java -version
```

## 参考链接

1. [Oracle JDK Archive](https://www.oracle.com/java/technologies/downloads/archive/)
2. [Overview of JDK Installation(Oracle JDK 17)](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html)
3. [Installing the 64-Bit JDK 17 on Linux Platforms](https://docs.oracle.com/en/java/javase/17/install/installation-jdk-linux-platforms.html#GUID-ADC9C14A-5F51-4C32-802C-9639A947317F)
4. [Installation of the JDK on Microsoft Windows Platforms](https://docs.oracle.com/en/java/javase/17/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA)