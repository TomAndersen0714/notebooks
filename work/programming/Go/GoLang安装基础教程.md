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

在Windows系统下一般无需配置，因为在执行MSI安装包程序时候一般会自动创建此环境变量

在Linux系统中，由于一般是通过手动指定安装路径和解压安装包，因此需要手动配置此变量，将其指向Go安装的根目录，如`/usr/local/go`


### GOPATH

GOPATH环境变量，用于指定GoLang的Workspace路径，所有下载的Package都会保存在此路径下，其中通常会包含以下三类文件夹：
1. src/: location of Go source code (for example, .go, .c, .g, .s).
2. pkg/: location of compiled package code (for example, .a).
3. bin/: location of compiled executable programs built by Go.

GoLang Workspace的主要作用是为GoLang项目维护package，即Libraries。

### GOPROXY

Windows：
在系统环境变量中，新建`GOPROXY`环境变量，设置对应值即可。

重新打开Terminal登录Shell，打印环境变量，查看配置是否成功：
```bash
echo %GOPROXY%
```

Linux：
直接配置环境变量即可，按需设置用户级别或系统级别。

```bash
export GOPROXY=https://goproxy.cn,https://goproxy.io,direct
```

参考：[Linux-Shell环境变量配置基础教程](learning/subjects/ComputerScience/OperatingSystem/Linux/Linux-Shell环境变量配置基础教程.md)

### GOPRIVATE

GOPRIVATE环境变量，用于指定私有包的仓库地址，当package地址以这类域名为前缀时，则不会去`GOPROXY`指定的镜像服务器中获取，而是从直接从其URL对应的原始的版本控制系统（如 Git、SVN）中获取。

此配置仅在某些特殊情况下使用，一般都是直接通过域名重定向实现私有Package的下载和引入。

https://play-with-go.dev/working-with-private-modules_go119_en/


## 参考链接
1. [The Go Programming Language](https://go.dev/)
2. [Documentation-Download and install](https://go.dev/doc/install)
3. [Go语言中文网](https://studygolang.com/dl)
4. [Go Packages](https://pkg.go.dev/)