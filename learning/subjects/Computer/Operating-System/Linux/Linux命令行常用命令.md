# Linux命令行常用命令

## 前言

Shell（Shell解释器）：Shell是一种命令行解释器，负责解释和执行用户输入的命令。它是用户与操作系统内核之间的接口。在Linux系统中，常见的Shell有Bash（Bourne Again Shell）、Sh（Bourne Shell）、Ksh（Korn Shell）、Csh（C Shell）等。Shell提供了命令行界面（CLI）来与系统进行交互，并支持执行命令、编写脚本、管道操作、环境变量设置等功能，如Bash、Sh、ZSH等。

Terminal（终端）：Terminal是用户与Shell进行交互的界面。它是一个提供可视化输入和输出功能的程序。在Linux系统中，Terminal通常指的是一个模拟终端窗口，也称为终端仿真器。终端窗口可以打开一个Shell会话，使用户能够在其中输入命令、执行程序、查看输出结果等。每个终端窗口都会运行一个Shell进程，用户通过终端窗口与Shell进行交互。主流的Terminal工具有iterm2、Windows Terminal、on-my-zsh等，支持远程登录的Terminal客户端工具则有XShell、SecuritySRT等。

**不同的Shell所支持的Linux命令不尽相同，本文主要介绍在Bash Shell中支持的Linux命令**。


## Bash常用命令

### Environment

设置PS1变量，即命令行前缀提示字符的格式，此类命令通常会被放置在：
`export PS1='[\u@\h \W]\$ '`


### Text


#### alias

option:
`-p`: 打印所有配置的alias
example: 
```bash
# 设置别名命令
`alias kd='kubectl -n dev-lane'`

# 取消别名命令
unalias kd
```

#### id

### File


#### file
查看文件类型


### Network


#### ifconfig


#### hostname

```bash
# 打印当前主机的hostname
hostname

# 修改主机hostname
hostname <new_name>

# 打印当前主机的所有网络中的IP
hostname -I
```

### User


### Memory

关闭 swap：`sudo swapoff -a`
开启 swap：`sudo swapoon -a`

### Disk

#### lsblk
查看磁盘信息

### Other


#### lshw
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

#### lscpu
查看Processor信息

### 参考链接
1. https://wangchujiang.com/linux-command/
2. https://github.com/jaywcjlove/linux-command

