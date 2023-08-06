# 浏览器 SOP 同源策略


## 简介

同源策略（Same Origin Policy，SOP）是一种由浏览器实施的安全策略，仅存在于浏览器中。“同源策略”是浏览器为了保护用户的安全而采取的措施，目的是限制来自不同源的脚本在网页上的交互。浏览器在加载网页时，会执行同源策略来防止跨域请求和潜在的安全风险。

在浏览器中，“同源策略”通过检查脚本、Cookie、DOM 访问和 XMLHttpRequest 等操作的来源和目标，判断它们**URL 是否具有相同的协议、主机和端口，如果三者相同则视为同源，反之则不同源**。如果请求不符合同源规则，浏览器会阻止其执行。

需要注意的是，“SOP同源策略”一般只存在于浏览器中，是浏览器对网页执行的一种安全策略。而在其他环境中，如服务器端、移动应用程序等，通常不存在同源策略的限制，因为这些环境的执行环境和安全模型不同于浏览器。因此，当开发跨域请求或其他跨环境操作时，需要考虑不同环境的限制和安全性。

所有违反 SOP 的网络请求，都被称为**跨域请求**。

## 浏览器支持实现跨域请求的常见方法


### JSONP


### WebSocket



### CORS

CORS Response Headers

https://help.aliyun.com/document_detail/87730.html?spm=a2c4g.87730.0.0.578a49682CbXiQ

### 服务器代理请求

通过服务器代理请求来避免违反 SOP，是指不通过前端来发起请求，而是通过后端等方式进行代理请求。当使用代理服务器时，浏览器会将请求发送到代理服务器，代理服务器会将请求转发到目标域名，并将目标域名的响应返回给浏览器。这样，浏览器就不会直接与目标域名进行通信，而是通过代理服务器进行通信，从而避免了违反 SOP。


## 参考链接
1. [wiki - Same-origin policy](https://en.wikipedia.org/wiki/Same-origin_policy)
2. [MDN - CORS](https://developer.mozilla.org/en-US/docs/Glossary/CORS)
3. [wiki - JSONP](https://en.wikipedia.org/wiki/JSONP)
4. [w3schools - JSONP](https://www.w3schools.com/js/js_json_jsonp.asp)