# Python 命令行基础教程

## 命令行选项

`-c` 选项：
1. 执行 Python 代码片段

`-m` 选项：
1. 如果 bar 是一个模块 module（`.py/.pyc/.pyd` 等结尾的文件），如 `python3 -m foor.bar`（注意没有加上文件名后缀），则会直接执行对应的文件。同时会将 Python 命令行执行时当前路径添加到 `sys.path` 中
2. 如果bar是一个包 package（带有 `__init__` 文件的文件夹），则会直接执行包中的 `__main__` 模块 module（即 `__main__.py` 文件），同样是会将python命令行执行时的路径添加到sys. Path中，如果缺少 `__main__.py` 文件则会报错 `No module named test.__main__`
3. 如果不使用 `-m` 选项，则是按照文件路径的层次进行调用，使用斜杠 `/` 来声明调用层次，并且同时将脚本文件所在的绝对路径添加到 ` sys.path ` 变量中，而不是 python 命令的当前路径，如 ` python3 foor/bar.py `（注意需要加上文件后缀）
4. 不论是哪种执行方式变量`__name__`的值都为`__main__`

## 常用命令

**Python查看查找路径（sys.path）**
```shell
# 直接命令行执行Python代码
python3 -c "import sys; print(sys.path)"
# 命令行执行对应模块main方法
python -m site
```

**Python查看当前工作目录（work directory）**：
```shell
python3 -c "import os; print(os.getcwd())"
```

**Python的模块安装路径site-packages 和dist-packages的区别**：
dist-packages是 Debian/Ubuntu 等Linux发行版的默认Python模块安装文件夹。
site-packages 是 RHEL/CentOS 等Linux发行版的默认Python模块安装文件夹。
PS：Debian/Ubuntu 使用“pip、pip3”或者“easy_install”安装的模块 package 默认存放在“/usr/local/lib/python2.7/dist-packages”路径下
PS：[参考链接](https://blog.csdn.net/huiseguiji1/article/details/45111891)

**Python 安装 Python 虚拟环境**：

[官方教程](https://packaging.python.org/tutorials/installing-packages/#creating-virtual-environments)

创建 Python 虚拟环境的目的是为了实现 Python 运行环境的隔离，支持分离部署不同的模块。

可以使用 Python3 自带工具 venv：创建 Python 虚拟环境 `python3 -m venv <env dir>`，加载虚拟环境的环境变量 `source <env_dir>/bin/activate`。

需要删除 Python 虚拟环境时，直接删除对应的虚拟环境文件夹即可。