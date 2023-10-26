# MySQL 安装基础教程

## Install on Linux


### 安装 MySQL Server 和 Client

通常情况下安装 MySQL Server 时，会一起安装 MySQL Client ，因为两者一般都是一起打包发布的。

[MySQL Document - A Quick Guide to Using the MySQL Yum Repository](https://dev.mysql.com/doc/mysql-yum-repo-quick-guide/en/)

### 配置 MySQL Server

[MySQL 8.0 Documentation - 4.2.2 Specifying Program Options](https://dev.mysql.com/doc/refman/8.0/en/program-options.html)


## Install on Docker

https://hub.docker.com/_/mysql

```bash
docker run --name some-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:8.1
```

用户名：`root`，密码：`my-secret-pw`

连接客户端：`mysql -P 3306 -u root -p`

## 参考链接
1. [MySQL 8.0 Documentation - MySQL Installation Guide](https://dev.mysql.com/doc/mysql-installation-excerpt/8.0/en/)
2. [MySQL 8.0 Reference Manual - Chapter 2 Installing and Upgrading MySQL](https://dev.mysql.com/doc/refman/8.0/en/installing.html)
3. [MySQL Document - A Quick Guide to Using the MySQL Yum Repository](https://dev.mysql.com/doc/mysql-yum-repo-quick-guide/en/)
4. [Install and Configure MySQL for Cloudera Software](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/cm_ig_mysql.html#cmig_topic_5_5_1)