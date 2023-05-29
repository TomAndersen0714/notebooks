# Git常见问题



1. `git push`时时报，提示WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!

[参考链接1](https://blog.csdn.net/qq_41884002/article/details/123358315)


2. 配置完SSH公钥后，push时显示需要输入`git@github.com`的密码

[参考链接1](https://blog.csdn.net/yuzhiqiang_1993/article/details/127032178)
[参考链接2](https://blog.csdn.net/wxc_1998/article/details/127291104)


3. error updating changes: detected dubious ownership in repository
方法一：设置git安全路径

方法二： 修改文件夹所有者为对应git用户

Win：修改文件夹所有者为对应git用户
Linux：`chown -R <current_user> <repo_folder>`

