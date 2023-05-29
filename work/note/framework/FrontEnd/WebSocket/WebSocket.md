# WebSocket基础教程

## 简介

WebSocket是一种基于TCP的通信协议，它提供了全双工、实时、双向通信的能力。

WebSocket通常用于实现实时性要求较高的应用场景，如实时聊天、实时数据推送等。

WebSocket可以在客户端和服务器之间建立长连接，使得双方可以随时发送和接收数据，而不需要通过不断发起HTTP请求来实现通信。



## WebSocket Server

常用的WebSocket服务框架有很多，以下是一些常见的框架：

Java：

Java API for WebSocket (JSR 356)：Java官方提供的原生WebSocket API，可用于创建WebSocket服务器和客户端。
Tomcat WebSocket：Apache Tomcat中内置的WebSocket实现，可用于创建WebSocket服务器。
Jetty WebSocket：Eclipse Jetty中内置的WebSocket实现，可用于创建WebSocket服务器。
Spring WebSocket：Spring框架提供的WebSocket支持，可以与Spring应用程序集成。
Node.js：

Socket.IO：一个流行的实时应用程序框架，支持WebSocket和轮询等多种实时通信方式。
ws：一个简单且高性能的WebSocket库，可用于创建WebSocket服务器和客户端。
uWebSockets.js：一个快速且可扩展的WebSocket库，基于uWebSockets C++库的Node.js封装。
Python：

Flask-SocketIO：基于Flask的WebSocket库，用于创建WebSocket服务器和客户端。
Tornado WebSocket：Tornado框架提供的WebSocket支持，可用于创建高性能的WebSocket服务器。
Go：

Gorilla WebSocket：Go语言中广受欢迎的WebSocket库，提供了灵活且易用的WebSocket功能。
这些框架提供了不同语言的实现和各种功能特性，您可以根据自己的需求选择适合的框架进行WebSocket开发。


## WebSocket Client