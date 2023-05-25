# Vue基础学习笔记

## 前言





## 简介

Vue (发音为 /vjuː/，类似 view) 是一款用于构建用户界面的 JavaScript 框架。它基于标准 HTML、CSS 和 JavaScript 构建，并提供了一套声明式的、组件化的编程模型，帮助你高效地开发用户界面。无论是简单还是复杂的界面，Vue 都可以胜任。

https://cn.vuejs.org/guide/introduction.html


### Vue的特点

1. 采用**组件化**模式，提高代码复用率，且让代码更好维护
2. **声明式**编码，让编码人员无需直接操作DOM，提高开发效率
3. 使用**虚拟DOM**，和优秀的**Diff算法**，尽量复用DOM节点

### Vue核心功能

声明式渲染：Vue 基于标准 HTML 拓展了一套模板语法，使得我们可以声明式地描述最终输出的 HTML 和 JavaScript 状态之间的关系。

响应性：Vue 会自动跟踪 JavaScript 状态并在其发生变化时响应式地更新 DOM。


### Vue API风格

#### 选项式 API (Options API)


#### 组合式 API (Composition API)



## Quick Start

使用官方脚手架工具create-vue初始化一个Vue3项目。
```bash
npm init vue@latest
```
PS：`npm init vue@latest`命令实际上是先安装vue，然后转换为`npm exec create-vue@latest`命令安装create-vue package，以及执行初始化vue项目的脚本。


## Vue.js开发常用工具

https://cn.vuejs.org/guide/scaling-up/tooling.html


## Vue.js术语表

**单文件组件（Single File Component，SFC）**
https://cn.vuejs.org/guide/scaling-up/sfc.html

**路由（Router）**
https://cn.vuejs.org/guide/scaling-up/routing.html

**容器（Container）**
在Vue3中，容器（container）是指一个可以包含其他Vue组件的HTML元素或Vue组件。容器可以用来在页面中布局其他组件，或者用来封装其他组件以提供特定的功能。

常见的容器包括`<div>`、`<section>`、`<main>`等HTML元素，以及Vue提供的`<component>`、`<transition>`等组件。


## Vue awesome

https://github.com/vuejs/awesome-vue

## 参考链接

1. [Vue.js - Documentation](https://cn.vuejs.org/guide/introduction.html)
2. [Vue.js - API](https://cn.vuejs.org/api/)
3. [W3CScheool-Vue3教程](https://www.w3cschool.cn/vuejs3/)
4. [Element Plus](https://element-plus.gitee.io/zh-CN/)
5. [Bilibili - 尚硅谷Vue2.0+Vue3.0全套教程丨vuejs从入门到精通](https://www.bilibili.com/video/BV1Zy4y1K7SH/)
