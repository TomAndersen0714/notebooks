# Python基础教程

## 前言

## Python 的应用领域

自动化和脚本化

Web 开发

数据分析和科学计算

人工智能和机器学习

金融和量化分析

网络开发和服务器

## Python 程序的分类

Application

Scripts/Tools

Libraries/Frameworks

## Python 安装基础教程

[Python安装基础教程](work/programming/Python/Operation/Python安装基础教程.md)
[Python-pip基础教程](work/programming/Python/CLI/Python-pip基础教程.md)


## Python Tutorials

https://docs.python.org/3/tutorial/index.html

### Python 语言特性

数据类型

控制流

内置模块

### Python 命名空间和作用域


#### Namespace

[Python Tutorials - Classes - Python Scopes and Namespaces](https://docs.python.org/3/tutorial/classes.html#python-scopes-and-namespaces)

[菜鸟教程-Python3 命名空间和作用域](https://www.runoob.com/python3/python3-namespace-scope.html)


命名空间 (Namespace) 是从名称到对象的映射，大部分的命名空间都是通过 Python 字典来实现的。

命名空间提供了在项目中避免名字冲突的一种方法。各个命名空间是独立的，没有任何关系的，所以一个命名空间中不能有重名，但不同的命名空间是可以重名而没有任何影响。

我们举一个计算机系统中的例子，一个文件夹 (目录) 中可以包含多个文件夹，每个文件夹中不能有相同的文件名，但不同文件夹中的文件可以重名。


Python 一般有三种命名空间，后者会包含前者：
- **内置名称（built-in names）**， Python 语言内置的名称，比如函数名 abs、char 和异常名称 BaseException、Exception 等等。可以使用 `dir(builtins)` 打印对应的列表。
- **全局名称（global names）**，模块中定义的名称，记录了模块的变量，包括函数、类、其它导入的模块、模块级的变量和常量。可以使用内置函数 [globals()](https://docs.python.org/3/library/functions.html?highlight=globals#globals) 打印对应的字典。
- **局部名称（local names）**，函数中定义的名称，记录了函数的变量，包括函数的参数和局部定义的变量。（类中定义的也是）。可以使用内置函数 [locals()](https://docs.python.org/3/library/functions.html?highlight=globals#locals) 打印对应的字典。

#### Scopes

作用域（Scope）就是一个 Python 程序可以访问对应命名空间（Namespace）的代码段范围，也可以理解为命名空间的存活范围。

在一个 python 程序中，直接访问一个变量，会从内到外依次访问所有的作用域（Scope）对应的命名空间（Namespace）直到找到，否则会报未定义的错误。

程序的变量并不是在哪个位置都可以访问的，是否可以访问取决于对应代码位置，是否属于该变量的作用域（Scope）（变量声明的那一行代码所属的作用域），只有当 Python 代码在其对应的作用域（Scope）内部时，才可以从对应作用域的命名空间（Namespace）中，获取到变量名映射的对象。


Python 的作用域（Scope）一共有 4 种，分别是：
- L（Local）：最内层，包含局部变量，比如一个函数/方法内部。
- E（Enclosing）：包含了非局部 (non-local) ，即非全局 (non-global) 的变量。比如两个嵌套函数，一个函数（或类） A 里面又包含了一个函数 B ，那么对于 B 中的名称来说 A 中的作用域就为 nonlocal。
- G（Global）：当前脚本的最外层，比如当前模块的全局变量。
- B（Built-in）： 包含了内建的变量/关键字等，最后被搜索。

### Python Import 原理



## Q&A

1. **Python中`__new__`方法和`__init__`方法的区别：**
	1. `__new__` 方法，是类的 static method 方法，在 Instance 创建之前调用，其作用是创建并返回对应类的对象实例
	2. `__init__` 方法，是类的 instance method 方法，在 Instance 创建之后调用，其作用是在完成对应类的对象实例创建之后，对对象的属性进行初始化，在 `__new__` 方法之后执行
	3. [参考链接](https://www.cnblogs.com/shenxiaolin/p/9307496.html)
2. **Python 中模块 Module 和包 Package 的区别**：
	1. Module 是一个 Python 文件，而 Package 则是一个包含 `__init__.py` 文件的目录。
	2. Module 用于组织代码和重用代码，Package 用于组织模块和其他文件。
	3. Module 和 Package 都可以被其他 Python 文件引用，两者可以一起使用。



## 参考链接
1. [Python Documentation](https://docs.python.org/3/index.html)