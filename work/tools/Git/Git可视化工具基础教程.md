# Git 可视化工具基础教程


## 前言

Git 可视化工具千奇百怪，从开源、商用等角度来看，目前个人理解使用体验最好的，还是 VSCode 的 GitLens 插件，以及 IDEA 内置的 Git 仓库管理功能。


## IDEA Git

配置教程：
1. Open Git tool window in Main menu
2. Add commit tool window
	1. Interface: `Settings | Version Control | Commit`, then enable `Use non-modal commit interface`
	2. Window: go to `Settings | Advanced Settings` then Enable `Commit tool window`
https://stackoverflow.com/a/72490630/13774262


## VSCode Plugin - Gitlens

VSCode Plugin 直接安装即可：
https://github.com/gitkraken/vscode-gitlens
特点：
1. 开源
2. 功能丰富，支持多 Repository 管理
配置教程：
1. VSCode 创建 Workspace
2. VSCode 中添加文件夹到当前 Workspace
3. GitLens 即可自动识别这些文件夹下的首个 Git 仓库

## Github Destop

特点：
1. 开源
2. 功能单一

## SourceTree

特点：
1. 免费，部分特性开源，支持商用
2. 功能齐全
安装教程：
https://www.sourcetreeapp.com/
配置教程：
`Tools | Options | General | SSH Client Configuration | SSH Client` 选择 `OpenSSH`
参考链接：
https://www.liaoxuefeng.com/wiki/896043488029600/1317161920364578

## 参考链接
1. [Git GUI Clients](https://git-scm.com/downloads/guis)
2. 