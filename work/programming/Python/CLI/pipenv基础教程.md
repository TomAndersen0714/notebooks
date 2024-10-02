# Python pipenv 基础教程

## 简介

Python 项目的虚拟环境和依赖管理工具，相当于 pip 和 venv 工具的结合。

[Pipenv](https://packaging.python.org/en/latest/key_projects/#pipenv) is a dependency manager for Python projects. If you’re familiar with Node.js ’ [npm](https://www.npmjs.com/) or Ruby’s [bundler](https://bundler.io/), it is similar in spirit to those tools.

While [pip](https://packaging.python.org/en/latest/key_projects/#pip) alone is often sufficient for personal use, Pipenv is recommended for collaborative projects as it’s a higher-level tool that simplifies dependency management for common use cases.

## 安装

```shell
pip install pipenv
```

注意：在 Windows 上安装时，需要将对应安装路径的 Script 文件路径已经添加到 Path 环境变量中，否则在命令行直接使用时会报错无法找到对应的程序。

## 命令行

[Pipenv Commands — pipenv 2023.11.16.dev0 documentation](https://pipenv.pypa.io/en/latest/commands.html#shell)

## 参考链接

1. [Pipenv Commands — pipenv 2023.11.16.dev0 documentation](https://pipenv.pypa.io/en/latest/commands.html#shell)
2. [GitHub - pypa/pipenv: Python Development Workflow for Humans.](https://github.com/pypa/pipenv)