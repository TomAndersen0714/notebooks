# WebStorm 基础教程


## 安装 WebStrom




## 安装插件

### . Ignore

用于交互式生成. Ignore 文件

### Translation

一个 JetBrain 上的翻译插件，支持各大主流翻译工具，建议使用有道翻译

### Live Edit

此插件作用类似于 VSCode 中的 Live Server 插件，主要用于在本地免打包启动 Web 服务，自动加载和刷新代码，即时查看页面效果


## 修改配置

### Debugger

File | Settings | Build, Execution, Deployment | Debugger

勾选“Allow unsigned requests”，避免 WebStorm 使用自定义的 request header 来发送 HTTP 请求，导致无法调试。

### Postfix Completion

File | Settings | Editor | General | Postfix Completion

Postfix Completion 中增加 template，可以在编辑器中表达式之后，键入对应后缀，快速生成对应代码段。

增加 Key 名为“logvar”，表达式为 `console.log("$EXPR$ = " + $EXPR$)` 的 template，即可以通过“表达式. Logvar”的方式来在编辑器中自动生成对应的 console 调用代码段。


### Live Templates

File | Settings | Editor | Live Templates

Live Templates 配置中增加 Live Templates，可以在编辑器中，通过键入对应 Key，快速生成对应代码段。

在 JavaScript Group 下增加，名为 logvar 的 template，template text 设置为 `console.log("$EXPR$ = " + $EXPR$)`，即可以通过直接键入“logvar”的方式，来自动生成对应的 console 调用代码段。

### Code Style

File | Settings | Editor | Code Style | HTML

在 Code Generation 中，取消 `Link comment at first column` 的勾选，避免注释起始标签放置在行首，使得注释更加紧凑。勾选 `Block comment at first column`，勾选 `Add spaces around block comments`，使得注释标签内容首位增加空格。

### Keymap

File | Settings | Keymap

删除或者调整快捷键配置 Plugins | Terminal | Close Tab，避免 Terminal 中 Bash 快捷键和 IDEA Terminal 快捷键发生冲突。


### TODO（可选）

File | Settings | Editor | TODO

在 patterns 选项中，增加 pattern 值为 `\bnote\b.*` 的选项，勾选 `use color scheme TODO default colors`，便于在注视中，通过 `NOTE` 关键字来高亮显示注释内容，便于在注视中记录笔记。


## 使用技巧

### 标签自动补全
```
（1）纯标签补全
例：输入h1,按Tab键，


（2）纯标签+地址“id”
例：输入h1#ccg,按Tab键，


（3）纯标签+类“class”
例：输入h1.ccg,按Tab键，


（4）标签+子标签+子标签个数
例：输入div>p*6,按Tab键，

（5）标签+类+子标签+子标签个数+子子标签+地址+}HTML}
例：输入ul.menu>li*6>a[href=#]{HTML},按Tab键，
```


自动补全标签：输入标签在，在浮动列表中，选择一项后，输入 `>` 符号，则自动补全结束标签。

### Show npm Scripts

鼠标右键 npm 项目的 package. Json 文件，点击 `Show npm Scripts`。

或者，`View | Tool Windows | npm`，即可打开 npm 脚本命令窗口，类似于 Maven 脚本命令窗口，可以快速执行对应脚本命令。

