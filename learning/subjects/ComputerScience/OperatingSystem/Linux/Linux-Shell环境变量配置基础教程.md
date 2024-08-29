# Linux Shell 环境变量配置基础教程


## 前言

在 Linux 中，不同的 Shell 的环境配置文件也是不同的。以 Bash（Bourne Again SHell）和 Sh（Bourne SHell）为例，虽然 Bash 可以运行大部分 Sh 脚本，并且在大多数 Linux 系统中，默认使用 Bash 作为默认 Shell，但它们加载的环境配置文件是不同的。

如果你使用的是 Bash 作为默认 Shell，那么除了系统级别的配置文件，可能还会执行 `~/.bash_profile, ~/.bash_login, and ~/.profile`。

如果你使用的是 Sh 作为默认 Shell，那么只有 `~/.profile` 会被加载和执行。


## login shell, non-login shell

**login shell**：
运行 bash 时需要完整的登陆流程的，就称为 login shell。举例来说，你要由 tty 1 ~ tty 6 登陆，需要输入用户的账号与密码，此时取得的 bash 就称为 `login shell`。

**non-login shell**：
运行 bash 时不需要重复的登录举动，如：
1. 在某个 Terminal 中新建标签页，此时不需要输入账号密码，即运行了一个 non-login shell；
2. 又如，在已经运行的 bash 中，再次运行 bash 命令进入新的 bash 命令行，也是属于 non-login shell；
3. 又如，直接使用 `su <user>` 命令切换 Linux 用户，默认情况下，也是运行一个新的 non-login shell。


## login shell读取的环境配置文件

![](resources/images/Pasted%20image%2020230618235539.png)

### /etc/profile

`/etc/profile` 是 Linux 系统级别的环境配置文件，每次 login shell 启动时，都首先会读取并执行此配置文件中的命令。

在某些 Linux 版本中（如：`CentOS 5.X` 等），`/etc/profile` 文件中还会依次读取并执行一下文件中的命令：
1. `/etc/inputrc`
2. `/etc/profile.d/*.sh`：/etc/profile. D 文件夹下的所有. Sh 文件
3. `/etc/sysconfig/i18n`


### ~/.bash_profile, ~/.bash_login, and ~/.profile

login Shell 在读取完 `/etc/profile` 之后，便会按照 `~/.bash_profile, ~/.bash_login, and ~/.profile` 的顺序读取用户级别的配置文件，并依次选择首个存在的文件读取并执行。

Bash 的 `--noprofile` 选项，可以抑制此动作。

一般情况下 `~/.bash_profile` 文件中，还会包含类似于 `if [ -f ~/.bashrc ]; then . ~/.bashrc; fi` 命令，用于读取和执行 `~/.bashrc` 文件中的命令。


## non-login shell读取的环境配置文件

### ~/.bashrc

每次 non-login shell 启动时，都会读取并执行用户级别配置文件 `~/.bashrc` 中的脚本命令。此行为可以通过 `--norc` 选项来抑制，也可以通过 `--rcfile file` 选项来指定其他的文件。

在 CentOS，以及 Red Hat 系统中，`~/.bashrc` 还会进一步读取和执行 `/etc/bashrc` 文件中的命令，而其他的 Linux 发行版中（如 Ubuntu），通常是通过 `/etc/profile` 文件来读取的。其中 `/etc/bashrc` 文件的命令，除了定义了 UMARK 值、Bash 命令行提示字符（即 PS 1 变量）外，还会去调用 `/etc/profile.d/*.sh` 文件。

如果缺少 `~/.bashrc` 文件 (可能自己不小心删除了) ，那么你会发现你的 bash 提示字符可能会变成这个样子：
```
-bash-3.2$
```


## 查看环境变量

```bash

# 列出所有当前环境变量及其值。
printenv
printenv PATH
# 列出所有当前环境变量及其值
env
# `set` 命令不仅会列出环境变量，还会列出所有Shell变量和函数。
set
# 列出所有当前环境变量及其值
export -p
```

## 配置环境变量

1. 如果是设置系统级别环境变量（针对所有用户生效），建议以 `.sh` 脚本文件的形式，添加到 `/etc/profile.d/*.sh` 中，支持可插拔。
2. 如果是设置用户级别环境变量，建议添加到 `~/.bashrc` 文件中。

## 参考链接

1. [Bash Reference Manual - Bash Features](https://www.gnu.org/software/bash/manual/html_node/Bash-Startup-Files.html)
2. [《鸟哥的Linux私房菜-基础篇》-Bash shell 的操作环境](http://cn.linux.vbird.org/linux_basic/0320bash_4.php)