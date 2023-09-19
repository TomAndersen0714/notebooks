# Gradle常见问题


**Gradle启动时报错，Could not create parent directory for lock file**

通常是文件夹权限问题，Linux 上直接 chown -R 命令赋予指定用户组-用户权限，Windows 上直接文件夹属性-安全，修改组和用户名权限即可。


**JetBrains IDE 中无法更新 Windows System Environment 的方案，打开 Gradle 菜单和 Terminal 无法显示正确的环境变量值**

1. 安装 JetBrains ToolBox，通过 ToolBox 升级和管理 IDE
2. 直接重启主机