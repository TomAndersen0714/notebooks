# 浏览器同源策略Same-origin policy


## 简介

同源策略（Same-origin policy, CORS policy）是一种由浏览器实施的安全策略，仅存在于浏览器中。“同源策略”是浏览器为了保护用户的安全而采取的措施，目的是限制来自不同源的脚本在网页上的交互。浏览器在加载网页时，会执行同源策略来防止跨域请求和潜在的安全风险。

在浏览器中，“同源策略”通过检查脚本、Cookie、DOM访问和XMLHttpRequest等操作的来源和目标，判断它们**URL是否具有相同的协议、主机和端口，如果三者相同则视为同源，反之则不同源**。如果请求不符合同源规则，浏览器会阻止其执行。

需要注意的是，“同源策略”一般只存在于浏览器中，是浏览器对网页执行的一种安全策略。而在其他环境中，如服务器端、移动应用程序等，通常不存在同源策略的限制，因为这些环境的执行环境和安全模型不同于浏览器。因此，当开发跨域请求或其他跨环境操作时，需要考虑不同环境的限制和安全性。


## 浏览器支持实现跨域请求的常见方法


### CORS Response Headers


### JSONP


### 代理服务器


### WebSocket



## 参考链接
1. [wiki - Same-origin policy](https://en.wikipedia.org/wiki/Same-origin_policy)
2. [MDN - CORS](https://developer.mozilla.org/en-US/docs/Glossary/CORS)
3. [wiki - JSONP](https://en.wikipedia.org/wiki/JSONP)
4. [w3schools - JSONP](https://www.w3schools.com/js/js_json_jsonp.asp)