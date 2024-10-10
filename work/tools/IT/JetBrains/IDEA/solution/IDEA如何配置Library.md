# IDEA 如何配置 Library

注意：在 IDEA 最佳实践中，不建议手动引入任何 SDK，一般是通过在 Maven `pom.xml` 文件，或者 Gradle `build.gradle` 文件中声明对应的依赖，并在 IDEA 导入 Maven/Gradle 项目时自动配置各个 Module 的依赖。

如 Maven pom.xml 文件声明 Scala 依赖：
```xml
<dependency>  
  <groupId>org.scala-lang</groupId>  
  <artifactId>scala-library</artifactId>  
  <version>${scala.version}</version>  
</dependency>
```

## 平台级别 Library

![](resources/images/Pasted%20image%2020241010112121.png)

## 项目级别 Library

![](resources/images/Pasted%20image%2020241010112044.png)

## 模块级别 Library

Maven 项目一般都是通过 pom.xml 文件来定义依赖，在 IDEA 中导入 Maven 项目或者模块时，也不建议手动设置依赖。

![](resources/images/Pasted%20image%2020241010112655.png)