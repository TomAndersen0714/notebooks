# Kubernetes基础教程


## Concepts

### Overview


#### Objects In Kubernetes

https://kubernetes.io/docs/concepts/overview/working-with-objects/#kubernetes-objects

##### Labels and Selectors
https://kubernetes.io/docs/concepts/overview/working-with-objects/labels/

Label是用于标记kubernetes object（如：pods）的键值对，label本身并不具备唯一性，并且通常情况下，kubernetes建议使用相同的Label来标注object。

Kubernetes中的deployment、service等resources，都是通过selector来确定其关联的pod。


### Workloads


#### Pods
https://kubernetes.io/docs/concepts/workloads/pods/

Pods是Kubernetes中最小的可调度计算单元，可以创建和管理pod。

其中`spec.volumes`字段声明了当前pod需要使用的volume的详细信息，如使用的是ConfigMap，那么`spec.volumes.configMap`还会声明对应的ConfigMap信息。而配置中的`.spec.containers[*].volumeMounts`字段则定义了volume在pod中的具体挂载路径。


##### Pod Lifecycle
https://kubernetes.io/docs/concepts/workloads/pods/pod-lifecycle/


#### Workload Resources


##### Deployments
https://kubernetes.io/docs/concepts/workloads/controllers/deployment/


###### Pod Template

### Services, Load Balancing, and Networking

#### Service ClusterIP allocation
https://kubernetes.io/docs/concepts/services-networking/cluster-ip-allocation/

Service是一种为pod提供网络代理的资源，而集群中同一个namespace下的所有的pod能通过DNS解析Service的hostname，并解析出对应的代理IP，即ClusterIP，后续的请求则由Service代理到对应的pod上。

### Storage

#### Volumes
[Kubernetes Documentation/Concepts/Storage/Volumes](https://kubernetes.io/docs/concepts/storage/volumes/)


其中`spec.volumes`字段声明了当前pod需要使用的volume的详细信息，而`.spec.containers[*].volumeMounts`字段则定义了volume在pod中的挂载路径。

通过`volume`来存储配置文件，是pods配置文件管理的常见方式之一。


## 其他

### OpenStack和Kubernetes的区别

云计算服务的种类从下往上，主要可以分为三类，即基础架构即服务（Infrastructure as a Service, IaaS）、平台即服务（Platform as a Service）、和软件即服务（Software as a Service）。

云计算主要分为四种类型，私有云、公有云、混合云和多云。
https://www.redhat.com/zh/topics/cloud-computing/public-cloud-vs-private-cloud-and-hybrid-cloud

OpenStack是基于Linux的IaaS（Infrastructure as a Service）云服务的解决方案，而Kubernetes是PaaS（Platform as a Service）云服务的解决方案。

简单来说，OpenStack能够将多个物理主机虚拟成一个整体，即云（Cloud），并在此基础上提供各种虚拟机服务，可以实现主机（Host）级别的资源池化和资源隔离。

而Kubernetes则是将多个主机（物理或者虚拟）虚拟成一个整体，并在此基础上实现应用软件的快速部署，彼此之间相互隔离，可以实现应用（Application）级别的资源池化和资源隔离。

PS：虚拟化技术的主要作用，个人认为主要两点就是，1是资源池化，灵活分配，提高资源利用率，2是资源隔离，提高服务质量。


### OpenStack和VMware vSphere的区别

VMware vSphere是一种企业级虚拟化和云计算平台，用于构建和管理大规模的虚拟化基础设施，包括服务器、存储和网络等资源。而VMware WorkStation，是一种面向个人用户和小规模部署的虚拟化工具，，同时也是一款桌面级应用软件，可以直接安装在个人主机上。

OpenStack和VMware vSphere的主要区别在于，OpenStack是开源的分布式云计算平台，而VMware vSphere则是由VMware负责开发和提供的。

两者都是IaaS的解决方案，属于同类型的产品。
https://www.cnblogs.com/sdjnzqr/p/3798990.html

## 参考链接
1. [Kubernetes Documentations](https://kubernetes.io/docs/home/)