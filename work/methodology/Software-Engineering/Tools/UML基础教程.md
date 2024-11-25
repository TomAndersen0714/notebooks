# UML(统一建模语言)基础教程

## UML 概述

UML（Unified Modeling Language，统一建模语言）是由对象管理组织（Object Management Group, OMG）制定的一个通用的、可视化的建模语言标准，可以用来描述、可视化、构造和文档化（specifying, visualizing, constructing, and documenting）软件密集型系统的各种工件，并且已被 ISO 确立为国际标准。

**UML（统一建模语言）最初确实是为面向对象方法设计的，但它并不仅限于面向对象建模**。UML 可以用于描述和表示各种类型的系统和问题领域，不仅局限于面向对象的软件分析和设计。

目前最新版本的 UML 名为 UML2。

### UML 概念模型

UML 概念模型主要包含三个部分：
1. 事物（Things）
2. 关系（Relationship）
3. UML 图（Diagrams）

### UML 适用和不适用的场景

**使用场景**：
1. 项目采用了 Object-Oriented 的方法论
2. 问题复杂，需要更加抽象的方式来概括描述，抓住问题本质，同时提高交流效率
3. 方案需要归档，已便后续复用，减少重复开发成本

**不适用场景**：
1. OO 技术使用很少，项目中现有的方式已经完全适用
2. 需求实现难度低，文字描述就可以阐述清楚

### OO 基本概念

[面向对象的基础概念](work/methodology/Software-Engineering/Analysis-and-Design/Software-Analysis-and-Design/Object-Oriented-Design/面向对象的基础概念.md)

## UML Things（事物）

结构事物（Structural Thing）：UML 模型中的名词

行为事物（Behavioral Thing）：UML 模型中的动词

分组事物（Grouping Thing）：UML 模型中的结构

注释事物（Annotational Thing）：UML 模型的注解部分

## UML Relationship (关系)

UML 中本节的内容虽然介绍的是 UML Relationship，但实际上官方的 UML Relationship 中仅仅介绍了部分 Diagram 中的 Relationship，而非全部。

**UML Relationship 主要介绍了类图（Class Diagram）、对象图（Object Diagram）、包图 （Package Diagram），和组合结构图（Composite Structure Diagram）中各个组件元素之间的关系，以及其对应的图形符号**。

而在其他的 UML 图中，如用例图（Use Case Diagram）、构件图（Component Diagram）、部署图（Deployment Diagram）中，各个组件元素之间的关系，和本节中介绍的 Relationship 并不相同，如果想要获知各个 Diagram 中的 Relationship 图形，需要到对应的章节进行查看。

### 依赖（Dependency）

若 A 使用了 B，则表明 A 依赖于 B。

```plantuml
@startuml
class A
class B

A ..> B : dependency: A use B

@enduml
```

### 关联（Association）

关联（Association）是一种强语义联系的结构关系，表名两个事物之间存在明确的、稳定的语义联系。聚合（Aggregation）和组合（Combination）是两种不同类型的关联关系。

在关联关系的图形符号中，可以使用实线+箭头，也可以不使用箭头，单独使用实线，默认也视为关联关系，不过是双向关联。

```plantuml
@startuml
class A
class B

A "1" <-- "0..1" B

@enduml

```

#### 关联关系-聚合（Aggregation）

即Has-a 的关系
```plantuml
@startuml
Class o-- Student
@enduml
```

#### 关联关系-组合（Combination）

即 contains-a 的关系
```plantuml
@startuml
Human *-- Head
Human *-- Body
@enduml
```

### 泛化（Generalization）

即继承（extend）
```plantuml
@startuml
Parents <|-- Son
Parents <|-- Daughter
@enduml
```

### 实现（Realization）

```plantuml
@startuml
Product <|.. ProductA
Product <|.. ProductB
@enduml
```

## UML Diagrams（图）

建议参考《UML2 面向对象分析与设计（第 2 版）》，2.5 应用 UML2 建模
也可以参考：
https://www.cnblogs.com/wolf-sun/category/531196.html

### 结构图（Structural Diagrams）

#### 组件图（Component Diagrams）

```plantuml
@startuml

[First Component] as one
[Second Component] as two
DataAccess -> one
one ..> HTTP : use

@enduml

```
#### 包图（Package Diagrams）

