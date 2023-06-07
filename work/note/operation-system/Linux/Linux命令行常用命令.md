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


## Environment

设置命令行前缀格式
`export PS1='[\u@\h \W]\$ '`



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

## Disk

### lsblk
查看磁盘信息

## Other


### lshw
查看主机硬件配置

```bash
# 打印硬件详细信息
lshw

# 打印硬件路径, 以及简短信息
lshw --short

# 查看内存信息
lshw -short -class memory

# 查看处理器信息
lshw -short -class processor
```

### lscpu
查看Processor信息

## 参考链接
1. 

