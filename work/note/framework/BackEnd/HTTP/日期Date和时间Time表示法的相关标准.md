# 日期Date和时间Time表示法的相关标准

## 前言

关于日期和时间表示法的标准，目前实际生活中使用最多的两个标准是`ISO 8601`和`RFC 3339`，其中后者是基于前者的一种扩展格式。

在这两个标准中，针对日期、时间、时间间隔等表示法都进行了相关规定，而本文主要截取其中的时间、日期、时区组合表示法来进行介绍。


## ISO 8601

日期、时间、时区信息的组合表示法，完整格式为：
```Java
YYYY-MM-DDThh:mm:ss.sss±hh:mm
```

其中日期和时间表达式之前使用`T`作为分隔符，时区信息紧跟在时间表达式之后，可以使用
`Z`表示UTC时间，或者使用`+`或`-`正负号来表示时区偏移量。

示例，如：
```Java
2007-04-05T12:30:00:231+08:00
2007-04-05T12:30:00−02:00
2007-04-05T12:30:00Z
```


## RFC 3339


日期、时间、时区信息组合表示法，完整格式为：
```Java
YYYY-MM-DDThh:mm:ss.ssssss±hh:mm
```

示例，如：
```Java
2007-04-05T12:30:00.112321−02:00
2007-04-05T12:30:00Z
```



## ISO 8601和RFC 3339标准的区别

本节内容参考自ChatGPT3.5

RFC 3339标准规定的格式也是基于ISO 8601标准的扩展格式，主要是针对互联网协议中需要使用日期时间的情况做出了一些特定的规定，旨在解决一些在使用 ISO 8601 时可能存在的模糊和歧义问题。以下是 RFC 3339 标准和 ISO 8601 标准的一些区别：

1. 时间精度不同
RFC 3339 在时间表示中使用了微秒级别的精度，而不是毫秒级别。ISO 8601 则只支持毫秒级别的精度。

2. 时间分隔符不同
RFC 3339 标准规定，日期和时间之间可以使用大写字母 "T" 或者空格分隔。而在 ISO 8601 标准中，日期和时间之间只能使用大写字母 "T" 分隔。

>For example, "2007-04-05T14:30". In ISO 8601:2004 it was permitted to omit the "T" character by mutual agreement as in "200704051430", but this provision was removed in ISO 8601-1:2019. 
>
>Separating date and time parts with other characters such as space is not allowed in ISO 8601, but allowed in its profile RFC 3339.


3. 时区信息不同
RFC 3339 和 ISO 8601 都支持使用 UTC 时间表示，并且都使用以字母 "Z" 结尾表示 UTC 时间。但是，在表示本地时间时，它们的规则略有不同。RFC 3339 要求使用以±HH:MM 形式表示时区偏移量，而 ISO 8601 则可以使用以字母 "Z" 结尾表示 UTC 时间，也可以使用以±HH:MM 形式表示时区偏移量。

4. 标准的范围不同
RFC 3339 标准是一种推荐实践，主要应用于 Web 服务、电子邮件、日志记录等应用程序。而 ISO 8601 则是一个国际标准，广泛应用于数据交换、时间戳记、计算机语言和软件等领域。

需要注意的是，虽然 RFC 3339 和 ISO 8601 存在一些不同之处，但它们的目的都是规范化日期和时间的表示方式，使得不同系统之间能够更加准确地交换和处理日期和时间信息。因此，在实际应用中，应根据具体情况选择适合的标准。


## 其他格式


除了目前主流的ISO 8601和RFC 3339两个标准中描述的格式之外，还有一种广泛使用的格式：
```Java
YYYY-MM-DD hh:mm:ss
YYYY-MM-DD hh:mm:ss.sss
YYYY-MM-DD hh:mm:ss.ssssss
```

这种格式的优点是简单明了，**不带时区信息，易于阅读和排序**。日期和时间按照从大到小的顺序排列，也就是先年份，再月份，最后是日期和时间。另外，这种格式在计算机系统中使用广泛，支持各种操作和运算，方便数据处理和存储。

**但值得注意的是，这种格式并不符合任何一种国际标准**，在实际使用过程中，经常需要自己手动维护序列化和反序列化，十分不方便，而且容易在某些框架中踩坑（如：ES、ClickHouse等）。


## 总结

鉴于目前在实际使用过程中，各种标准混杂不够统一，ISO标准在各个编程语言中支持也不够统一和完善。

如Python3中，低版本datetime API并未完全支持ISO标准。
[stackoverflow - How do I translate an ISO 8601 datetime string into a Python datetime object?](stackoverflow.com/questions/969285/how-do-i-translate-an-iso-8601-datetime-string-into-a-python-datetime-object)
[stackoverflow - How do I parse an ISO 8601-formatted date?](https://stackoverflow.com/questions/127803/how-do-i-parse-an-iso-8601-formatted-date)

**建议针对所有日期时间字段的序列化和传输，统一采取时间戳（timestamp）的形式**，原因在于时间戳是全球统一的，不带有时区信息，不存在歧义，且整型便于存储和排序，但相对的会牺牲部分可读性。


## 参考链接
1. [wiki-ISO 8601](https://zh.wikipedia.org/wiki/ISO_8601)
2. [RFC1123](https://datatracker.ietf.org/doc/html/rfc1123)
3. [RFC3339](https://datatracker.ietf.org/doc/html/rfc3339)
4. [ES 中时间日期类型 “yyyy-MM-dd HH:mm:ss” 的完全避坑指南](https://blog.csdn.net/wlei0618/article/details/123712605)
5. [stackoverflow - How do I translate an ISO 8601 datetime string into a Python datetime object?](stackoverflow.com/questions/969285/how-do-i-translate-an-iso-8601-datetime-string-into-a-python-datetime-object)
6. [stackoverflow - How do I parse an ISO 8601-formatted date?](https://stackoverflow.com/questions/127803/how-do-i-parse-an-iso-8601-formatted-date)