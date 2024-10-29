# Mermaid 基础教程

## 简介

Mermaid 是一个开源的 JavaScript 库，用于绘制流程图、时序图、类图和其他各种图表。它使用简洁的文本语法来描述图表结构，并将其转换为可视化图形。Mermaid 可以直接嵌入到 Markdown 文档、网页或其他文档中，以创建可交互和易于阅读的图表。

但 Mermaid 能够支持的 UML 图形有限，只能支持一些常用的 UML 图形，如果想要自由使用所有的 UML 图形，还是需要使用 PlantUML 这类专业绘图工具。

这类通过代码自动生成图形的工具，其特点都是无法自行控制流程细节，适合简单的标准图形，不适合复杂图形。

Documentation
https://github.com/mermaid-js/mermaid
https://mermaid.js.org/intro/

Online Demo
https://mermaid.live/

## 常用图例

### 流程图

从左到右
```mermaid
flowchart LR
A[方形] -->B(圆角)
    B --> C{条件a}
    C -->|a=1| D[结果1]
    C -->|a=2| E[结果2]
```

从上至下

```mermaid
flowchart TD
A[方形] --> B(圆角)
    B --> C{条件a}
    C --> |a=1| D[结果1]
    C --> |a=2| E[结果2]
```

subgraph

```mermaid
flowchart LR
    subgraph subgraph1
        direction TB
        top1[top] --> bottom1[bottom]
    end
    subgraph subgraph2
        direction TB
        top2[top] --> bottom2[bottom]
    end
    %% ^ These subgraphs are identical, except for the links to them:

    %% Link *to* subgraph1: subgraph1 direction is maintained
    outside --> subgraph1
    %% Link *within* subgraph2:
    %% subgraph2 inherits the direction of the top-level graph (LR)
    outside ---> top2

```

### 序列图

