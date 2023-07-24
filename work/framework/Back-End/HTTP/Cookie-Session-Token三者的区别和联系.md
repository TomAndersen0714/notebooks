# Cookie、Session、Token 三者的区别和联系


## HTTP 

标准 HTTP 协议是无状态 (Stateless) 的，各个 HTTP 请求之间相互独立。

为了提升 Web 应用的用户体验，提供个性化服务，越来越多需求要求 Web 应用能够保存其用户的状态，并针对不同的用户状态，提供不同的功能。如：若用户当天的历史请求次数为 0，则重定向到“欢迎页”。

## Cookie

Cookie 浏览器中的存放的一种小型文件，同时也是 HTTP Request Header 中的一个字段，都是以 Key-Value 键值对的形式存储数据。

默认情况下，浏览器在每次发起 HTTP Request 时，都会将 Cookie 中的数据添加到 HTTP Request Header 的 Cookie 字段中，一起发出。

而服务器端则可以通过 HTTP 的响应头 HTTP Response Header 中的 Set-Cookie 字段，来指示浏览器将该字段中的 Key-Value 数据，保存到浏览器的 Cookie 中，这是 `RFC 6265` 协议中明确声明浏览器必须支持的标准功能。

通过 Cookie ，客户端可以保存状态，服务器可以让客户端（浏览器）存储 HTTP 请求的状态数据。

优势是简单直接。
缺陷是数据以明文的形式进行传输和存储，数据安全性较低，因此在某些情况下用户会禁用浏览器的 Cookie 功能。同时，现代浏览器对 Cookie 有一些限制和安全策略，如同源策略、HttpOnly 属性、Secure 属性等，以保护用户的隐私和安全。

## Session

Session 是一种在服务器端维护的会话状态管理机制，用于跟踪用户在一段时间内的操作和状态，并通过 Session ID 唯一标识对应的数据。

通常情况，Session ID 默认是通过 Cookie 来进行存储的。服务器会在用户第一次访问网站时为其创建一个唯一的 Session ID，并将这个 Session ID 存储在响应头的 Set-Cookie 中发送给浏览器，要求浏览器保存在其 Cookie 中存储对应的 Session ID。随后，浏览器在后续的请求中会自动将 Cookie 中的这个 Session ID 附加在请求中发送给服务器，从而使得服务器可以识别用户并继续维护用户的会话状态。

除了 Cookie 之外，Session ID 也可以通过请求头、URL 参数等方式进行传递，Cookie 只是一种最常见的实现方式。

优势是避免了数据安全问题。
缺陷是不适合大型分布式应用，服务器端需要维持分布式一致性。

## Token


优势：安全性高，不依赖于 Cookie。


## 参考链接

1. [博客园 - Session和cookie的作用](https://www.cnblogs.com/seamy/p/15648557.html)
2. [Wiki - Session(computer science)](https://en.wikipedia.org/wiki/Session_(computer_science))