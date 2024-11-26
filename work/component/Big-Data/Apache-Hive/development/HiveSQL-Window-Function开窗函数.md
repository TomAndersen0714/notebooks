# Apache Hive SQL Window Function 开窗函数

1. [Apache Hive - LanguageManual WindowingAndAnalytics](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+WindowingAndAnalytics)
2. [微信-大数据的奇妙冒险-Hive 从入门到放弃](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzU0MTcxNjM5OQ==&action=getalbum&album_id=2303851490576367625&scene=173&from_msgid=2247484032&from_itemidx=1&count=3&nolastread=1#wechat_redirect)
3. [微信-大数据的奇妙冒险-通俗易懂：窗口函数 | 全是案例，看完不懂算我输](https://mp.weixin.qq.com/s/6k39z-620xXE6Bxm4utNDQ)

## 基础语法

```sql
SELECT
	*,
	last_value(score) over(PARTITION BY class ORDER BY score) last1,
    last_value(score) over(PARTITION BY class ORDER BY score RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT row) last2,
    last_value(score) over(PARTITION BY class ORDER BY score RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) last3,
    last_value(score) over(PARTITION BY class ORDER BY score RANGE BETWEEN 3 PRECEDING AND 3 FOLLOWING) last4,
    last_value(score) over(PARTITION BY class ORDER BY score ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) last5
FROM stu_scores;
```

## Windows 窗口语法

```sql
(ROWS | RANGE) BETWEEN (UNBOUNDED | [num]) PRECEDING AND ([num] PRECEDING | CURRENT ROW | (UNBOUNDED | [num]) FOLLOWING)
(ROWS | RANGE) BETWEEN CURRENT ROW AND (CURRENT ROW | (UNBOUNDED | [num]) FOLLOWING)
(ROWS | RANGE) BETWEEN [num] FOLLOWING AND (UNBOUNDED | [num]) FOLLOWING
```

### OVER(...)

用于定义窗口的大小和行数据的顺序。

### PARTITION BY, 和 ORDER BY

`PARTITION BY, ORDER BY` 是用于声明当前行对应窗口的初始大小和顺序，`PARTITION BY` 代表按照多个指定列的值进行分组，和当前值相同的在同一个窗口，`ORDER BY` 则是用于定义当前窗口中，行与行之间的相对顺序。

**注意事项：**
1. 当 `ORDER BY` 被声明时，则窗口默认为 `RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW`，即会选取 ORDER BY 列之前的值到当前行的值的窗口。
2. When ORDER BY is specified with missing WINDOW clause, the WINDOW specification defaults to `RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW`.
3. 当 `ORDER BY` 和 Windows 都未被声明时，则窗口默认为 `ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING`，即整个数据范围。
4. When both ORDER BY and WINDOW clauses are missing, the WINDOW specification defaults to `ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING`.

### RANGE BETWEEN 和 ROWS BETWEEN

`RANGE BETWEEN` 是基于值的范围定义。它根据行的实际数值进行窗口范围的定义，而不考虑行之间的物理位置。这意味着如果多行具有相同的排序值，则它们将被视为在同一个窗口内。

`ROWS BETWEEN` 是基于物理位置的范围定义。它考虑行之间的物理相对位置，不考虑行的实际值。这意味着无论值如何，只要行在指定的物理位置范围内，它们都被视为在同一个窗口内。

### UNBOUNDED, CURRENT ROW, 和 `[num]`

`UNBOUNDED`, `CURRENT ROW`, `[num]` 是用于声明当前窗口的上下边界，相对于当前行的偏移量，`UNBOUNDED` 代表边界和 `OVER()`

### PRECEDING, FOLLOWING

`PRECEDING` 和 `FOLLOWING` 是用于声明当前窗口的上下边界相对当前行的方向（即 AND 的左右侧参数），即是相对于当前行之前，还是相对于当前行之后。

Example:
```sql
SELECT
	COUNT(DISTINCT a) OVER (PARTITION BY c ORDER BY d ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING)
FROM t1;
```

## 常用开窗函数

### LEAD, LAG

LEAD: 接收 1 个 (column) 或者 3 个参数 (column, offset, default)，作用是返回当前 Window 中当相对当前行的前 offset 行的 column 值，如果超出窗口，则会返回默认值 default，默认情况下传单个参数时，只会获取上一行结果。

```sql
-- LEAD using default 1 row lead and not specifying default value
SELECT a, LEAD(a) OVER (PARTITION BY b ORDER BY C)
FROM T;

-- LEAD specifying a lag of 3 rows and default value of 0
SELECT a, LEAD(a, 3, 0) OVER (PARTITION BY b ORDER BY C)
FROM T;
```

LAG：使用方式同 LEAD 函数，区别在于 LEAD 是获取前缀行，LAG 是获取后缀行。

```sql
-- LAG specifying a lag of 3 rows and default value of 0
SELECT a, LAG(a, 3, 0) OVER (PARTITION BY b ORDER BY C)
FROM T;
```

### FIRST_VALUE, LAST_VALUE

`FIRST_VALUE`: 接收 1 个或者 2 个参数 (column, skip_null)，作用是返回当前窗口的首个值，第 1 个参数是列名，第二个参数是 boolean 类型，控制是否跳过 null 值。
`LAST_VALUE`: 使用方法同上，作用相反。

### RANK, DENSE_RANK, ROW_NUMBER

`RANK`: 非连续排序，同值排名相同，同值排名的后续排名不连续。
`DENSE_RANK`: 连续排序，同值排名相同，同值排名的后续排名连续。
`ROW_NUMBER`: 连续排序，同值排名不同，编号唯一。

## 参考链接

1. [Apache Hive - LanguageManual WindowingAndAnalytics](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+WindowingAndAnalytics)
2. [微信-大数据的奇妙冒险-Hive 从入门到放弃](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzU0MTcxNjM5OQ==&action=getalbum&album_id=2303851490576367625&scene=173&from_msgid=2247484032&from_itemidx=1&count=3&nolastread=1#wechat_redirect)
3. [微信-大数据的奇妙冒险-通俗易懂：窗口函数 | 全是案例，看完不懂算我输](https://mp.weixin.qq.com/s/6k39z-620xXE6Bxm4utNDQ)