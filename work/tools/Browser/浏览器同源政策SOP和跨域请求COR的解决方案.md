# 浏览器同源政策 SOP 和跨域请求 COR 的解决方案


## 简介

**同源策略（Same Origin Policy，SOP）**，又名“同源政策”，是一种由浏览器实施的安全策略，仅存在于浏览器中。“同源策略”是浏览器为了保护用户的安全而采取的措施，目的是限制来自不同源的脚本在网页上的交互。浏览器在加载网页时，会执行同源策略来限制**跨域请求（Cross Origin Request，COR）** ，避免潜在的安全风险。

在浏览器中，“同源策略”通过检查脚本 Script、Cookie、DOM 访问和 XMLHttpRequest 等操作的来源和目标，判断它们**URL 是否具有相同的协议、主机和端口，如果三者相同则视为同源，反之则不同源（Cross Origin）**。如果跨域请求 COR 不符合同源策略 SOP，则浏览器默认会阻止其执行，并报错。

需要注意的是，“SOP 同源策略”一般只存在于浏览器中，是浏览器对网页执行的一种安全策略。而在其他环境中，如服务器端、移动应用程序等，通常不存在同源策略的限制，因为这些环境的执行环境和安全模型不同于浏览器。因此，当执行跨域请求或其他跨环境操作时，需要考虑不同环境的限制和安全性。


## 浏览器支持实现跨域请求的常见方法


### JSONP


### WebSocket


### CORS

CORS Response Headers

https://help.aliyun.com/document_detail/87730.html?spm=a2c4g.87730.0.0.578a49682CbXiQ

在前端开发中，关于跨域请求，存在两种类型：简单请求（Simple Request）和复杂请求（Complex Request）。这两者之间的区别在于浏览器处理跨域请求的方式，以及服务器响应的要求。

#### 简单请求

请求满足如下所有条件，为简单请求：

1. 请求方法是如下之一：
- HEAD
- GET
- POST
2. HTTP 头信息不超过以下几种字段：
- Cache-Control
- Content-Language
- Content-Type
- Expires
- Last-Modified
- Pragma
- DPR
- Downlink
- Save-Data
- Viewport-Width
- Width
3. Content-Type Header 的值仅限下列几种：
- Text/plain
- Multipart/form-data
- Application/x- www-form-urlencoded

#### 非简单请求

不符合简单请求条件的请求，即非简单请求，会在正式通信之前触发一个 `OPTIONS` HTTP 请求进行预检，这类请求为预检请求。

预检请求会在请求头中附带一些正式请求的信息给服务端，主要有：
- Origin：请求源信息。
- Access-Control-Request-Method：接下来的请求类型，如 POST、GET 等。
- Access-Control-Request-Headers：接下来的请求中包含的用户显式设置的 Header 列表。

服务端收到预检请求后，会根据上述附带的信息判断是否允许跨域，通过响应头返回对应的信息：
- Access-Control-Allow-Origin：允许跨域的 Origin 列表。
- Access-Control-Allow-Methods：允许跨域的方法列表。
- Access-Control-Allow-Headers：允许跨域的 Header 列表。
- Access-Control-Expose-Headers：允许暴露的 Header 列表。
- Access-Control-Max-Age：最大的浏览器缓存时间，单位：秒。
- Access-Control-Allow-Credentials：是否允许发送 Cookie。

浏览器会根据返回的 CORS 信息判断是否继续发送真实的 HTTP 请求。以上行为都是浏览器自动完成的，服务端只需要配置特定的 CORS 规则。

### 服务器代理请求

通过服务器代理请求来避免违反 SOP，是指不通过前端来发起请求，而是通过后端等方式进行代理请求。当使用代理服务器时，浏览器会将请求发送到代理服务器，代理服务器会将请求转发到目标域名，并将目标域名的响应返回给浏览器。这样，浏览器就不会直接与目标域名进行通信，而是通过代理服务器进行通信，从而避免了违反 SOP。


## 参考链接
1. [wiki - Same-origin policy](https://en.wikipedia.org/wiki/Same-origin_policy)
2. [MDN - CORS](https://developer.mozilla.org/en-US/docs/Glossary/CORS)
3. [wiki - JSONP](https://en.wikipedia.org/wiki/JSONP)
4. [w3schools - JSONP](https://www.w3schools.com/js/js_json_jsonp.asp)
5. [BiliBili-掌握 CORS 跨域请求，看这个视频就够了【渡一教育】](https://www.bilibili.com/video/BV1rp4y1K7nU)
6. [阮一峰-浏览器同源政策及其规避方法](https://www.ruanyifeng.com/blog/2016/04/same-origin-policy.html)
7. [阮一峰-跨域资源共享 CORS 详解](https://www.ruanyifeng.com/blog/2016/04/cors.html)
8. [BiliBili-掌握 CORS 跨域请求，看这个视频就够了【渡一教育】](https://www.bilibili.com/video/BV1rp4y1K7nU/)
