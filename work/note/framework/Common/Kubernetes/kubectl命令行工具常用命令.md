# kubectl命令行工具常用命令


## Syntax

```
kubectl [command] [TYPE] [NAME] [flags]
```


## Type

https://kubernetes.io/docs/reference/kubectl/#resource-types

### nodes

```bash
# Display all the nodes 
kubectl get nodes

# Display the details of the node with name <node-name>.
kubectl describe nodes <node-name>
```

### namespaces


### services


### deployments

在 Kubernetes 中，Deployments（部署）是一种用于管理 Pod（容器）副本的资源对象。Deployments 提供了一种声明式的方式来定义和控制 Pod 的创建、更新和删除过程，从而实现应用的部署和扩缩容。

通过 Deployments，可以指定所需的 Pod 副本数量、使用的容器镜像、资源限制等参数。Deployments 会根据指定的配置自动创建和管理 Pod 的副本，并确保在发生故障或扩缩容时进行适当的调整。


### pods

### configmaps



## Command


### get

kubectl get services query-panel-service -o yaml

kubectl get pods --selector=app=query-panel -o yaml

其中`spec.volumes`字段声明了当前pod需要使用的volume的详细信息，如使用的是ConfigMap，那么`spec.volumes.configMap`还会声明对应的ConfigMap信息。而配置中的`.spec.containers[*].volumeMounts`字段则定义了volume在pod中的具体挂载路径。

kubectl describe configmaps conf-query-panel
kubectl get configmaps conf-query-panel -o yaml

kubectl get service query-sdk-service -o yaml

kubectl get pods --selector=app=xdsync
kubectl get pods --selector=app=xdsync -o yaml

kubectl get deployments --selector=app=query-panel
kubectl get configmap conf-query-sdk -o yaml
kubectl get configmaps conf-query-sdk


### describe

kubectl describe pods --selector=app=query-panel

### edit

```bash
kubectl edit configmap <configmap_name>
```

## Name



## Flags

`-o`: 支持yaml、json、wide
```
kubectl get pods --selector=<key=value> -o yaml


kubectl get pods --selector=app=query-panel -o yaml
```

`--selector`
```
kubectl get pods --selector=<key=value> -o yaml


kubectl get pods --selector=app=query-panel -o yaml
```


## 参考链接

1. [Kubectl Reference Docs](https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#-strong-getting-started-strong-)
2. [Command line tool (kubectl)](https://kubernetes.io/docs/reference/kubectl/)

