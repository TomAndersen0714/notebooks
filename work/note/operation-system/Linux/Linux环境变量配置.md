# Linux环境变量配置


## 系统级别


### /etc/environment


### /etc/profile.d

`/etc/profile`会调用`/etc/profile.d`路径下的每个脚本



## 用户级别

### ~/.bash_profile

以登录的方式打开的shell，即登录Shell（login shell），需要加载的配置文件。当用户登录时，首先会执行~/.bash_profile文件。

它通常用于设置用户级别的环境变量、启动会话相关的服务和执行与登录相关的任务。该文件只在用户登录时执行一次，它的作用是初始化登录会话的环境。

在登录shell执行完~/.bash_profile后，Shell会进一步执行~/.bashrc文件。

### ~/.bashrc

打开非登录Shell（non-login shell）时，需要加载的配置文件。它通常用于设置用户级别的别名、函数、路径和其他自定义设置。

~/.bashrc文件在每次打开新的Terminal，或启动新的非登录shell时都会执行，它的作用是配置用户的Terminal环境。

