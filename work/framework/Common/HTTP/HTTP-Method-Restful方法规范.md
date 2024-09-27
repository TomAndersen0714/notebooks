# HTTP Method Restful方法规范

GET 方法是幂等的，用于执行查询请求，通过 URI 传参，即 URL 查询字符串（Query String）、URL 路径参数（Path Parameters）两种方式。

POST 方法是非幂等的，一般用于新增数据，通过 Request Body 传参，常见格式是 form-data、application/json 等。但是当参数过多，无法通过 GET 方法的 URI 进行传输时，则可以通过 Post 方法的 Request Body 来传输参数，即 Post-as-Get。

DELETE 方法是非幂等的，且仅用于删除的，通过 URI 传参中的 URL 路径参数（Path Parameters）。