# HTTP基础教程


## 前言

Hypertext Transfer Protocol (HTTP)超文本传输协议是TCP/IP协议的应用层协议之一，被设计用于在浏览器和Web服务器之间，传输超媒体（Hypermedia）文档，例如HTML、文本、图像、多媒体等。

HTTP不涉及数据包（Packet）传输，主要规定了客户端和服务器之间的通信格式，默认访问服务器的80端口。

HTTP遵循了经典的CS（client-server model）模型，即客户端建立连接，然后发送请求并等待响应。

HTTP原本是一种无状态协议（Stateless Protocol），即协议中并未规定服务器是否必需要在响应结束后保留数据。但在HTTP的实际应用过程中，cookie的出现，使得HTTP协议不再是无状态（Stateless）的了。

HTTP由两部分组成：请求（Request）和响应（Response）。


## An overview of HTTP
https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview



## HTTP Request Message


### Request Line

#### Method
https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods

#### Request-URI

#### HTTP-Version

#### CRLF

### Request Headers

请求头Request headers

对于HTTP GET请求，一般请求的参数通常是通过URL中的查询字符串传递的，因此它的payload一般是空的，但HTTP并未强制规定GET请求的payload是否为空，因此GET请求的payload具体是否为空，需要根据需求的具体实现方式来确定。

而对于HTTPPOST、PUT、DELETE等请求，请求的参数通常是通过请求体传递的，它的payload就是请求体的内容。在请求头中会有Content-Type字段指明请求体的类型，例如application/json、application/x-www-form-urlencoded等。


### Request Body


## HTTP Response Message

### Status Line

#### HTTP-Version

#### Status-Code
https://developer.mozilla.org/en-US/docs/Web/HTTP/Status

#### Reason-Phrase

#### CRLF

### Response Headers

响应头Response Headers

HTTP请求中，响应头（Response Headers）中的Set-Cookie字段，是用于告知客户端在下次请求中需要将Headers中的cookie字段设置成对应值，主要用于鉴别客户端身份。

HTTP请求中的，payload是指请求消息的主体部分，也就是请求体（RequestBody）。通常情况下，HTTP请求的payload包含了客户端发送给服务器的数据，例如表单数据、JSON数据等。

### Response Body


## HTTP Client Libraries

通常情况下，各个语言的HTTP API库中，都会有Session对象，用来维持会话状态，会自动维护连续请求的Cookie


## 参考链接
1. [阮一峰 - HTTP 协议入门](http://www.ruanyifeng.com/blog/2016/08/http.html)
2. [阮一峰 - HTTPS 升级指南](http://www.ruanyifeng.com/blog/2016/08/migrate-from-http-to-https.html)
3. [MDN - HTTP](https://developer.mozilla.org/en-US/docs/Web/HTTP)
4. [MDN - HTTP response status codes](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)
5. [菜鸟教程 - HTTP 状态码](https://www.runoob.com/http/http-status-codes.html)
6. [有关重定向的一些细节](https://blog.lishunyang.com/2020/06/redirect.html)
