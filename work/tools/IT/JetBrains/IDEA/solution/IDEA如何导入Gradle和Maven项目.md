# IDEA 如何导入 Gradle 和 Maven 项目

IDEA 打开/导入 Gradle/Maven 项目后，会自动执行 `gradle dependencies` 和 `mvn dependency:resolve` 等命令，来解析和下载当前 Java 项目的依赖。

导入之前，需要检查对应的 JDK 和 Maven 环境都已经搭建完成，而 Gradle 则可以通过项目自带的 Gradle Wrapper 脚本来自动下载。

## Maven

1. 打开、导入 Maven 项目后，配置使用本地安装的 Maven，不然默认会使用 IDEA 内置的 Maven，而 IDEA 内置的 Maven 经常有 BUG；[IDEA如何配置Maven](work/tools/IT/JetBrains/IDEA/solution/IDEA如何配置Maven.md)
2. 命令行执行 Maven 命令行解析依赖 `mvn dependency:resolve`；
3. 如果 Maven 命令执行完成之后，IDEA 依旧无法识别，可能是 IDEA 中内置的 Maven 已经下载，但下载不完全，需要通过 Maven 命令行指定下载对应的依赖，对应命令参考 [Maven命令行基础教程](work/programming/Java/tools/Apache-Maven/Maven命令行基础教程.md)；
4. 命令行执行 `mvn compile -DskipTests` 命令编译当前项目，来测试项目的开发环境搭建是否正确
5. 最后再去 IDEA 的 Maven 侧边栏工具箱中，选择重新加载所有 Maven 项目，即 `Reload All Maven Projects`。选中对应项目的对应 `Lifecycle/compile`，并执行，执行成功则代表 IDEA 项目已经导入成功，相关的开发环境也已经准备完成。

## Gradle

1. 如果是普通的 Gradle 项目，直接导入，然后配置对应的 Gradle 路径即可；[IDEA如何配置Gradle](work/tools/IT/JetBrains/IDEA/solution/IDEA如何配置Gradle.md)
2. 如果是 Gradle Wrapper 项目，则需要先在命令行执行 `gradlew dependencies` 来自动下载对应版本的 Gradle 以及当前项目的依赖，之后 IDEA 才能正确识别当前项目所使用的 Gradle 版本。如果 Gradle 官方链接的下载速度太慢，或者需要使用私有仓库，则需要调整对应的下载 url，配置方式参考 [Gradle配置基础教程](work/programming/Java/tools/Gradle/Gradle配置基础教程.md)；
3. 命令行执行 `gradle build` 或者 `gradlew build` 命令编译当前项目，来测试项目的开发环境搭建是否正确
4. 最后再去 IDEA 的 Gradle 侧边栏工具箱中，选择重新加载所有 Gradle 项目，即 `Reload All Gradle Projects`。选中对应项目的对应 `Tasks/build/build`，并执行，执行成功则代表 IDEA 项目已经导入成功，相关的开发环境也已经准备完成。

## Gradle + Maven

IDEA 打开/导入同时包含 Gradle/Maven 模块的 Project 时，无法同时加载双方对应的配置文件和插件，需要自己手动将缺失的项目其作为 Module 进行导入。

在 `File | Settings | Project Structure | Project Settings | Modules` 下通过 `Import Module` 的方式手动导入对应的子项目，即可自动识别。

其中 Gradle 构建工具，只需要通过 `settings.gradle` 单向声明即可确定 root project 和 subproject 之间的关系，而 Maven 构建工具，则需要通过在 `pom.xml` 文件中双向声明，root project 和 subproject 中都需要声明，且Gradle 项目无法将 Maven 项目作为子项目。