# Docker Bridge网络中的容器无法访问外网

## 前言

问题症状

1. 宿主机能访问Docker容器端口

2. Docker容器无法访问宿主机端口

3. 宿主机外部无法访问Docker容器端口
4. Docker以默认Bridge网络模式启动容器后，`brctl show`依旧显示bridge id为8000.000000000000



系统版本

```shell
[root@cdh0] /# uname -a
Linux cdh0 3.10.0-123.el7.x86_64 #1 SMP Mon Jun 30 12:09:22 UTC 2014 x86_64 x86_64 x86_64 GNU/Linux
```





## 操作步骤



### 方案一

systemctl status docker

systemctl stop docker



安装网桥管理工具

yum install bridge-utils

查看网桥

brctl show

关闭网桥

ip link set docker0 down，或ifconfig docker0 down

删除原有网桥

brctl delbr docker0

brctl show

添加网桥docker0

brctl addbr docker0

设置网桥地址和子网掩码（可选）

ip addr add 172.17.0.1/24 dev docker0，或ifconfig docker0 172.17.0.1 netmask 255.255.255.0 broadcast 172.17.0.255

ifconfig docker2 172.25.0.1 netmask 255.255.255.0 broadcast 172.25.0.255

开启网桥

ip link set dev docker0 up，或ifconfig docker0 up

启动Docker

systemctl start docker



### 方案二

手动创建另外一个网桥，写好子网网段、子网掩码、广播网段

docker network创建一个网络，使用相同的网桥名字，子网网段、掩码、网关，即可

后续创建容器，使用`--network=<network_name>`连接对应的docker network即可



### 方案三

升级内核，重启服务器



### 测试

对比节点正常的网络



## 总结

不要使用docker network为你创建的网桥，要先自己手动创建网桥（包括容器的默认网桥），然后指定网桥名称，无需重启Docker

方案一重建docker默认网络和网桥（不需要重建容器，但是需要重启docker），方案二新建docker网络（不需要重启docker，但是需要重建容器）



## 参考链接

[Docker网桥模式ping不通宿主机](https://blog.csdn.net/qq_36059826/article/details/106550332)