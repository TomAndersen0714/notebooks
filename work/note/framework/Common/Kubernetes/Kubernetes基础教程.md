# Kubernetes基础教程


其中`spec.volumes`字段声明了当前pod需要使用的volume的详细信息，而`.spec.containers[*].volumeMounts`字段则定义了volume在pod中的挂载路径。

通过`volume`来存储配置文件，是pods配置文件管理的常见方式之一。


## 其他

### OpenStack和Kubernetes的区别

OpenStack是基于Linux的IaaS（Infrastructure as a Service）云服务的解决方案，而Kubernetes是PaaS（Platform as a Service）云服务的解决方案。

简单来说，OpenStack能够将多个物理主机虚拟成一个整体，即云，并在此基础上提供各种虚拟机服务，彼此之间相互隔离。而Kubernetes则是将多个主机（物理或者虚拟）虚拟成一个整体，机云，并在此基础上实现应用软件的快速部署，彼此之间相互隔离。

PS：云计算服务的种类从下往上，主要可以分为三类，即基础架构即服务（Infrastructure as a Service, IaaS）、平台即服务（Platform as a Service）、和软件即服务（Software as a Service）。

PS：云计算主要分为四种类型，私有云、公有云、混合云和多云。
https://www.redhat.com/zh/topics/cloud-computing/public-cloud-vs-private-cloud-and-hybrid-cloud

PS：虚拟化技术的主要作用，个人认为主要就是整合资源，动态分配，资源隔离，提高资源利用率

### VMware WorkStation（VMware vSphere）和OpenStack的区别

两者都是IaaS的解决方案，属于同类型的产品。
https://www.cnblogs.com/sdjnzqr/p/3798990.html

## 参考链接
1. [Kubernetes Documentations](https://kubernetes.io/docs/home/)
2. [Kubernetes Documentation/Concepts/Storage/Volumes](https://kubernetes.io/docs/concepts/storage/volumes/)