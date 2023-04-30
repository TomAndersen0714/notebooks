


HTTP请求中，响应头（ResponseHeaders）中的Set-Cookie字段，是用于告知客户端在下次请求中需要将Headers中的cookie字段设置成对应值，主要用于鉴别客户端身份。

HTTP请求中的，payload是指请求消息的主体部分，也就是请求体（RequestBody）。通常情况下，HTTP请求的payload包含了客户端发送给服务器的数据，例如表单数据、JSON数据等。

对于HTTP GET请求，一般请求的参数通常是通过URL中的查询字符串传递的，因此它的payload一般是空的，但HTTP并未强制规定GET请求的payload是否为空，因此GET请求的payload具体是否为空，需要根据需求的具体实现方式来确定。

而对于HTTPPOST、PUT、DELETE等请求，请求的参数通常是通过请求体传递的，它的payload就是请求体的内容。在请求头中会有Content-Type字段指明请求体的类型，例如application/json、application/x-www-form-urlencoded等。

通常情况下，各个语言的HTTP库中，都会有Session对象，用来维持会话状态，会自动维护连续请求的Cookie


