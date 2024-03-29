# Python-pip 命令基础教程


## Pip 常用命令

1. **pip 升级 pip 命令**：`pip3 install--upgrade pip`
2. **pip3 install**：
	1. 安装 Python 模块，命令格式 `pip3 install <package_name>`，如 `pip3 install mrjob`
	2. 安装 whl 文件（wheel 离线安装包）：`pip3 install <wheel_file_name>`
	3. **-i**，指定本次下载使用的仓库镜像 url，使用 `-i` 选项可以手动指定 package 的下载源，避免国内下载速度过慢，如 `pip3 install mrjob -i https://mirrors.aliyun.com/pypi/simple/ ` 将下载源修改为阿里云镜像。
	4. **-r**，下载指定文件中标明的 Python 模块信息（一般为 requirements. Txt 文件），`pip3 install -r <filename>`，如 `pip3 install -r requirements.txt`
	5. **--upgrade**，升级指定包，`pip3 install --upgrade <package_name>`，如 `pip3 install--upgrade pip`
	6. **--no-cache-dir**，禁用缓存减少内存开销，[官方链接](https://pip.pypa.io/en/stable/topics/caching/)
3. **pip3 uninstall**：卸载对应包
4. **pip3 freeze**
	1. 提取并生成当前 Python 环境对应的所有安装包信息（requirements. Txt）
	2. `pip3 freeze> <filename>`，如 `pip3 freeze > requirements.txt`。导出当前 Python 环境模块到 requirements. Txt 文件中，通常用于 Python 环境迁移
5. **pip3 list**
	1. 查看已安装的 Python 模块（包），命令格式 `pip3 list`。加上 `--format=columns` 可以实现按列格式化输出，即 `pip3 list --format=columns`
	2. Pip3 show, 查看已安装 Package 的详细信息，命令格式 `pip3 show<package_name>`，如 `pip3 show mrjob`，此命令查看对应 package 的安装路径

## 参考链接
1. [pip config - pip documentation v24.0](https://pip.pypa.io/en/stable/cli/pip_config/)