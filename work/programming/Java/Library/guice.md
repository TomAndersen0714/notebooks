# Google Guice


## 基础概念


Injector（注射器），可以类比于 Spring Application Context（Spring 上下文）。

[GettingStarted · google/guice Wiki · GitHub](https://github.com/google/guice/wiki/GettingStarted)



## 常用注解

`@Inject`，声明需要被注入实例的变量。

`@Named`，声明注入时需要使用的实例名


`binder().bind(A.Class).to(B.Class)`，在当前 Module 下创建一个 bind，

## 参考链接
1. [GitHub - google/guice: Guice (pronounced 'juice') is a lightweight dependency injection framework for Java 8 and above, brought to you by Google.](https://github.com/google/guice)
3. [Motivation · google/guice Wiki · GitHub](https://github.com/google/guice/wiki/Motivation)
