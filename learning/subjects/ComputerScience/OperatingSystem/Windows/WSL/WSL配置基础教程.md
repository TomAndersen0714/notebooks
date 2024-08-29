# WSL 配置基础教程


## WSL 修改默认登录用户

方法一：设置对应 Linux 的默认用户
```bash
查看wsl版本
wsl -l -v

# 设置子系统<Distro>的默认登录用户为<username>
<Distro> config --default-user <username>

# 例如：
Ubuntu2204 config --default-user root
```


方法二：每次启动时指定用户

```bash
wsl -u <username>
```

## WSL 配置文件

配置文件位置：`%UserProfile%/.wslconfig`，


### WSL禁止加载Windows Path环境变量内容到Path中

https://docs.microsoft.com/zh-cn/windows/wsl/wsl-config
https://blog.csdn.net/weixin_43698781/article/details/124792708


## 参考链接

1. [Microsoft - Advanced settings configuration in WSL](https://learn.microsoft.com/en-us/windows/wsl/wsl-config)