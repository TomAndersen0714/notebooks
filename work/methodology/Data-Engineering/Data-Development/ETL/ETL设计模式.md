# ETL 设计模式

## 前言

ETL 设计属于数仓设计中的子任务之一。


将 [ETL算法设计模式](work/methodology/Data-Engineering/Data-Development/ETL/ETL算法设计模式.md)和[ETL批处理常用算法](work/methodology/Data-Engineering/Data-Development/ETL/ETL批处理常用算法.md) 中的内容搬运过来，进行整合。


判断依据，数据实时性要求、数据量的规模


## 拉链表

拉链表的更新和回滚方案：
https://blog.csdn.net/guicaizhou/article/details/82021735
http://lxw1234.com/archives/2015/08/473.htm
http://lxw1234.com/archives/2015/04/20.htm
https://www.cnblogs.com/rango-lhl/p/14310487.html
https://juejin.cn/post/7024067235517825061

[微信-数据仓库与 Python 大数据-详解数据仓库之拉链表（原理、设计以及在 Hive 中的实现）](https://mp.weixin.qq.com/s/2-4w6jGjVzDmlTMck5gKLg)

## 宽表

可以通过将待更新数据写入临时表，然后将所有待更新数据 JOIN 后写入最终表的方式来实现更新


## 5 种流式 ETL 模式
https://mp.weixin.qq.com/s/Mj56qNBqEpMT6w2jw5axGg