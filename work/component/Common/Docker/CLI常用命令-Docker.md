# Docker CLI常用命令


## Container

### View

#### docker info

#### docker ps

#### docker top

#### docker stats

#### docker port


### Manage

#### docker login

#### docker exec


### Modify

#### docker update


### Create

#### docker run


## Image

### View

#### docker images


## Network


### View

#### docker network ls

#### Docker network inspect


### Create

#### docker network create


## 其他

1. root赋予指定用户docker组权限
```
sudo usermod -aG docker <username>
```

## 参考链接
1. [Docker - Use the Docker command line](https://docs.docker.com/engine/reference/commandline/cli/)