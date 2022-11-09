# ClickHouse Distribued DDL执行失败问题排查



### 报错场景1

![image-20221108171759644](images/ClickHouse_Distribued_DDL%E6%89%A7%E8%A1%8C%E5%A4%B1%E8%B4%A5%E9%97%AE%E9%A2%98%E6%8E%92%E6%9F%A5/image-20221108171759644.png)



![image-20221108171854006](images/ClickHouse_Distribued_DDL%E6%89%A7%E8%A1%8C%E5%A4%B1%E8%B4%A5%E9%97%AE%E9%A2%98%E6%8E%92%E6%9F%A5/image-20221108171854006.png)



### 报错场景2

![image-20221108174245567](images/ClickHouse_Distribued_DDL%E6%89%A7%E8%A1%8C%E5%A4%B1%E8%B4%A5%E9%97%AE%E9%A2%98%E6%8E%92%E6%9F%A5/image-20221108174245567.png)



![image-20221108174316014](images/ClickHouse_Distribued_DDL%E6%89%A7%E8%A1%8C%E5%A4%B1%E8%B4%A5%E9%97%AE%E9%A2%98%E6%8E%92%E6%9F%A5/image-20221108174316014.png)



由于FINAL关键字，重试是没有用的



## 问题排查



### 查询github issue：

https://github.com/ClickHouse/ClickHouse/issues?q=Cannot+execute+replicated+DDL+query+on+leader+





## 参考链接

https://github.com/ClickHouse/ClickHouse/issues?q=Cannot+execute+replicated+DDL+query+on+leader+

https://github.com/ClickHouse/ClickHouse/issues/11884

https://github.com/ClickHouse/ClickHouse/issues/8282

https://github.com/ClickHouse/ClickHouse/pull/13450