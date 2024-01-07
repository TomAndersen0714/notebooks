# JavaScript基础教程

## JavaScript简介

JavaScript是世界上最流行的脚本语言。JavaScript是属于Web的语言，适用于PC、笔记本电脑、平板电脑和手机，最初是为了在浏览器中运行而设计出来。

JavaScript 最初被设计为向 HTML 页面增加交互性。许多 HTML 开发者都不是程序员，但是 JavaScript 却拥有非常简单的语法，几乎每个人都有能力将小的 JavaScript 代码块添加到 HTML 文档中。

JavaScript是Web开发人员必学的三门语言之一：
1. HTML定义了网页的内容和层级
2. CSS定义了网页内容的样式
3. JavaScript定义了网页元素的行为

JavaScript 是一种轻量级的脚本编程语言，支持插入 HTML 文档中，可由所有的现代浏览器执行。其中 JavaScript 标准版本，被称为 ECMAScript，由 Ecma 国际组织维护和发布，目前主流的标准版本为 `ECMAScript 6, ES6`。

JS是一种编程语言，可以实现具体的算法和应用程序，而HTML和CSS只是一种标记和描述性语言。


>ChatGPT 3.5: 
>JavaScript 是一种脚本语言，通常用于创建交互式网页和 Web 应用程序。它由 Brendan Eich 在 1995 年创造，最初被命名为 LiveScript，后来更名为 JavaScript。JavaScript 是一种动态、解释型语言，可在 Web 浏览器中运行。它不需要编译，而是在运行时解释。JavaScript 可以与 HTML 和 CSS 集成，允许创建动态网页和复杂的 Web 应用程序。
>
>JavaScript 的语法类似于 C 和 Java，具有面向对象的特性。它支持变量、数组、条件语句、循环语句、函数等基本编程结构。JavaScript 还有一些特殊的特性，例如闭包、异步编程、原型继承等，这些特性可以让开发人员编写出更加优秀的代码。
>
>除了在 Web 浏览器中使用，JavaScript 还可以在服务器端使用，例如使用 Node.js 。 Node.js 可以使 JavaScript 运行在服务器上，以实现 Web 应用程序、命令行工具等。此外，JavaScript 也被广泛用于开发移动应用程序、桌面应用程序和游戏等。



## JS开发环境搭建

参考《JavaScript开发环境搭建》


## JS基础


### JavaScript Runtime Environment

#### Browser

##### chrome
https://www.runoob.com/js/js-chrome.html


##### Firefox

#### Node.js



### JavaScript Module

#### JavaScript Module标准

https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Guide/Modules
https://www.ruanyifeng.com/blog/2020/08/how-nodejs-use-es6-module.html

JavaScript Module标准并未实现统一，在不同的运行环境中，其语法也存在大大小小的差异（如：Browser、Node.js等）。

目前最主流的两种标准，一种是`CommonJS`，即Node.js中使用的Module标准，支持同步和动态加载，主要用于Node.js服务端项目中，使用 require 和 module.exports 或 exports 关键字来声明和使用模块。

另外一种是`ES6 Module`，是JavaScript的2015官方标准，即`ECMAScript 6, ECMAScript 2015, ES6`，支持静态编译和动态编译，主要用于在浏览器（Browser）上运行的客户端项目中，使用 import 和 export 关键字来声明和使用模块。


#### Browser Environment

##### \<script type="module">


##### \<script type="module" src="...">



#### Node.js Environment

##### ECMAScript modules in Node.js

https://nodejs.org/dist/latest-v18.x/docs/api/esm.html
https://nodejs.org/docs/latest-v18.x/api/esm.html#enabling
https://nodejs.org/docs/latest-v18.x/api/packages.html#determining-module-system

在Node.js默认是使用CommonJS Module语法，要想使用ECMAScript的模块语法，需要进行调整。

方法一（推荐）：
在ES6 Module文件的同级路径下添加Node.js package.json文件，并将的type值设置为module，即`"type": "module"`。
**一般在开发项目时，会在项目根目录下生成对应的`package.json`文件，故在根目录的package.json文件中增加`"type": "module"`条目即可实现项目全局生效ES6语法。**


