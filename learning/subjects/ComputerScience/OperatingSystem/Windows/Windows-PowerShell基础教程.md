# PowerShell 基础教程

## 前言

PowerShell 是 Windows 命令提示符的升级版本，除了支持 Windows 命令外，还支持额外微软的 cmdlet 命令。

## 常用命令

```powershell

# 获取脚本路径
Get-Command <cmd_name>
Get-Command python

# 打印环境变量
$env:<VariableName>
$env:GOPROXY

```
## 常见问题

报错信息
- `错误，在此系统上禁止运行脚本`
解决方案
```bash
# 查看现用执行策略
get-executionpolicy

# 更改执行策略
set-executionpolicy unrestricted
```
参考链接
- [【PowerShell】错误：在此系统上禁止运行脚本\_powershell 因为在此系统上禁止运行脚本-CSDN博客](https://blog.csdn.net/qq_36308757/article/details/140896098)
- [Set-ExecutionPolicy (Microsoft.PowerShell.Security) - PowerShell | Microsoft Learn](https://learn.microsoft.com/en-us/powershell/module/microsoft.powershell.security/set-executionpolicy?view=powershell-7.4)

## 参考链接

1. [windows为什么有两个命令行工具？命令提示符与PowerShell有什么区别？\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1Nx4y147n3/?vd_source=31f9517734e43a6c180d5d1d56a5e162)
2. [PowerShell Documentation - PowerShell | Microsoft Learn](https://learn.microsoft.com/en-us/powershell/)