```plantuml
@startuml

package "Package 1" {
    class ClassA
    class ClassB
}

package "Package 2" {
    class ClassC
    class ClassD
}

ClassA --> ClassB
ClassC --> ClassD

@enduml

```

#### 类图（Class Diagrams）

[UML类图详解统一建模语言（Unified Modeling Language，缩写UML）是非专利的第三代建模和规约语 - 掘金](https://juejin.cn/post/6844903893327937550)

```plantuml
@startuml
class Car {
  + brand: String
  + color: String
  + speed: int
  + accelerate(): void
  + brake(): void
}

class ElectricCar {
  + batteryCapacity: int
  + chargeLevel: int
  + charge(): void
}

class GasolineCar {
  + fuelCapacity: int
  + fuelLevel: int
  + refuel(): void
}

Car <|-- ElectricCar
Car <|-- GasolineCar

@enduml
```

#### 对象图（Object Diagrams）

重点在于描述类之间的关系

```plantuml
@startuml

object ObjectA
object ObjectB
object ObjectC

ObjectA ..> ObjectB
ObjectB --> ObjectC

@enduml

```

#### 组合结构图（Composite Structure Diagrams）

#### 部署图（Deployment Diagrams）

### 行为图（Behavioral Diagrams）

#### 用例图（Use Case Diagrams）

作用：用于描述 Actor 和 System 中的 Use Case 之间的联系，常用于业务分析和需求分析阶段。

组成元素：参与者（Actor）、用例（Use Case）、关系（Relationship）、系统（System）。

关系：关联（Association）、泛化（Generalization）、包含（include）、扩展（Extend）。

```plantuml
@startuml
left to right direction
actor "Food Critic" as fc
rectangle Restaurant {
  usecase "Eat Food" as UC1
  usecase "Pay for Food" as UC2
  usecase "Drink" as UC3
}
fc --> UC1
fc --> UC2
fc --> UC3
@enduml
```

#### 活动图（Activity Diagrams）

https://www.cnblogs.com/wolf-sun/p/3432135.html

#### 状态图、状态机图（State Machine Diagrams）

#### 通信图（Communication Diagrams）

#### 协作图（Collaboration Diagram）

#### 顺序图（Sequence Diagrams）

```plantuml
@startuml
actor User
participant ObjectA
participant ObjectB

User -> ObjectA: Message1
User <-- ObjectA: Message2
ObjectA -> ObjectB: Message3
@enduml

```

#### 时序图（Timing Diagrams）

```plantuml
@startuml
participant A
participant B
A -> B: Message 1
B --> A: Message 2
@enduml
```

#### 交互概览图（Interaction Overview Diagrams）

## UML 绘制工具

**图表只是一种工具，只需要有一个能用的即可，不用非得纠结哪个工具才是最好的，重要的是方法。**

对于比较简单的、临时性的图形，建议使用 PlantUML、Mermaid 这种支持文本化保存的工具来绘制，简单快捷，不需要关心图形的相对位置；而对于比较复杂的、需要长期存储的图形，建议使用在线的（如：ProcessOn）、离线的（如：Visual Paradigm）的应用工具来绘制，支持的图形功能也更强。

详情见：[模型设计工具基础教程](work/methodology/Software-Engineering/Tools/模型设计工具基础教程.md)

## 参考链接

1. [Wiki-Unified Modeling Language](https://en.wikipedia.org/wiki/Unified_Modeling_Language)
2. [SparxSystems - UML2 Tutorial](https://sparxsystems.com/resources/tutorials/uml2/index.html)
3. [Tutorialspoint - UML Tutorial](https://www.tutorialspoint.com/uml/index.htm)
4. 《UML2面向对象分析与设计（第2版）》-清华大学出版社
5. [Bilibili-北京航空航天大学-软件学院-UML2面向对象分析与设计](https://www.bilibili.com/video/BV1fq4y1q7KP)
6. [清华大学出版社-UML2面向对象分析与设计（第2版）-资源下载](http://www.tup.tsinghua.edu.cn/booksCenter/book_07138701.html#)
7. [阿里巴巴-Java开发手册(黄山版)](https://github.com/alibaba/p3c)
8. [UML类图详解统一建模语言（Unified Modeling Language，缩写UML）是非专利的第三代建模和规约语 - 掘金](https://juejin.cn/post/6844903893327937550)