# Apache Hive SQL 常用函数

## Functions

### LanguageManual UDF

https://cwiki.apache.org/confluence/display/Hive/LanguageManual+UDF

#### 内置运算符 Built-in Operators

- `A <=> B` ：Returns same result with EQUAL (=) operator for non-null operands, but returns TRUE if both are NULL, FALSE if one of the them is NULL. . (As of version 0.9.0 .)
##### 关系运算符 Relational Operators



#### 内置函数 Built-in Functions

- `binary(string)` ：字符串转换为字节
- `length(string)` ：计算字符串长度

#### 内置 Built-in UDTF

- `Explode`
- `Posexplode`
### Windowing and Analytics Functions

[HiveSQL-Window-Function开窗函数](work/component/Big-Data/Apache-Hive/Hive-SQL/HiveSQL-Window-Function开窗函数.md)
