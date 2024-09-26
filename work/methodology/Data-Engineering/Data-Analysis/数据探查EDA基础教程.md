# 数据探查 (EDA) 基础教程

数据探查，又名为探索性数据分析（Exploratory Data Analysis，EDA）。实际上就是在进行数据开发以及深度分析之前，对数据集进行初步探索和理解的必备过程，是一种简单的数据分析活动，用于发现数据中代表的信息，理解对应的业务逻辑。

同时 EDA 十分有利于提前发现数据中可能存在的问题，避免后续数据开发过程中返工，或者数据分析时得出错误的结论。不论是内部数据、还是外部数据，对于首次到达的数据集而言，数据探查 EDA 的流程都是必备的，尤其是外部数据，更容易出现不符合预期的情况。

EDA 实际上就是针对数据集的首次数据质量校验（或者说首次数据测试），与数据测试、数据质量校验 DQC 相同的是，都是用于判断数据是否符合预期，不同的是数据测试是每次数据集有变动时候校验，而数据质量校验 DQC 是每当有新数据集产生时进行校验，即一种日常的校验活动。

[What is Exploratory Data Analysis? | IBM](https://www.ibm.com/topics/exploratory-data-analysis)
[Exploratory data analysis - Wikipedia](https://en.wikipedia.org/wiki/Exploratory_data_analysis)

## 数据探查事项

EDA 除了需要了解当前数据集中每行数据代表的业务流程，还需要确定当前状态下数据集的数据质量。

业务流程：
1. 表主键
2. 表数据量
3. 表数据量变化：增量 or 全量

数据质量：
1. 字段分布
2. 字段值空值率：null 值、空值、空白字符值占比
3. 字段值枚举值：如状态 A、B、C
4. 字段值格式：如某日期字段，格式全都是 YYYYMMDD，还是说全都是 YYYY-MM-DD，各种情况的占比是多少，是否所有数据格式都一致

## 数据探查常用工具

[GitHub - fbdesignpro/sweetviz: Visualize and compare datasets, target values and associations, with one line of code.](https://github.com/fbdesignpro/sweetviz)

[Deequ基础教程](work/component/Big-Data/Deequ/Deequ基础教程.md)

## 参考链接

1. [4 Libraries that can perform EDA in one line of python code](https://towardsdatascience.com/4-libraries-that-can-perform-eda-in-one-line-of-python-code-b13938a06ae)