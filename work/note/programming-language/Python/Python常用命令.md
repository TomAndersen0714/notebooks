# Python常用命令

1. **Python3命令行选项“-m”参数的作用**：
	1) 如果bar是一个模块（.py/.pyc/.pyd等），如“python3 -m foor.bar”（注意没有加上文件名后缀），则会直接执行对应的文件。同时会将Python命令行执行时当前路径添加到sys.path中
	2) 如果bar是一个包（带有__init__文件的文件夹），则会直接执行包中的`__main__`模块（即__main__.py文件），同样是会将python命令行执行时的路径添加到sys.path中，如果缺少`__main__.py`文件则会报错`No module named test.__main__`
	3) 如果不使用“-m”参数，则是按照文件路径的层次进行调用，使用斜杠来声明调用层次，并且同时将脚本文件所在的绝对路径添加到sys.path变量中，而不是python命令的当前路径，如“python3 foor/bar.py”（注意需要加上文件后缀）
	4) 不论是哪种执行方式变量`__name__`的值都为`__main__`

2. Python查看查找路径（sys.path）：
	方法一：直接命令行执行Python代码，“python3 -c "import sys; print(sys.path)"”
	方法二：使用命令“python -m site”，同理python3则使用“python3 -m site”

3. Python查看当前工作目录（base path）：
	“python3 -c "import os; print(os.getcwd())"”
	PS：工作目录，即执行Python命令行时，所在的文件系统目录

4. Python的模块安装路径site-packages 和dist-packages的区别：
	dist-packages是 Debian/Ubuntu 等Linux发行版的默认Python模块安装文件夹。
	site-packages 是 RHEL/CentOS 等Linux发行版的默认Python模块安装文件夹。
	PS：Debian/Ubuntu 使用“pip、pip3”或者“easy_install”安装的模块 package 默认存放在“/usr/local/lib/python2.7/dist-packages”路径下
	PS：[参考链接](https://blog.csdn.net/huiseguiji1/article/details/45111891)

5. Python虚拟环境管理：
	1) 创建
		使用Python3自带工具venv：创建Python虚拟环境`python3 -m venv <env dir>`，加载（启动）虚拟环境`source <env_dir>/bin/activate`
		PS：创建Python虚拟环境的目的是为了实现Python运行环境的隔离，支持分离部署不同的模块。[官方教程](https://packaging.python.org/tutorials/installing-packages/#creating-virtual-environments)
	2) 删除
		直接删除创建的虚拟环境路径即可
