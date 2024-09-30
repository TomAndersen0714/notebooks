# 基于Docker搭建Python开发环境

## 前言

本文主要介绍基于Docker构建Python开发环境教程，便于开发环境的分发和快速部署，避免环境的重复安装、实现开发环境统一，以及支持通过IDE进行Python的远程开发。

主要实现思路就是，基于Docker构建一个带有Python环境的SSH服务器。

### 基本步骤

1. 拉取Python运行基础镜像
此镜像作为后续构建SSH服务器镜像的父镜像
docker pull python:3.6.8-alpine3.10

2. 编写Dockerfile，构建新镜像，并创建容器
安装OpenSSH工具，并配置SSH Server
设置容器CMD为运行SSH Server，以及运行Bash

3. 使用PyCharm的SSH Interpreter连接镜像进行开发