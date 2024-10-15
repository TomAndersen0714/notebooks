Scala 术语词汇表

Class 类
- Scala 中的类和其他编程语言中的类没有区别

Case Class 样例类
- 样例类是一种特殊的类，它们经过优化以被用于模式匹配

Object 对象
- Scala 中的 object 一般是指单例对象，此单例对象是其同名类的全局唯一单例

Companion Object 伴生对象
- 在Scala中，伴生对象（Companion Object）是与某个类共享相同名称的对象。伴生对象和它的伴生类可以互相访问彼此的所有成员。
- 伴生对象通常用于定义，与对应伴生类相关的静态成员和方法
	- 通过伴生对象 apply 方法，来构造伴生类的实例

Case Object 样例对象
- 针对单例对象，也可以用于模式匹配的样例对象

Access Modifier 访问修饰符
-  `private[this]`、`private`、`private[package_name]` 等

Contextual Parameters/Implicit Parameters 隐式参数
- [Contextual Parameters, aka Implicit Parameters | Tour of Scala | Scala Documentation](https://docs.scala-lang.org/tour/implicit-parameters.html)

Variance 型变
- Contravariant
- Convariant

Sealed 密封的
- Sealed Class 密封类
	- 所有的子类
- Sealed Trait 密封特质
	- 

Nothing
- Nothing 是

Option/Some/None