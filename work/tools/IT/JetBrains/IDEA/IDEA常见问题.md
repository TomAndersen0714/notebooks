# IDEA 常见问题


## 环境变量

**Windows JetBrains IDE 中无法更新环境变量，打开 Terminal 查看环境变量也和主机设置不同**

**问题原因**：
- Windows 修改环境变量后，并不会直接更新在 JetBrains IDE 中，需要重新启动 IDE 才能加载到最新的变量
**解决方案**：
- 关闭所有 IDEA 相关进程，然后重新打开对应项目
- 安装 JetBrains ToolBox，通过 ToolBox 启动 IDEA
- 重启主机

