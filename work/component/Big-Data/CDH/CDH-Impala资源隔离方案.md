# CDH Impala 资源隔离方案

## 用户级别资源隔离方案


### Impala Admission Control

#### 开启功能

![](resources/images/Pasted%20image%2020231113154310.png)


![](resources/images/Pasted%20image%2020231113154314.png)

#### 创建资源池

![](resources/images/Pasted%20image%2020231113154328.png)


#### 资源池参数设置

PS：如果仅设置了“最大内存”，则相当于事前内存预估，十分不精确；建议同时设置“默认查询内存限制”

![](resources/images/Pasted%20image%2020231113154341.png)

![](resources/images/Pasted%20image%2020231113154344.png)


#### 设置资源池使用规则

![](resources/images/Pasted%20image%2020231113154352.png)

#### 测试用例


![](resources/images/Pasted%20image%2020231113154402.png)

![](resources/images/Pasted%20image%2020231113154409.png)

## 参考链接
1. https://cloud.tencent.com/developer/article/1363401

