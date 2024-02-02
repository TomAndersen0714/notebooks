# Python 开发基础教程


## 开发环境

[Python开发环境搭建基础教程](work/programming/Python/Python开发环境搭建基础教程.md)

## 开发流程

分析、设计、开发、测试、部署、运维

## 开发（Develop）


## 测试 (Test)


## 部署（Deploy）


### 生成 requirements.txt 文件

`requirements.txt` 文件是一个用于记录 Python 项目所依赖的软件包及其版本信息的文本文件。可以用于快速环境部署。

导出当前 Python 环境下的所有 package 信息：
1.  `pip freeze > requirements.txt`。
导出指定文件路径下的所有 package 信息：
1. `pip install pipreqs`
2. `pipreqs /path/to/project --encoding=utf8--force`。

PS：使用 `freeze` 和 `pipreqs` 工具，一般都无法直接导出 package 的真实名称，通常还需要人工检查 `requirements.txt` 文件，确定 package 名称和实际的一致。


安装 `requirements.txt` 中指定的 package：` pip install -r requirements. Txt `。


### Python Library

[Packaging Python Projects](https://packaging.python.org/en/latest/overview/)

### Python Application

#### Binary
https://pyinstaller.org/en/stable/

#### Source

不支持 C/C++ Library 自动部署，需要手动安装 C/C++ Library。
https://blog.csdn.net/smilehappiness/article/details/117337943

