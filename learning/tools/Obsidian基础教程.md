# Obsidian基础教程

## 核心功能

### Canvas

[知识管理神器 Obsidian，终于有了白板功能！ - 少数派](https://sspai.com/post/77463)
[一起来看看大佬们的Obsidian白板（Canvas）使用案例分享 - 哔哩哔哩](https://www.bilibili.com/read/cv21024214)

## 插件

### 常用插件

Editing Toolbar

Easy Typing

Emoji Toolbar

Image Toolkit

LaTex Suite

Advanced Table

PlantUML

Auto Link Title
[OB0207 obsidian 自动获取url链接：auto-link-title插件使用\_auto link title-CSDN博客](https://blog.csdn.net/qq_41520353/article/details/128675834)

Excalidraw
[GitHub - excalidraw/excalidraw: Virtual whiteboard for sketching hand-drawn like diagrams](https://github.com/excalidraw/excalidraw)
[左手文字，右手图形，用Excalidraw去弥补Obsidian笔记的不足\_哔哩哔哩\_bilibili](https://www.bilibili.com/video/BV1j8411S7pT/)

KanBan

Git

Trim Whitespace
### 常用插件配置

Easy Typing

User-defined Regular Expression, one expression per line:
```
{{.*?}}|++
\[\!.*?\][-+]{0,1}|-+
(file:///|https?://|ftp://|obsidian://|zotero://|www.)[^\s（）《》。,，！？;；：“”‘’\)\(\[\]\{\}']+|++


[a-zA-Z0-9_\-.]+@[a-zA-Z0-9_\-.]+|++
(?<!#)#[\u4e00-\u9fa5\w\/]+|++

[#].*|++

(\w+\.)+(js|com|cn|xml)+|++
\w+\.\w+(\.\w+)+|++
\d+(:\d+)+|++
```

## 样式修改

[Obsidian主题样式修改半入门教学 - 经验分享 - Obsidian 中文论坛](https://forum-zh.obsidian.md/t/topic/180)

Chrome 开发者工具打开对应快捷键“Ctrl+Shift+I”。

## 数据同步方案

### 对象存储

[对象存储和传统存储的区别、对象存储OSS、COS、OBS的区别\_oss储存和一般储存的区别-CSDN博客](https://blog.csdn.net/qq_15821487/article/details/124926518)
[知乎-obsidian第三方同步方案(remotely插件+腾讯云cos)-保姆级教程](https://zhuanlan.zhihu.com/p/479961754)

### 云盘

阿里云盘、百度网盘等。

### OneDrive

目前 OneDrive 数据同步的方案并不成熟，其中主要的缺点在于 OneDrive 无法保存空文件，以及 Obsidian Remotely Save 插件无法同步一些以 `.git` 这类以 `.` 为开头的文件夹内容，相当于 `.git` 文件中的内容无法同步，即无法同步完整的 Git 仓库内容。

[obsidian全平台好用的保姆级免费同步方案\_obsidian onedrive-CSDN博客](https://blog.csdn.net/xiaozhuang0827/article/details/135267966)
[Obsidian Remotely Save 插件+OneDrive 实现多平台同步 - 经验分享 - Obsidian 中文论坛](https://forum-zh.obsidian.md/t/topic/5291)

## 参考链接

1. [2021年新教程 - Obsidian中文教程 - Obsidian Publish](https://publish.obsidian.md/chinesehelp/01+2021%E6%96%B0%E6%95%99%E7%A8%8B/2021%E5%B9%B4%E6%96%B0%E6%95%99%E7%A8%8B)
2. [Airtable - OB社区插件汇总 - Johnny整理 - 每周更新 - B站 Johnny学](https://airtable.com/appErQxa3n8SnyUdO/shrdmp10Lxmf5Wmgl/tblJqnWpcKURTjysX)
3. [如何使用ob进行任务管理 - Obsidian中文教程 - Obsidian Publish](https://publish.obsidian.md/chinesehelp/01+2021%E6%96%B0%E6%95%99%E7%A8%8B/%E5%A6%82%E4%BD%95%E4%BD%BF%E7%94%A8ob%E8%BF%9B%E8%A1%8C%E4%BB%BB%E5%8A%A1%E7%AE%A1%E7%90%86)