# Python 虚拟环境基础教程

创建 Python 虚拟环境的目的是为了实现 Python 运行环境的隔离，支持环境隔离不同版本的模块。需要删除 Python 虚拟环境时，直接删除对应的虚拟环境文件夹即可。

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

Pipenv 是 Pip 和 Venv 工具的结合升级版，用于进行 Python 项目的虚拟环境创建和依赖管理。

[Pipenv](https://packaging.python.org/en/latest/key_projects/#pipenv) is a dependency manager for Python projects. If you’re familiar with Node.js ’ [npm](https://www.npmjs.com/) or Ruby’s [bundler](https://bundler.io/), it is similar in spirit to those tools.

While [pip](https://packaging.python.org/en/latest/key_projects/#pip) alone is often sufficient for personal use, Pipenv is recommended for collaborative projects as it’s a higher-level tool that simplifies dependency management for common use cases.

安装 pipenv：
```shell
pip install pipenv
```



## 参考链接

1. [Installing Packages - Python Packaging User Guide](https://packaging.python.org/tutorials/installing-packages/#creating-virtual-environments)