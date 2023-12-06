# Git常见问题


## 权限

**报错 `error updating changes: detected dubious ownership in repository`**

方法一（推荐）：以指定对应角色运行 git 命令

如，git pull 时使用的是 Windows 管理员身份，则需要以管理员身份运行对应的命令行才不会报错。同样在 Unix 环境下，也需要使用对应的用户（如 worker），来执行对应的 git 命令。

PS: 任何命令操作，都要严格控制用户权限，特别是 WSL+Windows 的开发模式，在启动 WSL Terminal 时使用的是什么角色，在 WSL 中创建的文件就会在 Windows Explorer 中显示为什么角色所有。


方法二： 修改文件夹所有者为对应 git 用户

Win：修改文件夹所有者为对应 git 用户， https://blog.csdn.net/tcjy1000/article/details/127129224
Linux：`chown -R <current_user> <repo_folder>`

方法三：设置 git 安全路径


## 网络

**`git push` ，提示 WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!**


[参考链接1](https://blog.csdn.net/qq_41884002/article/details/123358315)


**配置完SSH公钥后，push时显示需要输入`git@github.com`的密码**

[参考链接1](https://blog.csdn.net/yuzhiqiang_1993/article/details/127032178)
[参考链接2](https://blog.csdn.net/wxc_1998/article/details/127291104)


**DNS 解析 github.com 出的 IP 无法访问**


## 使用

**`git fetch` 无法获取远程分支信息**

- `git clone` 时，如果使用 `git clone --depth=1` 命令限制 `depth` ，会导致项目只会获取特定数量 commit 相关的分支，而不是全部分支，这样可以大大加快克隆的速度，但同时也无法获取其他 commit 相关的分支信息。
- 如果要解除限制，可以使用 `git remote set-branches origin '*'` 命令，修改 `.git/Config` 中的配置。




