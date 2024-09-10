# Maven 命令行基础教程


## Maven 命令行常用命令


Maven 创建项目:
```bash
# 基于指定 archetype 创建 Maven 项目
mvn archetype:generate
```

Maven 清理项目 target 目录:
```bash
mvn clean
```

Maven compile:
```bash
mvn compile
```

Maven install:
```
mvn install
mvn install -DskipTests # 跳过测试
```


Maven 下载指定依赖:

注意事项:
- Id 参数需要加上双引号，否则在 Windows 命令行会传入错误参数。
```bash
mvn dependency:get -DgroupId="com.alibaba" -DartifactId="fastjson" -Dversion="1.2.70"
mvn dependency:get -Dartifact="com.google.guava:guava:30.1.1-jre"
```