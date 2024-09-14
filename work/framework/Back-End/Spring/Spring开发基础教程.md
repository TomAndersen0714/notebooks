# Spring 开发基础教程


什么是 Spring？



站在前人的肩膀上：
1. [GitHub - halo-dev/halo: 强大易用的开源建站工具。](https://github.com/halo-dev/halo)


基于 Maven Wrapper 构建的项目结构参考：

```bash
root@TomLaptop:~/workspace/codes/springboot# tree
.
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── springboot
│   │   │               ├── SpringbootApplication.java
│   │   │               ├── common
│   │   │               ├── config
│   │   │               ├── controller
│   │   │               ├── database
│   │   │               │   ├── dao
│   │   │               │   └── mapper
│   │   │               ├── entity
│   │   │               └── service
│   │   └── resources
│   │       ├── application.properties
│   │       ├── mapper
│   │       │   └── UserInfoMapper.xml
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── springboot
│                       └── SpringbootApplicationTests.java
```