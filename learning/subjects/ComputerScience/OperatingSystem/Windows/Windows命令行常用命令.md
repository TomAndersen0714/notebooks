# Windows命令行常用命令


## Windows Shell 常用命令

### 常用命令

`netsh` ：重置所有网络配置（需要重启），常用于网络连接突然中断或无法连接到互联网:
```bash
netsh winsock reset
```

`netstat` ：查看 TCP/IP 网络连接（端口占用）情况
```bash
-a: 显示所有的连接和侦听端口
-n: 以数字的形式显示地址和端口号
-o: 显示拥有的与每个连接关联的进程ID

常用命令:
netstat -ano | findstr "8080"
netstat -ano | findstr "3306"
```

### cmd Shell

`cd` ：打开指定路径
`del` ：删除文件
`where` ：查看可执行文件位置
`echo` ：打印指定内容，如打印环境变量：`echo %GOPROXY%`


### PowerShell

`$env` ：打印环境变量，`$env:VariableName`，如：`$env:GOPROXY`


### WSL 常用命令


WSL 查看系统版本：
```bash
Wslfetch
```

WSL 查看当前版本：
```bash
wsl -l -v
```

WSL 停止或启动：
```bash
# 停止
net stop LxssManager
wsl --shutdown

# 启动
net start LxssManager
```

WSL 设置默认启动版本：
```bash
wsl --set-default-version 2
```

WSL 升级更新：
```bash
wsl --update
```

## Windows 批处理脚本

在 Windows 批处理脚本（通常以 `.bat` 或 `.cmd` 为扩展名）中，你可以使用 Windows 命令组合来实现各种操作。

这些命令通常是针对 Windows 操作系统的。一些常见的 Windows 命令包括 dir（列出目录内容）、copy（复制文件）、del（删除文件）、echo（打印消息）等等。


## 参考链接
1. [Microsoft-Windows Commands](https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/windows-commands)
2. [PowerShell Documentation](https://learn.microsoft.com/en-us/powershell/)
3. [WiKi - Windows Batch Scripting](https://en.wikibooks.org/wiki/Windows_Batch_Scripting)

