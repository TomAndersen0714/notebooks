# Maven 命令行基础教程

在官方文档中，Maven 命令行中使用的各项子命令，届被称为 Maven Plugin。
[Maven – Available Plugins](https://maven.apache.org/plugins/index.html)

## 常用命令

Maven archetype:
- 初始化项目
- Generate a skeleton project structure from an archetype.
```bash
# 交互式创建 Maven 项目
mvn archetype:generate

# 指定 archetype 生成Maven项目 
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
```

Maven clean:
- 清理项目 target 目录
- Cleans the maven project by deleting the target directory (files generated by previous build)
```bash
mvn clean
```

Maven compile:
```bash
mvn compile
mvn compile -DskipTests
```

Maven install:
- 构建项目，并将项目内容安装到本地 Repository，支持其他项目作为依赖 Dependency 引入
- Builds the project and installs resulting artifacts (JAR, WAR, etc) into your local Maven repository
```bash
mvn install
mvn install -DskipTests
```

Maven dependency:
解析当前项目依赖：
```bash
mvn dependency:resolve
```

下载指定依赖
- 注意事项: 各项参数需要加上双引号，否则在 Windows 命令行会传入错误参数。
```bash
mvn dependency:get -DgroupId="com.alibaba" -DartifactId="fastjson" -Dversion="1.2.70"
mvn dependency:get -Dartifact="org.apache.maven.plugins:maven-javadoc-plugin:2.9.1"
```

## 常用选项

`-DskipTests`: 跳过测试流程

## 参考链接

1. [Maven – Available Plugins](https://maven.apache.org/plugins/index.html)
2. [Maven: Common Maven Commands Reference · GitHub](https://gist.github.com/adojos/f51a3e908b0fe65340b4e99ce3bf3b8e)
3. [Apache Maven Dependency Plugin – Introduction](https://maven.apache.org/plugins/maven-dependency-plugin/index.html)
4. [Maven Archetype Plugin](https://maven.apache.org/archetype/maven-archetype-plugin/index.html)
5. [Maven – Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)