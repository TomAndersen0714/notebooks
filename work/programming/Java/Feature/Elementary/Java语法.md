# 基础语法

## 类(Class)

1. public class：同一个Java源文件中最多只能有一个顶级（Top-level）的public class，且必须与源文件同名。PS：推测是Java设计时为了保证每个Java源文件只有一个对其他包展示的类，用于提供接口访问
2. import static：import static是JDK1.5中的新特性，格式为`import static 包名.类名.*`，用于导入指定类中的静态成员，可以简化类的静态成员调用方法，不用附加类名作为前缀。如果引入同名静态变量，则会出现编译错误。