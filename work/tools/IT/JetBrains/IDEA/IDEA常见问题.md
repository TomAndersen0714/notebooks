# IDEA 常见问题

## 环境变量

**问题描述**：
- Windows JetBrains IDE 中无法更新环境变量，打开 Terminal 查看环境变量也和主机设置不同
**问题原因**：
- Windows 修改环境变量后，并不会直接更新在 JetBrains IDE 中，需要重新启动 IDE 才能加载到最新的变量
**解决方案**：
- 关闭所有 IDEA 相关进程，然后重新打开对应项目
- 安装 JetBrains ToolBox，通过 ToolBox 启动 IDEA
- 重启主机

## 项目导入

**问题描述**：
- IDEA 打开同时包含 Gradle/Maven 项目的 Project 时，无法同时加载
**问题原因**：
- IDEA 默认不支持同时自动导入 Gradle 和 Maven 项目，需要自己手动将其作为 Module 进行导入
**解决方法**：
- 在 `File | Settings | Project Structure | Project Settings | Modules` 下通过 `Import Module` 的方式手动导入对应的子项目，即可自动识别