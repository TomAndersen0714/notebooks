# Python Poetry 基础教程

## 简介

Python 项目的虚拟环境和依赖管理工具，相当于 pip 和 venv 工具的结合。

## 安装

```shell
pip install poetry
```

注意：在 Windows 上安装时，需要将对应安装路径的 Script 文件路径已经添加到 Path 环境变量中，否则在命令行直接使用时会报错无法找到对应的程序。

## 常用命令

```
# 创建项目
poetry new <project_path>

# 初始化当前项目
cd <project_path>
poetry init

# 配置package的repository
poetry source add <repository_name> <repository_url>
poetry source add custom-repo https://pypi.tuna.tsinghua.edu.cn/simple/

# 添加并安装 python package
poetry add <package_name>
# 添加并安装 python package, 使用指定的 repository
poetry add <package_name> --source <repository_name>

# 进入项目的 python 虚拟环境
poetry shell
# 退出 python 虚拟环境
exit

# 安装当前项目依赖
poetry install

# 检索repository中的package
poetry search <package_name>

# 导出requirements.txt
poetry export --format requirements.txt --output requirements.txt --without-hashes
```

## 常见问题

报错信息
- `HTTPSConnectionPool(host='pypi.tuna.tsinghua.edu.cn', port=443): Max retries exceeded with url: /simple/pandas/ (Caused by ProxyError('Unable to connect to proxy', FileNotFoundError(2, 'No such file or directory')))`
问题原因
- 一般是由于网络问题，导致即便设置了国内 PyPI 镜像，也会报错
解决方案
- 关闭相关代理工具，使用普通网络重新尝试

## 参考链接

1. [GitHub - python-poetry/poetry: Python packaging and dependency management made easy](https://github.com/python-poetry/poetry)
2. [Commands | Documentation | Poetry - Python dependency management and packaging made easy](https://python-poetry.org/docs/cli/)