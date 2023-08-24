# Python 安装基础教程


## Windows

https://www.python.org/downloads/windows/


## Linux/Unix

https://www.python.org/downloads/source/


## Python 虚拟环境安装

创建 Python 虚拟环境的目的是为了实现 Python 运行环境的隔离，支持分离部署不同的模块。需要删除 Python 虚拟环境时，直接删除对应的虚拟环境文件夹即可。

[官方教程](https://packaging.python.org/tutorials/installing-packages/#creating-virtual-environments)

### venv

创建 Python 虚拟环境：
`python3 -m venv <env_dir>`

加载虚拟环境：
`source <env_dir>/bin/activate`。

#### 常见问题

1.  `Error: Command '['/data0/workspace/cc/xdt_env/bin/python3', '-Im', 'ensurepip', '--upgrade', '--default-pip']' returned non-zero exit status 1.`

在 `CentOS Linux release 7.9.2009` 的 `Python3.6.8` 中使用 `venv` 创建时遇见的问题，如果 `venv` 无法使用，建议使用 `virtualenv` 来创建虚拟环境。

### virtualenv

Virtualenv 并非 Python 自带 package，需要自己手动安装。

安装 virtualenv：
`pip3 install virtualenv -i https://mirrors.aliyun.com/pypi/simple/`

创建 Python 虚拟环境：
`python3 -m virtualenv <env_dir>`

加载虚拟环境：
`source <env_dir>/bin/activate`


## 参考链接

1. https://www.python.org/about/