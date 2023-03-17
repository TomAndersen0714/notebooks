# HTML5+CSS3+JavaScript入门学习笔记

## 前言

本文主要是基于《HTML5+CSS3+JavaScript》书籍，以及“W3Cschool”、“菜鸟教程”中关于HTML、CSS、JavaScript的教程撰写，具体内容可参考相关书籍或在线链接。


## HTML

### 简介

超文本标记语言（HyperText Markup Language，HTML）是一种用于创建网页的标准标记语言。开发人员可以使用HTML来建立自己的Web站点，HTML运行在浏览器上，由浏览器来解析。

每个HTML页面，都是由一系列元素组成的，其中每个HTML元素指的是从开始标签（start tag）到结束标签（end tag）的所有代码。

HTML文件以`.html`和`.htm`后缀为结尾。


### HTML5文档的基本组成元素

#### 最小的HTML5文档
```html
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>文档标题</title>
</head>

<body>
    文档内容......
</body>

</html>
```

#### DOCTYPE声明

HTML 文档类型声明，也被称为 DOCTYPE，是每个 HTML 或 XHTML 文档中需要的第一行代码。DOCTYPE 声明是给 Web 浏览器的一个指令，说明网页是用什么版本的 HTML 写的。这可以确保不同的网络浏览器对网页的解析方式是相同的。

文档类型声明必须声明在首行，在HTML5标准中，HTML文档类型声明统一为`<!DOCTYPE html>`

[Document type declaration](https://en.wikipedia.org/wiki/Document_type_declaration#HTML5_DTD-less_DOCTYPE)
[HTML 中的 DOCTYPE 声明是什么](https://www.freecodecamp.org/chinese/news/what-is-the-doctype-declaration-in-html/)


#### html元素

一个HTML文档的所有其他元素都被包含在一个html元素中，所有其他的元素都被包含在html元素的html起止标签中。

即所有其他的元素都是html元素的子元素，html是所有元素的父元素。

###### head元素

标签表示文档的头部，其中包含了与该文档有关的描述信息，同时`<head>` 元素是所有头部元素的容器。

`<head>`元素必须包含文档的标题（即title元素），可以包含脚本、样式、meta 信息 以及其他更多的信息。

以下列出的元素能被用在`<head>`元素内部：
```
<meta>
<title> （在头部中，这个元素是必需的）
<style>
<base>
<link>
<script>
<noscript>
```


###### meta元素

` <meta> `元素仅在` <head> `元素内部使用，用于声明 HTML 文档的元数据信息，如：
```html
<head>
    <meta name="description" content="免费在线教程">
    <meta name="keywords" content="HTML,CSS,XML,JavaScript">
    <meta name="author" content="w3cschool">
    <meta charset="UTF-8">
</head>
```


###### title元素

` <title> `元素仅在` <head> `元素内部使用，用于定义html文档的标题
```html
<head>
    <meta charset="utf-8">
    <title>文档标题</title>
</head>
```


##### body元素
` <body> ` 标签定义文档的主体。
` <body> ` 元素包含文档的所有内容（比如文本、超链接、图像、表格和列表等等）。

```html
<body>
    <h1>章节标题</h1>
    文档内容
</body>
```


## CSS

### 简介

为了满足日益丰富的网页设计需求，HTML不断添加各种各样的元素，即不同的显示标签和属性，于是就带来一个问题，网页结构和样式混用让网页代码变得混乱不堪，代码冗余增加了带宽负担，代码维护也变得苦不堪言，CSS便应运而生。

#### 什么是CSS

1. CSS是Cascading Style Sheet的缩写，译为“层叠样式表”或“级联样式表”
2. CSS文件通常以`.css`为结尾
3. CSS 是一种标记语言，属于浏览器解释型语言，可以直接由浏览器执行，不需要编译
4. CSS定义浏览器如何显示HTML或XML的标签对应的元素内容，用于设计网页的外观展示效果

#### 使用CSS的优势

1. 通过使用CSS，实现HTML文档的内容与展示样式分离，实现了代码解耦，增强了代码复用率，极大地提高了工作效率。
2. 使用CSS可以减少网页的代码量，增加网页的浏览速度。

#### 如何使用CSS

有三种方法可以在站点网页上使用样式表：
1. 外联式Linking（也叫外部样式）：将网页链接到外部样式表。
2. 嵌入式Embedding（也叫内页样式）：在网页上创建嵌入的样式表。
3. 内联式Inline（也叫行内样式）：应用内嵌样式到各个网页元素。

其中，优先级：内联式 > 嵌入式 > 外联式


### CSS语法



## 其他

### HTML5元素标签手册
https://www.runoob.com/tags/html-reference.html

### HTML5元素属性手册
https://www.runoob.com/tags/ref-standardattributes.html



## 参考链接
1. [W3Cschool - HTML/CSS教程](https://www.w3cschool.cn/tutorial)
2. [HTML5教程-菜鸟教程](https://www.runoob.com/html/html-tutorial.html)