# Python 如何生成 requirement.txt 文件

`requirements.txt` 文件是一个用于记录 Python 项目所依赖的软件包及其版本信息的文本文件，可以用于项目运行环境的快速部署。

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