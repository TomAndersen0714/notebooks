# Docker镜像打包上传基础教程

## 前言

本文主要简单介绍一下，docker打包镜像，并上传到指定镜像仓库，并在目标节点拉取的过程



## 基本流程

**示例仓库地址和对应账号，本次示例的私有仓库，主要是使用**

| **URL**                                  | **username** | **password** |
| ---------------------------------------- | ------------ | ------------ |
| registry.cn-hangzhou.aliyuncs.com        | testuser     | testpassword |
| registry-vpc.cn-zhangjiakou.aliyuncs.com | testuser     | testpassword |

本次实验中，registry.cn-hangzhou.aliyuncs.com是内网环境访问镜像仓库地址，registry-vpc.cn-zhangjiakou.aliyuncs.com是云主机环境环境镜像仓库地址，两者设置了自动同步，如果是同一环境，则使用相同url即可



### docker login

在本地节点登录远程仓库，输入密码：testpassword

```Shell
sudo docker login --username=testuser registry-vpc.cn-zhangjiakou.aliyuncs.com
```



### docker tag

在本地节点给镜像文件打标签

```Shell
sudo docker tag apachepulsar/pulsar:2.8.4 registry-vpc.cn-zhangjiakou.aliyuncs.com/bigdata-pulsar:2.8.4
```

或

```shell
sudo docker tag 69c9156407e9 registry-vpc.cn-zhangjiakou.aliyuncs.com/bigdata-pulsar:2.8.4
```



### docker push

在本地节点上传指定镜像到当前登录仓库

```Shell
sudo docker push registry-vpc.cn-zhangjiakou.aliyuncs.com/bigdata-pulsar:2.8.4
```



### docker pull

在目标节点拉取指定镜像

```Shell
sudo docker pull registry-vpc.cn-zhangjiakou.aliyuncs.com/bigdata-pulsar:2.8.4
```



### docker logout

注销登录

## 示例

在上传镜像之前，必须要先登录对应仓库，否则默认拉取配置仓库，如：

```
sudo docker login --username=testuser registry.cn-hangzhou.aliyuncs.com
```



下面示例主要的执行过程是：

1. 获取git tag, commit号，作为镜像的tag
2. 通过镜像编译服务
3. 通过Dockerfile构建镜像
4. 给本地镜像打上新的标签，用于在远程仓库存储
5. push镜像到远程仓库

```Shell
#!/bin/bash

SERVER_NAME=robotserver-tb   # 服务名
VERSION=$(git describe --tags 2>/dev/null)
COMMIT=$(git rev-parse --short HEAD)
TIME=$(date +%FT%T)
if [[ -z ${VERSION} ]]; then
    VERSION=${COMMIT}
fi

BUILD_IMG_NAME=go_build

docker stop $BUILD_IMG_NAME || echo "skip stop $BUILD_IMG_NAME"
docker rm -f $BUILD_IMG_NAME || echo "skip stop $BUILD_IMG_NAME"echo "pack ..."echo "build ..."

docker run -v $(pwd):/go \
  -w /go \
  --name $BUILD_IMG_NAME \
  registry.cn-hangzhou.aliyuncs.com/centos7.6 \
  /bin/bash -c "export GO111MODULE=on && pwd && ls && env GOOS=linux GOARCH=amd64 go build -mod=vendor  -o ${SERVER_NAME} && echo \"---build success---\" && exit 0 || echo \"---build failed---\" && exit 1"

docker_build_state=$?
if [[ ${docker_build_state} -ne 0 ]]; then
  echo "build failed"
  exit 1
fi

docker rmi ${SERVER_NAME}:${VERSION} --force || echo "skip docker rmi ${SERVER_NAME}:${VERSION}"

docker build -f ./Dockerfile -t ${SERVER_NAME}:${VERSION} ./

docker_build_state=$?
if [[ ${docker_build_state} -ne 0 ]]; then
  echo "pack failed"
  exit 1
fiecho "build done"echo "push to image hub"

docker tag ${SERVER_NAME}:${VERSION} registry.cn-hangzhou.aliyuncs.com/${SERVER_NAME}:${VERSION}

docker push registry.cn-hangzhou.aliyuncs.com/${SERVER_NAME}:${VERSION}

docker_build_state=$?
if [[ ${docker_build_state} -ne 0 ]]; then
  echo "push to registry failed"
  exit 1
fiecho "push to image done"echo "pack done"
```

## 参考链接

1. [docker docs - docker login](https://docs.docker.com/engine/reference/commandline/login/)

2. [docker docs - Deploy a registry server](https://docs.docker.com/registry/deploying/)

