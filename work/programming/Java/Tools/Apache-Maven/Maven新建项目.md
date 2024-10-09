# Maven 新建项目

Maven archetype: Generate a skeleton project structure from an archetype.

```bash
# 交互式创建 Maven 项目
mvn archetype:generate

# 指定 archetype 生成Maven项目 
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
```

## 参考链接

1. [Maven – Introduction to Archetypes](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html)