# Scala case class 样例类

## Case Class 内置方法

在 Scala Class 中，一旦使用了 Case 关键字进行修饰，Scala 便会为该类注入 `apply/unapply/copy/equals/hashCode/toString` 方法。

Another Scala feature that provides support for functional programming is the _case class_. A case class has all of the functionality of a regular class, and more. When the compiler sees the `case` keyword in front of a `class`, it generates code for you, with the following benefits:

- Case class constructor parameters are public `val` fields by default, so accessor methods are generated for each parameter.
- An `apply` method is created in the companion object of the class, so you don’t need to use the `new` keyword to create a new instance of the class.
- An `unapply` method is generated, which lets you use case classes in more ways in `match` expressions.
- A `copy` method is generated in the class. You may not use this feature in Scala/OOP code, but it’s used all the time in Scala/FP.
- `equals` and `hashCode` methods are generated, which let you compare objects and easily use them as keys in maps.
- A default `toString` method is generated, which is helpful for debugging.