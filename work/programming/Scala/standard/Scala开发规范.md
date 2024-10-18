# Scala 开发规范

依据约束力强弱及故障敏感性，规约依次分为【强制】、【推荐】、【参考】三大类。

## Class 类和 Object 对象

【推荐】在 Companion Class 伴生类中声明 Instance Method 实例方法，而在 Companion Object 伴生对象中声明 Static Method 静态方法/Class Method 类方法

【推荐】Case Class 样例类只用于创建和存放不可修改的 Immutable 数据对象，其相关的方法应该通过继承抽象类 abstract class 或者实现特质 trait 来进行扩展