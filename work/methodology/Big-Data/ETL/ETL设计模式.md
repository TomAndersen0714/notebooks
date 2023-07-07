# ETL 设计模式


将 [ETL常用算法](work/methodology/Big-Data/ETL/ETL常用算法.md) 中的内容搬运过来，进行整合。

## 数仓设计模式

[数仓设计模式](work/methodology/Big-Data/DW-Analysis-and-Design/数仓设计模式.md)
数仓设计是设计关键节点，ETL 设计是设计关键路径



## 拉链表

拉链表的更新和回滚方案：
https://blog.csdn.net/guicaizhou/article/details/82021735
http://lxw1234.com/archives/2015/08/473.htm
http://lxw1234.com/archives/2015/04/20.htm
https://www.cnblogs.com/rango-lhl/p/14310487.html
https://juejin.cn/post/7024067235517825061


## 宽表

可以通过将待更新数据写入临时表，然后将所有待更新数据 JOIN 后写入最终表的方式来实现更新


## 5 种流式 ETL 模式
https://mp.weixin.qq.com/s/Mj56qNBqEpMT6w2jw5axGg