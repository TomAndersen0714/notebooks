# IDEA 如何查看 Scala 隐式转换和隐式参数的调用

使用 IDEA `Scala` 插件的 X-Ray 功能，即可显示编辑器当前文件中的隐式调用，`File | Settings | Languages & Frameworks | Scala | X-Ray Mode`。此功能在 Scala 插件 [2023.3.16](https://plugins.jetbrains.com/plugin/1347-scala/versions/stable/446506) 版本之后开始支持，对应的 IDEA 版本，也是 `2023.3` 版本之后。

Scala 插件的 `X-Ray` 功能的按钮在 Scala 源文件编辑器右上角：

![](resources/images/Pasted%20image%2020241012091410.png)

`X-Ray` 功能触发的默认快捷键为，双击 Ctrl 并保持按压：

![](resources/images/demonstrate.gif)

`X-Ray` 功能的配置页面如下：

![](resources/images/Pasted%20image%2020241011081636.png)