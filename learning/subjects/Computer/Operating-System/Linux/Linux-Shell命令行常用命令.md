# Linux命令行常用命令

## 前言


Shell（Shell解释器）：Shell是一种命令行解释器，负责解释和执行用户输入的命令。它是用户与操作系统内核之间的接口。在Linux系统中，常见的Shell有Bash（Bourne Again Shell）、Sh（Bourne Shell）、Ksh（Korn Shell）、Csh（C Shell）等。Shell提供了命令行界面（CLI）来与系统进行交互，并支持执行命令、编写脚本、管道操作、环境变量设置等功能，如Bash、Sh、ZSH等。

Terminal（终端）：Terminal是用户与Shell进行交互的界面。它是一个提供可视化输入和输出功能的程序。在Linux系统中，Terminal通常指的是一个模拟终端窗口，也称为终端仿真器。终端窗口可以打开一个Shell会话，使用户能够在其中输入命令、执行程序、查看输出结果等。每个终端窗口都会运行一个Shell进程，用户通过终端窗口与Shell进行交互。主流的Terminal工具有iterm2、Windows Terminal、on-my-zsh等，支持远程登录的Terminal客户端工具则有XShell、SecuritySRT等。

**不同的Shell所支持的Linux命令不尽相同，本文主要介绍在Bash Shell中支持的Linux命令**。

## Linux 命令在线文档

**[Linux 命令在线搜索](https://wangchujiang.com/linux-command/)**

## Bash 常用命令


### Environment


#### export

设置PS1变量，即命令行前缀提示字符的格式，此类命令通常会被放置在：
`export PS1='[\u@\h \W]\$ '`


#### set

Set 命令作用主要是显示系统中已经存在的 shell 变量，以及设置 shell 变量的新变量值。

常用选项：
- `-a` ：将已定义的变量，添加为环境变量
- `-e` ：若指令传回值不等于 0，则立即退出 shell。
- `-x` ：执行指令后，会先显示该指令及所下的参数。
- ` +<参数> ` ：取消某个 set 曾启动的特性。


### Text

#### grep

常用选项：
- `-l` ：仅打印名称
- `-v` ：剔除包含指定字符串的文本
- `-E` ：按照正则表达式解析 pattern
- `-r` ：递归检索
- `-i` ：忽视大小写
- `-n` ：显示文件中命中文本对应行号


常用命令：
```bash


```

#### awk

#### sed

#### alias

常用选项：
`-p` : 打印所有配置的 alias

常用命令：
```bash
# 设置别名命令
`alias kd='kubectl -n dev-lane'`

# 取消别名命令
unalias kd
```


#### xargs

作用：
将标准输入中的内容解析为参数，传递给后续的命令

常用选项：
`-n` ：
	切割字符串后，每次执行命令时，传递的参数个数。PS：使用 `xargs -n 1` 命令来换行命令时，会导致每次都会生成新的行命令来执行，效率太低。如果仅仅是需要实现参数切割并按行打印，建议使用 `tr ' ' '\n\n' | xargs` 命令来进行按空格切分，而不要使用 `xargs -n 1`；
`-I` ：`-I replace-str` 
	设置参数替换字符串，会替换后续命令中的对应字符串为 xargs 解析出的参数；


### File


#### file
查看文件类型


#### ln



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


#### id



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
1. [Linux 命令在线搜索](https://wangchujiang.com/linux-command/)
2. https://github.com/jaywcjlove/linux-command

