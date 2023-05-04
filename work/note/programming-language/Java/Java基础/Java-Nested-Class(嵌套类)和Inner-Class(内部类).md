# Java Nested Class(嵌套类)和Inner Class(内部类)

## 废话在前

最近在阅读《Java核心技术-卷I开发基础(原书12版)》第6.3章时, 感觉书中对于Java的内部类(inner class)以及嵌套类(nested class)的定义不够清晰和严谨, 其中静态内部类(static inner class)的定义, 又与内部类本身部分特性的描述产生冲突, 感觉难以理解并且无法自洽, 故而在网上冲浪过程中搜寻与Java内部类更为严谨可靠的定义和分类. 

目前找到的, 可信度较高的两个文档, 分别是Oracle官网的"The Java Tutorials", 和"The Java Language Specification, Java SE 11 Edition". 本文中的主要内容, 也是摘录这两份文档中的相关术语的定义, 并进行了简单的梳理和对比, 为了后续回忆起相关概念时能有个可靠的依据.



## What Are Nested Classes and Inner Classes?



Top Level Class:

- A top level class is a class that is not a nested class. (The Java Language Specification, Java SE 11 Edition, §8)



Nested Class:

- A `nested class` is any class whose declaration occurs within the body of another class or interface. (The Java Language Specification, Java SE 11 Edition, §8)
- This chapter discusses the common semantics of all classes - top level (§7.6) and `nested` (including member classes (§8.5, §9.5), local classes (§14.3) and anonymous classes (§15.9.5)). (The Java Language Specification, Java SE 11 Edition, §8)
- `Nested classes` are divided into two categories: non-static and static. Non-static nested classes are called inner classes. Nested classes that are declared static are called static nested classes. (The Java Tutorials - Nested Classes)
- A `nested class` is a member of its `enclosing class`. Non-static nested classes (inner classes) have access to other members of the enclosing class, even if they are declared private. Static nested classes do not have access to other members of the enclosing class. As a member of the OuterClass, a nested class can be declared *private*, *public*, *protected*, or *package private*. (Recall that outer classes can only be declared *public* or *package private*.) (The Java Tutorials - Nested Classes)



Static Nested Class:

- Nested classes are divided into two categories: non-static and static. Non-static nested classes are called inner classes. Nested classes that are declared static are called `static nested classes`. (The Java Tutorials - Nested Classes)



Inner Class:

- An `inner class` is a nested class that is not explicitly or implicitly declared static. (The Java Language Specification, Java SE 11 Edition, §8.1.3)
- An `inner class` may be a non-static member class (§8.5), a local class (§14.3), or an anonymous class (§15.9.5). A member class of an interface is implicitly static (§9.5) so is never considered to be an inner class. (The Java Language Specification, Java SE 11 Edition, §8.1.3)
- Nested classes are divided into two categories: non-static and static. Non-static nested classes are called `inner classes`. Nested classes that are declared static are called static nested classes. (The Java Tutorials - Nested Classes)



Member Class:

- `Member class` declarations (§8.5) describe nested classes that are members of the surrounding class. Member classes may be static, in which case they have no access to the instance variables of the surrounding class; or they may be inner classes (§8.1.3). (The Java Language Specification, Java SE 11 Edition, §8)
- `Member interface` declarations (§8.5) describe nested interfaces that are members of the surrounding class. (The Java Language Specification, Java SE 11 Edition, §8)
- A `member class` is a class whose declaration is directly enclosed in the body of another class or interface declaration (§8.1.6, §9.1.4). (The Java Language Specification, Java SE 11 Edition, §8.5)
- A `member interface` is an interface whose declaration is directly enclosed in the body of another class or interface declaration (§8.1.6, §9.1.4). (The Java Language Specification, Java SE 11 Edition, §8.5)



Local Class:

- A local class is a nested class (§8 (Classes)) that is not a member of any class and that has a name (§6.2, §6.7). (The Java Language Specification, Java SE 11 Edition, §14.3)
- All local classes are inner classes (§8.1.3). (The Java Language Specification, Java SE 11 Edition, §14.3)
- Local classes are classes that are defined in a *block*, which is a group of zero or more statements between balanced braces. You typically find local classes defined in the body of a method. (The Java Tutorials - Local Classes)



Anonymous Class:

- An anonymous class declaration is automatically derived from a class instance creation expression by the Java compiler. (The Java Language Specification, Java SE 11 Edition, §15.9.5)
- An anonymous class is never abstract (§8.1.1.1). An anonymous class is never final (§8.1.1.2). An anonymous class is always an inner class (§8.1.3); it is never static (§8.1.1, §8.5.1). (The Java Language Specification, Java SE 11 Edition, §15.9.5)
- Anonymous classes enable you to make your code more concise. They enable you to declare and instantiate a class at the same time. They are like local classes except that they do not have a name. Use them if you need to use a local class only once. (The Java Tutorials - Anonymous Classes)



Lambda Expressions:

