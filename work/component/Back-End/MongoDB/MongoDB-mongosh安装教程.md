# MongoDB-mongosh安装教程

## 前言

mongosh是MongoDB 5.0及以上版本的官方推出的跨平台、可扩展的JavaScript shell和CLI工具，用于连接和管理MongoDB实例和集群。它提供了类似于MongoDB shell的交互式命令行界面，并支持通过JavaScript编写脚本进行管理和查询。

本文主要演示mongosh在 CentOS 7 下的安装步骤。


## 安装方法

### 一：通过 Yum Repository 安装

#### 生成 yum repository 配置文件

`/etc/yum.repos.d/mongodb-org-6.0.repo`

```repo
[mongodb-org-6.0]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/redhat/$releasever/mongodb-org/6.0/$basearch/
gpgcheck=1
enabled=1
gpgkey=https://www.mongodb.org/static/pgp/server-6.0.asc
```

#### 安装 mongosh
`sudo yum install -y mongodb-mongosh`

#### 连接测试
`mongosh mongodb://root:3SqzSt65@10.248.33.114:27017`


### 二：离线下载RPM包进行安装

#### 下载RPM离线包

https://www.mongodb.com/try/download/shell

Red Hat or CentOS version (for example, 8)

MongoDB edition (for example, mongodb-enterprise)

MongoDB release version (for example, 6.0)

Architecture (for example, x86_64)

如：`wget https://downloads.mongodb.com/compass/mongodb-mongosh-1.1.0.el7.x86_64.rpm`


#### 安装RPM包
`rpm -ivh <rpm_package_name>`


#### 连接测试
如使用 URL 连接，`mongosh mongodb://xdmp:20E6QK8V@10.248.33.114:27017` 


## 参考链接

1. https://www.mongodb.com/docs/mongodb-shell/install/
2. https://www.mongodb.com/try/download/shell
