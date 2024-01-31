# HiveSQL DQL常用技巧


## 列转行

```sql
SUM(IF)

```

## 行转列

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