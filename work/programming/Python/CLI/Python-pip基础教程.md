# Python-pip 基础教程


## 前言

PyPI (Python Package Index) 是 Python 编程语言的软件存储库。开发者可以通过 PyPI 查找和安装由 Python 社区开发和共享的软件，也可以将自己开发的库上传至 PyPI。[PyPI官方仓库](https://pypi.org/)

Pip3 和 pip 的命令大部分都相同，`pip3` 和 `pip` 通常可以直接相互替换，[pip官方教程](https://pip.pypa.io/en/stable/user_guide/#user-guide)

## Pip 安装基础教程

### 安装

方案一：如果是通过apt或者yum等包管理工具安装的话，则需要手动安装pip/pip3工具，但一般情况下安装完对应版本的Python后会自带安装pip工具：
1. APT：`apt install python-pip python-pip3`
2. YUM：`sudo yum install python-pip python3-pip`

方案二：如果是通过源码编译安装Python，则在其Scripts文件夹下就带有pip工具

### 配置

**pip 修改 PyPI 仓库源的三种常用方法**：[参考链接](https://developer.aliyun.com/mirror/pypi?spm=a2c6h.13651102.0.0.3e221b110KOHKi)
1. 方法一：修改 pip 当前用户配置，修改（没有则创建）`$HOME/.pip/pip.conf` 或者 `$HOME/.config/pip/pip.conf`
2. 方法二：修改 pip 全局配置，修改（没有则创建）`/etc/pip.conf`
3. 方法三：通过 `-i` 参数指定本次使用下载源：`sudo pipinstall <package name> -i <mirror_url>`
4. PS：如果是 Python 虚拟环境，则配置文件路径为 `$VIRTUAL_ENV/pip.conf`
5. PS：配置文件优先级，由高到低：virtual env（Python 虚拟环境）->user（当前用户配置）->global（全局配置），其中手动指定优先级最高


**pip 的 module 默认安装路径 `site-packages` 和 `dist-packages` 的区别**：
1. dist-packages是 Debian/Ubuntu 等Linux发行版的默认Python模块安装文件夹，即`APT`等包管理工具安装的pip，默认Python模块安装路径为`/usr/local/lib/pythonX.X/dist-packages`。 [参考链接](https://blog.csdn.net/huiseguiji1/article/details/45111891)
2. Site-packages 是 RHEL/CentOS 等 Linux 发行版的默认 Python 模块安装文件夹，即 `YUM` 等包管理工具安装的 pip，默认 Python 模块安装路径为 `/usr/local/lib/pythonX.X/site-packages`。
3. 通过编译源码安装的 pip 工具 Python 模块默认安装路径和 YUM 相同。[参考链接](https://blog.csdn.net/huiseguiji1/article/details/45111891)


**常用 PyPI 源镜像 URL**：
1. 阿里云： https://mirrors.aliyun.com/pypi/simple/
2. 清华： https://pypi.tuna.tsinghua.edu.cn/simple/
3. 中国科技大学： https://pypi.mirrors.ustc.edu.cn/simple/
4. 华中科技大学： http://pypi.hustunique.com/
5. 山东理工大学： http://pypi.sdutlinux.org/
6. 豆瓣： http://pypi.douban.com/simple/

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

