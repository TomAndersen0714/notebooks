# WSL 常见问题

## 启动问题

### WSL 无法正常启动

重启 LxssManager 服务：
1. 在“服务”页面中重启 LxssManager 服务。
2. 如果 LxssManager 被挂起，无法手动重启，则可以通过 PowerShell 等 Shell 查找对应的 PID：`tasklist /svc /fi "imagename eq svchost. Exe" | findstr LxssManager`。然后直接终止对应进程，然后重新打开 WSL 即可：`kill <pid>`。

如果 Ubuntu 版本是 WSL 2，还需要确保虚拟机平台、Hyper-V 功能已被打开：
1. 查看 WSL 的 Linux 子系统版本信息： `wsl -l -v`
2. 开启 Windows 功能：“控制面板” -> “程序” -> “启用或关闭Windows功能”

### WSL2: Wsl/Service/CreateInstance/CreateVm/HCS/0x80070032.

直接运行 wsl.exe 程序时报错 `Wsl/Service/CreateInstance/CreateVm/HCS/0x80070032`，错误代码 `0x80070032` 表示 "The request is not supported"（请求不被支持）。

WSL2 中需要确保 Windows 上虚拟机平台、Hyper-V 两个功能已被打开：
1. 查看 WSL 的 Linux 子系统版本： `wsl -l -v`
2. 开启 Windows 功能：“控制面板” -> “程序” -> “启用或关闭 Windows 功能”

### WSL2: The remote procedure call failed. 远程过程调用失败

[WSL2 backend always crash System.InvalidOperationException: Failed to deploy distro docker-desktop · Issue #7208 · docker/for-win · GitHub](https://github.com/docker/for-win/issues/7208)

1. 卸载 Docker Desktop
2. 控制面板-程序-启动或关闭 Windows 功能，关闭 Hyper-V
3. 控制面板-程序-启动或关闭 Windows 功能，关闭 Windows Subsystem for Linux
4. 重启主机

### WSL2: Can't open display: (null)

WSL2 无法运行依赖可视化的命令行工具，如 xsel。

[windows subsystem for linux - WSL: when I try to use GUI package get error "Can't open display" - Ask Ubuntu](https://askubuntu.com/a/1470362)

1. Open `C:\Users\<username>\.wslconfig`
2. Find the line: `guiApplications=false` and change it to `guiApplications=true`. If you can not find the line, add it in the file.
3. Restart your PC (or just do `wsl --shutdown` followed by `wsl` as suggested by @bkakilli).

## 网络问题

### 子系统无法访问网络

[No internet connectivity from WSL2/Ubuntu · Issue #4926 · microsoft/WSL · GitHub](https://github.com/microsoft/WSL/issues/4926#issuecomment-679410653)
1. 重置 Windows 宿主机网络：
2. 重启主机

### 子系统无法访问宿主机

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