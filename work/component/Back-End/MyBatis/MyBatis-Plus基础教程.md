# MyBatis-Plus 基础教程

## 简介

MyBatis Plus 的主要作用是在现有的 MyBatis 基础上进行功能扩展，可以避免在 MyBatis 中通过配置文件来定义数据库查询 SQL 的传统方式，而是将一些常用的 CRUD 操作抽象并实现在了代码中，支持通过自定义继承、实现相关接口，来直接调用对应方法进行 CRUD，而不用再去创建和定义对应的 XML 配置文件。

## MyBatis Plus 配置

MyBatis-Plus 中默认会开启配置 `map-underscore-to-camel-case`，即开启驼峰命名映射，即在在映射实体或者实体的属性时，将数据库中表名和字段名中的下划线去掉，按照驼峰命名法映射，如：`address_book` 会映射为 `addressBook`。

## 参考链接

1. [GitHub - baomidou/mybatis-plus: An powerful enhanced toolkit of MyBatis for simplify development](https://github.com/baomidou/mybatis-plus)