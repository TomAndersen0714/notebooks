# pip运维



pip的module默认安装路径`site-packages`和`dist-packages`的区别：
- dist-packages是 Debian/Ubuntu 等Linux发行版的默认Python模块安装文件夹。
- site-packages 是RHEL/CentOS 等Linux发行版的默认Python模块安装文件夹。

PS: Debian/Ubuntu 使用`pip、pip3`或者`easy_install`安装的模块 package 默认存放在`/usr/local/lib/python2.7/dist-packages`路径下，[参考链接](https://blog.csdn.net/huiseguiji1/article/details/45111891)
PS：`APT`等包管理工具安装的pip，默认Python模块安装路径为`/usr/local/lib/pythonX.X/dist-packages`，而`YUM`等包管理工具安装的pip，默认Python模块安装路径为`/usr/local/lib/pythonX.X/site-packages`，源码编译的pip工具Python模块默认安装路径和YUM相同。[参考链接](https://blog.csdn.net/huiseguiji1/article/details/45111891)





1. pip工具安装：
   1. 如果是通过apt或者yum等包管理工具安装的话，则可以手动安装pip/pip3工具，但一般情况下安装完对应版本的Python后会自带安装pip工具：
      1. APT：`apt install python-pip`（pip），`apt install python-pip3`（pip3）
      2. YUM：`sudo yum install python-pip`（pip），`sudo yum install python3-pip`（pip3）
   2. 如果是通过源码编译安装Python，则在其Scripts文件夹下就带有pip工具


2. pip修改镜像配置：[参考链接](https://developer.aliyun.com/mirror/pypi?spm=a2c6h.13651102.0.0.3e221b110KOHKi)
