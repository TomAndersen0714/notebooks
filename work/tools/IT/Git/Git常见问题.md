# Git常见问题

## 配置

问题描述：Git commit 时文件名不显示中文，而是显示数字
调整 Git 配置：`git config --global core.quotepath false`

## 权限

**报错 `error updating changes: detected dubious ownership in repository`**

方法一（推荐）：统一文件夹的所有者，然后以对应角色运行 git 命令

统一文件夹的所有者：
1. Windows 环境下，需要通过修改文件夹的所有者，来实现文件的所有者统一
2. Unix 环境下，则通过 `chown -R` 命令，来递归修改文件夹及其文件的所有者

任何命令操作，都要严格控制用户权限，尤其是在 WSL Git 的开发模式中，启动 WSL Terminal 时使用的是什么角色，则在 WSL 中创建的文件就会在 Windows Explorer 中显示为什么角色所有。

如：以管理员身份启动 Windows Terminal，那么在 Windows 资源管理器中，就会以对应的用户进行创建文件。

方法二： 修改文件夹所有者为对应 git 用户

Win：修改文件夹所有者为对应 git 用户， https://blog.csdn.net/tcjy1000/article/details/127129224
Linux：`chown -R <current_user> <repo_folder>`

方法三：设置 git 安全路径

配置参数 `safe.directory`，具体参考 [Git配置基础教程](work/tools/IT/Git/Git配置基础教程.md)。

## 网络

**`git push` ，提示 WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!**

[参考链接1](https://blog.csdn.net/qq_41884002/article/details/123358315)

**配置完SSH公钥后，push时显示需要输入`git@github.com`的密码**

[参考链接1](https://blog.csdn.net/yuzhiqiang_1993/article/details/127032178)
[参考链接2](https://blog.csdn.net/wxc_1998/article/details/127291104)

**DNS 解析 github.com 出的 IP 无法访问**

## 命令

**`git fetch` 无法获取远程分支信息：**
- `git clone` 时，如果使用 `git clone --depth=1` 命令限制 `depth` ，会导致项目只会获取特定数量 commit 相关的分支，而不是全部分支，这样可以大大加快克隆的速度，但同时也无法获取其他 commit 相关的分支信息。
- 如果要解除限制，可以使用 `git remote set-branches origin '*'` 命令，修改 `.git/Config` 中的配置。

**`git add *` 无法添加 Linux 隐藏文件，如 `.gitignore` 等：**
- 在 Shell 中 `*` 是一种统配符，匹配的是任意数量的字符，但不包括 `.` 符号开头的文件，如 `.gitignore`。
- 在 Shell 中符号 `.` 表示的是当前目录，使用 `git add .` 表示的将当前文件夹添加到 catch 暂存区，因此使用 `git add .` 替换 `git add *`，即可解决对应问题。