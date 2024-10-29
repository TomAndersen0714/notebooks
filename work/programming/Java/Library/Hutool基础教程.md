# Hutool 基础教程

## CollUtil

```java
// cn.hutool.core.collection.CollUtil.isEqualList  
// 判断两个List中的元素是否每个都相等  
System.out.println("CollUtil.isEqualList(List.of(1, 2, 3), List.of(1, 3, 2)) = " +  
    CollUtil.isEqualList(List.of(1, 2, 3), List.of(1, 3, 2)));
```

## DateUtil

```java
// cn.hutool.core.date.DateUtil.date()
// 获取当前日期时间对应的 DateTime 对象  
System.out.println("DateUtil.date() = " + DateUtil.date());  
System.out.println("DateUtil.date(Calendar.getInstance()) = " + DateUtil.date(Calendar.getInstance()));  
System.out.println("DateUtil.date(System.currentTimeMillis()) = " + DateUtil.date(System.currentTimeMillis()));  
  
// 获取当前日期时间对应的 String 字符串，格式：yyyy-MM-dd HH:mm:ss  
System.out.println("DateUtil.now() = " + DateUtil.now());  
  
// 获取当前日期对应的 String 字符串，格式：yyyy-MM-dd  
System.out.println("DateUtil.today() = " + DateUtil.today());
```

## 参考链接

1. [简介 | Hutool](https://doc.hutool.cn/pages/index/)
2. [GitHub - dromara/hutool: 🍬A set of tools that keep Java sweet.](https://github.com/dromara/hutool)