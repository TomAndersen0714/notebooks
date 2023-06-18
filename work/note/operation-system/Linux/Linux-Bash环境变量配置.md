# Linux Bash环境变量配置

## 前言

在Linux中，不同的Shell的环境配置文件也是不同的。以Bash（Bourne Again SHell）和Sh（Bourne SHell）为例，虽然Bash可以运行大部分Sh脚本，并且在大多数Linux系统中，默认使用Bash作为默认Shell，但它们加载的环境配置文件是不同的。

如果你使用的是Bash作为默认Shell，那么除了系统级别的配置文件，可能还会执行`~/.bash_profile, ~/.bash_login, and ~/.profile`。

如果你使用的是Sh作为默认Shell，那么只有`~/.profile`会被加载和执行。


## login Shell, non-login shell

**login shell**：
运行 bash 时需要完整的登陆流程的，就称为 login shell。举例来说，你要由 tty1 ~ tty6 登陆，需要输入用户的账号与密码，此时取得的 bash 就称为`login shell`。

**non-login shell**：
运行 bash 时不需要重复的登录举动，如：
1. 在某个Terminal中新建标签页，此时不需要输入账号密码，即运行了一个non-login shell；
2. 又如，在已经运行的 bash 中，再次运行 bash 命令进入新的 bash 命令行，也是属于non-login shell；
3. 又如，直接使用`su <user>`命令切换Linux用户，默认情况下，也是运行一个新的non-login shell。


## login shell读取的环境配置文件


### /etc/profile

`/etc/profile`是Linux系统级别的环境配置文件，每次login shell启动时，都首先会读取并执行此配置文件中的命令。

在某些Linux版本中（如：CentOS 5.x等），`/etc/profile`文件中还会依次读取并执行一下文件中的命令：
1. `/etc/inputrc`
2. `/etc/profile.d/*.sh`：/etc/profile.d文件夹下的所有.sh文件
3. `/etc/sysconfig/i18n`


### ~/.bash_profile, ~/.bash_login, and ~/.profile

login Shell在读取完`/etc/profile`之后，便会按照`~/.bash_profile, ~/.bash_login, and ~/.profile`的顺序读取用户级别的配置文件，并依次选择首个存在的文件读取并执行。

Bash的`--noprofile`选项，可以抑制此动作。

一般情况下`~/.bash_profile`文件中，还会包含类似于`if [ -f ~/.bashrc ]; then . ~/.bashrc; fi`命令，用于读取和执行`~/.bashrc`文件中的命令。


![](resources/images/Pasted%20image%2020230618235539.png)

## non-login shell读取的环境配置文件

### ~/.bashrc

每次non-login shell启动时，都会读取并执行用户级别配置文件`~/.bashrc`中的脚本命令。此行为可以通过`--norc`选项来抑制，也可以通过`--rcfile file`选项来指定其他的文件。

在CentOS，以及Red Hat系统中，`~/.bashrc`还会进一步读取和执行`/etc/bashrc`文件中的命令。其中`/etc/bashrc`文件的命令，除了定义了UMARK值、Bash命令行提示字符（即PS1变量）外，还会去调用`/etc/profile.d/*.sh`文件。

万一你没有~/.bashrc (可能自己不小心将他删除了)文件，那么你会发现你的 bash 提示字符可能会变成这个样子：
```
-bash-3.2$
```

## 参考链接
1. [Bash Reference Manual - Bash Features](https://www.gnu.org/software/bash/manual/html_node/Bash-Startup-Files.html)
2. [《鸟哥的Linux私房菜-基础篇》-Bash shell 的操作环境](http://cn.linux.vbird.org/linux_basic/0320bash_4.php)