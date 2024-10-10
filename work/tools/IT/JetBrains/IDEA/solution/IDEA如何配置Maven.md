# IDEA 如何配置 Maven

## Maven Home Path

IDEA 中一般会内置 Maven 工具，但是其内置的 Maven 工具，经常会出现各种 BUG，不要使用，直接使用本地安装的 Maven，或者使用 Maven Wrapper（如果项目中已经包含），通过 `Maven Home Path` 配置来控制使用的 Maven。

![](resources/images/Pasted%20image%2020241010085227.png)

## Change the JDK version

当出现 Maven 版本和 JDK 版本不兼容时，可能需要手动调整 JDK，建议在 `Project Structure` 中全局调整。

![](resources/images/Pasted%20image%2020241010084441.png)

## 参考链接

1. [Maven | IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/maven-support.html)