# Deequ 基础教程

## 简介

Deequ 是什么？一个基于 Apache Spark 的数据质量工具依赖项。

Deequ 是用来解决什么问题？解决大数据质量相关问题。

Deequ 基础模块：
- Metrics Computation - Deequ 会基于上层针对数据集制定的数据质量标准（Constraint）的定义，提取其中依赖的基础指标（Metric），并通过 Apache Spark 引擎来读取对应数据集，并计算这些指标，用于进行数据质量规则的校验。
- Constraint Verification - Deequ 支持用户定义各种数据质量标准（Constraint），并通过计算相关的指标来判断对应数据集是否满足标准，同时生成包含各数据质量标准校验结果的数据质量校验报告。
- Constraint Suggestion - Deequ 支持用户自定义数据质量标准，同时也会提供一些默认的校验规则用于快速配置质量标准（Constraint）。

Deequ 文档:
1. [PyDeequ — PyDeequ 0.0.4 documentation](https://pydeequ.readthedocs.io/en/latest/README.html)

## 参考链接

1. [GitHub - awslabs/deequ: Deequ is a library built on top of Apache Spark for defining "unit tests for data", which measure data quality in large datasets.](https://github.com/awslabs/deequ)
2. [Test data quality at scale with Deequ | AWS Big Data Blog](https://aws.amazon.com/cn/blogs/big-data/test-data-quality-at-scale-with-deequ/)