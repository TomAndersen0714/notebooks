# SpringBoot 基础教程

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