# JVM 类加载


## JVM 类加载器

Java 中类加载器分为两类，一种是启动类加载器（Bootstrap Classloader），另一种就是其他类加载器，这些类加载器都继承于 java. Lang. ClassLoader 抽象类。

JVM 运行时，一般默认创建有三个类加载器：启动类加载器（Bootstrap ClassLoader）、扩展类加载器（Extension ClassLoader）、应用程序类加载器（Application ClassLoader）。

启动类加载器（Bootstrap ClassLoader）负责加载 `<JAVA_HOME>\lib` 目录中的类，扩展类加载器（Extension ClassLoader）负责加载 `<JAVA_HOME>\lib\ext` 目录中的类。

应用程序类加载器（Application ClassLoader）负责加载用户类路径（`ClassPath`）上指定的类，其中应用程序加载器是程序默认的类加载器。

JVM 中应用类加载器（Application ClassLoader）也称为系统类加载器（System ClassLoader）。


## JVM 类加载的过程


### 加载

Java 通过类加载器（Class Loader），定位 Java 字节码文件

### 验证

验证 Java 字节码的安全性，避免非法代码注入。

### 准备

类加载机制中的准备阶段主要是为类变量分配内存空间，并赋初值，基本数据类型赋各自对应的初值，而引用类型变量则初值为 null。

### 解析


### 初始化

当初始化某个类时，便会执行其中的 static 代码块和 static 赋值语句（被 final 修饰、已经在编译期把结果放入到 class 文件常量池的静态字段除外），且按源码顺序执行。

JVM 初始化 Class 对象的时机：
1. 当虚拟机启动时，用户需要指定一个需要执行的主类（即包含 main 方法的类），虚拟机会先初始化这个类。
2. 最常见的就是使用 new 关键字实例化对象的时候、读取或者设置一个类的静态字段（被 final 修饰、已经在编译期把结果放入到 class 文件常量池的静态字段除外）的时候，以及调用一个类的静态方法的时候。
3. 当初始化一个子类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
4. 使用 java. Lang. Reflect 包的方法对类进行反射调用的时候，如果类没有进行过初始化，则需要先触发其初始化。
5. 使用 JDK1.7 及之后版本的动态语言支持时的某些特定情况。
