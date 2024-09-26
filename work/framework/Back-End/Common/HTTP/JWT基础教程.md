# JWT基础教程

## 简介

JSON web token (JWT)，发音对应英文单词"jot"，/dʒɑːt/（美），是 `RFC 7519` 中定义的一种开放标准，提供了一种紧凑的、独立的、安全的、以 JSON 对象为载体传递信息的方式。同时，JWT 是一种常用的令牌（Token）协议（标准）之一。

由于 JWT 相对尺寸较小，通常会通过 URL（Query String）、HTTP Body、HTTP Header 等方式进行传输。JWT 中通常会包含所有的必要信息，避免后续还要多次查询数据库，JWT 的接收者也不需要通过调用服务来验证 JWT。

PS：JWT Token 中的签名（Signature），与传统的加密技术领域的数字签名（Sigital Signature）技术不完全相同，最大的区别是 JWT 中的 Signature 可以通过对称加密算法（如：HS 256）来生成。

## JWT Libraries

GoLang：

Jwt-go
https://github.com/golang-jwt/jwt
https://golang-jwt.github.io/jwt/usage/create/

Python：

Pyjwt
https://github.com/jpadilla/pyjwt/
https://pyjwt.readthedocs.io/en/stable/index.html

Authlib
https://github.com/lepture/authlib

## 参考链接
1. [JWT-Introduction](https://jwt.io/introduction)
2. [Auth0-JSON Web Tokens](https://auth0.com/docs/secure/tokens/json-web-tokens)
3. [JSON Web Token](https://en.wikipedia.org/wiki/JSON_Web_Token)
4. [RFC7519](https://datatracker.ietf.org/doc/html/rfc7519)