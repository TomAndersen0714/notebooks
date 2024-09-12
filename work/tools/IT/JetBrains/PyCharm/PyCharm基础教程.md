# PyCharm 基础教程


## 安装 Python

[Python基础教程](work/programming/Python/Python基础教程.md)

## 安装 PyCharm

建议初学者，可以自己申请，或者淘宝购买学生账号，支持 JetBrains 全家桶。

## 远程 Run/Debug

### 配置远程解释器

创建 SSH Remote Interpreter 时，建议选择远程的虚拟环境，如果选择远程系统环境，可能导致无法正常使用，触发 PyCharm 的 BUG。

配置完成 SSH Remote  Interpreter 后，会自动创建一个 Deployment 配置，指向对应的 SSH Server。即便后续删除了 SSH Remote Interpreter，这个 Deployment 的配置依旧会存在。

建议 Deployment 设置成自动同步。

代码上传完成后，就可以通过 SSH Remote Interpreter 来运行和调试，远程的代码。具体的 working directory、parameter、run model as script 等配置项，都可以通过修改 Run/Debug Configuration 配置来调整。

Run/Debug Configuration 支持设置 Working Directory、Parameter 等等，既可以按照 Module 方式执行 `__init__`，也可以按照 Script 的方式直接执行 Python 脚本。
## 安装插件

### .ignore

用于交互式生成. Ignore 文件

### Translation

一个 JetBrain 上的翻译插件，支持各大主流翻译工具，建议使用有道翻译

### CodeGlance Pro

提供代码缩略图

### Github Copilot

Microsoft Github AI 编程神器。

## 修改配置


添加 Python Interpreters

设置 Python Interpreter Path（可选）
[pycharm设置python path\_pycharm python path-CSDN博客](https://blog.csdn.net/roughman9999/article/details/79458262)

## 常见问题


### 远程解释器无法使用

创建 Remote SSH Interpreter 时，建议优先选择虚拟环境（没有，则需要在开发机服务器上创建，一般每个项目都有独立的 Python 虚拟环境，保证环境隔离），如果选择系统环境，可能导致无法正常使用。


### 远程解释器无法设置 Path Mapping

https://youtrack.jetbrains.com/issue/PY-53568/Targets-API-Path-mappings-field-is-missing-in-the-python-interpreter-settings

此问题出现在 PyCharm 2022.2.1 之后的版本中（不包括 2022.2.1），因此如果想要调整 Interpreter 的 Path Mapping，可以通过以下其他方式实现：
1. 使用 Docker Interpreter
2. 使用旧版本 PyCharm
3. 重建 Remote Interpreter
4. 修改 Deployment SSH Server 的 Path Mapping
5. 在 RUN/Debug Configurations 中修改每次运行时配置的 Path Mapping。

## 参考链接

1. [Python基础教程](work/programming/Python/Python基础教程.md)