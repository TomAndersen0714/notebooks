# GitHub配置SSH教程


## 创建SSH Key pair

如果没有现成的SSH Key pair，则可以通过ssh-keygen命令自行创建密钥对。


## 添加SSH Public Key

打开Github Account Settings | SSH and GPG keys，将SSH Key的.pub配置文件内容，复制粘贴到Github Account的Key表单下。


## 验证 SSH 配置

```bash
ssh -Tv git@github.com
```

## 参考链接
1. [GitHub Docs - Connecting to GitHub with SSH](https://docs.github.com/en/authentication/connecting-to-github-with-ssh)