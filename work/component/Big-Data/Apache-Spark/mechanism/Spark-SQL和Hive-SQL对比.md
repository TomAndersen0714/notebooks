# Spark SQL 和 Hive SQL 对比


隐式类型转换

Spark SQL 支持自动类型转换，如 UNION ALL 查询中，后续子查询的结果，会自动转换为首个子查询中各个列对应的数据类型。而 Hive SQL 中，则要求强烈对齐。