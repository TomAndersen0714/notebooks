# Maven pom.xml 项目配置文件基础教程


## What is a POM?


## Project Inheritance

[Maven – Introduction to the POM](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html#project-inheritance)

## Project Dependency

[Maven – Introduction to the Dependency Mechanism](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)

### Transitive Dependencies


### Dependency Scope 

[Maven – Introduction to the Dependency Mechanism#scope](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#dependency-scope)

依赖的生效范围
- `compile` ：默认的作用范围，在编译和打包时，都会引入对应的 Dependency。
- `provide` ：仅在编译时引入对应的 Dependency，打包时不引入。使用此 scope 一般的情况，已经有了对应的运行时环境（即依赖的 jar 包等），而对应的依赖环境一般是独立维护，只需要运行时通过 classpath 进行引入即可。

## 参考链接

1. [Maven – Introduction to the POM](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)
2. [Maven – Introduction to the Dependency Mechanism](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)