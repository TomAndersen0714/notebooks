# IDEA 如何导入 Gradle 和 Maven 项目

IDEA 打开同时包含 Gradle/Maven 项目的 Project 时，无法同时加载对应的配置文件和插件，需要自己手动将缺失的项目其作为 Module 进行导入。

在 `File | Settings | Project Structure | Project Settings | Modules` 下通过 `Import Module` 的方式手动导入对应的子项目，即可自动识别。

其中 Gradle 构建工具，只需要通过 `settings.gradle` 单向声明即可确定 root project 和 subproject 之间的关系，而 Maven 构建工具，则需要通过在 `pom.xml` 文件中双向声明，root project 和 subproject 中都需要声明，且Gradle 项目无法将 Maven 项目作为子项目。