- A `lambda expression` is like a method: it provides a list of formal parameters and a body - an expression or block - expressed in terms of those parameters. (The Java Language Specification, Java SE 11 Edition, §15.27)
- Evaluation of a `lambda expression` produces an instance of a `functional interface`(§9.8). Lambda expression evaluation does not cause the execution of the expression's body; instead, this may occur at a later time when an appropriate method of the functional interface is invoked. (The Java Language Specification, Java SE 11 Edition, §15.27)
- A `functional interface` is an interface that has just one abstract method (aside from the methods of Object), and thus represents a single function contract. (The Java Language Specification, Java SE 11 Edition, §9.8)
- One issue with `anonymous classes` is that if the implementation of your anonymous class is very simple, such as an interface that contains only one method, then the syntax of anonymous classes may seem unwieldy and unclear. In these cases, you're usually trying to pass functionality as an argument to another method, such as what action should be taken when someone clicks a button. Lambda expressions enable you to do this, to treat functionality as method argument, or code as data. (The Java Tutorials - Lambda Expressions)

PS: Lambda表达式(Lambda Expression), 个人理解, 可以看做是为实现指定的函数式接口(functional interface), 声明并实例化对应的匿名内部类(Anonymous Class)时的一种语法糖表达式. 



## Why Use Nested Classes?

> Compelling reasons for using nested classes include the following:
>
> - **It is a way of logically grouping classes that are only used in one place**: If a class is useful to only one other class, then it is logical to embed it in that class and keep the two together. Nesting such "helper classes" makes their package more streamlined.
> - **It increases encapsulation**: Consider two top-level classes, A and B, where B needs access to members of A that would otherwise be declared `private`. By hiding class B within class A, A's members can be declared private and B can access them. In addition, B itself can be hidden from the outside world.
> - **It can lead to more readable and maintainable code**: Nesting small classes within top-level classes places the code closer to where it is used.



## When to Use Nested Classes, Local Classes, Anonymous Classes, and Lambda Expressions?

> As mentioned in the section [Nested Classes](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html), nested classes enable you to logically group classes that are only used in one place, increase the use of encapsulation, and create more readable and maintainable code. Local classes, anonymous classes, and lambda expressions also impart these advantages; however, they are intended to be used for more specific situations:
>
> - [Local class](https://docs.oracle.com/javase/tutorial/java/javaOO/localclasses.html): Use it if you need to create more than one instance of a class, access its constructor, or introduce a new, named type (because, for example, you need to invoke additional methods later).
> - [Anonymous class](https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html): Use it if you need to declare fields or additional methods.
> - [Lambda expression](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html):
>   - Use it if you are encapsulating a single unit of behavior that you want to pass to other code. For example, you would use a lambda expression if you want a certain action performed on each element of a collection, when a process is completed, or when a process encounters an error.
>   - Use it if you need a simple instance of a functional interface and none of the preceding criteria apply (for example, you do not need a constructor, a named type, fields, or additional methods).
> - [Nested class](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html): Use it if your requirements are similar to those of a local class, you want to make the type more widely available, and you don't require access to local variables or method parameters.
>   - Use a non-static nested class (or inner class) if you require access to an enclosing instance's non-public fields and methods. Use a static nested class if you don't require this access.



## Conclusion

**"The Java Tutorials"中对于Java Class的分类整理后如下:** 

1. Top-level class
2. Nested class
   - Non-static nested class(Inner class)
     - local class
     - anonymous class
   - Static nested class

显然, 在"The Java Tutorials"中, Non-static nested class(Inner class)的定义部分, 缺少了关于既非local class, 也非anonymous class的这一类class的分类和定义. 

其中各个术语的定义对应的韦恩图如下: 


![](resources/images/The_Java_tutorials-Java_classes.jpg)


**"The Java Language Specification, Java SE 11 Edition"中对于Java Class的分类整理后如下:** 

1. Top level class
2. Nested class
   - inner class
   - local class
   - anonymous class
   - member class
     - static member class
     - non-static member class

相较于"The Java Tutorials", "The Java Language Specification, Java SE 11 Edition"中的class分类体系则显然更加全面一些.

其中各个术语的定义对应的韦恩图如下: 

![](resources/images/JLS-Java_classes.jpg)





**两书中关于`static nested class`和`static member class`之间的区别和联系:**

1. 在`"The Java Language Specification, Java SE 11 Edition"`全书中并未出现`static nested class`关键词, 但是却明确说明了`member class`, 可以为static或non-static类型, 并且将nested class分为了三类(member class, local class, anonymous class), 其中local class, anonymous class都属于inner class, 即为non-static类型, 同时文中也指出了inner class的定义也和non-static member class存在交集. 
2. 在`"The Java Tutorials"`的`Nested Classes`章节中, 作者明确描述了nested class可以分为两类, `non-static nested class`(即`inner class`), 和`static nested class`. 其中关于`inner class`的定义和描述, 与`"The Java Language Specification, Java SE 11 Edition"`中的`Inner Classes and Enclosing Instances`章节中的完全相同. 
3. 由前面两点可知, `"The Java Language Specification, Java SE 11 Edition"`中定义的`static member class`和`"The Java Tutorials"`中定义的`static nested class`, 两者的定义不谋而合



**综上所述, "The Java Tutorials"和"The Java Language Specification, Java SE 11 Edition"虽然都是Oracle官方提供的文档, 但两者对于Java Class的分类却仍然存在部分分歧, 且各自自成体系, 具体应该采用哪一种, 目前似乎也并没有一个官方的, 或者大家都支持的定义和描述.** 



## References

1. [The Java Tutorials - Nested Classes](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html)
2. [The Java Tutorials - Why Use Nested Classes?](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html)
3. [The Java Tutorials - When to Use Nested Classes, Local Classes, Anonymous Classes, and Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/whentouse.html)
4. [The Java Language Specification, Java SE 11 Edition](https://docs.oracle.com/javase/specs/jls/se11/html/index.html)