# HiveSQL DQL常用技巧

## 行压缩（行转列）

```sql
SUM(IF)
```

## 行展开（列转行）

```sql
SELECT
	name,
	subject,
	score
FROM (
	SELECT 'Alice' as name, MAP('Math', 90, 'Physics', 85) as grades
	UNION ALL
	SELECT 'Bob' as name, MAP('Math', 75, 'Physics', 80) as grades
	UNION ALL
	SELECT 'Jim' as name, MAP('Math', 75, 'Physics', 80) as grades
) t
LATERAL VIEW EXPLODE(grades) grades_table AS subject, score;
```

## 参考链接

1. [hive-行列互转，posexplode、lateral view、concat、collect\_lateral view posexplode-CSDN博客](https://blog.csdn.net/qq_34224565/article/details/124449548)
3. [Hive SQL中的 lateral view 与 explode（列转行）以及行转列\_lateral view explode-CSDN博客](https://blog.csdn.net/qq_42374697/article/details/115273726)