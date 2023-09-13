# Spark Shell 基础教程


## 简介



## spark-shell 常用命令




## spark-shell 常见问题


报错信息：
`org.apache.hadoop.security.AccessControlException: Permission denied: user=root, access=WRITE, inode="/user":hdfs:supergroup:drwxr-xr-x`
原因：
Spark-shell 启动时，默认会使用当前登录的系统用户。从报错信息上来看，当前登录 Linux 系统时使用的用户为 `root`，执行操作为 `write`，但当前用户并没有写入对应路径的写入权限，于是报错。
解决方案：
1. 使用 sudo 命令，切换为可访问用户执行 `spark-shell`，如 `sudo -u spark spark-shell`
