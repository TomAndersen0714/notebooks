# GoLang安装基础教程

## Installation


### Microsoft Windows

下载对应的MSI文件，安装即可，安装程序会将go工具的bin路径添加到PATH环境变量中。

安装验证：
```bash
go version
```

### Linux

Remove any previous Go installation
```bash
 rm -rf /usr/local/go && tar -C /usr/local -xzf go1.20.5.linux-amd64.tar.gz
```

Add /usr/local/go/bin to the PATH environment variable. You can do this by adding the following line to your $HOME/.profile or /etc/profile (for a system-wide installation):
```bash
export PATH=$PATH:/usr/local/go/bin
```

Verify that you've installed Go by opening a command prompt and typing the following command:
```bash
go version
```


## Environment config


### GOROOT

在Windows系统下一般无需配置，在执行MSI安装包程序时候会自动创建此环境变量

在Linux系统中，需要将此变量指向Go安装的根目录，如`/usr/local/go`


### GOPATH

GOPATH环境变量，用于保存GoLang的Workspace路径，其中通常会包含以下三类文件夹：
1. src/: location of Go source code (for example, .go, .c, .g, .s).
2. pkg/: location of compiled package code (for example, .a).
3. bin/: location of compiled executable programs built by Go.

主要作用是为GoLang项目，维护和提供Library。

### GOPROXY

```bash
export GOPROXY=https://goproxy.cn,https://goproxy.io,direct
```

GOPRIVATE



## 参考链接
1. [The Go Programming Language](https://go.dev/)
2. [Documentation-Download and install](https://go.dev/doc/install)
3. [Go语言中文网](https://studygolang.com/)
4. [Go Packages](https://pkg.go.dev/)