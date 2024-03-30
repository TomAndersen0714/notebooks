# Python-pip 配置基础教程

## 配置文件

[Configuration - pip documentation v24.0](https://pip.pypa.io/en/stable/topics/configuration/)

配置文件中配置项的 `global/freeze/install/...` 等指的是 `Per-command section`，用于指定在使用对应 pip 子命令时，加载的参数，`global` 则代表针对所有子命令都生效。

## 配置 Pip repository 源


**方法 1：通过 `pip config` 命令修改配置文件内容**

`pip config set --global global.index-url https://mirrors.aliyun.com/pypi/simple/`


**方法 2 ：修改配置文件（配置优先级为升序）**

Unix:
1. Global
	1. In a “pip” subdirectory of any of the paths set in the environment variable XDG_CONFIG_DIRS (if it exists), for example `/etc/xdg/pip/pip.conf`.
	2. This will be followed by loading `/etc/pip.conf`.
2. User
	1. `$HOME/.config/pip/pip.conf`, which respects the `XDG_CONFIG_HOME` environment variable.
	2. The legacy “per-user” configuration file is also loaded, if it exists: `$HOME/.pip/pip.conf`.
3. Site
	1. `$VIRTUAL_ENV/pip.conf`

配置文件中增加 pip 配置项：
```
[global]
index-url=https://mirrors.aliyun.com/pypi/simple/
```


**方法 3：通过 `-i` 参数指定本次使用下载源**

`sudo pipinstall <package name> -i <mirror_url>`


**常用 PyPI 源镜像 URL**：
1. 阿里云： https://mirrors.aliyun.com/pypi/simple/
2. 清华： https://pypi.tuna.tsinghua.edu.cn/simple/
3. 中国科技大学： https://pypi.mirrors.ustc.edu.cn/simple/
4. 华中科技大学： http://pypi.hustunique.com/
5. 山东理工大学： http://pypi.sdutlinux.org/
6. 豆瓣： http://pypi.douban.com/simple/


## 配置 Model 安装路径

**pip 的 module 默认安装路径 `site-packages` 和 `dist-packages` 的区别**：
1. Dist-packages 是 Debian/Ubuntu 等 Linux 发行版的默认 Python 模块安装文件夹，即 `APT` 等包管理工具安装的 pip，默认 Python 模块安装路径为 `/usr/local/lib/pythonX.X/dist-packages`。 [参考链接](https://blog.csdn.net/huiseguiji1/article/details/45111891)
2. Site-packages 是 RHEL/CentOS 等 Linux 发行版的默认 Python 模块安装文件夹，即 `YUM` 等包管理工具安装的 pip，默认 Python 模块安装路径为 `/usr/local/lib/pythonX.X/site-packages`。
3. 通过编译源码安装的 pip 工具 Python 模块默认安装路径和 YUM 相同。[参考链接](https://blog.csdn.net/huiseguiji1/article/details/45111891)


## 参考链接
1. [Configuration - pip documentation v24.0](https://pip.pypa.io/en/stable/topics/configuration/)
2. [pypi镜像\_pypi下载地址\_pypi安装教程-阿里巴巴开源镜像站](https://developer.aliyun.com/mirror/pypi?spm=a2c6h.13651102.0.0.3e221b110KOHKi)
3. [Configuration - pip documentation v24.0](https://pip.pypa.io/en/stable/topics/configuration/)