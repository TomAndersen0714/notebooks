# Python 虚拟环境基础教程

在同一个 Python 环境中，只能安装某单个版本的 package ，也只能 import 某单个版本的 python package 作为依赖。而在 Java 中却可以通过 Maven、Gradle 来控制项目依赖的版本，同时支持多种版本依赖。

在不同 Python 项目中，经常会出现各自依赖的同名 package 其版本不同的情况，而 Python 虚拟环境就是为了通过环境隔离，支持不同的项目中依赖和使用不同版本的模块。

当需要卸载 Python 虚拟环境时，直接删除对应的虚拟环境文件夹即可。

[官方教程](https://packaging.python.org/tutorials/installing-packages/#creating-virtual-environments)

### venv

创建和加载 Python 虚拟环境：
```
# linux
python3 -m venv <env_dir>
source <env_dir>/bin/activate

# windows
py -m venv <env_dir>
<env_dir>\Scripts\activate
```

#### 常见问题

报错信息
-  `Error: Command '['/data0/workspace/cc/xdt_env/bin/python3', '-Im', 'ensurepip', '--upgrade', '--default-pip']' returned non-zero exit status 1.`
解决方案
- 在 `CentOS Linux release 7.9.2009` 的 `Python3.6.8` 中使用 `venv` 创建时遇见的问题，如果 `venv` 无法使用，建议使用 `virtualenv` 来创建虚拟环境。

### virtualenv

Virtualenv 并非 Python 自带 package，需要自己手动安装。

安装 virtualenv：
`pip3 install virtualenv -i https://mirrors.aliyun.com/pypi/simple/`

创建和加载虚拟环境：
```
# linux
python3 -m virtualenv <DIR>
source <DIR>/bin/activate

# windows
virtualenv <DIR>
<DIR>\Scripts\activate
```

### pipenv

Pipenv 是 Pip 和 Venv 工具的结合，支持虚拟环境和项目依赖管理，

[pipenv基础教程](work/programming/Python/CLI/pipenv基础教程.md)

### poetry

[Poetry基础教程](work/programming/Python/CLI/Poetry基础教程.md)

## 参考链接

1. [Installing Packages - Python Packaging User Guide](https://packaging.python.org/tutorials/installing-packages/#creating-virtual-environments)