# gRPC基础教程



## Overview

gRPC是Google开源的一个RPC框架。

### Protocol Buffers

安装Protocol Buffers
[Protocol Buffers基础教程](work/framework/Common/Serialization/Protocol-Buffers.md)


### Service Definition



### Using the API


### Synchronous and asynchronous


### Metadata


### Channels


## RPC life cycle


### RPCs


#### Unary RPCs


#### Server streaming RPCs


#### Client streaming RPCs


#### Bidirectional streaming RPCs


### Deadlines/Timeouts


### RPC termination


### Cancelling an RPC



## Supported languages/platforms



### Java

https://grpc.io/docs/languages/java/quickstart/

在编译代码之前，记得切换到指定tag，而不是直接使用master分支，master分支代码并不稳定，属于未发布版本，仅针对开发者使用，一般子项目依赖之间都或多或少存在的版本兼容问题，无法正常编译。

官方建议使用Gradle



### Python


### C++


### JavaScript

Node

Web
gRPC-Web是用于在浏览器中使用的gRPC客户端框架，主要通过HTTP/1.1或WebSocket与gRPC服务进行通信。



## 参考链接
1. [gRPC Documentation](https://grpc.io/docs/)