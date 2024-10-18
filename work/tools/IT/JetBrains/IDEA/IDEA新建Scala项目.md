# IDEA 新建 Scala 项目

## Maven

通过 Maven Archetype 来创建：

![](resources/images/Pasted%20image%2020241010102648.png)

Maven Central 仓库的 Archetype 元数据经常加载不出来，这时候建议切换成手动添加 Archetype: `net.alchim31.maven:scala-archetype-simple:1.7`，如果因网络等原因一开始没有下载和生成相关文件，后续也可以通过 Maven 侧边工具栏重新加载项目，手动触发初始化。

![](resources/images/Pasted%20image%2020241010103533.png)

或者 Maven 命令行创建：
```bash
mvn archetype:generate -DarchetypeGroupId="net.alchim31.maven" -DarchetypeArtifactId="scala-archetype-simple" -DarchetypeVersion="1.7"
```

注意：建议删除此项目模板 pom.xml 文件的插件配置 `sourceDirectory` 和 `testSourceDirectory`，避免在增加 java 代码时，Maven compile 时仅使用指定路径，而不包含 java 源代码路径（`src/main/java`）。

```xml
<sourceDirectory>src/main/scala</sourceDirectory>  
<testSourceDirectory>src/test/scala</testSourceDirectory>
```

## Gradle

## SBT

## 参考链接

1. [Get started with Scala | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/get-started-with-scala.html)