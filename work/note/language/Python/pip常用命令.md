# pip常用命令

## 前言

PyPI(Python Package Index)是Python编程语言的软件存储库。开发者可以通过PyPI查找和安装由Python社区开发和共享的软件，也可以将自己开发的库上传至PyPI。[PyPI官方仓库](https://pypi.org/)

pip3和pip的命令大部分都相同，`pip3`和`pip`通常可以直接相互替换，[pip官方教程](https://pip.pypa.io/en/stable/user_guide/#user-guide)


## 常用命令

1. **pip升级pip命令**：`pip3 install--upgrade pip`

2. **pip3 install**：
	1. 安装Python模块，命令格式`pip3 install <package_name>`，如`pip3 install mrjob`
	2. 安装whl文件（wheel离线安装包）：`pip3 install <wheel_file_name>`
	3. `-i`，指定本次下载使用的仓库镜像url，使用`-i`选项可以手动指定package的下载源，避免国内下载速度过慢，如`pip3 install mrjob -i https://mirrors.aliyun.com/pypi/simple/ `将下载源修改为阿里云镜像。
	4. `-r`，下载指定文件中标明的Python模块信息（一般为requirements.txt文件），`pip3 install -r <filename>`，如`pip3 install -r requirements.txt`
	5. `--upgrade`，升级指定包，`pip3 install --upgrade <package_name>`，如`pip3 install--upgrade pip`
	6. `--no-cache-dir`，禁用缓存减少内存开销，[官方链接](https://pip.pypa.io/en/stable/topics/caching/)


3. **pip3 freeze**
	1. 提取并生成当前Python环境对应的所有安装包信息（requirements.txt）
	2. `pip3 freeze> <filename>`，如`pip3 freeze > requirements.txt`。导出当前Python环境模块到requirements.txt文件中，通常用于Python环境迁移


4. **pip3 list**
	1. 查看已安装的 Python模块（包），命令格式`pip3 list`。加上`--format=columns`可以实现按列格式化输出，即`pip3 list --format=columns`
	2. pip3 show,查看已安装 Package 的详细信息，命令格式`pip3 show<package_name>`，如`pip3 show mrjob`，此命令查看对应package的安装路径

5. **pip修改PyPI仓库源的三种常用方法**：
	1. 方法一：修改pip当前用户配置，修改（没有则创建）`$HOME/.pip/pip.conf`或者`$HOME/.config/pip/pip.conf`
	2. 方法二：修改pip全局配置，修改（没有则创建）`/etc/pip.conf`
	3. 方法三：通过`-i`参数指定本次使用下载源：`sudo pipinstall <package name> -i <mirror_url>`
	4. PS：如果是Python虚拟环境，则配置文件路径为`$VIRTUAL_ENV/pip.conf`
	5. PS：配置文件优先级，由高到低：virtual env（Python虚拟环境）->user（当前用户配置）->global（全局配置），其中手动指定优先级最高



**常用PyPI镜像地址**：
- 阿里云：https://mirrors.aliyun.com/pypi/simple/
- 清华：https://pypi.tuna.tsinghua.edu.cn/simple/
- 中国科技大学：https://pypi.mirrors.ustc.edu.cn/simple/
- 华中理工大学：http://pypi.hustunique.com/
- 山东理工大学：http://pypi.sdutlinux.org/
- 豆瓣：http://pypi.douban.com/simple/


