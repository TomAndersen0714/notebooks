# Windows命令行常用命令

Windows has two command-line shells: the Command shell and [PowerShell](https://learn.microsoft.com/en-us/powershell/scripting/overview). Each shell is a software program that provides direct communication between you and the operating system or application, providing an environment to automate IT operations.

推荐使用 PowerShell，功能强大，且有些命令和 Bash Shell 命令很接近。

## Windows Shell 常用命令

### 通用命令

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

### Command Shell 命令

`cd` ：打开指定路径
`del` ：删除文件
`where` ：查看可执行文件位置
`echo` ：打印指定内容，如打印环境变量：`echo %GOPROXY%`
`D:`：切换到指定磁盘根目录

### PowerShell 命令

`$env` ：打印环境变量，`$env:VariableName`，如：`$env:GOPROXY`

Memory Compression:
对于内存 RAM 资源充足的，不用开启此功能，此功能会额外开销 CPU 资源，反之，则维持默认状态即可。每次调整功能后需要重启才能生效，可以通过**任务管理器-内存**页面，观察已压缩的内存空间大小是否为 0 来判断是否已经成功禁用 Memory Compression。
```bash
# 查看内存压缩功能是否开启
Get-MMAgent

# 禁用内存压缩mc, 调整设置后重启服务器生效
Disable-MMAgent -mc

# 启用内存压缩mc, 调整设置后重启服务器生效
Enable-MMAgent -mc

```

### WSL 命令

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