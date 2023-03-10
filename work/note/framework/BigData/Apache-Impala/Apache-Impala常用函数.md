# Apache Impala常用函数

## 前言

1. Apache Impala中的函数都是非大小写敏感的（Non-case sensitive），无视大小写
2. Apache Impala支持的数据类型：[Cloudera Enterprise 6.3.x - Data Types](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/impala_datatypes.html#datatypes)


## [类型转换函数](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/impala_conversion_functions.html#conversion_functions)

1. **CAST(expr AS type)**：将指定列转换为对应类型，如“SELECT CAST(2 AS String)”
2. **TYPEOF(type value)**：返回输入参数的数据类型名称，如“SELECT TYPEOF(2), TYPEOF(2+2);”


## [日期和时间函数](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/impala_datetime_functions.html)

1. **NOW()**：返回节点当前的本地时间，返回数据类型为TIMESTAMP


## [字符串函数](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/impala_string_functions.html#string_functions)

1. **REPLACE(STRING initial, STRING target, STRING replacement)**：返回值类型为String，替换原始字符串中的指定子串，并使用新字符串进行替代，如：“SELECT replace('2021-08-31','-','')”

## [数值函数](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/impala_math_functions.html#math_functions)

1. **ROUND(DOUBLE a, INT d)**：返回值类型为Double，将Double类型的浮点数的前d位小数进行四舍五入，如：SELECT round(1.5,1)结果为1.0


## 聚合函数

1. **GROUP_CONCAT(STRING col, STRING sep)**：**列转行函数**，将当前数据集下指定列的所有值按照指定分隔符拼接成单个字符串，默认分隔符为英文逗号。返回值为String类型。PS：行转列，目前只能通过“union”关键字实现，且只能支持有限行



## 参考链接

1. [Cloudera Enterprise 6.3.x - Data Types](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/impala_datatypes.html#datatypes)
2. [Impala Date and Time Functions](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/impala_datetime_functions.html)