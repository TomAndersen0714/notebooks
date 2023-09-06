# WSL 常见问题


## WSL 无法启动

在“服务”中重启 LxssManager 服务。

如果 LxssManager 被挂起，无法手动重启，则可以通过 PowerShell 等 Shell 查找对应的 PID：`tasklist /svc /fi "imagename eq svchost. Exe" | findstr LxssManager`。然后直接终止对应进程，然后重新打开 WSL 即可：`kill <pid>`。


## WSL2 可以解析网址，但无法访问外网

https://github.com/microsoft/WSL/issues/4926#issuecomment-679410653

1. 重置网络
2. 重启主机

## WSL2: The remote procedure call failed. 远程过程调用失败

https://github.com/docker/for-win/issues/7208

1. 卸载 Docker Desktop
2. 控制面板-程序-启动或关闭 Windows 功能，关闭 Hyper-V
3. 控制面板-程序-启动或关闭 Windows 功能，关闭 Windows Subsystem for Linux
4. 重启主机

## 无法访问宿主机

WSL2 无法解析 host，ping DNS Server（`/etc/resolv.conf` 中的 nameserver）显示 Destination Host Unreachable。

1. 重置网络
2. 重启主机


## 用户权限问题

在启动 Terminal 时使用的是什么角色，在 WSL 中创建的文件就会在 Windows Explorer 中显示为什么角色所有。

比如，如果使用“管理员”用户打开 Terminal 连接 WSL，那么通过 git clone 命令克隆项目时自动创建的文件夹，在 Windows 资源管理器中，显示的也是“管理员”用户，与此同时，后续在 Windows 环境下使用 git 时，也同样需要以“管理员”的身份使用，否则会报错 `detected dubious ownership in repository`。


## 环境变量问题

WSL 默认会将宿主机 Windows 里的环境变量 PATH 中的内容一起加载到 WSL 的 path 环境变量中，可能会导致 WSL 中无法识别正确的命令位置，建议禁止这个默认的加载行为。


可以通过 Windows 资源管理器上的 `%UserProfile%/.wslconfig` 文件设置所有的 WSL Linux 发行版中生效，或者在对应的 WSL Linux 发行版的 `/etc/wsl.conf` 文件中进行配置。

https://learn.microsoft.com/zh-cn/windows/wsl/wsl-config#wslconf
https://learn.microsoft.com/zh-cn/windows/wsl/wsl-config#wslconfig


WSL 禁止加载 Windows Path 环境变量内容到 Path 中：
https://docs.microsoft.com/zh-cn/windows/wsl/wsl-config
https://blog.csdn.net/weixin_43698781/article/details/124792708


