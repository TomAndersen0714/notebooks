# Mac 常见问题

## 键鼠问题

### 修改快捷键

[Mac修改快捷键](learning/subjects/ComputerScience/OperatingSystem/Mac/Mac修改快捷键.md)

### 键盘大写键的延迟

[取消Mac键盘大写键的延迟 · HUAN.cc](https://huan.cc/tech/mac-caps-lock-delay/)

### 交换 cmd 和 control 键

从 Win 切换到 Mac 不适用这种相反的快捷键修饰键，需要手动切换
[如何在macOS中互换command键和control键？ - MacWang.net-Mac下载网](https://www.macwang.net/blogs/2832.html)
[在 Mac 上更改修饰键的行为 - 官方 Apple 支持 (中国)](https://support.apple.com/zh-cn/guide/mac-help/mchlp1011/mac)

### 鼠标滚动不顺滑

下载 MOS App
[Mac常用小工具](learning/subjects/ComputerScience/OperatingSystem/Mac/Mac常用小工具.md)

## 窗口管理

### 无法快速最小化

修改系统快捷键。如果只是修改“APP 快捷键”，则部分 APP 也许会不支持这种全局增加快捷键的方式，尤其是那些已经内置了快捷键的 APP。

使用 Keyboard Maestro 工具增加按键映射，将 `Command+Q` 映射为执行 `Command+M`，实现窗口快速最小化。
[Mac常用小工具](learning/subjects/ComputerScience/OperatingSystem/Mac/Mac常用小工具.md)
[Mac 有办法全局统一管理快捷键吗？ - V2EX](https://www.v2ex.com/t/846411)
[「Keyboard Maestro」的搜索结果\_马克喵](https://www.macat.vip/?cat=&s=Keyboard+Maestro)

### 快速切换打开的窗口

解决 Mac 无法像 Windows 一样，支持 Alt+Tab 键快速切换窗口的功能。
[AltTab - Windows alt-tab on macOS](https://alt-tab-macos.netlify.app/)

## 顶部菜单栏管理

### 菜单移动和删除

按住 Command 键，鼠标左键选中后拖动即可调整顺序和删除
[mac怎么管理顶部菜单状态栏? 怎么添加,删除, 排序移动顶部菜单状态栏图标? 超详细简单一看就会.\_mac顶部状态栏图标添加-CSDN博客](https://blog.csdn.net/weixin_37281289/article/details/114242348)

### 隐藏顶部菜单 Hidden Bar

支持菜单折叠和展示
[Hidden Bar：一键折叠，给 macOS 菜单栏解压 - 少数派](https://sspai.com/post/58194)

## 安全问题

### 无法安装非 App store 软件

报错信息：`MacOS遇到“无法打开xxx，因为Apple无法检查其是否包含恶意软件”`
解决方案：[【解决方案】MacOS遇到“无法打开xxx，因为Apple无法检查其是否包含恶意软件”，怎么处理。\_无法打开阿里云盘,因为apple无法检查-CSDN博客](https://blog.csdn.net/m0_38068876/article/details/115091884)