# Git常见问题



##  `git push` 时时报，提示 WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!


[参考链接1](https://blog.csdn.net/qq_41884002/article/details/123358315)


## 配置完SSH公钥后，push时显示需要输入`git@github.com`的密码

[参考链接1](https://blog.csdn.net/yuzhiqiang_1993/article/details/127032178)
[参考链接2](https://blog.csdn.net/wxc_1998/article/details/127291104)


## error updating changes: detected dubious ownership in repository

方法一（推荐）：以指定对应角色运行git命令

如，git pull时使用的是Windows管理员身份，则需要以管理员身份运行对应的命令行才不会报错。同样在Unix环境下，也需要使用对应的用户（如worker），来执行对应的git命令。

PS: 任何命令操作，都要严格控制用户权限，特别是WSL+Windows的开发模式，在启动WSL Terminal时使用的是什么角色，在WSL中创建的文件就会在Windows Explorer中显示为什么角色所有。


方法二： 修改文件夹所有者为对应git用户

Win：修改文件夹所有者为对应git用户， https://blog.csdn.net/tcjy1000/article/details/127129224
Linux：`chown -R <current_user> <repo_folder>`

方法三：设置git安全路径


##  DNS 解析 github.com 出的 IP 无法访问


