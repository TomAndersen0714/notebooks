# HTTP Method Restful方法规范

GET 方法是幂等的，用于执行不影响服务器数据的操作，通过 URI 传参，即 URL 查询字符串（Query String）、URL 路径参数（Path Parameters）两种方式。

POST 方法是非幂等的，用于执行会影响服务器数据的操作，常用于新增数据，通过 Request Body 传参，常见格式是 form-data、application/json 等。

DELETE 方法是非幂等的，且仅用于删除的，通过 URI 传参中的 URL 路径参数（Path Parameters）。