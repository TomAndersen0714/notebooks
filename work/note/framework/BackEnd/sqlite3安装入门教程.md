sqlite3安装入门教程

## 前言

安装python2.x及以上版本时，都会同时安装自带的sqlite3

## 具体步骤



### 方法一：yum或apt直接安装，但是版本无法调整

```shell
sudo yum install sqlite3
```



```shell
sudo apt install sqlite3
```





### 方法二：sqlite3源码编译安装

参考链接：https://blog.csdn.net/kaikai508282737/article/details/128353237



编译源码并安装

```
```



覆盖原命令行工具





拷贝并替换原库文件

cp /usr/lib64/libsqlite3.so.0.8.6 ./libsqlite3.so.0.8.6_backup

cp /usr/local/lib/libsqlite3.so.0.8.6 /usr/lib64/





#### 查看sqlite3版本

```shell
# 查看命令行工具
sqlite3 --version

# 查看Python链接库
python3 -c "import sqlite3; print(sqlite3.sqlite_version)"
```





## 参考链接

1. https://blog.csdn.net/weixin_45661908/article/details/123942800