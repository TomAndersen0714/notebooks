# HTML5+CSS3基础学习笔记

## 前言

本文主要是基于《HTML5+CSS3+JavaScript》书籍，以及“W3Cschool”、“菜鸟教程”中关于HTML、CSS、JavaScript的教程撰写，具体内容可参考相关书籍或在线链接。


## HTML

### 简介

超文本标记语言（HyperText Markup Language，HTML）是一种用于创建网页的标准标记语言。开发人员可以使用HTML来建立自己的Web站点，HTML运行在浏览器上，由浏览器来解析。

每个HTML页面，都是由一系列元素组成的，其中每个HTML元素指的是从开始标签（start tag）到结束标签（end tag）的所有代码。

HTML文件以`.html`和`.htm`后缀为结尾。


### HTML元素

HTML 文档由相互嵌套的 HTML 元素构成。大多数 HTML 元素可以嵌套（HTML 元素可以包含其他 HTML 元素）。

#### HTML元素语法

1. HTML元素以**起始标签（opening tag），或开始标签**为开始，以**闭合标签（closing tag），或结束标签**为终止。
2. 起止标签之间的内容即为**元素的内容**，某些元素的内容可以为空（empty content）。
3. 内容永远为空的元素被称为**空元素**，空元素可以直接在开始标签中进行终止。
4. **HTML标签必须小写**。
5. 大多数HTML元素都拥有属性。
6. 大多数HTML元素都支持嵌套其他HTML元素，HTML文档由相互嵌套的HTML元素组成。

**HTML5常见的空元素：**

```html
<area>
<base>
<br>
<col>
<colgroup>
<command>
<embed>
<hr>
<img>
<input>
<keygen>
<link>
<meta>
<param>
<source>
<track>
<wbr>
```


#### HTML5文档的基础元素

##### 最小的HTML5文档
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

##### DOCTYPE声明

HTML 文档类型声明，也被称为 DOCTYPE，是每个 HTML 或 XHTML 文档中需要的第一行代码。DOCTYPE 声明是给 Web 浏览器的一个指令，说明网页是用什么版本的 HTML 写的，这可以确保不同的网络浏览器对网页的解析方式是相同的。

文档类型声明必须声明在首行，在HTML5标准中，HTML文档类型声明统一为`<!DOCTYPE html>`

