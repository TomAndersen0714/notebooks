# Apache Hive SQL 常用函数

## Functions

### LanguageManual UDF

https://cwiki.apache.org/confluence/display/Hive/LanguageManual+UDF

#### 内置运算符 Built-in Operators

- `A <=> B` ：Returns same result with EQUAL (=) operator for non-null operands, but returns TRUE if both are NULL, FALSE if one of the them is NULL. . (As of version 0.9.0 .)
##### 关系运算符 Relational Operators

#### 内置函数 Built-in Functions

##### Json 函数

`get_json_object`

A limited version of JSONPath is supported:
- $ : Root object
- . : Child operator
- [] : Subscript operator for array
- * : Wildcard for array []

Example:
```sql

+----+
json
+----+
{"store":
  {"fruit":\[{"weight":8,"type":"apple"},{"weight":9,"type":"pear"}],
   "bicycle":{"price":19.95,"color":"red"}
  },
 "email":"amy@only_for_json_udf_test.net",
 "owner":"amy"
}
+----+


hive> SELECT get_json_object(src_json.json, '$.owner') FROM src_json;
amy
 
hive> SELECT get_json_object(src_json.json, '$.store.fruit\[0]') FROM src_json;
{"weight":8,"type":"apple"}
 
hive> SELECT get_json_object(src_json.json, '$.non_exist_key') FROM src_json;
NULL
```

##### 其他

- `binary(string)` ：字符串转换为字节
- `length(string)` ：计算字符串长度

#### 内置 Built-in UDTF

- `Explode`
- `Posexplode`
### Windowing and Analytics Functions

[HiveSQL-Window-Function开窗函数](work/component/Big-Data/Apache-Hive/development/HiveSQL-Window-Function开窗函数.md)

## 参考链接

1. [LanguageManual UDF - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+UDF)