方法二：
将项目中所有使用ES6 Module语法（即export、import）的JS文件后缀，设置为`.mjs`；


##### CommonJS modules in Node.js
https://nodejs.org/dist/latest-v18.x/docs/api/modules.html
https://nodejs.org/docs/latest-v18.x/api/modules.html#enabling


## DOM（Document Object Model）
https://www.runoob.com/js/js-htmldom.html

当网页被加载时，浏览器会创建页面的文档对象模型DOM（Document Object Model）。每个HTML元素都有与之一一对应的DOM元素对象，可以将DOM元素对象视为运行态的HTML元素。

通过可编程的对象模型，JavaScript 获得了足够的能力来修改动态的 HTML：
1. JavaScript 能够改变页面中的所有 HTML 元素
2. JavaScript 能够改变页面中的所有 HTML 属性
3. JavaScript 能够改变页面中的所有 CSS 样式
4. JavaScript 能够对页面中的所有事件做出反应


### HTML Document（文档对象）

#### methods

##### document.getElementById()

返回对拥有指定 id 的第一个对象的引用。


Demo: https://www.w3cschool.cn/tryrun/showhtml/tryjsref_document_getelementbyid

```html
<!DOCTYPE html>
<html>
<body>

<p id="demo">Click the button to change the text in this paragraph.</p>

<button onclick="myFunction()">Try it</button>

<script>

    function myFunction() {
        document.getElementById("demo").innerHTML = "Hello World";
    };

</script>

</body>
</html>
```


#### properties


### HTML Element（元素对象）


#### methods


#### properties


##### innerHTML

设置或者返回HTML元素的内容，即起止标签之间的内容。

示例: https://www.w3cschool.cn/tryrun/showhtml/tryjsref_elmnt_innerhtml

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>菜鸟教程(runoob.com)</title>
<script>
function changeLink(){
	document.getElementById('myAnchor').innerHTML="RUNOOB";
	document.getElementById('myAnchor').href="//www.runoob.com";
	document.getElementById('myAnchor').target="_blank";
}
</script>
</head>
<body>
 
<a id="myAnchor" href="//www.microsoft.com">Microsoft</a>
<input type="button" onclick="changeLink()" value="修改链接">
 
</body>
</html>
```


### HTML Attributes（属性对象）


### HTML Events（事件对象）



### DOM参考手册

[JavaScript 和 HTML DOM 参考手册](https://www.w3cschool.cn/jsref/jsref-tutorial.html)


## BOM（Browser Object Model）
https://www.runoob.com/js/js-window.html
BOM（Browser Object Model）浏览器对象模型



## 事件处理

JavaScript 能够对页面中的所有事件做出反应


## Ajax

Asynchronous JavaScript and XML



## 开发规范

### Google开发规范

[Google 风格指南中文版](https://github.com/zh-google-styleguide/zh-google-styleguide)
[Google 风格指南-中文版-JavaScript](https://zh-google-styleguide.readthedocs.io/en/latest/google-javascript-styleguide/contents/)
[Google style guide](https://github.com/google/styleguide)

JavaScript文件名命名规范通常遵循以下几个约定：
1. 文件名应该具有描述性，以便其他开发人员可以很容易地理解文件的用途。
2. 文件名应该全部使用小写字母，避免使用大写字母和特殊字符。
3. 如果文件名包含多个单词，则可以使用连字符（-）将它们连接在一起，以提高可读性。例如：my-script.js。
4. 对于包含JavaScript模块的文件，通常会使用“module”单词作为文件名的一部分，例如：app-module.js。

JavaScript规范中要求，任何表达式结尾都必须使用分号。


## 参考链接
1. [菜鸟教程 - JavaScript教程](https://www.runoob.com/js/js-tutorial.html)
2. [W3CSchool - JavaScript教程](https://www.w3cschool.cn/javascript/)
3. [W3School - JavaScript Tutorial](https://www.w3schools.com/js/default.asp)
4. [MDN Web Doc - JavaScript Guide]https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide)