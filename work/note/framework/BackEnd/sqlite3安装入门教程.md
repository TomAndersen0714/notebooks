# sqlite3安装升级入门教程

## 前言

安装python2.x及以上版本时，都会同时安装自带的sqlite3，但如Django、Airflow等组件通常需要较高的sqlite3版本



## 具体步骤

### 方法一：yum或apt直接安装，但是版本无法调整

```shell
sudo yum install sqlite3
```



```shell
sudo apt install sqlite3
```





### 方法二：sqlite3源码编译安装

#### 源码下载

源码下载链接：https://www.sqlite.org/download.html，下载autoconf版本，如[sqlite-autoconf-3400000.tar.gz](https://www.sqlite.org/2022/sqlite-autoconf-3400000.tar.gz)

```shell
wget https://www.sqlite.org/2022/sqlite-autoconf-3400000.tar.gz
```

#### 编译安装

解压并编译源码

```shell
tar zxvf sqlite-autoconf-3400000.tar.gz

cd sqlite-autoconf-3400000

# 设置编译结果输出路径
./configure --prefix=/usr/local/sqlite3

# 使用2个CPU核心并行编译
make -j 2

make install
```

如果不指定`--prefix`参数，则默认情况下，可执行文件默认放在`/usr/local/bin`，链接库文件默认放在`/usr/local/lib`，配置文件默认放在`/usr/local/etc`，其它的资源文件放在`/usr/local/share`

如果中途出现错误，重试前要执行`make clean`清除上次编译结果



#### 设置环境变量

设置环境变量，添加sqlite3命令行工具路径

```shell
echo -e 'export PATH=/usr/local/sqlite3/bin:$PATH\nexport PKG_CONFIG_PATH=/usr/local/sqlite3/lib/pkgconfig:$PKG_CONFIG_PATH' >> /etc/profile.d/sqlite3.sh
```



#### 配置链接库

```
mv /etc/ld.so.conf.d/sqlite3.conf /tmp/sqlite3.conf_backup

echo -e "/usr/local/sqlite3/lib" > /etc/ld.so.conf.d/sqlite3.conf

ldconfig -v

ldconfig -p | grep sqlite
```



#### 查看sqlite3版本

```shell
# 查看命令行工具
sqlite3 --version

# 查看Python默认使用的sqlite3版本
python3 -c "import sqlite3; print(sqlite3.sqlite_version)"
```

两个版本一定要对应上



#### 卸载sqlite3

如果要卸载本次安装的sqlite3，则建议执行以下步骤：

1. 删除sqlite3的安装文件夹`/usr/local/sqlite3`
2. 删除配置文件`/etc/profile.d/sqlite3.sh`和`/etc/ld.so.conf.d/sqlite3.conf`文件
3. 执行`ldconfig -v`命令
4. 最后重新登录当前用户即可



## 参考链接

1. https://www.sqlite.org/download.html
2. https://blog.csdn.net/weixin_45661908/article/details/123942800
3. https://blog.csdn.net/kaikai508282737/article/details/128353237