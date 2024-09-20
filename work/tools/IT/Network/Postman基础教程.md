# Postman 基础教程

## 常用功能

导入浏览器请求：
[谷歌浏览器的请求，一键复制到Postman的小技巧\_谷歌浏览器请求头不好复制了-CSDN博客](https://blog.csdn.net/woshiyigerenlaide/article/details/103068885)

设置 Cookie：
[Postman 中如何添加 Cookie？图文教程](https://apifox.com/apiskills/how-to-add-cookies-to-postman/)
1. 在当前请求的 Header 中添加 Cookie。在Header 中增加键值对，其中 `Cookie` 作为 Key，Cookie 的值作为 Value。Cookie 的格式通常是 "key=value" 的形式，并且如果你有多个 Cookie，你可以用英文分号分隔它们，如：`sessionId=abc123; userId=12345`。
2. 针对指定域名的请求增加 Cookie。配置后，所有针对该域名的请求，都会被附加上对应的 Cookie。

Request 转换代码片段 Code Snippet：
[Postman的请求转化成代码怎么操作？\_postman中的请求如何转为requests-CSDN博客](https://blog.csdn.net/qq_34972627/article/details/123686299)

## 常见问题

问题描述：Postman不返回300的重定向类请求，会自动跳转
解决方案：Settings 中的配置 `Automatically follow redirects` 配置默认为 on，修改配置为 off 即可