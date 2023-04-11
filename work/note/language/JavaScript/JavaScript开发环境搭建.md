# JavaScript开发环境搭建

## 前言

工欲善其事，必先利其器。本文主要用于记录JavaScript开发环境搭建过程，仅介绍VSCode和WebStorm这两种主流JS开发环境的搭建方式。

初学者，可以直接从VSCode开始，等VSCode已经无法满足的时，再尝试切换至WebStorm，简单来说就是，杀鸡焉用牛刀。


## VSCode

### 安装Node.js

参考《Node.js入门教程》


### 安装插件

#### JavaScript (ES6) code snippets

用于在VSCode中，实现代码片段的自动生成，通过某些简写单词快速生成完整代码片段。

#### ESLint

ESLint是一个JavaScript代码静态检查工具，检查代码是否符合一些预定义的规则和最佳实践。Microsoft将ESLint以此模块的形式集成到了VSCode中。


#### Vetur

Vetur是一个用于在VSCode中编辑Vue.js项目的插件，可以帮助开发人员更轻松地编写Vue.js代码，包括语法高亮、代码补全、代码片段生成等。


#### Prettier

Prettier插件是一个用于格式化代码的工具，它可以帮助你自动规范和优化你的代码，让你的代码更加易读和一致性。Prettier支持多种编程语言，包括JavaScript、TypeScript、CSS、HTML等。例如，对于JavaScript文件，可以使用快捷键"Shift+Alt+F"来格式化代码。

使用命令面板：按下"F1"打开命令面板，然后输入"format document with..."来列出可用的代码格式化选项。选择"Prettier"选项，即可使用Prettier插件来格式化代码。


#### Live Server

以下是Live Server插件的作用：

1. 实时预览：Live Server可以在浏览器中实时预览你正在编辑的网页，无需手动刷新页面，从而提高了开发效率。
2. 热更新：当你对代码进行修改并保存后，Live Server会自动刷新页面并应用最新的更改，这样你可以立即看到代码变化的效果。
3. 支持多种文件类型：Live Server支持多种文件类型，包括HTML、CSS、JavaScript等等。
4. 支持跨域：Live Server支持跨域请求，可以让你在本地开发时测试跨域请求，将请求代理到指定地址。

### 修改配置

#### Run In Terminal

在Setting中，检索并设置`Run In Terminal`为True，即使用默认终端的命令行而非内置终端运行文件。便于一些需要进行命令行交互的程序执行，如果使用内置终端则交互时会一直等待标准输入，无法运行命令行交互式程序。


### 运行和调试JS

直接点击VSCode顶端菜单栏的“Run”即可，选择直接运行（Run Without Debugging），或者启动调试（Start Debugging）


## WebStrom



## 参考链接
1. [Microsoft - JavaScript in Visual Studio Code](https://code.visualstudio.com/docs/languages/javascript)