[Document type declaration](https://en.wikipedia.org/wiki/Document_type_declaration#HTML5_DTD-less_DOCTYPE)
[HTML 中的 DOCTYPE 声明是什么](https://www.freecodecamp.org/chinese/news/what-is-the-doctype-declaration-in-html/)


##### html元素

一个HTML文档的所有其他元素都被包含在一个html元素中，所有其他的元素都被包含在html元素的html起止标签中。

即所有其他的元素都是html元素的子元素，html是所有元素的父元素。

通常在`<html>`起始标签中，通常需要声明`lang`属性，表示当前html文档内容所使用的语言，用于告知搜索引擎和浏览器。如`lang="en"`表示当前文档内容使用的是英文，`lang="zh-CN"`则表示当前文档内容使用的是中文。

在W3C推荐标准中，在每个文档中都应该声明html标签的lang属性，这样有助于浏览器和搜索引擎更好地识别页面的语言，提供更好的用户体验和搜索结果，如：主动提示是否需要将页面进行翻译。

HTML语言代码参考手册: https://www.runoob.com/tags/html-language-codes.html


##### head元素

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


#### HTML常见元素（标签）

##### `<br>`换行符元素

br是一个空元素，可以理解为HTML文档的换行符
```html
<p> 使用 br 元素<br>在文本中<br>换行。 </p>
```

##### `<div>`块级元素

div元素是无特殊含义的**块级元素**，每个div元素渲染时都会从新的一行开始显示，可以通过CSS设置高度、内外边距等样式


##### `<span>`行内元素

span元素是无特殊含义的**行内元素**，它在渲染时可以与同级元素在一行显示（块级元素除外），可以用于元素分组，使它们以不同的样式显示

##### `<p>`段落元素

p元素表示当前文本内容是一个新的段落，也属于一种块级元素。
```html
<p>这是一个段落。</p>
```


##### `<script>` JS脚本元素

script元素一般放置在HTML文档的head标签内部，作为head元素的直接子元素，用于引入外部的JavaScript文件

但script元素也可以放置在其他标签内部（如：body标签），相当于直接运行对应的JavaScript脚本代码，如：
```html
<body>
    <script>
        document.write("Hello World!")
    </script> 
</body>
```
## 

#### HTML5元素标签手册
https://www.w3cschool.cn/htmltags/html-reference.html

### HTML属性

HTML 属性是 HTML 元素的附加信息

示例：
```html
<a href="https://www.w3cschool.cn">这是一个链接使用了 href 属性</a>
```

#### HTML属性语法

1. HTML属性一般定义在HTML元素的开始标签中
2. HTML属性总是以键值对的形式出现，即属性名（key）和属性值（value），如`name="value"`
3. HTML属性名（key）和属性值（value）都应该小写
4. HTML属性值（value）应该始终被包括在双引号内，如果属性值中带有双引号，则需要使用单引号


#### HTML常见属性

##### 全局通用属性

###### class
为html元素定义一个或多个类名（classname）(类名从CSS样式文件引入)

###### id
定义元素的唯一id，不应该以数字为开头，虽然浏览器容忍单个文档出现重复id，但是**规范中禁止重复id**

###### style
规定元素的内联样式（inline style）

###### title
描述了元素的额外信息（常作为工具条使用）

###### lang
通常在`<html>`标签中使用，表示当前html文档内容所使用的语言，用于告知搜索引擎和浏览器。如`lang="en"`表示当前文档内容使用的是英文，`lang="zh-CN"`则表示当前文档内容使用的是中文。有助于浏览器和搜索引擎更好地识别页面的语言，提供更好的用户体验和搜索结果。


##### 全局通用属性手册

https://www.w3cschool.cn/htmltags/ref-standardattributes.html
https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes


##### 非全局通用属性

###### src
src属性用于指定资源的URL，用于元素引入外部资源。目前支持src属性的标签有：
```
<audio>, <embed>, <iframe>, <img>, <input>, <script>, <source>, <track>, <video>
```

### HTML实体

HTML Entity实体，指的是一个以ampersand (&)为开头，以semicolon (;)为结尾的字符串片段。

某些HTML保留字，无法直接在HTML元素中直接使用，否则会被视为HTML代码，故需要使用特殊字符组合进行等价替换，而这些特殊字符的组合，则被称为HTML Entity。

如符号`<`，在HTML文档中，需要被替换为`&lt;`，才能正常显示。

https://developer.mozilla.org/en-US/docs/Glossary/Entity


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
1. 外联式Linking（也叫**外部样式**）：将网页链接到外部样式表。
2. 嵌入式Embedding（也叫**内页样式**）：在网页上创建嵌入的样式表。
3. 内联式Inline（也叫**行内样式**）：内嵌样式到各个网页元素。

其中，**优先级：内联式 > 嵌入式 > 外联式**，通常情况下样式都以外部样式保存在.css文件中，通过引入外部样式表，可以在不同的html文档页面中使用相同的样式


#### CSS样式表的分类及优先级

当同一个HTML元素定义了多个样式时，高优先级的样式会覆盖低优先级样，优先级如下
1. 浏览器缺省设置
2. **外部样式表(External style sheet)**：以 **.css文件**的形式声明的样式表，通过文件的方式进行引入
3. **内部样式表(Internal style sheet)**：在HTML文档中，以` <style> `**标签**声明的元素，并通常放置于` <head> `标签内部，作为其子元素
4. **内联样式表、行内样式表(Inline style)**：在 HTML 元素的起始标签内部**style属性**中设定css样式，适用于修改简单样式

内联样式（在 HTML 元素内部）拥有最高的优先权，这意味着它将优先于以下的样式声明：标签中的样式声明，外部样式表中的样式声明，或者浏览器中的样式声明（缺省值）。

### CSS语法

#### CSS实例

CSS实例的声明由两个主要部分构成，选择器（Selector）和声明（Declaration）。
1. 多个选择器（Selector）之间使用特殊符号进行连接，不同的连接符号，代表不同的元素选择规则。
2. 每个声明（Declaration）使用英文分号作结尾，所有声明使用花括号进行囊括。

声明（Declaration）由属性（property）和属性值（value）组成，每个属性（property）都有一个属性值（value）与其一一对应，属性和值之间使用英文冒号分割。

CSS实例的示例：
```css
h1, h2 {
    color: red;
    text-align: center;
}
```


#### CSS注释

CSS注释以 \/\* 开始, 以 \*\/ 结束, 实例如下:
```css
/*这是个注释*/
p {
    text-align: center;
    /*这是另一个注释*/
    color: black;
    font-family: arial;
}
```


### CSS选择器

id和class是HTML元素中最常用的两个属性，CSS的id和class选择器可以**为具有对应属性的HTML元素设置对应的展示样式**。

通常HTML元素的id属性的属性值不允许重复，但class属性有别于id属性，相同的class属性值，可以在相同HTML文档中的多个HTML元素中重复使用。

在CSS3中，支持了多种多样的选择器，除了支持元素选择器、id和class选择器外，还支持以通配符的方式来定位需要渲染的元素。

**多个选择器（Selector）之间使用使用不同的符号（如：英文逗号、空格等）进行连接时，其指定元素的方式也不相同**。


#### CSS选择器分类

[CSS3选择器归类整理](https://www.w3cschool.cn/css3/css3-selector.html)

##### 基本选择器


###### 元素/标签选择器

CSS中元素选择器，即以 HTML 标签作为 CSS 修饰所用的选择器，相当于直接指定对应标签囊括的内容，需要使用当前的CSS实例来进行渲染。如：

```CSS
p {
    text-align: center;
}
```



###### id选择器

CSS中id选择器，以`#`符号开头来定义，格式为`#id`，如下的CSS样式定义，仅应用于属性id的属性值为"para1"的元素，如：

```css
#para1 {
    text-align: center;
    color: red;
}
```


###### class类选择器

CSS中的Class选择器，以一个点`.`符号开头定义，格式为`.class`，用于描述一组class属性值相同的元素的样式，如：
```CSS
.center {
    text-align: center;
}
```

或同时声明HTML元素，即仅针对特定的HTML元素生效
```CSS
p.center {
    text-align: center;
}
```


###### 分组/群组选择器

分组选择器，实际上多个选择器的一个组合，命中的是各个子选择器命中的元素的并集。多个选择器之间，使用英文逗号进行连接。如：

```CSS
h1,h2 {
    text-align: center;
}
```


##### 层次选择器

###### 后代选择器（包含选择器）
[CSS的选择器——逗号、空格分隔和连写的区别](https://blog.csdn.net/qq_40314214/article/details/106819267)

`E F`，代表匹配元素E中包含的所有元素F，即`F是E的后代`，不限层级

###### 子代选择器

`E>F`，代表匹配元素E的直接包含子元素F，即`F是E的直接子元素`，仅一层

###### 兄弟选择器

`E~F`，代表匹配元素E之后，同层级范围内的所有元素F，即`F是E的后继兄弟元素`

###### 相邻选择器

`E+F`，代表匹配与元素E之后，同层级直接相邻的元素F


##### 其他选择器


#### CSS选择器参考手册

CSS选择器，除了id和class选择器外，还有很多其他类别的选择器，用于筛选HTML元素，并给筛选的元素指定对应的样式，如使用通配符、元素名来选择HTML元素的选择器
https://www.w3cschool.cn/cssref/css-selectors.html


### CSS属性


#### CSS属性参考手册
https://www.w3cschool.cn/cssref/3ps212do.html


### CSS样式声明和引入方式

#### 外部样式表

外部样式，在`.css`文件中声明，在HTML文档的`head`元素的`link`空元素中进行引入。

当CSS样式需要同时应用于很多页面时，外部样式表将是理想的选择。在使用外部样式表的情况下，可以通过改变一个文件来改变整个站点的外观。

CSS外部样式表可以在任何文本编辑器中进行编辑。CSS文件不能包含任何的 html 标签。样式表应该以 .css 扩展名进行保存。下面是一个样式表文件的例子：

```css
hr {
    color: rgb(255, 0, 0);
}

h1 {
    color: green;
}

p {
    margin-left: 20px;
}

body {
    background-image: url("../img/background.jpg");
}
```


每个页面的元素使用标签`<link>`来链接样式表。`<link>`标签需要声明在HTML文档的头部：

```html
<head>
    <link rel="stylesheet" type="text/css" href="mystyle.css">
</head>
```



#### 内部样式表

内部样式，在HTML文档的`head`元素的`style`子元素中进行声明。

当单个HTML文档需要特殊的样式时，就应该使用内部样式表，便可以使用`<style>`标签在HTML文档的元素`<head>`（即HTML头）内定义内部样式表，如：
```html
<head>
    <style>
        hr {
            color: sienna;
        }

        p {
            margin-left: 20px;
        }

        body {
            background-image: url("images/back40.gif");
        }
    </style>
</head>
```


#### 内联样式

内联样式，位于对应的HTML元素的起始标签内部，通过`style`属性进行声明。

内联样式表，是当将内容和样式混杂在一起，会损失掉样式表的许多优势，使用时需要慎重。内两样式表，仅针对当前声明时所在的元素生效，且不需要声明CSS选择器。还需要在对应的HTML元素的起始标签内，使用`style`属性，即样式属性，来声明当前元素所需要使用的样式即可。如：

```css
<p style="color:sienna;margin-left:20px">这是一个段落。</p>
```


#### CSS多重样式规则

当同一个 HTML 元素被不止一个样式定义时，会使用哪个样式呢？一般根据对应CSS样式的权值来决定多重样式中应该选择哪一个

##### CSS样式之间的优先级

通常情况下，优先级如下所示：
**（内联样式）Inline style > （内部样式）Internal style sheet ≈（外部样式）External style sheet > 浏览器默认样式**

其中值得注意的是，内部样式（Internal Style Sheet）和外部样式（External Style Sheet）之间的优先级，取决于两者的声明先后顺序。

如果外部样式的引入声明（即`<head>`标签的`<link>`子标签）的位置，在内部样式表的声明（即`<head>`标签的`<style>`子标签）之后，则外部样式表会覆盖内部样式表中的属性。

但一般而言，外部样式表的声明都在`<head>`标签中的首几行，且在内部样式表之前。


##### 权重计算

内联样式表的权值为1000
ID类选择器（即通过元素的id属性值匹配的选择器）的权值为100
Class类选择器（即通过元素的class属性值匹配的选择器）的权值为10
HTML标签选择器（即通过元素的标签名匹配的选择）的权值为1

如：
```html
<!DOCTYPE html>
<html>

<head>
    <style type="text/css">
        #redP p {
            /* 权值 = 100+1=101 */
            color: #F00;
            /* 红色 */
        }

        #redP .red em {
            /* 权值 = 100+10+1=111 */
            color: #00F;
            /* 蓝色 */
        }

        #redP .red span em {
            /* 权值 = 100+1+1+1=103 */
            color: #000;
            /*黑色*/
        }
    </style>
</head>

<body>
    <div id="redP">
        <p class="red">red
        
            <em>em red</em>
            <span><em>em red</em></span>
        </p>
        <p>red</p>
    </div>
</body>

</html>
```


##### !important

当 !important 规则被应用在一个样式声明中时，该样式声明会覆盖 CSS 中任何其他的声明，无论它处在声明列表中的哪里。尽管如此，!important 规则还是与优先级毫无关系。使用 !important 不是一个好习惯，因为它改变了你样式表本来的级联规则，从而使其难以调试。



## 常用帮助手册

### HTML5元素标签手册
https://www.w3schools.com/tags/default.asp
https://www.w3cschool.cn/htmltags/html-reference.html

### HTML5元素属性参考手册
https://www.w3schools.com/tags/ref_attributes.asp

### HTML5元素全局通用属性手册
https://www.w3cschool.cn/htmltags/ref-standardattributes.html

### CSS参考手册
https://www.w3cschool.cn/cssref/

### CSS选择器参考手册
https://www.w3cschool.cn/cssref/css-selectors.html

### CSS属性参考手册
https://www.w3cschool.cn/cssref/3ps212do.html


## 参考链接
1. [W3Cschool - HTML/CSS教程](https://www.w3cschool.cn/tutorial)
2. [菜鸟教程-HTML5教程](https://www.runoob.com/html/html-tutorial.html)
3. [菜鸟教程-CSS教程](https://www.runoob.com/css/css-tutorial.html)
4. [菜鸟教程-CSS3教程](https://www.runoob.com/css3/css3-tutorial.html)
5. [CSS属性在线视觉引导](https://www.w3cschool.cn/moresource/cssreference/index.html)
6. [W3School - HTML References](https://www.w3schools.com/html/default.asp)