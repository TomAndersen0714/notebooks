# Spark SQL UDF 开发基础教程

Todo: 要求有 Demo，要求有开发、调试操作和截图。

Spark SQL UDF，既支持 Java/Scala/Python API 进行注册，也支持 Spark SQL 进行注册。

## Docs

[Functions - Spark 3.5.3 Documentation](https://spark.apache.org/docs/latest/sql-ref-functions.html#udfs-user-defined-functions)

UDFs
[Scalar User Defined Functions (UDFs) - Spark 3.5.3 Documentation](https://spark.apache.org/docs/latest/sql-ref-functions-udf-scalar.html)
UDAFs
[User Defined Aggregate Functions (UDAFs) - Spark 3.5.3 Documentation](https://spark.apache.org/docs/latest/sql-ref-functions-udf-aggregate.html)

## UDFs

UDFs work on a single row as input and generate a single row as output.

Example:
```

```

## UDAFs

UDAFs operate on multiple rows and return a single aggregated row as a result.

Example:
```

```

## UDTFs

UDTFs (User Defined Tabular Functions) that act on one row as input and return multiple rows as output.

Example:
```

```

## 参考链接

1. [Scalar User-Defined Functions (UDFs)](https://spark.apache.org/docs/latest/sql-ref-functions-udf-scalar.html)
2. [User-Defined Aggregate Functions (UDAFs)](https://spark.apache.org/docs/latest/sql-ref-functions-udf-aggregate.html)
3. [Integration with Hive UDFs/UDAFs/UDTFs](https://spark.apache.org/docs/latest/sql-ref-functions-udf-hive.html)