# npm基础教程

## 前言

npm（Node Package Manager）是Node.js的包管理器，它用于安装、管理和共享JavaScript模块。

npm可以让开发人员轻松地安装和管理模块，以便在自己的项目中重复使用。

Node主要是用于运行JavaScript代码，而npm主要是用于管理和共享JavaScript模块。Node和npm通常一起使用，因为在使用Node构建应用程序时，需要使用npm安装和管理所需的模块。

Yarn是另一种常用的JavaScript包管理器，类似于npm，也是用于安装、管理和共享JavaScript模块。它由Facebook、Google和Exponent共同开发，旨在解决npm一些性能和安全性问题。Yarn提供了更友好的命令行界面和更详细的错误提示，以帮助开发人员更容易地诊断和解决问题。



## 常用命令

### npm

1. 查看当前版本：`npm -v`


### npm install

此命令用于安装指定package，以及同时安装此package中依赖的所有其他package。
安装时，会将`Node.js Module`放置于`node_modules`路径下，会将可执行文件放置于`node_modules/.bin`路径下。

其中package可以是以下类型中的任意一种：
```
a) a folder containing a program described by a package.json file
b) a gzipped tarball containing (a)
c) a url that resolves to (b)
d) a <name>@<version> that is published on the registry (see registry) with (c)
e) a <name>@<tag> (see npm dist-tag) that points to (d)
f) a <name> that has a "latest" tag satisfying (e)
g) a <git remote url> that resolves to (a)
```

语法：
```bash
npm install [<package-spec> ...]

aliases: add, i, in, ins, inst, insta, instal, isnt, isnta, isntal, isntall
```

常用选项：
1. `-g` or `--global`：全局安装Node.js的包，会应用于所有使用当前Node.js环境的项目，通常建议仅将一些重要的大型package采用全局安装的方式，而小型package则可以仅安装在当前项目路径下
2. `--registry=<url>`：安装时临时指定registry源地址，如`npm install --registry=http://registry.npmmirror.com <pacakge_name>`


### npm config

此命令用于管理npm配置文件

语法：
```bash
npm config set <key>=<value> [<key>=<value> ...]
npm config get [<key> [<key> ...]]
npm config delete <key> [<key> ...]
npm config list [--json]
npm config edit
npm config fix

alias: c
```


#### 修改registry

**持续生效**
1. 查看npm安装包源地址：`npm config get registry`
2. 修改npm为中国npm镜像：`npm config set registry http://registry.npmmirror.com`

**临时生效**
1. 也可以在npm安装时临时指定源地址，`npm install --registry=http://registry.npmmirror.com <pacakge_name>`


### npm ls

用于查看已安装的package。

常用选项：
`-g|--global`：查看全局环境下安装的包。


### npm init

用于初始化一个npm package，或者配置一个已经存在的package。

语法：
```bash
npm init <initializer>
```

其中`initializer`指的是一些按照`create-<initializer>`命名的npm package，此package在`npm init`命令执行时被安装后，会由`npm exec`命令执行其bin文件，可能会创建或者更新`package.json`文件，或者执行其他相关的初始化动作。

指定了`initializer`的`npm init`命令，等价于`npm exec`命令，如：
```bash
npm init foo -> npm exec create-foo
npm init @usr/foo -> npm exec @usr/create-foo
npm init @usr -> npm exec @usr/create
npm init @usr@2.0.0 -> npm exec @usr/create@2.0.0
npm init @usr/foo@2.0.0 -> npm exec @usr/create-foo@2.0.0
```



### npm run

执行npm package中package.json文件的`scripts`部分定义的脚本。

如package.json文件内容如下：
```json
{
  "name": "my-first-vue-project",
  "version": "0.0.0",
  "private": true,
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview",
    "test:unit": "vitest",
    "lint": "eslint . --ext .vue,.js,.jsx,.cjs,.mjs --fix --ignore-path .gitignore",
    "format": "prettier --write src/"
  },
  "dependencies": {
    "vue": "^3.2.47",
    "vue-router": "^4.1.6"
  },
  "devDependencies": {
    "@rushstack/eslint-patch": "^1.2.0",
    "@vitejs/plugin-vue": "^4.0.0",
    "@vue/eslint-config-prettier": "^7.1.0",
    "@vue/test-utils": "^2.3.0",
    "eslint": "^8.34.0",
    "eslint-plugin-vue": "^9.9.0",
    "jsdom": "^21.1.0",
    "prettier": "^2.8.4",
    "vite": "^4.1.4",
    "vitest": "^0.29.1"
  }
}

```

如，在执行`npm run format`命令，实际上执行`package.json`文件中`format`值的对应命令`prettier --write src/`，而此命令实际上则是执行`node_modules/.bin`路径下的可执行文件`prettier`，并附带参数`--write src/`。

### npm exec

执行指定npm package对应的可执行文件。默认从全局安装路径下定位对应的脚本。


### npx

和`npm exec`命令类似，都是用于执行指定npm package对应的可执行文件。但npx并不会从全局安装路径下定位package的脚本，而是在当前项目中寻找对应package，如果没有则会**临时安装**并执行对应脚本，执行完成之后，再删除掉对应的package。这样可以避免在本地安装一些不必要的package，同时也能保证脚本在执行时所依赖的package是最新的。

https://docs.npmjs.com/cli/v9/commands/npx



## 参考链接
1. [npm CLI Commands](https://docs.npmjs.com/cli/v9/commands)
2. [npm常用软件包](https://www.cnblogs.com/ajaemp/p/13810123.html)
