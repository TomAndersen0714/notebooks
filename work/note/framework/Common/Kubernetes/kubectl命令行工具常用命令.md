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

```bash
# Display all the nodes 
kubectl get namespaces

```

### services


### deployments

在 Kubernetes 中，Deployments（部署）是一种用于管理 Pod（容器）副本的资源对象。Deployments 提供了一种声明式的方式来定义和控制 Pod 的创建、更新和删除过程，从而实现应用的部署和扩缩容。

通过 Deployments，可以指定所需的 Pod 副本数量、使用的容器镜像、资源限制等参数。Deployments 会根据指定的配置自动创建和管理 Pod 的副本，并确保在发生故障或扩缩容时进行适当的调整。


### pods


### configmaps



## Command/Operations
https://kubernetes.io/docs/reference/kubectl/#operations

### get

Syntax: `kubectl exec (POD | TYPE/NAME) [-c CONTAINER] [flags] -- COMMAND [args...]`

Example: 
```
# Display all the nodes 
kubectl get nodes

# Display the details of the node with name <node-name>.
kubectl describe nodes <node-name>

kubectl get services query-panel-service -o yaml

kubectl get pods --selector=app=query-panel -o yaml

kubectl describe configmaps conf-query-panel
kubectl get configmaps conf-query-panel -o yaml

kubectl get service query-sdk-service -o yaml

kubectl get pods --selector=app=xdsync
kubectl get pods --selector=app=xdsync -o yaml

kubectl get deployments --selector=app=query-panel
kubectl get configmap conf-query-sdk -o yaml
kubectl get configmaps conf-query-sdk
```


### describe

Syntax: `kubectl describe (-f FILENAME | TYPE [NAME_PREFIX | -l label] | TYPE/NAME)`

Example: 
```bash
kubectl describe pods -l app=query-panel
```


### exec
https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#exec
Execute a command against a container in a pod.

Syntax: `kubectl exec (POD | TYPE/NAME) [-c CONTAINER] [flags] -- COMMAND [args...]`

Example: 
```bash
kubectl exec query-sdk-deploy-7fc6667c66-8mp8q -it -- bash

docker exec -it 691d760d5240 bash
```


### edit

Syntax: `kubectl edit (RESOURCE/NAME | -f FILENAME)`

Example: 
```bash
kubectl edit configmap <configmap_name>
```


## Name



## Flags


### Selector

Syntax: `-l | --selector <label_key=label_value>`

Examples: 
```
kubectl get pods -l app=query-panel -o yaml
kubectl get pods --selector app=query-panel -o yaml
```



### Formatting output
https://kubernetes.io/docs/reference/kubectl/#formatting-output

Syntax: `-o|--output <output_format>`

output_format: json, name, wide, yaml

Examples: 
```
kubectl get pods --selector=app=query-panel -o yaml
```


### Sorting list objects



## 参考链接

1. [Kubectl Reference Docs](https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#-strong-getting-started-strong-)
2. [Command line tool (kubectl)](https://kubernetes.io/docs/reference/kubectl/)

