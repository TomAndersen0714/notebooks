# HTTP 传递参数的常见方式

## 请求头（Headers）

参数可以作为 HTTP 请求头的一部分，以键值对的形式传递。常见的请求头参数包括 Content-Type、Authorization 等。

应用场景：用于传递一些额外的元数据或控制信息，如身份认证信息、内容类型等。

## URL查询字符串（Query String）

参数可以作为 URL 的一部分，在 URL 的末尾以? 符号开始，参数之间使用&符号分隔。例如： http://example.com/api?param1=value1&param2=value2

应用场景：常用于传递资源的过滤条件、排序条件、分页信息等参数，支持缓存、可共享和保存链接、具备可见性，是 GET 请求传递参数的最佳实践。

## URL路径参数（Path Parameters）

参数可以作为 URL 的一部分，直接嵌入在路径中。通常在 RESTful 风格的 API 中使用，用于标识资源的唯一标识符或参数。例如： http://example.com/api/resource/{id}。

应用场景：常用于传递表达层级结构、资源唯一标识符等参数。适合于 RESTful 风格的 API。

## 请求体（Request Body）

参数可以作为请求的正文内容，主要用于 POST、PUT 等请求方法。参数通常以表单数据、JSON、XML 等格式进行编码，并通过 Content-Type 指定编码方式。

应用场景：用于传递复杂或大量的数据，如创建、更新资源等操作，是 POST 请求传递参数的最佳实践。