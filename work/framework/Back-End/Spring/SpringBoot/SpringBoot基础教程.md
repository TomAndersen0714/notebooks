# SpringBoot 基础教程

什么是 SpringBoot？

SpringBoot 是基于 Spring Framework 之上构建的用于简化程序启动和部署的框架。

为什么会出现 SpringBoot？

传统通过 XML 配置文件定义程序启动配置、部署的方式，可读性差，且不好维护，于是便有了 SpringBoot，正如其名。

SpringBoot 相关开源项目，站在前人的肩膀上：
1. [GitHub - halo-dev/halo: 强大易用的开源建站工具。](https://github.com/halo-dev/halo)

基于 Maven Wrapper 构建的 SpringBoot 项目结构参考：
```bash
root@TomLaptop:~/workspace/codes/springboot# tree
├── HELP.md
├── bin
├── conf
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── springboot
    │   │               ├── SpringbootApplication.java
    │   │               ├── common
    │   │               │   ├── exception
    │   │               │   └── util
    │   │               ├── config
    │   │               │   ├── FilterConfig.java
    │   │               │   └── LoginFilter.java
    │   │               ├── controller
    │   │               │   └── UserController.java
    │   │               ├── mapper
    │   │               │   ├── UserMapper.java
    │   │               │   └── entity
    │   │               │       └── User.java
    │   │               ├── model
    │   │               │   ├── UserDTO.java
    │   │               │   └── UserVO.java
    │   │               └── service
    │   │                   ├── UserService.java
    │   │                   └── impl
    │   │                       └── UserServiceImpl.java
    │   └── resources
    │       ├── application.properties
    │       ├── mapper
    │       │   └── UserInfoMapper.xml
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── example
                    └── springboot
                        └── SpringbootApplicationTests.java
```

备注：
1. Model 中放置 DTO、VO 等，非 DO 对象
2. Entity 中放置数据库表对应对象，即 DO

## 参考链接

1. [SpringBoot项目的目录结构及名规范\_spring boot项目名称命名规则-CSDN博客](https://blog.csdn.net/goodjava2007/article/details/122275079)