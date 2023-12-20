# Gradle常见问题


## 权限问题


**Gradle启动时报错，Could not create parent directory for lock file**

通常是文件夹权限问题，Linux 上直接 chown -R 命令赋予指定用户组-用户权限，Windows 上直接文件夹属性-安全，修改组和用户名权限即可。


## 配置问题

**JetBrains IDE 中无法更新 Windows System Environment 的方案，打开 Gradle 菜单和 Terminal 无法显示正确的环境变量值**

解决方案：
1. 安装 JetBrains ToolBox，通过 ToolBox 升级和管理 IDE，使用普通用户从 ToolBox 启动，就能正确加载环境变量
2. 直接重启主机


**Gradle Sync 时报错 `Module entity with name: xxx should be available`**

解决方案：
1. `File | Project Structure | Project Settings | Modules` 修改对应 Modle 的名称，与项目文件 `settings.gradle` 中声明的完全相同
参考链接：
https://mozhimen.blog.csdn.net/article/details/131160641


**IDEA 中 Gradle 项目无法正确检索到引用**

解决方案：
1. Gradle 项目未正确加载，因此需要先解决 Gradle 的安装问题。