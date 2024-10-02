# Python 如何生成 requirement.txt 文件

`requirements.txt` 文件是一个用于记录 Python 项目所依赖的软件包及其版本信息的文本文件，可以用于项目运行环境的快速部署。

## Pip freeze

导出当前 Python 环境下的所有 dependency 信息：
1.  `pip freeze > requirements.txt`。
2. [Python-pip命令基础教程](work/programming/Python/CLI/Python-pip命令基础教程.md)

## Pipreqs

导出指定项目（文件夹）下的所有 dependency 信息：
1. `pip install pipreqs`
2. `pipreqs /path/to/project --encoding=utf8--force`。
3. `pipreqs` 工具，一般都无法直接导出 package 的真实名称，通常还需要人工检查 `requirements.txt` 文件，确定 package 名称和实际的一致。