# pip基础教程



## pip运维

1. **pip安装教程**：
	1. 方案一：如果是通过apt或者yum等包管理工具安装的话，则需要手动安装pip/pip3工具，但一般情况下安装完对应版本的Python后会自带安装pip工具：
		1. APT：`apt install python-pip python-pip3`
		2. YUM：`sudo yum install python-pip python3-pip`
	2. 方案二：如果是通过源码编译安装Python，则在其Scripts文件夹下就带有pip工具

2. **pip修改PyPI源**：[参考链接](https://developer.aliyun.com/mirror/pypi?spm=a2c6h.13651102.0.0.3e221b110KOHKi)


3. **pip的module默认安装路径`site-packages`和`dist-packages`的区别：**
	- dist-packages是 Debian/Ubuntu 等Linux发行版的默认Python模块安装文件夹，即`APT`等包管理工具安装的pip，默认Python模块安装路径为`/usr/local/lib/pythonX.X/dist-packages`，。[参考链接](https://blog.csdn.net/huiseguiji1/article/details/45111891)
	- site-packages是 RHEL/CentOS 等Linux发行版的默认Python模块安装文件夹，即`YUM`等包管理工具安装的pip，默认Python模块安装路径为`/usr/local/lib/pythonX.X/site-packages`。
	- 通过编译源码安装的pip工具Python模块默认安装路径和YUM相同。[参考链接](https://blog.csdn.net/huiseguiji1/article/details/45111891)
