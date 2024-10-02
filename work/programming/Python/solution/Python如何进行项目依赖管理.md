# Python 如何进行项目依赖管理

## 前言

在早期的 PEP 规范中，Python 项目是通过 `pip+requirements.txt` 的方式来进行手动版本管理，其缺陷在于不同的环境，要维护不同的 requirements.txt 文件，无法统一。

后续逐渐出现了 PEP 517 and PEP 621 等新的 Python 规范，以及对应的依赖管理工具，PDM、Pipenv、Poetry 等。

## Pipenv

[pipenv基础教程](work/programming/Python/CLI/pipenv基础教程.md)
## Poetry

[Poetry基础教程](work/programming/Python/CLI/Poetry基础教程.md)

## PDM

## 参考链接

1. [Managing Application Dependencies - Python Packaging User Guide](https://packaging.python.org/en/latest/tutorials/managing-dependencies/)