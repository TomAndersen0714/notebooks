# Linux命令行常用命令


## Text


### alias

option:
`-p`: 打印所有配置的alias
example: 
```bash
# 设置别名命令
`alias kd='kubectl -n dev-lane'`

# 取消别名命令
unalias kd
```

### id


## Network


### ifconfig


### hostname

```bash
# 打印当前主机的hostname
hostname

# 修改主机hostname
hostname <new_name>

# 打印当前主机的所有网络中的IP
hostname -I
```



## Environment

设置命令行前缀格式
`export PS1='[\u@\h \W]\$ '`




## 参考链接
1. 

