# Python 项目如何打包和分发

## Python Package

对于 Library 类别的 Python 项目，一般需要通过打包成 python package 分发到其他的 python 环境和项目中使用。此类项目中一般通过 `setup.py` 文件来声明当前 python 项目对应的 python package 信息。

[Overview of Python Packaging - Python Packaging User Guide](https://packaging.python.org/en/latest/overview/)

## Binary

对于 Application 类的 Python 项目，一般是打包成不同操作系统对应的 binary 可执行文件，用于免安装 python 环境直接运行。

[Pyinstaller基础教程](work/programming/Python/CLI/Pyinstaller基础教程.md)

## Source

对于开源项目而言，一般还会将源代码打包成对应的压缩文件，如 `.tar.gz/.7zip` 等。

## 参考链接

1. [Python开发基础教程](work/programming/Python/Python开发基础教程.md)