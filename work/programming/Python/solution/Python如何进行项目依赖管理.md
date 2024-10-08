# Python 如何进行项目依赖管理

## 前言

在早期的 PEP 规范中，Python 项目是通过 `pip + venv + requirements.txt` 的方式来进行手动版本管理，其缺陷在于不同的部署环境，要维护不同的 requirements.txt 文件，复杂度较高。

后续逐渐出现了 PEP 517 and PEP 621 等新的 Python 规范，以及对应的依赖管理工具，PDM、Pipenv、Poetry 等。

## Pip

[Python-pip基础教程](work/programming/Python/CLI/Python-pip基础教程.md)

## Pipenv

[pipenv基础教程](work/programming/Python/CLI/pipenv基础教程.md)
## Poetry

[Poetry基础教程](work/programming/Python/CLI/Poetry基础教程.md)

## PDM

## 参考链接

1. [Managing Application Dependencies - Python Packaging User Guide](https://packaging.python.org/en/latest/tutorials/managing-dependencies/)
2. [Manage project requirements | PyCharm Documentation](https://www.jetbrains.com/help/pycharm/managing-project-dependencies.html)