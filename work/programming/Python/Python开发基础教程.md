# Python 开发基础教程

## 开发环境

[Python开发环境搭建基础教程](work/programming/Python/Python开发环境搭建基础教程.md)

## 开发流程

分析、设计、开发、测试、部署、发布、运维

## 开发（Develop）

### 开发环境

[Python开发环境搭建基础教程](work/programming/Python/Python开发环境搭建基础教程.md)

### 项目搭建

[项目结构 - Python 项目工程化开发指南](https://pyloong.github.io/pythonic-project-guidelines/guidelines/project_management/project_structure/)

## 测试 (Test)

## 部署（Deploy）

不支持 C/C++ Library 自动部署，需要手动安装 C/C++ Library。
https://blog.csdn.net/smilehappiness/article/details/117337943

## 发布（Distribution）

### Package

对于 Library 类别的 Python 项目，一般需要通过打包成 python package 分发到其他的 python 环境和项目中使用。此类项目中一般通过 `setup.py` 文件来声明当前 python 项目对应的 python package 信息。

[Overview of Python Packaging - Python Packaging User Guide](https://packaging.python.org/en/latest/overview/)

### Binary

对于 Application 类的 Python 项目，一般是打包成不同操作系统对应的 binary 可执行文件，用于免安装 python 环境直接运行。

[Pyinstaller基础教程](work/programming/Python/CLI/Pyinstaller基础教程.md)

### Source

对于开源项目而言，一般还会将源代码打包成对应的压缩文件，如 `.tar.gz/.7zip` 等。

## 最佳实践

初始化开发环境

```
# 创建Python虚拟环境
python -m venv myenv

# 加载虚拟环境
source myenv/bin/activate

# 配置PIP源
pip config set --local global.index-url https://pypi.tuna.tsinghua.edu.cn/simple
pip config set --local global.trusted-host pypi.tuna.tsinghua.edu.cn

# 安装项目依赖
pip install -r requirements.txt
```

## 参考链接

1. [Python 项目工程化最佳实践指南Python 工程化这件事都没有统一的规范和项目管理的方案，也许是因为 Python - 掘金](https://juejin.cn/post/7170876308505755679)
2. [项目结构 - Python 项目工程化开发指南](https://pyloong.github.io/pythonic-project-guidelines/guidelines/project_management/project_structure/#3)