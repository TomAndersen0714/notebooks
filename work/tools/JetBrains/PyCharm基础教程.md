# PyCharm 基础教程


## 安装 Python

[Python基础教程](work/programming/Python/Python基础教程.md)

## 安装 PyCharm

建议初学者，可以自己申请，或者淘宝购买学生账号，支持 JetBrains 全家桶。


## 配置远程解释器

创建 SSH Remote Interpreter 时，建议选择远程的虚拟环境，如果选择远程系统环境，可能导致无法正常使用，触发 PyCharm 的 BUG。

配置完成 SSH Remote  Interpreter 后，会自动创建一个 Deployment 配置，指向对应的 SSH Server。即便后续删除了 SSH Remote Interpreter，这个 Deployment 的配置依旧会存在。

建议 Deployment 设置成自动同步。

代码上传完成后，就可以通过 SSH Remote Interpreter 来运行和调试，远程的代码。具体的 working directory、parameter、run model as script 等配置项，都可以通过修改 Run/Debug Configuration 配置来调整。

## 安装插件

### . Ignore

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
https://blog.csdn.net/roughman9999/article/details/79458262


## 常见问题


### 远程解释器无法使用

创建 Remote SSH Interpreter 时，建议选择虚拟环境，如果选择系统环境，可能导致无法正常使用