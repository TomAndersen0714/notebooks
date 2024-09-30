# Python builtin 对象内置变量和方法

1. **Python 中 `__new__` 方法和 `__init__` 方法的区别：**
	1. `__new__` 方法，是类的 static method 方法，在 Instance 创建之前调用，其作用是创建并返回对应类的对象实例
	2. `__init__` 方法，是类的 instance method 方法，在 Instance 创建之后调用，其作用是在完成对应类的对象实例创建之后，对对象的属性进行初始化，在 `__new__` 方法之后执行
	3. [Python 中的\_\_new\_\_和\_\_init\_\_的区别 - CuriousZero - 博客园](https://www.cnblogs.com/shenxiaolin/p/9307496.html)