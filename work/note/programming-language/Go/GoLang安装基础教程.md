# GoLang安装基础教程

## Installation


### Microsoft Windows

下载对应的MSI文件，安装即可，安装程序会将go工具的bin路径添加到PATH环境变量中。

### Linux

Remove any previous Go installation
```bash
 rm -rf /usr/local/go && tar -C /usr/local -xzf go1.20.5.linux-amd64.tar.gz
```

Add /usr/local/go/bin to the PATH environment variable.

You can do this by adding the following line to your $HOME/.profile or /etc/profile (for a system-wide installation):
```bash
export PATH=$PATH:/usr/local/go/bin
```



## Config




## 参考链接
1. [The Go Programming Language](https://go.dev/)
2. [Documentation-Download and install](https://go.dev/doc/install)
3. [Go语言中文网](https://studygolang.com/)