# HTTP基础教程

## 前言

Hypertext Transfer Protocol (HTTP) 超文本传输协议是 TCP/IP 协议的应用层协议之一，被设计用于在浏览器和 Web 服务器之间，传输超媒体（Hypermedia）文档，例如 HTML、文本、图像、多媒体等。

HTTP 不涉及数据包（Packet）传输，主要规定了客户端和服务器之间的通信格式，默认访问服务器的 80 端口。

HTTP 遵循了经典的 CS（client-server model）模型，即客户端建立连接，然后发送请求并等待响应。

HTTP 原本是一种无状态协议（Stateless Protocol），即协议中并未规定服务器是否必需要在响应结束后保留数据。但在 HTTP 的实际应用过程中，cookie 的出现，使得 HTTP 协议不再是无状态（Stateless）的了。

HTTP 由两部分组成：请求（Request）和响应（Response）。

## An overview of HTTP
https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview

## HTTP Request Message

### Request Line/Start Line

#### Method
https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods

GET

POST

PUT

HEAD

DELETE

OPTIONS

预检请求（preflight request）
预检响应（preflight response）

TRACE

CONNECT

#### Request URI

在 Web 浏览器中的网址即 URL，是 URI 的一种具体形式

#### HTTP Version

### Request Headers

请求头 Request headers。

对于 HTTP GET 请求，一般请求的参数通常是通过 URL 中的查询字符串（Query String）传递的，因此它的 payload 一般是空的，但 HTTP 并未强制规定 GET 请求的 payload 是否为空，因此 GET 请求的 payload 具体是否为空，需要根据需求的具体实现方式来确定。

而对于 HTTPPOST、PUT、DELETE 等请求，请求的参数通常是通过请求体传递的，它的 payload 就是请求体的内容。在请求头中会有 Content-Type 字段指明请求体的类型，例如 application/json、application/x- www-form-urlencoded等 。

### CRLF

### Request Body

Request Body（请求体），是 HTTP 请求中的数据部分，由客户端填充，和 Respond Body（响应体）一起并称为 payload。

## HTTP Response Message

### Status Line

#### HTTP Version

#### Status Code
https://developer.mozilla.org/en-US/docs/Web/HTTP/Status

#### Reason Phrase

### Response Headers

响应头 Response Headers

HTTP 请求中，响应头（Response Headers）中的 Set-Cookie 字段，是用于告知客户端在下次请求中需要将 Headers 中的 cookie 字段设置成对应值，主要用于鉴别客户端身份。

HTTP 请求中的，payload 是指请求消息的主体部分，也就是请求体（Request Body）。通常情况下，HTTP 请求的 payload 包含了客户端发送给服务器的数据，例如表单数据、JSON 数据等。

### CRLF

### Response Body

Respond Body（响应体），是 HTTP 响应中的数据部分，由服务端填充，和 Request Body（请求体）一起并称为 payload。

## HTTP Headers

### Content-Type

HTTP 中的 Content-Type 头部字段用于标识发送或接收的实体主体（body）的媒体数据类型（MIME type），用于告知 HTTP 消息的接收端如何解析和处理实体主体数据。

## HTTP Client Libraries

### JavaScript

#### ECMAScript

##### XMLHttpRequest

##### fetch

#### Node.js

##### Axios

### Python

#### requests

Session 对象，用来维持会话状态，会自动维护连续请求的 Cookie 值

## HTTPS

[HTTPS基础教程](learning/subjects/ComputerScience/Networking/HTTP/HTTPS基础教程.md)

## 参考链接

1. [阮一峰 - HTTP 协议入门](http://www.ruanyifeng.com/blog/2016/08/http.html)
2. [阮一峰 - HTTPS 升级指南](http://www.ruanyifeng.com/blog/2016/08/migrate-from-http-to-https.html)
3. [MDN - HTTP](https://developer.mozilla.org/en-US/docs/Web/HTTP)
4. [MDN - HTTP - HTTP Messages](https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages)
5. [MDN - HTTP - HTTP Headers](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers)
6. [MDN - HTTP response status codes](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)
7. [菜鸟教程 - HTTP 状态码](https://www.runoob.com/http/http-status-codes.html)
8. [有关重定向的一些细节](https://blog.lishunyang.com/2020/06/redirect.html)