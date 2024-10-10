# IDEA 新建 Scala 项目

## Maven

通过 Maven Archetype 来创建：

![](resources/images/Pasted%20image%2020241010102648.png)

Maven Central 仓库的 Archetype 元数据经常加载不出来，这时候建议切换成手动添加，后续通过 Maven 侧边工具栏重新加载项目，实现初始化。
![](resources/images/Pasted%20image%2020241010103533.png)

或者 Maven 命令行创建：
```bash
mvn archetype:generate -DarchetypeGroupId=net.alchim31.maven -DarchetypeArtifactId=scala-archetype-simple
```

## Gradle

## SBT

## 参考链接

1. [Get started with Scala | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/get-started-with-scala.html)