```mermaid
sequenceDiagram  
actor User  
User ->> MetricExample$ : main  
activate MetricExample$  
MetricExample$ ->> ExampleUtils$ : withSpark  
activate ExampleUtils$  
ExampleUtils$ ->> SparkSession$ : builder  
activate SparkSession$  
SparkSession$ -->> ExampleUtils$ : #32;   
deactivate SparkSession$  
ExampleUtils$ ->> Builder : master  
activate Builder  
Builder -->> ExampleUtils$ : #32;   
deactivate Builder  
ExampleUtils$ ->> Builder : appName  
activate Builder  
Builder -->> ExampleUtils$ : #32;   
deactivate Builder  
ExampleUtils$ ->> Builder : config  
activate Builder  
Builder -->> ExampleUtils$ : #32;   
deactivate Builder  
ExampleUtils$ ->> Builder : getOrCreate  
activate Builder  
Builder -->> ExampleUtils$ : #32;   
deactivate Builder  
ExampleUtils$ ->> SparkContext : setCheckpointDir  
activate SparkContext  
SparkContext -->> ExampleUtils$ : #32;   
deactivate SparkContext  
ExampleUtils$ ->> SparkSession : stop  
activate SparkSession  
SparkSession -->> ExampleUtils$ : #32;   
deactivate SparkSession  
ExampleUtils$ ->> MetricExample$ : session -&gt;  
activate MetricExample$  
MetricExample$ ->> ExampleUtils$ : itemsAsDataframe  
activate ExampleUtils$  
ExampleUtils$ ->> SparkContext : parallelize  
activate SparkContext  
SparkContext -->> ExampleUtils$ : #32;   
deactivate SparkContext  
ExampleUtils$ ->> SparkSession : createDataFrame  
activate SparkSession  
SparkSession -->> ExampleUtils$ : #32;   
deactivate SparkSession  
ExampleUtils$ -->> MetricExample$ : #32;   
deactivate ExampleUtils$  
MetricExample$ ->> Analysis : addAnalyzer  
activate Analysis  
Analysis ->> Analysis$ : apply  
activate Analysis$  
Analysis$ -->> Analysis : #32;   
deactivate Analysis$  
Analysis -->> MetricExample$ : #32;   
deactivate Analysis  
MetricExample$ ->> Analysis : addAnalyzer  
activate Analysis  
Analysis ->> Analysis$ : apply  
activate Analysis$  
Analysis$ -->> Analysis : #32;   
deactivate Analysis$  
Analysis -->> MetricExample$ : #32;   
deactivate Analysis  
MetricExample$ ->> Analysis : addAnalyzer  
activate Analysis  
Analysis ->> Analysis$ : apply  
activate Analysis$  
Analysis$ -->> Analysis : #32;   
deactivate Analysis$  
Analysis -->> MetricExample$ : #32;   
deactivate Analysis  
MetricExample$ ->> Analysis : addAnalyzer  
activate Analysis  
Analysis ->> Analysis$ : apply  
activate Analysis$  
Analysis$ -->> Analysis : #32;   
deactivate Analysis$  
Analysis -->> MetricExample$ : #32;   
deactivate Analysis  
MetricExample$ ->> Analysis : addAnalyzer  
activate Analysis  
Analysis ->> Analysis$ : apply  
activate Analysis$  
Analysis$ -->> Analysis : #32;   
deactivate Analysis$  
Analysis -->> MetricExample$ : #32;   
deactivate Analysis  
MetricExample$ ->> Analysis : addAnalyzer  
activate Analysis  
Analysis ->> Analysis$ : apply  
activate Analysis$  
Analysis$ -->> Analysis : #32;   
deactivate Analysis$  
Analysis -->> MetricExample$ : #32;   
deactivate Analysis  
MetricExample$ ->> Analysis : addAnalyzer  
activate Analysis  
Analysis ->> Analysis$ : apply  
activate Analysis$  
Analysis$ -->> Analysis : #32;   
deactivate Analysis$  
Analysis -->> MetricExample$ : #32;   
deactivate Analysis  
MetricExample$ ->> Analysis : addAnalyzer  
activate Analysis  
Analysis ->> Analysis$ : apply  
activate Analysis$  
Analysis$ -->> Analysis : #32;   
deactivate Analysis$  
Analysis -->> MetricExample$ : #32;   
deactivate Analysis  
MetricExample$ ->> Analysis : addAnalyzer  
activate Analysis  
Analysis ->> Analysis$ : apply  
activate Analysis$  
Analysis$ -->> Analysis : #32;   
deactivate Analysis$  
Analysis -->> MetricExample$ : #32;   
deactivate Analysis  
MetricExample$ ->> Analysis : addAnalyzer  
activate Analysis  
Analysis ->> Analysis$ : apply  
activate Analysis$  
Analysis$ -->> Analysis : #32;   
deactivate Analysis$  
Analysis -->> MetricExample$ : #32;   
deactivate Analysis  
MetricExample$ ->> Analysis : addAnalyzer  
activate Analysis  
Analysis ->> Analysis$ : apply  
activate Analysis$  
Analysis$ -->> Analysis : #32;   
deactivate Analysis$  
Analysis -->> MetricExample$ : #32;   
deactivate Analysis  
MetricExample$ ->> AnalysisRunner$ : run  
activate AnalysisRunner$  
AnalysisRunner$ ->> AnalysisRunner$ : doAnalysisRun  
activate AnalysisRunner$  
AnalysisRunner$ -->> AnalysisRunner$ : #32;   
deactivate AnalysisRunner$  
AnalysisRunner$ -->> MetricExample$ : #32;   
deactivate AnalysisRunner$  
MetricExample$ ->> IterableLike : foreach  
activate IterableLike  
IterableLike -->> MetricExample$ : #32;   
deactivate IterableLike  
MetricExample$ -->> ExampleUtils$ : #32;   
deactivate MetricExample$  
ExampleUtils$ -->> MetricExample$ : #32;   
deactivate ExampleUtils$  
deactivate MetricExample$
```
### 用户旅程图

User Journey Diagram
```mermaid
journey
    title My working day
    section Go to work
      Make tea: 5: Me
      Go upstairs: 3: Me
      Do work: 1: Me, Cat
    section Go home
      Go downstairs: 5: Me
      Sit down: 5: Me
```

### 饼图
Pie Chart

```mermaid
pie
title Pets adopted by volunteers
    "Dogs" : 386
    "Cats" : 85
    "Rats" : 15
```

### 甘特图

```mermaid
gantt
    title A Gantt Diagram
    dateFormat YYYY-MM-DD
    section Section
        A task          :a1, 2014-01-01, 30d
        Another task    :after a1, 20d
    section Another
        Task in Another :2014-01-12, 12d
        another task    :24d
```