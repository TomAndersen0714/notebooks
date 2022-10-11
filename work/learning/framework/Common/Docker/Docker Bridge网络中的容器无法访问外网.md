# Docker Bridge网络中的容器无法访问外网

## 前言

### 问题症状

1. 宿主机能访问Docker容器端口

2. Docker容器无法访问宿主机端口

3. 宿主机外部无法访问Docker容器端口
4. Docker以默认的Bridge网络模式启动容器后，`brctl show`依旧显示Docker的默认网桥docker0的bridge id依旧为8000.000000000000



### 系统版本

```shell
[root@cdh0] /# uname -a
Linux cdh0 3.10.0-123.el7.x86_64 #1 SMP Mon Jun 30 12:09:22 UTC 2014 x86_64 x86_64 x86_64 GNU/Linux

[root@cdh0] /# cat /etc/centos-release
CentOS Linux release 7.9.2009 (Core)
```





## 操作步骤

### 方案一：重建Docker默认网桥和网络

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



### 方案二：新建Docker网桥和网络

手动创建另外一个网桥，写好子网网段、子网掩码、广播网段（可选）

docker network创建一个网络，使用相同的网桥名字，子网网段、掩码、网关，即可

后续创建容器，使用`--network=<network_name>`连接对应的docker network即可



### 方案三：升级Linux内核

升级内核，重启服务器

```shell
yum list kernel

yum update kernel

reboot
```





### 使用注意事项

方案一重建docker默认网桥和网络：不需要重建容器，但是需要重启docker服务

方案二新建docker网桥和网络：不需要重启docker服务，但是需要重建容器

方案三升级Linux内核：需要重启服务器



## 总结

即不要使用docker network为你创建的网桥，要先自己手动创建网桥（包括容器的默认网桥），然后指定网桥名称，无需重启Docker

网上部分文档显示是docker加载内核的bridge.ko驱动异常，导致docker network创建网络时，自动创建的网桥无法正常使用



## 参考链接

[Docker网桥模式ping不通宿主机](https://blog.csdn.net/qq_36059826/article/details/106550332)

[centos7中docker网络docker0与容器间网络不通的坑](https://blog.csdn.net/weixin_42288415/article/details/105366176)