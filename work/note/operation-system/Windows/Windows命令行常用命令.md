# Windows命令行常用命令


## Shell相关命令

### cmd Shell

cd：打开指定路径

del：删除文件

where：查看可执行文件位置

echo：打印指定内容，如打印环境变量：`echo %GOPROXY%`


### PowerShell

\$env：打印环境变量，`$env:VariableName`，如：`$env:GOPROXY`


## WSL相关命令

WSL查看当前版本：
```bash
wsl -l -v
```

WSL停止或启动：
```bash
net stop LxssManager
wsl --shutdown

net start LxssManager
```

WSL设置默认启动版本：
```bash
wsl --set-default-version 2
```

WSL升级更新：
```bash
wsl --update
```

## 参考链接
1. [Microsoft-Windows Commands](https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/windows-commands)
2. [PowerShell Documentation](https://learn.microsoft.com/en-us/powershell/)

