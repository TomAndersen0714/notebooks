# SpringBoot 基础教程


什么是 SpringBoot？

为什么会出现 SpringBoot？

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
│   │   │               │   ├── exception
│   │   │               │   └── util
│   │   │               ├── config
│   │   │               ├── controller
│   │   │               │   ├── UserController.java
│   │   │               │   └── entity
│   │   │               │       └── UserVO.java
│   │   │               ├── mapper
│   │   │               │   ├── UserMapper.java
│   │   │               │   └── entity
│   │   │               │       └── User.java
│   │   │               └── service
│   │   │                   ├── UserService.java
│   │   │                   ├── entity
│   │   │                   │   └── UserDTO.java
│   │   │                   └── impl
│   │   │                       └── UserServiceImpl.java
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


## 参考链接

1. [SpringBoot项目的目录结构及名规范\_spring boot项目名称命名规则-CSDN博客](https://blog.csdn.net/goodjava2007/article/details/122275079)