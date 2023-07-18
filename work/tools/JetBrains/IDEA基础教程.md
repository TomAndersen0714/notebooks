# IDEA基础教程


## 安装 IDEA

建议初学者，可以自己申请，或者淘宝购买学生账号，支持 JetBrains 全家桶。


## 配置 JDK

File | Settings | Project Structure | Platform Settings | SDKs | Add New SDK

将之前安装的 Java 对应的 JAVA_HOME 路径，作为 SDK 的路径，将 SDK 添加到 IDEA 中。


**JetBrains IDE 中无法更新 System Environment 系统环境变量的解决方案：**
1. 建议使用下载安装 JetBrains ToolBox 启动或升级 IDE
2. 直接重启主机


## 安装插件

### . Ignore

用于交互式生成. Ignore 文件

### Translation

一个 JetBrain 上的翻译插件，支持各大主流翻译工具，建议使用有道翻译


## 修改配置

### Build

Version: IntelliJ IDEA 2022.3.2 (Ultimate Edition)

File | Settings | Build, Execution, Deployment | Build Tools

取消勾选，Reload project after changes in the build scripts，避免每次 Build 脚本变动，或者打开新项目时，都自动构建，无法手动控制。

https://stackoverflow.com/questions/43192504/how-do-i-disable-or-enable-gradle-maven-auto-import-for-an-intellij-idea-proje/43192764#43192764

### Gradle

File | Settings | Build, Execution, Deployment | Build Tools | Gradle

`Build and run using`，选择 Intellij IDEA，即选择在 IDEA 中编译和运行编译后的 Java 程序，而不是通过 gradle task 的方式来运行编译结果。

`Use Gradle from`，选择 `gradle-wrapper.properties file`，即使用 Gradle 官方推荐的 Wrapper 来执行 Gradle 命令。


### Encoding

File | Settings | Editor | File Encodings

设置文件编码方式，避免出现命令行乱码。

`Global Encoding` 和 `Project Encoding` 都设置成 `UTF-8`

`Default encoding for properties files` 设置成 `UTF-8`

`Create UTF-8 files` 设置成 `with NO BOM`


### Keymap

File | Settings | Keymap

删除或者调整快捷键配置 Plugins | Terminal | Close Tab，避免 Terminal 中 Bash 快捷键和 IDEA Terminal 快捷键发生冲突。


### Project

Project View - Options - Tree Appearance

勾选 `Show Members`，`Folds Always on Top`


## 调试技巧

### Reset Frame

Debug | Debugger | Reset Frame

在 Debugger 窗口下可以选中特定的 Frame 条目，然后点击右键，选择"Reset Frame"可以，回退调用栈中指定 Frame 的上一层，程序执行的 Cursor 也会回到上一层的代码行。

通过 Reset Frame 的方式，可以快速回退程序执行路径，避免通过重启程序回到跳过的 Break Point。


### Evaluate Expression

在 Debug 时，可以通过选中指定的代码段，点击右键打开菜单，选中“Evaluate Expression”来执行代码片段。

这个功能可以用于直接探测指定代码的返回值，而不用进入代码内部；也可以用于修改，运行时的变量值，来进行自测。

使用此功能时，需要注意的是，任何代码段都是在程序中实际执行过的，会实际影响到内存中的变量。