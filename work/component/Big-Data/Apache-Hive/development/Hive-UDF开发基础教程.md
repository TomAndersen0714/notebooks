# Hive UDF 开发基础教程

Todo: 要求有 Demo，要求有开发、调试操作和截图。
## UDF 简介

在 Apache Hive 中，UDF（User-Defined Function）可以分为几种不同的类型，主要根据其功能和处理方式进行分类。以下是一些常见类型的 Hive UDF：

1. **普通 UDF（Regular UDF）**：
   - 普通 UDF 是最常见的一种，用于对单个数据项进行操作，通常一次处理一行数据并返回一个结果。
   - 这些函数的类需要继承自 `org.apache.hadoop.hive.ql.exec.UDF` 类，并实现 `evaluate()` 方法。

2. **UDAF（User-Defined Aggregate Function）**：
   - UDAF 允许用户自定义聚合函数，用于对数据进行聚合操作，如求和、计数、平均值等。
   - 这些函数的类需要继承自 `org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver` 类。

3. **UDTF（User-Defined Table-Generating Function）**：
   - UDTF 允许用户自定义生成表格的函数，用于产生多行输出。
   - 这些函数的类需要继承自 `org.apache.hadoop.hive.ql.udf.generic.GenericUDTF` 类，并实现 `process()` 方法。

4. **GenericUDF**：
   - GenericUDF 是一种更通用的 UDF 类型，可以处理多种输入类型，并返回单个结果或多个结果。
   - 这些函数的类需要继承自 `org.apache.hadoop.hive.ql.udf.generic.GenericUDF` 类，并实现 `evaluate()` 方法。

5. **UDTF with GenericUDTF**：
   - 有时候，用户定义的表格生成函数（UDTF）会与通用表格生成函数（GenericUDTF）结合使用，以提供更大的灵活性和功能。
   - 这些函数的类可以同时继承自 `org.apache.hadoop.hive.ql.udf.generic.GenericUDTF` 和 `org.apache.hadoop.hive.ql.udf.generic.GenericUDF`。

以上是 Hive 中常见的 UDF 类型，用户可以根据自己的需求选择合适的类型并实现相应的函数。




