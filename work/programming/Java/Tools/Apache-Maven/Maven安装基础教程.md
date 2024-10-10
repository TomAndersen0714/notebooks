# Maven安装基础教程

## 兼容性检查

检查 Maven 和 JDK 的兼容性（compatibility），确定需要安装的 Maven 和 JDK 版本。
[Maven – Maven Releases History](https://maven.apache.org/docs/history.html)

## 下载教程

https://maven.apache.org/download.cgi

Maven archive:
https://archive.apache.org/dist/maven
https://archive.apache.org/dist/maven/maven-3/
https://archive.apache.org/dist/maven/maven-4/

## 安装教程

https://maven.apache.org/install.html

### 安装配置 JDK

Have a JDK installation on your system. Either set the `JAVA_HOME` environment variable pointing to your JDK installation or have the `java` executable on your `PATH`.
[Oracle-JDK安装基础教程](work/programming/Java/operation/Oracle-JDK安装基础教程.md)

注意事项：
1. 如果配置了 `JAVA_HOME` 环境变量，则 Maven 会优先使用此变量指向的 JDK
2. 否则，如果直接查找 `PATH` 环境变量，则 Maven 会使用其中指定的 JDK 版本

### 解压 Maven 压缩包

将 Maven 压缩包中内容解压到安装路径下

```cmd
unzip apache-maven-3.9.9-bin.zip
```

```sh
tar xzvf apache-maven-3.9.9-bin.tar.gz
```

### 配置环境变量 Path

- Add the `bin` directory of the created directory `apache-maven-3.9.9` to the `PATH` environment variable

### 验证安装结果

- Confirm with `mvn -v` in a new shell. The result should look similar to

```sh
Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
Maven home: /opt/apache-maven-3.9.9
Java version: 1.8.0_45, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.8.5", arch: "x86_64", family: "mac"
```

## 参考链接
1. https://maven.apache.org/
2. https://maven.apache.org/install.html