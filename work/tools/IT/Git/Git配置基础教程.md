# Git 配置基础教程


## 常用 config


`user.name`, `user.email`
```bash
git config --global user.name "TomAndersen"
git config --global user.email "1040994898@qq.com"
```


`core.autocrlf` ：控制提交时的换行符转换机制，一般设置为 input，即提交时转换为 Unix 换行符 `\n`
```bash
git config --global core.autocrlf input
```

`core.quotepath` ：控制 Git 输出路径时的字符解析，一般设置为 false，使得 Git 支持显示中文字符
```bash
git config --global core.quotepath false
```


`http.proxy, https.proxy`
```bash
git config --global http.proxy <protocol://><host:port>
git config --global https.proxy <protocol://><host:port>


git config --global http.proxy http://127.0.0.1:58591
git config --global https.proxy https://127.0.0.1:58591

# PS: WSL中需要使用宿主机IP, 即DNS(cat /etc/resolv.conf), 替换127.0.0.1本机地址
git config --global http.proxy http://172.30.32.1:58591
git config --global https.proxy https://172.30.32.1:58591

git config --global --unset http.proxy
git config --global --unset https.proxy
```

`safe.directory` : 避免 Git 安全目录告警，一般不会调整此配置，也不建议调整，因为忽视错误是消极策略。
```bash
git config --global --add safe.directory <directory>
git config --global safe.directory '*'

git config --global --unset safe.directory <directory>
git config --global --unset-all safe.directory

```

`core.longpaths` ：解决 Windows 上 Git 告警 filename too long 的问题 
```bash
git config --global core.longpaths true
```