# Apache Hive SQL 函数基础教程

## Hive 内置函数

### LanguageManual UDF

https://cwiki.apache.org/confluence/display/Hive/LanguageManual+UDF

#### 运算符 Operators

`A <=> B` ：
- Returns same result with EQUAL (=) operator for non-null operands, but returns TRUE if both are NULL, FALSE if one of the them is NULL. . (As of version 0.9.0 .)
- 支持判断 null 值的等于符号。

##### 关系运算符 Relational Operators

#### UDF

Normal user-defined functions, such as concat (), take in a single input row and output a single output row.

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

##### Array 函数

| **Name(Signature)**             | **Return Type** | **Description**                                                                                                                                                                           |
| ------------------------------- | --------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| size(Map<K.V>)                  | int             | Returns the number of elements in the map type.                                                                                                                                           |
| size(Array<T>)                  | int             | Returns the number of elements in the array type.                                                                                                                                         |
| map_keys(Map<K.V>)              | array<K>        | Returns an unordered array containing the keys of the input map.                                                                                                                          |
| map_values(Map<K.V>)            | array<V>        | Returns an unordered array containing the values of the input map.                                                                                                                        |
| array_contains(Array<T>, value) | boolean         | Returns TRUE if the array contains value.                                                                                                                                                 |
| sort_array(Array<T>)            | array<t>        | Sorts the input array in ascending order according to the natural ordering of the array elements and returns it (as of version [0.9.0](https://issues.apache.org/jira/browse/HIVE-2279)). |

##### String 函数

- `binary(string)` ：字符串转换为字节
- `length(string)` ：计算字符串长度

##### 其他

- `nvl(column, alternative)`：返回默认值，如果指定值为 null，否则返回对应值。

#### UDTF

Table-generating functions transform a single input row to multiple output rows.

UDTF 支持接收单行结果，并展开为多行，但是在 SELECT 语句中使用 UDTF 时，能且仅能使用一个 UDTF 函数，无法使用其他的 Expression。

- `explode`
- `posexplode`

#### UDAF

### Windowing and Analytics Functions

[HiveSQL-Window-Function开窗函数](work/component/Big-Data/Apache-Hive/development/HiveSQL-Window-Function开窗函数.md)

## Hive 开源函数库

第三方 Hive UDF 仓库：
1. [GitHub - jeromebanks/brickhouse: Hive UDF's for the data warehouse](https://github.com/jeromebanks/brickhouse)
2. [GitHub - brndnmtthws/facebook-hive-udfs: Facebook's Hive UDFs](https://github.com/brndnmtthws/facebook-hive-udfs)
3. [GitHub - aaronshan/hive-third-functions: Some useful custom hive udf functions, especial array, json, math, string functions.](https://github.com/aaronshan/hive-third-functions)

## 参考链接

1. [LanguageManual UDF - Apache Hive - Apache Software Foundation](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+UDF)