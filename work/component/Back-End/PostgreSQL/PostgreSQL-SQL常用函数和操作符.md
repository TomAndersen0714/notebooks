# PostgreSQL SQL 常用函数和操作符

https://www.postgresql.org/docs/14/functions.html


## JSON Functions and Operators
https://www.postgresql.org/docs/14/functions-json.html

1) **json_build_object ( VARIADIC "any" ) → json**：
   将输入参数组组装成json并返回，如：`SELECT json_build_object('foo', 1, 2, row(3,'bar'))`


## NULL Functions and Operators

https://www.postgresql.org/docs/14/functions-comparison.html
IS NULL → boolean
IS NOT NULL → boolean
ISNULL → boolean
NOTNULL → boolean

https://www.postgresql.org/docs/14/functions-conditional.html#FUNCTIONS-NULLIF
NULLIF (value1, value2)

## System Information Functions and Operators
https://www.postgresql.org/docs/14/functions-info.html

1) **pg_typeof ( "any" ) → regtype**：
   获取指定值的数据类型，如：`SELECT pg_typeof(33)`


## Window Functions

https://www.postgresql.org/docs/14/sql-expressions.html#SYNTAX-WINDOW-FUNCTIONS
https://www.postgresql.org/docs/14/tutorial-window.html
https://www.postgresql.org/docs/14/functions-window.html


## Type Conversion
https://www.postgresql.org/docs/14/typeconv.html


### Type Conversion Functions
https://www.postgresql.org/docs/14/typeconv-func.html

1) **CAST( "any" AS "type")**：
   将任意变量转换为指定类型，如：`SELECT CAST(json_build_object('foo', 1, 2, row(3,'bar')) AS text)`



## 参考链接
1. [PostgreSQL - Functions and Operators](https://www.postgresql.org/docs/14/functions.html)
2. [PostgreSQL - System Information Functions and Operators](https://www.postgresql.org/docs/14/functions-info.html)
3. [PostgreSQL - Data Types](https://www.postgresql.org/docs/14/datatype.html)