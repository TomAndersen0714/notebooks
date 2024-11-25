# Python-pip 基础教程

## 前言

PyPI (Python Package Index) 是 Python 编程语言的软件存储库。开发者可以通过 PyPI 查找和安装由 Python 社区开发和共享的软件，也可以将自己开发的库上传至 PyPI。[PyPI官方仓库](https://pypi.org/)

Pip3 和 pip 的命令大部分都相同，`pip3` 和 `pip` 通常可以直接相互替换，[pip官方教程](https://pip.pypa.io/en/stable/user_guide/#user-guide)

## Pip 安装基础教程

### 安装

方法一：如果是在低版本 Unix 环境下通过 apt 或者 yum 等包管理工具安装 Python 的话，中需要手动安装 pip/pip3 工具
1. APT：`apt install python-pip python-pip3`
2. YUM：`sudo yum install python-pip python3-pip`

方法二：如果是通过源码编译安装 Python，则在其 Scripts 文件夹下就带有 pip 工具。

### 配置

[Python-pip配置基础教程](work/programming/Python/CLI/Python-pip配置基础教程.md)

## Pip 常用命令

[Python-pip命令基础教程](work/programming/Python/CLI/Python-pip命令基础教程.md)

## 常见问题

**Python 的模块安装路径 site-packages 和 dist-packages 的区别**：
- Dist-packages 是 Debian/Ubuntu 等 Linux 发行版的默认 Python 模块安装文件夹。
- Site-packages 是 RHEL/CentOS 等 Linux 发行版的默认 Python 模块安装文件夹。
- Debian/Ubuntu 使用“pip、pip 3”或者“easy_install”安装的模块 package 默认存放在“/usr/local/lib/python 2.7/dist-packages”路径下
- [参考链接](https://blog.csdn.net/huiseguiji1/article/details/45111891)