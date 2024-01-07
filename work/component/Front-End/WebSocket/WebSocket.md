# WebSocket基础教程

## 简介

WebSocket是一种基于TCP的通信协议，它提供了全双工、实时、双向通信的能力。

WebSocket通常用于实现实时性要求较高的应用场景，如实时聊天、实时数据推送等。

WebSocket可以在客户端和服务器之间建立长连接，使得双方可以随时发送和接收数据，而不需要通过不断发起HTTP请求来实现通信。



## WebSocket Server

常用的WebSocket服务框架有很多，以下是一些常见的框架：

Java：

1. Java API for WebSocket (JSR 356)：Java 官方提供的原生 WebSocket API，可用于创建 WebSocket 服务器和客户端。
2. Tomcat WebSocket：Apache Tomcat 中内置的 WebSocket 实现，可用于创建 WebSocket 服务器。
3. Jetty WebSocket：Eclipse Jetty 中内置的 WebSocket 实现，可用于创建 WebSocket 服务器。
4. Spring WebSocket：Spring 框架提供的 WebSocket 支持，可以与 Spring 应用程序集成。

Node.js：
1. Socket. IO：一个流行的实时应用程序框架，支持 WebSocket 和轮询等多种实时通信方式。
2. ws：一个简单且高性能的 WebSocket 库，可用于创建 WebSocket 服务器和客户端。
3. uWebSockets. Js：一个快速且可扩展的 WebSocket 库，基于 uWebSockets C++库的 Node. Js 封装。

Python：
1. Flask-SocketIO：基于 Flask 的 WebSocket 库，用于创建 WebSocket 服务器和客户端。
2. Tornado WebSocket：Tornado 框架提供的 WebSocket 支持，可用于创建高性能的 WebSocket 服务器。

Go：
1. Gorilla WebSocket：Go 语言中广受欢迎的 WebSocket 库，提供了灵活且易用的 WebSocket 功能。

这些框架提供了不同语言的实现和各种功能特性，您可以根据自己的需求选择适合的框架进行WebSocket开发。

## WebSocket Client