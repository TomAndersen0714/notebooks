# Linux Shell 脚本开发基础教程


## 开发


## 调试


### VSCode

VSCode 中主要通过 `Bash Debugger` 插件集成 `bashdb` 来支持 Shell 调试功能。

注意：
1. 在 Windows 环境下需要预先安装 WSL 和 Ubuntu，否则无法运行 bashdb。PS：WSL 安装详情参考 [WSL基础教程](learning/subjects/Computer/Operating-System/Windows/WSL/WSL基础教程.md)

添加 Run/Debug 配置文件步骤：
1. 打开对应的 Shell 脚本文件，点击 `Run | Add Configuration`，然后选择默认的 `bashdb` 配置模板

推荐配置如下：
```json
{
	"type": "bashdb",
	"request": "launch",
	"name": "Bash-Debug (Current File)",
	"cwd": "${workspaceFolder}",
	"program": "${file}",
	"args": ["language/Shell/test_project/wtss/jobs", "3"]
},
```
其中参数解释如下：
1. `name` ：指定当前配置文件的名称，显示在 `Run and Debug` 工具栏下
2. `cwd` ：指定运行时的 work directory，即当前工作目录要根据实际情况自己手动调整
3. `args` ：指定运行时传入对应脚本的参数，需要自己针对不同的脚本文件手动修改此运行配置文件中的参数来指定

常用快捷键：
1. `F5` ：继续执行，直到下一个断点 breakpoint
2. `F10` ：继续执行下一条语句

参考：
https://liushiming.cn/article/debug-bash-on-macos.html
https://zbttl-github-io.vercel.app/vscode-diao-shi-bash-shell-jiao-ben/
https://blog.csdn.net/babytiger/article/details/119937537

### IDEA

IDEA 中主要通过 `BashSupport Pro` 插件的调试功能来实现 Shell 脚本调试：
https://cloud.tencent.com/developer/ask/sof/108254071


## 常见问题

### xargs

使用 `xargs -n 1` 命令来换行时执行速度太慢，其中 `-n 1` 参数会严重影响 Shell 程序性能，建议使用 `tr ' ' '\n\n' | xargs` 命令替换。

## 参考链接
1. https://chetaofeng.github.io/2018/12/19/Linux-Shell%E8%84%9A%E6%9C%AC%E8%B0%83%E8%AF%95/