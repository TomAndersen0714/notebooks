# Node.js基础教程

## 简介

> ChatGPT 3.5: 
> Node.js是一种开源的跨平台JavaScript运行时环境，它基于Chrome V8 JavaScript引擎构建，由C++编写，使JavaScript可以在服务器端运行。
> 
> Node.js主要用于构建高效、可伸缩的网络应用程序和后端服务，例如Web服务器、API服务器、实时通信应用程序等。它还支持一些流行的Web框架和库，如Express、Socket.IO等。由于其高效性和易用性，Node.js在Web开发中变得越来越流行。


https://developer.mozilla.org/en-US/docs/Glossary/Node.js


## 安装教程

### Windows安装步骤

直接下载对应平台的LST版本msi安装文件后，使用对应安装包进行安装即可。
PS：安装时，如果指定自定义文件夹，则建议新建nodejs文件夹，因为自定义路径后，安装程序会直接将所有内容安装到指定文件夹而非新建nodejs文件夹。

安装完成后，cmd窗口下，使用`node -v`、`npm -v`命令验证安装结果。


### Linux安装步骤

下载完对应版本压缩包，在指定文件夹中使用`tar -xvf node-v18.15.0-linux-x64.tar.xz`命令解压即可

https://blog.csdn.net/qq_45830276/article/details/126022778


## 基础概念


## Module

Module是Node.js中可重用的代码单元，定义和JavaScript中的Module相同。Module可以是一个单独的文件，也可以是一个目录（其中包含一个包含index.js文件的package.json描述文件）。Module可以通过导入（require语句）在其他模块中使用。Module还可以导出（module.exports或exports对象）变量、函数和对象等。

#### CommonJS modules
https://nodejs.org/dist/latest-v18.x/docs/api/modules.html


#### ECMAScript modules
https://nodejs.org/dist/latest-v18.x/docs/api/esm.html



#### node:module API
https://nodejs.org/docs/latest-v18.x/api/module.html

## Package

在Node.js中，一个Package通常被定义为一个具有描述文件package.json的目录。package.json文件描述了该Package的属性，例如名称、版本、作者、依赖项等。Package 可以包含多个模块（Module）。
https://nodejs.org/dist/latest-v18.x/docs/api/packages.html


### package.json

https://docs.npmjs.com/cli/v9/configuring-npm/package-json


### index.d.ts

### index.js

### index.d.cts

### index.cjs


## 参考链接

1. [nodejs org](https://nodejs.org/en/)
2. [Node.js v18.16.0 documentation](https://nodejs.org/dist/latest-v18.x/docs/api/)