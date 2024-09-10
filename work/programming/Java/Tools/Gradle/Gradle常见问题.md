# Gradle常见问题


## 权限问题


**Gradle启动时报错，Could not create parent directory for lock file**

原因排查：
- 通常是文件夹权限问题
解决方案：
- Linux 上直接 chown -R 命令赋予指定用户组-用户权限，Windows 上直接文件夹属性-安全，修改组和用户名权限即可。


## 配置问题

**JetBrains IDE 中无法更新 Windows System Environment 的方案，打开 Gradle 菜单和 Terminal 无法显示正确的环境变量值**

原因排查：
- 因为环境变量更新后，一般都需要手动重新加载，或者重启对应程序，才能重新加载最新的环境变量
解决方案：
- 安装 JetBrains ToolBox，通过 ToolBox 升级和管理 IDE，使用普通用户从 ToolBox 启动，就能正确加载环境变量
- 直接重启主机


**Gradle Sync 时报错 `Module entity with name: xxx should be available`**

解决方案：
- `File | Project Structure | Project Settings | Modules` 修改对应 Modle 的名称，与项目文件 `settings.gradle` 中声明的完全相同
参考链接：
- https://mozhimen.blog.csdn.net/article/details/131160641


**IDEA 中 Gradle 项目无法正确检索到引用**

解决方案：
- Gradle 项目未正确加载，因此需要先解决 Gradle 的安装问题。

**IDEA 中 Gradle Build 时报错 `Cannot find a java installation on your machine matching this tasks requirements: {languageVersion=21}`**

原因排查：
- Gradle Build 配置脚本（`build.gradle`）中声明当前项目中指定的 Java 版本，在本地环境中并未找到，可能是未安装，也可能是配置文件中的 Java 相关配置项存在错误
解决方案：
- 如果是在 IDEA IDE 中，在 `File | Settings | Build, Execution, Deployment | Build Tools | Gradle` 中，通过 `Gradle JVM` 配置，设置 Gradle 使用的 JVM 安装路径，指向 build 配置脚本 `build.gradle` 中声明的对应版本
