# Windows命令行常用命令


## Shell相关命令


### Shell通用

`netsh` ：重置所有网络配置（需要重启主机）`netsh winsock reset`

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


## WSL相关命令


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

## 参考链接
1. [Microsoft-Windows Commands](https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/windows-commands)
2. [PowerShell Documentation](https://learn.microsoft.com/en-us/powershell/)

