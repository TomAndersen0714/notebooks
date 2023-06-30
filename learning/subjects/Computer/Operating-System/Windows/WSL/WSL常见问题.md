# WSL常见问题


## 权限问题

在启动Terminal时使用的是什么角色，在WSL中创建的文件就会在Windows Explorer中显示为什么角色所有。

如过，如果使用管理员身份打开Terminal，那么git clone命令克隆项目时自动创建的文件夹，在Windows资源管理器中，显示的也是管理员账户，同时在Windows环境下使用git时，也同样需要以管理员的身份使用，否则会报错`detected dubious ownership in repository`。


## 网络问题

WSL2无法解析host，ping DNS Server（/etc/resolv.conf）显示Destination Host Unreachable。

