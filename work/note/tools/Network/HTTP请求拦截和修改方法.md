# HTTP请求拦截和修改方法


## 浏览器

### 手动注入JavaScript代码

基于JavaScript脚本语言的特性，修改JavaScript内置网络请求方法，注入自定义代码，实现自定义修改HTTP请求的功能。

#### JS文件替换

定位接口请求，在JavaScript源文件的代码行。

保存并修改浏览器中使用的js源文件，然后上传并替换浏览器中的源文件，实现当前页面的JavaScript代码注入。

https://blog.csdn.net/m0_67390963/article/details/123344330

#### JS代码执行

直接在console控制台执行对应的JS命令即可。


### 浏览器插件

#### 油猴

油猴插件，匹配对应URL时自动执行自定义的JS脚本，通过脚本实现JS代码注入
https://blog.dqv5.com/2021/04/04/tampermonkey-request-intercept/


#### ModHeader插件

支持修改HTTP请求或返回的Headers

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
