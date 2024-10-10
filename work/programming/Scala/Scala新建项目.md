# Scala 新建项目

## SBT

[sbt基础教程](work/programming/Scala/tools/sbt基础教程.md)

## Maven

[scala-with-maven](https://docs.scala-lang.org/tutorials/scala-with-maven.html)

基于 Maven 创建 Scala 项目时，一般都是通过 Maven Archetype 来创建对应的初始空项目，项目初始 `pom.xml` 文件中引入的 plugin 插件 `net.alchim31.maven:scala-maven-plugin:3.2.2` 就是针对 Scala 编译时需要使用的插件。

```bash
mvn archetype:generate -DarchetypeGroupId=net.alchim31.maven -DarchetypeArtifactId=scala-archetype-simple
```

## Gradle

[Site Unreachable](https://docs.gradle.org/current/userguide/scala_plugin.html)
[Building Scala Applications Sample](https://docs.gradle.org/current/samples/sample_building_scala_applications.html)

## IDEA

[IDEA新建Scala项目](work/tools/IT/JetBrains/IDEA/IDEA新建Scala项目.md)

## 参考链接

1. [scala-with-maven](https://docs.scala-lang.org/tutorials/scala-with-maven.html)
2. [GitHub - davidB/scala-maven-plugin: The scala-maven-plugin (previously maven-scala-plugin) is used for compiling/testing/running/documenting scala code in maven.](https://github.com/davidB/scala-maven-plugin)
3. [scala-module-dependency-sample/maven-sample at master · scala/scala-module-dependency-sample · GitHub](https://github.com/scala/scala-module-dependency-sample/tree/master/maven-sample)