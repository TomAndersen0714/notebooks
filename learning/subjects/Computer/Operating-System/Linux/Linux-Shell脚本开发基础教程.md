# Linux Shell 脚本开发基础教程


## 开发


## 调试


### VSCode

VSCode 中主要通过 `Bash Debugger` 插件集成 `bashdb` 来支持 Shell 调试功能：
https://liushiming.cn/article/debug-bash-on-macos.html
https://zbttl-github-io.vercel.app/vscode-diao-shi-bash-shell-jiao-ben/
https://blog.csdn.net/babytiger/article/details/119937537

同时需要注意的是在 Windows 环境下需要先安装 WSL 和 Ubuntu，WSL 安装详情参考 [WSL基础教程](learning/subjects/Computer/Operating-System/Windows/WSL/WSL基础教程.md)

### IDEA

IDEA 中主要通过 `BashSupport Pro` 插件对应功能：
https://cloud.tencent.com/developer/ask/sof/108254071


## 常见问题

### xargs

使用 `xargs -n 1` 命令来换行以此执行速度太慢，建议使用 `tr ' ' '\n\n' | xargs` 命令替换。

## 参考链接
1. https://chetaofeng.github.io/2018/12/19/Linux-Shell%E8%84%9A%E6%9C%AC%E8%B0%83%E8%AF%95/