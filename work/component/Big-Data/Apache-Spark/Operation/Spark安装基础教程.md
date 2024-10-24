# Spark 安装基础教程

## 依赖项兼容性检查

JDK Compatibility

| **Spark Version** | **Supported Java Version(s)** | **Java 8** | **Java 11** | **Java 17** | **Java 21** | **Deprecated Java Version(s)**                      |
| ----------------- | ----------------------------- | ---------- | ----------- | ----------- | ----------- | --------------------------------------------------- |
| 3.5.1             | Java 8*/11/17                 | **Yes**    | **Yes**     | **Yes**     | No          | Java 8 prior to version 8u371 support is deprecated |
| 3.5.0             | Java 8*/11/17                 | **Yes**    | **Yes**     | **Yes**     | No          | Java 8 prior to version 8u371 support is deprecated |
| 3.4.2             | Java 8*/11/17                 | **Yes**    | **Yes**     | **Yes**     | No          | Java 8 prior to version 8u362 support is deprecated |
| 3.4.1             | Java 8*/11/17                 | **Yes**    | **Yes**     | **Yes**     | No          | Java 8 prior to version 8u362 support is deprecated |
| 3.4.0             | Java 8*/11/17                 | **Yes**    | **Yes**     | **Yes**     | No          | Java 8 prior to version 8u362 support is deprecated |
| 3.3.3             | Java 8*/11/17                 | **Yes**    | **Yes**     | **Yes**     | No          | Java 8 prior to version 8u201 support is deprecated |
| 3.3.2             | Java 8*/11/17                 | **Yes**    | **Yes**     | **Yes**     | No          | Java 8 prior to version 8u201 support is deprecated |
| 3.3.1             | Java 8*/11/17                 | **Yes**    | **Yes**     | **Yes**     | No          | Java 8 prior to version 8u201 support is deprecated |
| 3.3.0             | Java 8*/11/17^                | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u201 support is deprecated |
| 3.2.4             | Java 8*/11                    | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u201 support is deprecated |
| 3.2.3             | Java 8*/11                    | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u201 support is deprecated |
| 3.2.2             | Java 8*/11                    | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u201 support is deprecated |
| 3.2.1             | Java 8*/11                    | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u201 support is deprecated |
| 3.2.0             | Java 8*/11                    | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u201 support is deprecated |
| 3.1.3             | Java 8*/11                    | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u92 support is deprecated  |
| 3.1.2             | Java 8*/11                    | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u92 support is deprecated  |
| 3.1.1             | Java 8*/11                    | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u92 support is deprecated  |
| 3.0.3             | Java 8*/11                    | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u92 support is deprecated  |
| 3.0.2             | Java 8*/11                    | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u92 support is deprecated  |
| 3.0.1             | Java 8*/11                    | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u92 support is deprecated  |
| 3.0.0             | Java 8*/11                    | **Yes**    | **Yes**     | No          | No          | Java 8 prior to version 8u92 support is deprecated  |
| 2.4.8             | Java 8*                       | **Yes**    | No          | No          | No          |                                                     |
| 2.4.7             | Java 8*                       | **Yes**    | No          | No          | No          |                                                     |
| 2.4.6             | Java 8*                       | **Yes**    | No          | No          | No          |                                                     |
| 2.4.5             | Java 8*                       | **Yes**    | No          | No          | No          |                                                     |
| 2.4.4             | Java 8*                       | **Yes**    | No          | No          | No          |                                                     |
| 2.4.3             | Java 8*                       | **Yes**    | No          | No          | No          |                                                     |
| 2.4.2             | Java 8*                       | **Yes**    | No          | No          | No          |                                                     |
| 2.4.1             | Java 8*                       | **Yes**    | No          | No          | No          |                                                     |
| 2.4.0             | Java 8*                       | **Yes**    | No          | No          | No          |                                                     |

## 安装

### 安装依赖

[Oracle-JDK安装基础教程](work/programming/Java/operation/Oracle-JDK安装基础教程.md)

### 下载 Spark

[Downloads | Apache Spark](https://spark.apache.org/downloads.html)
[Index of /dist/spark](https://archive.apache.org/dist/spark/)

### 解压 Spark 包

### 配置 Spark 环境变量

## 验证安装

```bash
spark-submit --version
spark-shell --version
spark-sql --version
```

## 参考链接

1. [Downloads | Apache Spark](https://spark.apache.org/downloads.html)
2. [(超详细) Spark环境搭建（Local模式、 StandAlone模式、Spark On Yarn模式）-CSDN博客](https://blog.csdn.net/JunLeon/article/details/123625680)
3. [Spark and Java versions Supportability Matrix - Cloudera Community - 383669](https://community.cloudera.com/t5/Community-Articles/Spark-and-Java-versions-Supportability-Matrix/ta-p/383669)