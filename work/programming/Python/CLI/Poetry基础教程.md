# Python Poetry 基础教程

## 简介

Python 项目的虚拟环境和依赖管理工具，相当于 pip 和 venv 工具的结合。

`pyproject.toml` 文件中声明了当前项目中各个 python 依赖兼容的版本范围，而 `poetry.lock` 中则声明了当前 python 项目中具体使用的 python package 的版本信息。

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
# 删除python package, 及其依赖的package
poetry remove <package_name>

# 进入项目的 python 虚拟环境
poetry shell
# 退出 python 虚拟环境
exit

# 安装当前项目所需依赖, 会依据 poetry.lock 文件来安装具体某个版本的package, 如果 poetry.lock 文件未更新, 则会基于 pyproject.toml 文件来更新 lock 文件
poetry install

# 检索repository中的package
poetry search <package_name>

# 导出requirements.txt
poetry export --format requirements.txt --output requirements.txt --without-hashes

# 根据 pyproject.toml 文件重新生成 poetry lock 版本文件 poetry.lock
poetry lock --no-update

# 安装当前项目依赖兼容的最新版本,并更新 poetry.lock 文件
poetry update
poetry update <package_name>
```

## 常见问题

报错信息
- `HTTPSConnectionPool(host='pypi.tuna.tsinghua.edu.cn', port=443): Max retries exceeded with url: /simple/pandas/ (Caused by ProxyError('Unable to connect to proxy', FileNotFoundError(2, 'No such file or directory')))`
问题原因
- 一般是由于网络问题，导致即便设置了国内 PyPI 镜像，也会报错
解决方案
- 关闭相关代理工具，使用普通网络重新尝试

报错信息
- `Because apache-airflow (2.5.0) depends on sqlalchemy (>=1.4) and python-projects depends on sqlalchemy (>=1.3.24,<1.4.0), apache-airflow is forbidden. So, because python-projects depends on apache-airflow (2.5.0), version solving failed.`
问题原因
- 项目直接依赖的 package（即在 `pyproject.toml` 文件中声明的）和待安装的 package 的依赖存在版本冲突，但 poetry 只能自动更新间接依赖，不能更新直接依赖
解决方案
- [Python-poetry如何更新package](work/programming/Python/solution/Python-poetry如何更新package.md)

报错信息
- Poetry remove 包时候报错找不到对应的包 `The following packages were not found: pandas`
问题原因
- 如果你在使用 `poetry remove` 命令时遇到报错，提示找不到包，但使用 `poetry show` 命令时能看到对应的包，这可能是因为你尝试移除的包是一个间接依赖（即它是其他包的依赖），而不是直接在 `pyproject.toml` 文件中声明的依赖。
解决方案
- 因为属于当前项目的间接依赖，不建议删除

## 参考链接

1. [GitHub - python-poetry/poetry: Python packaging and dependency management made easy](https://github.com/python-poetry/poetry)
2. [Commands | Documentation | Poetry - Python dependency management and packaging made easy](https://python-poetry.org/docs/cli/)