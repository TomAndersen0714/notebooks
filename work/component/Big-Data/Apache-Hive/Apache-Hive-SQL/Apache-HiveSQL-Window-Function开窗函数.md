# Apache Hive SQL Window Function 开窗函数



[Apache Hive - LanguageManual WindowingAndAnalytics](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+WindowingAndAnalytics)

[微信-大数据的奇妙冒险-Hive 从入门到放弃](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzU0MTcxNjM5OQ==&action=getalbum&album_id=2303851490576367625&scene=173&from_msgid=2247484032&from_itemidx=1&count=3&nolastread=1#wechat_redirect)

[微信-大数据的奇妙冒险-通俗易懂：窗口函数 | 全是案例，看完不懂算我输](https://mp.weixin.qq.com/s/6k39z-620xXE6Bxm4utNDQ)

```sql
SELECT *,
  last_value(score) ov.r(PARTITION BY class ORDER BY score) last1,
  last_value(score) over(PARTITION BY class ORDER BY score RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT row) last2,
  last_value(score) over(PARTITION BY class ORDER BY score RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) last3,
  last_value(score) over(PARTITION BY class ORDER BY score RANGE BETWEEN 3 PRECEDING AND 3 FOLLOWING) last4,
  last_value(score) over(PARTITION BY class ORDER BY score ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) last5
FROM stu_scores;
```


## RANGE BETWEEN 和 ROWS BETWEEN 的区别

`RANGE BETWEEN` 是基于值的范围定义。它根据行的实际数值进行窗口范围的定义，而不考虑行之间的物理位置。这意味着如果多行具有相同的排序值，则它们将被视为在同一个窗口内。

`ROWS BETWEEN` 是基于物理位置的范围定义。它考虑行之间的物理相对位置，不考虑行的实际值。这意味着无论值如何，只要行在指定的物理位置范围内，它们都被视为在同一个窗口内。