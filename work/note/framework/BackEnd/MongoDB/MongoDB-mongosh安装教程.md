# MongoDB-mongosh安装教程

## 前言

mongosh是MongoDB 5.0及以上版本的官方推出的跨平台、可扩展的JavaScript shell和CLI工具，用于连接和管理MongoDB实例和集群。它提供了类似于MongoDB shell的交互式命令行界面，并支持通过JavaScript编写脚本进行管理和查询。

本文主要演示mongosh在 CentOS 7 下的安装步骤。


## 安装方法

### 方法一：通过配置Yum Repository安装

生成yum repository配置文件，`/etc/yum.repos.d/mongodb-org-6.0.repo`

```repo
[mongodb-org-6.0]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/redhat/$releasever/mongodb-org/6.0/$basearch/
gpgcheck=1
enabled=1
gpgkey=https://www.mongodb.org/static/pgp/server-6.0.asc
```

安装mongosh
`sudo yum install -y mongodb-mongosh`

测试链接
`mongosh  mongodb://root:3SqzSt65@10.248.33.114:27017`


### 方法二：离线下载RPM包进行安装

①Mongo官网下载版本适配的RPM离线包：

https://www.mongodb.com/try/download/shell

Red Hat or CentOS version (for example, 8)

MongoDB edition (for example, mongodb-enterprise)

MongoDB release version (for example, 6.0)

Architecture (for example, x86_64)

如：`wget https://downloads.mongodb.com/compass/mongodb-mongosh-1.1.0.el7.x86_64.rpm`


②安装RPM包：
`rpm -ivh <rpm_package_name>`


③连接MongoDB，测试安装结果：
如使用URL连接，`mongosh mongodb://xdmp:20E6QK8V@10.0.0.8:27017`


## 参考链接

1. https://www.mongodb.com/docs/mongodb-shell/install/
2. https://www.mongodb.com/try/download/shell
