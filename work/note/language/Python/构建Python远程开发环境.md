# 构建Python远程开发环境

## 前言

本文主要介绍基于Docker构建Python开发环境教程，便于PyCharm使用SSH连接容器，使用其中的Python解释器以及配置好的开发环境


## 方案一

Docker

### 基本步骤

1. 拉取Python运行基础镜像
此镜像作为后续构建SSH服务器镜像的父镜像
docker pull python:3.6.8-alpine3.10

2. 编写Dockerfile
安装OpenSSH工具，并配置SSH Server
设置容器CMD为运行SSH Server

3. 构建镜像，并上传私有registry


