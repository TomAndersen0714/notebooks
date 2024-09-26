# HTTP Body 内容格式

HTTP Request Body 中的格式和长度，分别通过 Content-Type header 和 content-length header 来声明。

Content-Type 常见值如下。
## form-data

**Content-Type**：`multipart/form-data`
**用途**：form-data 主要用于上传文件或发送包含文本和文件的表单数据。它允许用户通过 HTTP 请求发送键值对，其中值可以是文本（text）或文件（file）。
**示例**：常见的应用场景是文件上传，比如上传图片、视频等。每个字段都有一个名称，可以设置为键值对形式。

## x-www-form-urlencoded

**Content-Type**：`application/x-www-form-urlencoded`
**用途**：`x-www-form-urlencoded` 用于发送表单数据，数据以键值对的形式进行编码。它通常用于提交简单的表单数据，如登录表单、搜索表单等。
**示例**：可以将表单数据编码为键值对的形式，以便服务器可以轻松解析。

## raw

**Content-Type**：可以是多种类型，如 `application/json`、`text/plain` 等。
**用途**：raw 选项允许直接发送原始数据，如 JSON、XML 或纯文本。它适用于发送自定义格式的数据，如 JSON 对象、XML 文档等。
**示例**：可以根据需要选择不同的内容类型，比如发送 JSON 对象或纯文本数据。

## binary

**Content-Type**：通常是 `application/octet-stream`，表示二进制数据。
**用途**：binary 选项用于发送二进制文件，如图片、PDF 等。它适用于发送不需要经过编码的二进制数据。
**示例**：可以直接将文件作为二进制数据发送，而无需进行编码处理。

## 参考链接

1. [理解并使用 form-data：在网络通信中传输表单数据的基础](https://apifox.com/apiskills/what-is-formdata/)
2. [Postman 的 Body 中的 form-data、x-www-form-urlencoded、raw、binary、GraphOL 的区别](https://apifox.com/apiskills/difference-between-formdata-xwwwformurlencoded-raw-binary-and-graphol-in-postman/)
3. [POST - HTTP | MDN](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/POST)
4. [Http Body 的四种格式\_请求体格式-CSDN博客](https://blog.csdn.net/oneby1314/article/details/113406924)
5. [http的请求体body的几种数据格式\_使用bodybinary发送中文表单数据-CSDN博客](https://blog.csdn.net/qq_41063141/article/details/101505956)