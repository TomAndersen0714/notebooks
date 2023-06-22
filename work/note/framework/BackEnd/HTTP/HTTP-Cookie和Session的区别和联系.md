# Cookie和Session的区别和联系



Session Id用来唯一标识客户端，并且一个会话中只会使用同一个Session Id

### 服务端是如何识别特定的客户的？
这个时候Cookie就登场了。每次HTTP请求的时候，客户端都会发送相应的Cookie信息到服务端。实际上大多数的应用都是用 Cookie 来实现Session跟踪的，第一次创建Session的时候，服务端会在HTTP协议中告诉客户端，需要在 Cookie 里面记录一个Session ID，以后每次请求把这个会话ID发送到服务器，我就知道你是谁了。



## 参考链接

1. [博客园 - Session和cookie的作用](https://www.cnblogs.com/seamy/p/15648557.html)
2. [Wiki - Session(computer science)](https://en.wikipedia.org/wiki/Session_(computer_science))