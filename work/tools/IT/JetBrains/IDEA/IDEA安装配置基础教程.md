# IDEA基础教程

## IDEA 安装

建议初学者，自己申请，或者淘宝购买学生账号，支持 JetBrains 全家桶。

## 配置 Java 开发环境

`File | Settings | Project Structure | Platform Settings | SDKs | Add New SDK`

将之前安装的 Java 对应的 JAVA_HOME 路径，作为 SDK 的路径，将 SDK 添加到 IDEA 中。

**JetBrains IDE 中无法更新 System Environment 系统环境变量的解决方案：**
1. 建议使用下载安装 JetBrains ToolBox 启动或升级 IDE
2. 直接重启主机

具体参考：[JetBrains常见问题解决方案](work/tools/IT/JetBrains/JetBrains常见问题解决方案.md)

## 配置 Python 开发环境

在实际项目开发过程中，经常可能出现同一个项目中同时存在 Java 和 Python 代码，而 IDEA 支持安装对应的插件，来实现多语言集成开发。

具体见下文，“安装插件”和“修改配置”章节内容。

## 配置 Scala 开发环境

具体见下文，“安装插件”和“修改配置”章节内容。

## 安装插件

### . Ignore

用于交互式生成. Ignore 文件

### Translation

一个 JetBrain 上的翻译插件，支持各大主流翻译工具，建议使用有道翻译

### Alibaba Java Coding Guidelines

阿里巴巴 Java 开发手册 IDEA 插件，支持实时检查 Java 代码开发规范。
https://github.com/alibaba/p3c/wiki

常见问题：
https://github.com/alibaba/p3c/issues/900#issuecomment-1120173751
https://github.com/alibaba/p3c/files/8644807/Alibaba.Java.Coding.Guidelines-2.1.1-fix-2022.1.zip

### Regex Rename Files

支持文件、文件夹快速批量重命名
### CodeGlance Pro

提供代码缩略图

### Github Copilot

Microsoft Github AI 编程神器。
[Github-Copilot基础教程](work/tools/AI/Github-Copilot基础教程.md)

### Python

Plugin 中检索 `Python` 后安装对应插件即可。
### Scala

Plugin 中检索 `Scala` 后安装对应插件即可。

### LomBok

Java LomBok 是 Java 开发中的常用第三方注解框架，可以通过注解的方式在 Java 代码编译期间自动生成对应的 Java 代码，实现模板代码的自动生成和注入，可以节省重复代码的编写。

而 IDEA LomBok 插件，可以使得在编辑器中能正确识别对应的注解，以及调用其后续生成的代码时，编辑器静态代码检查时，不会报错。

### MybatisX

支持 MyBatis 中的语法提示，支持基于 IDEA DataBase 数据源的表结构自动生成 MyBatis/MyBatis Plus 代码和配置文件。

[MyBatisX基础教程](work/component/Back-End/MyBatis/MyBatisX基础教程.md)

### Guice

基于 Google Guice 依赖注入框架开发的辅助插件。

## 修改配置

### Python

1. 配置 Python SDK ：`File | Project Structure | Platform Settings | SDKs`
2. 配置 Python Interpreter：`File | Project Structure | Project Settings | Modules | Add `，添加 Python Framework 即可。

### Scala

1. 配置 Scala SDK：`File | Project Structure | Platform Settings | Global Libraries`
2. 具体参考： [Scala开发环境搭建基础教程](work/programming/Scala/Scala开发环境搭建基础教程.md)

### Build

Version: IntelliJ IDEA 2022.3.2 (Ultimate Edition)

File | Settings | Build, Execution, Deployment | Build Tools

取消勾选，Reload project after changes in the build scripts，避免每次 Build 脚本变动，或者打开新项目时，都自动构建，无法手动控制。

https://stackoverflow.com/questions/43192504/how-do-i-disable-or-enable-gradle-maven-auto-import-for-an-intellij-idea-proje/43192764#43192764

### Gradle

File | Settings | Build, Execution, Deployment | Build Tools | Gradle

`Build and run using`，选择 Intellij IDEA，即选择在 IDEA 中编译和运行编译后的 Java 程序，而不是通过 gradle task 的方式来运行编译结果。

`Use Gradle from`，选择 `gradle-wrapper.properties file`，即使用 Gradle 官方推荐的 Wrapper 来执行 Gradle 命令。

### Line separator

`File | Settings | Editor | Code Style | Line separator`

选择 \\n 作为默认下划线。
### Encoding

File | Settings | Editor | File Encodings

设置文件编码方式，避免源码和 properties 配置文件出现乱码。

`Global Encoding` 和 `Project Encoding` 都设置成 `UTF-8`。

`Default encoding for properties files` 设置成 `UTF-8`。

`Create UTF-8 files` 设置成 `with NO BOM`。

`Transparent native-to-ascii conversion` 选项，取消勾选，避免在 IDEA 中自动将 ASCII 编码以视图的方式转换为中文，而编辑器内容写入文件时依旧是使用 ASCII 码值。

### Keymap

File | Settings | Keymap

删除或者调整快捷键配置 Plugins | Terminal | Close Tab，避免 Terminal 中 Bash 快捷键和 IDEA Terminal 快捷键发生冲突。

### Project

Project View - Options - Tree Appearance

勾选 `Show Members`，`Folds Always on Top`

### Font

`File | Settings | Editor | Font`

1. `Size`: 13, `Line height`: 1.4。
2. `Font`: `Hack`
3. `typography settings | Fallback font`: `SimHei`

### Terminal

`File | Settings | Tools | Terminal`

`Application Settings` 中设置 `Shell Path`，来指定默认启动的 Terminal Shell。

### UI

#### Add Back or Forward Button in new UI

增加“前进”和“后退”快捷按钮到主菜单工具栏

1. Right Click the space of Toolbar
2. Click "Customize Toolbar"
3. Click "Add Actions", then click "Add Action"
4. Click "Main Menu" → "Navigate"→"Back" or "Forward", then click "OK"
5. Hold and Drag “Back” or "Forward" to right position

https://stackoverflow.com/questions/75554009/intellij-2023-new-ui-back-arrow-icon

#### Show tabs in multiple rows

支持 Tab 页面，多行显示

File | Settings | Editor | General | Editor Tabs，Show tabs in multiple rows

#### Add commit tool window

添加 git commit 菜单

1. Interface: `Settings | Version Control | Commit`, then enable `Use non-modal commit interface`
2. Window: go to `Settings | Advanced Settings` then Enable `Commit tool window`

https://stackoverflow.com/a/72490630/13774262

#### Highlighting Usages of element at caret

编辑器中，高亮当前光标选中的元素

File | Settings | Editor | Code Editing | Highlighting on Caret Movement

#### Show whitespaces

编辑器中，显示空格和 Tab

File | Settings | Editor | General | Appearance | Show whitespaces

### IDEA MaxHeapSize

`Help | Edit Custom VM Options`
https://blog.csdn.net/2301_76696220/article/details/134683935

### Close Reformat on Paste

在 IDEA 编辑器中粘贴代码时，默认会自动格式化，导致和复制的内容不符，因此建议关闭此功能，即将 `Reformat on Paste` 设置为 None。

File | Settings | Editor | General | Smart Keys | Reformat on Paste

## IDEA Debugger 调试

[IDEA-Debug调试基础教程](work/tools/IT/JetBrains/IDEA/IDEA-Debug调试基础教程.md)

## IDEA Git 使用技巧

[IDEA-Git功能基础教程](work/tools/IT/JetBrains/IDEA/IDEA-Git功能基础教程.md)