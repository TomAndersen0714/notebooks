## npm install修改包镜像地址

1. 持续生效
查看npm安装包源地址，`npm config get registry`
修改npm为中国npm镜像，`npm config set registry http://registry.npmmirror.com`

2. 临时生效
也可以在npm安装时临时指定源地址，`npm install --registry=http://registry.npmmirror.com <pacakge_name>`


## npm install修改模块默认安装路径

https://blog.csdn.net/qq_41857060/article/details/104342920