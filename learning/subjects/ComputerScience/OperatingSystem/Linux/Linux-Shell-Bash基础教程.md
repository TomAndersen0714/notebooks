# Linux Bash Shell基础教程


## Shell 的分类

### login shell

运行 Shell 时需要完整的登陆流程的，就称为 login shell。举例来说，你要由 tty1 ~ tty6 登陆，需要输入用户的账号与密码，此时取得的 bash 就称为 `login shell`。

### non-login shell

运行 Shell 时不需要重复的登录举动，如：
1. 在某个Terminal中新建标签页，此时不需要输入账号密码，即运行了一个non-login shell；
2. 又如，在已经运行的 bash 中，再次运行 bash 命令进入新的 bash 命令行，也是属于non-login shell；
3. 又如，直接使用`su <user>`命令切换Linux用户，默认情况下，也是运行一个新的non-login shell。

### interactive shell

interactive（交互式）：顾名思义就是 shell 与用户存在交互，用户登录后，在终端上输入命令，shell 立即执行用户提交的命令。当用户退出后，shell 也终止了。

### non-interactive shell

non-interactive（非交互式）：即 shell 与用户不存在交互，而是以 shell script 的方式执行的，这类Shell在启动时，除了加载对应的配置文件外，还会读取环境变量`BASH_ENV`，并在运行脚本之前执行其中的命令。

## Bash Shell

[Linux-Shell-Bash环境变量配置基础教程](learning/subjects/ComputerScience/OperatingSystem/Linux/Linux-Shell-Bash环境变量配置基础教程.md)

## 参考链接
1. [Bash Reference Manual - Bash Features](https://www.gnu.org/software/bash/manual/html_node/Bash-Startup-Files.html)
2. [《鸟哥的Linux私房菜-基础篇》-第十一章、认识与学习 BASH](http://cn.linux.vbird.org/linux_basic/0320bash.php)
3. [《鸟哥的Linux私房菜-基础篇》-Bash shell 的操作环境](http://cn.linux.vbird.org/linux_basic/0320bash_4.php)
4. [《鸟哥的 Linux 私房菜-基础篇》-第三部分：学习 Shell 与 Shell scripts](http://cn.linux.vbird.org/linux_basic/linux_basic.php#part3)
