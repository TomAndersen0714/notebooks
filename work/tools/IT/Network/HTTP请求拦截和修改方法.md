# HTTP 请求拦截和修改方法

## 浏览器

### 手动注入JavaScript代码

基于 JavaScript 脚本语言的特性，修改 JavaScript 内置网络请求方法，注入自定义代码，实现自定义修改 HTTP 请求的功能。

#### JS文件替换

定位接口请求，在 JavaScript 源文件的代码行。

保存并修改浏览器中使用的 js 源文件，然后上传并替换浏览器中的源文件，实现当前页面的 JavaScript 代码注入。

https://blog.csdn.net/m0_67390963/article/details/123344330

#### JS代码执行

直接在 console 控制台执行对应的 JS 命令即可。

### 浏览器插件

#### 油猴

油猴插件，匹配对应 URL 时自动执行自定义的 JS 脚本，通过脚本实现 JS 代码注入
https://blog.dqv5.com/2021/04/04/tampermonkey-request-intercept/

#### ModHeader插件

支持修改 HTTP 请求或返回的 Headers

Chrome
https://chrome.google.com/webstore/detail/modheader-modify-http-hea/idgpnmonknjnojddfkpgkljpfnnfcklj/related?hl=en

Edge
https://microsoftedge.microsoft.com/addons/detail/modheader-modify-http-h/opgbiafapkbbnbnjcdomjaghbckfkglc

## 桌面应用

### Fiddler

Windows

### Charles

Mac OS

## 参考链接

1. [浏览器插件能否做到拦截页面发起的请求进行参数改写再下发](https://segmentfault.com/q/1010000042213586)
2. [chrome浏览器F12调式，修改替换js文件](https://blog.csdn.net/m0_67390963/article/details/123344330)