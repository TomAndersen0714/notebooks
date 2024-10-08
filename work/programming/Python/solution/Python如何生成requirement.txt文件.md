# Python 如何生成 requirement.txt 文件

`requirements.txt` 文件是一个用于记录 Python 项目所依赖的软件包及其版本信息的文本文件，可以用于项目运行环境的快速部署。

Requirements.txt 文件中版本声明格式：

|Method|Example|
|---|---|
|Strong equality|`Django==3.0.3`|
|Greater or equal|`Django>=3.0.3`|
|Compatible version|`Django~=3.0.3`|

## Pip freeze

导出当前 Python 环境下的所有安装的包名和版本信息：
1.  `pip freeze > requirements.txt`。
2. [Python-pip命令基础教程](work/programming/Python/CLI/Python-pip命令基础教程.md)
注意事项：
- 此工具是直接导出 Python 环境下所有已安装的包，而非 Python 项目当前所引入的包名和版本

## Pipreqs

导出指定 Python 项目（文件夹）下的所有引入的包名和版本信息：
1. `pip install pipreqs`
2. `pipreqs /path/to/project --encoding=utf8 --force`。
注意事项：
-  `pipreqs` 工具，一般都无法直接导出 package 的真实名称，且可能会存在遗漏，通常还需要人工检查 `requirements.txt` 文件，确定 package 名称和实际的一致。

## IDEA

[Use requirements.txt | PyCharm Documentation](https://www.jetbrains.com/help/pycharm/managing-dependencies.html)

1. From the `Tools` menu, select `Sync Python Requirements`.
2. In the opened dialog, specify the name of the requirements file. The recommended name for the requirements file is `requirements.txt`. When a file with this name is added to the root project directory, it is automatically detected by [Python Integrated tools](https://www.jetbrains.com/help/pycharm/settings-tools-python-integrated-tools.html).

![](resources/images/Pasted%20image%2020241008091108.png)

![](resources/images/Pasted%20image%2020241008090946.png)


## Poetry

```bash
# 导出requirements.txt
poetry export --format requirements.txt --without-hashes --output requirements.txt
```
## 参考链接

1. [Use requirements.txt | PyCharm Documentation](https://www.jetbrains.com/help/pycharm/managing-dependencies.html)