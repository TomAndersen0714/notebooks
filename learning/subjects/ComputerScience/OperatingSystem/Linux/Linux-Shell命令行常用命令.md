# Linux命令行常用命令


## 前言

Shell（Shell 解释器）：Shell 是一种命令行解释器，负责解释和执行用户输入的命令。它是用户与操作系统内核之间的接口。在 Linux 系统中，常见的 Shell 有 Bash（Bourne Again Shell）、Sh（Bourne Shell）、Ksh（Korn Shell）、Csh（C Shell）等。Shell 提供了命令行界面（CLI）来与系统进行交互，并支持执行命令、编写脚本、管道操作、环境变量设置等功能，如 Bash、Sh、ZSH 等。

Terminal（终端）：Terminal 是用户与 Shell 进行交互的界面。它是一个提供可视化输入和输出功能的程序。在 Linux 系统中，Terminal 通常指的是一个模拟终端窗口，也称为终端仿真器。终端窗口可以打开一个 Shell 会话，使用户能够在其中输入命令、执行程序、查看输出结果等。每个终端窗口都会运行一个 Shell 进程，用户通过终端窗口与 Shell 进行交互。主流的 Terminal 工具有 iterm 2、Windows Terminal、on-my-zsh 等，支持远程登录的 Terminal 客户端工具则有 XShell、SecuritySRT 等。

**不同的 Shell 所支持的 Linux 命令不尽相同，本文主要介绍在 Bash Shell 中支持的 Linux 命令**。

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
- `-E` ：按照正则表达式解析 pattern
- `-l` ：仅打印存在匹配上 pattern 的文本对应的文件名
- `-L` ：仅打印未匹配上 pattern 的文件名
- `-h` ：不打印文件名
- `-H` ：打印当前文件名
- `-v` ：剔除包含指定字符串的文本行
- `-o` ：仅打印匹配的文本内容
- `-r` ：递归检索
- `-i` ：忽视大小写
- `-n` ：显示文件中命中文本对应行号
- `-q` ：不打印任何内容，通常用于 IF 判断
- `-C` ：打印匹配行的前后 3 行
- `-A` ：打印匹配行的后 3 行
- `-B` ：打印匹配行的前 3 行


常用命令：
```bash

# 递归检索文件夹中文件内容, 抽取符合指定 pattern 的字符串, 并打印
grep -rohE '\-\-type=[a-zA-Z0-9_]+'

# 递归检索文件夹中文件内容, 打印包含指定字符串的文件
grep -rl "client"
```

#### awk

#### sed

#### alias

常用选项：
- `-p` : 打印所有配置的 alias

常用命令：
```bash
# 设置别名命令
`alias kd='kubectl -n dev-lane'`

# 取消别名命令
unalias kd
```


#### xargs

作用：
- 将标准输入中的内容解析为参数，传递给后续的命令

常用选项：
- `-n` ：切割字符串后，每次执行命令时，传递的参数个数。
	- PS：使用 `xargs -n 1` 命令来换行命令时，会导致每次都会生成新的行命令来执行，效率太低。如果仅仅是需要实现参数切割并按行打印，建议使用 `tr ' ' '\n' | xargs` 命令来进行按空格切分，而不要使用 `xargs -n 1`；
- `-I` ：`-I replace-str` ，设置参数替换字符串，会替换后续命令中的对应字符串为 xargs 解析出的参数；

#### seq

作用：
- 打印某数值范围的递增数值序列，支持整数和浮点数，支持设置步长
- 可以和其他的编辑器（如 VSCode）配合使用，实现批量编辑

示例：
- `seq 1 10`
- `seq 1 2 10`


### File


#### file
查看文件类型


#### ln

常用选项：
-  `-s` ：建立软连接
常用命令：
```bash
ln -s /mnt/c/Users/ericcheng/Desktop desktop
```


#### find

常用选项：
- `-name` ：以指定字符串 pattern 进行检索
- `-iname` ：以指定字符串 pattern 进行非大小写敏感检索

#### tar

常用选项
- `-c, --create`: 创建归档文件
- `-f, --file`: 指定归档文件名
- `-z, --gzip, --gunzip, --ungzip`: 使用 gzip 算法压缩归档文件
- `-v, --verbose`: 打印命令执行过程
- `-x, --extract, --get`: 提取归档文件
- `-u, --update`: 将发生变化的文件增量添加到归档文件中，PS：增量同步并不一定会提高效率，因为

常用命令
- 创建归档文件 `tar -cvf archive.tar /path/to/directory`
- 更新归档文件 `tar -uvf archive.tar /path/to/directory`
- 解压缩 `.tar` 归档文件：`tar -xvf archive.tar`
- 解压缩 `.tar.gz` 归档文件：`tar -xzvf archive.tar.gz`
- 解压缩到指定目录：`tar -xzvf archive.tar.gz -C /path/to/destination`

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

#### cd

`-P` ：进入指定路径的实际路径，而非软链接路径


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

查看 CPU Processor 信息

### 参考链接
1. [Linux命令搜索引擎 命令，Linux Linux命令搜索引擎 命令详解：最专业的Linux命令大全，内容包含Linux命令手册、详解、学习，值得收藏的Linux命令速查手册。 -  Linux 命令搜索引擎](https://wangchujiang.com/linux-command/)
2. [GitHub - jaywcjlove/linux-command: Linux命令大全搜索工具，内容包含Linux命令手册、详解、学习、搜集。https://git.io/linux](https://github.com/jaywcjlove/linux-command)
3. [Linux 命令大全 | 菜鸟教程](https://www.runoob.com/linux/linux-command-manual.html)

