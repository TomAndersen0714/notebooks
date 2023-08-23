# Kudu Python Client 安装基础教程


## 前言

1. CDH 6.3.x官方文档：[CDH 6.3.x - Kudu Python Client](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/kudu_development.html#concept_jmn_dhc_jkb)


## 安装步骤

### 1. 安装Kudu C++ Client Libraries

https://kudu.apache.org/releases/1.8.0/docs/installation.html#build_cpp_client

**安装kudu-client、kudu-client-devel包：** 

**下载链接**：[Kudu Package Locations](https://kudu.apache.org/releases/1.8.0/docs/installation.html#install_packages)，PS：注意系统、组件的版本要对齐


**方法一，使用yum repository，在线安装，需先下载配置对应的yum repository**

```Shell
sudo yum install kudu-client0
sudo yum install kudu-client-devel
```

**方法二，使用RPM包，离线安装，如Kudu1.4.0+cdh5.12.2版本**

```Shell
wget https://archive.cloudera.com/kudu/redhat/7/x86_64/kudu/5/RPMS/x86_64/kudu-client-devel-1.4.0+cdh5.12.2+0-1.cdh5.12.2.p0.8.el7.x86_64.rpm
wget https://archive.cloudera.com/kudu/redhat/7/x86_64/kudu/5/RPMS/x86_64/kudu-client0-1.4.0+cdh5.12.2+0-1.cdh5.12.2.p0.8.el7.x86_64.rpm

sudo yum install kudu-client0-1.4.0+cdh5.12.2+0-1.cdh5.12.2.p0.8.el7.x86_64.rpm
sudo yum install kudu-client-devel-1.4.0+cdh5.12.2+0-1.cdh5.12.2.p0.8.el7.x86_64.rpm
```


### 2. 安装Kudu Python Client

**CentoOS 环境**

yum 安装 C++编译器：`sudo yum -y install gcc-c++`
yum 安装 python3-devel：`sudo yum -y install python-devel python3-devel`

**Python 环境**

Pip3 升级 pip：`pip3 install --upgrade pip`
pip3 安装升级 setup tools：`pip3 install --upgrade pip setuptools`
pip3 安装 Cpython：`pip3 install cython`
pip3 安装 kudu-python：`pip3 install kudu-python==1.2.0`

PS：必要时可以使用 PyPI 镜像源，清华 `-i https://pypi.tuna.tsinghua.edu.cn/simple/`，阿里云 `-i https://mirrors.aliyun.com/pypi/simple/`

## 常见问题

1. 离线安装RPM包时，报错 **missing requires of /lib/lsb/init-functions**
`yum install redhat-lsb-core`
https://community.cloudera.com/t5/Support-Questions/What-exact-dependencies-required-in-lib-lsb-init-functions/m-p/36174

2. pip安装kudu-python时，报错 **could not find int128.h in Kudu**
执行 `pip install kudu-python==1.2.0` 命令，安装 `kudu-python`

kudu-python和kudu c++ libraries之间存在版本兼容问题，需要使用`kudu-python==1.2.0`才和kudu1.4.0兼容
https://community.cloudera.com/t5/Support-Questions/can-not-install-kudu-python/td-p/67496

3. pip安装kudu-python时，报错 **fatal error: Python.h: No such file or directory**
未安装python-devel或python3-devel
`yum install python-devel python3-devel`
https://stackoverflow.com/questions/21530577/fatal-error-python-h-no-such-file-or-directory

4. `ERROR: Could not find a version that satisfies the requirement pytest-runner (from versions: none)`
执行 `pip3 install pytest-runner` 命令，安装 `pytest-runner`

5. PyCharm 远程调试时，一旦执行到 `import kudu` 命令，便会报错 ``python': free(): invalid pointer`
## 参考链接

1. [CDH 6.3.x - Kudu Python Client](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/kudu_development.html#concept_jmn_dhc_jkb)
2. [Apache Kudu - Install Using Packages](https://kudu.apache.org/releases/1.8.0/docs/installation.html#install_packages)

