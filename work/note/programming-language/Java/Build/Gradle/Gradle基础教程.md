# Gradle基础教程


## What is Gradle?

Gradle是一个开源的自动化构建工具，用于构建、测试和部署软件项目。它具有强大的灵活性和可扩展性，可以用于构建各种类型的项目，包括 Java、Kotlin、Groovy 等语言编写的应用程序、库和插件。

Gradle 基于 Groovy 和 Kotlin 语言的领域特定语言（DSL）进行构建脚本编写，提供了一种声明式的方式来定义项目的构建过程。它通过构建脚本中的任务（Task）和依赖关系来描述项目的构建逻辑，并支持自定义任务、插件和构建流程的灵活配置。

Gradle 提供了强大的依赖管理机制，可以从远程仓库下载依赖库，并自动解决依赖冲突。它还支持增量构建，只编译和执行需要更新的部分，以加快构建速度。

Gradle 是一个成熟且广泛使用的构建工具，在许多开发者和组织中得到了广泛应用。它与常见的集成开发环境（IDE）如 IntelliJ IDEA 和 Android Studio 集成良好，并提供了丰富的插件生态系统，以支持各种开发场景和工具集成。


### Design

High performance

JVM foundation

Conventions

Extensibility

IDE support

Insight


### Terminology

#### Projects

在Gradle中，Project是Gradle Build的基本单位，每个Gradle项目的根目录下，都有一个包含构建配置的 Kotlin 脚本`build.gradle`，或Groovy脚本`build.gradle.kts`，用于定义任务、依赖项、插件和其他配置，其中Build过程可以包含多个项目Project和子项目Subproject。

#### Tasks

Task中包含执行特定工作的逻辑，如编译代码、运行测试或部署软件。Gradle提供了许多常见的构建任务，如内置的Java测试任务，还可以通过插件提供其他类型的任务，在大多数情况下，用户可以直接使用已有的Task构建脚本。

Task主要由动作（Actions）、输入（Inputs）和输出（Outputs）组成。


#### Plugins

插件允许在构建中引入除Task、File和dependency之外的新概念，例如source sets。

插件使得在多个项目Build过程中可以重用逻辑和配置，减少构建脚本中的重复编写。

通过适当地使用插件对构建过程进行建模，可以大大提高易用性和效率。



#### Build Phases

Initialization
设置Build的执行环境，同时确定需要Build的Project

Configuration
构建Build的Task DAG

Execution
按照前一个阶段生成的Task DAG执行Task

#### Builds

Build是执行Gradle Project中一系列Task的过程，用户可以通过CLI、IDE来执行Build过程。

Gradle配置文件（build.gradle）中，定义了Build过程需要执行的Task。Gradle会根据需要的Task及其依赖关系，配置构建并运行最小的任务集合。


#### DSL

[Groovy基础教程](work/note/programming-language/Groovy/Groovy基础教程.md)

DSL(Domain Specific Language)特定领域语言，Gradle中支持使用 Kotlin 和 Groovy 两种DSL来编写Build脚本。

Groovy是Gradle早期使用的默认语言，因此在许多项目中仍然广泛使用。Groovy是一种基于JVM的动态语言，它具有简洁的语法和强大的表达能力，非常适合编写Gradle Build脚本。

Gradle项目根目录下的`build.gradle`文件就是使用Groovy语言编写的Build脚本文件。

Kotlin是一种现代的静态类型语言，也被广泛用于编写Gradle构建脚本，其中Gradle自 5.0版本开始引入Kotlin。Kotlin具有强大的类型推断、扩展函数和更严格的类型检查等特性，使得构建脚本更加安全和易于维护。Kotlin脚本的文件扩展名为".kts"。

Gradle项目根目录下的`build.gradle.kts`文件就是使用Kotlin语言编写的Build脚本文件。

尽管Gradle默认使用Groovy语言，但在Gradle社区中越来越多的项目开始转向使用Kotlin编写构建脚本，因为Kotlin具有更严格的类型检查和更强大的语言功能，可以提供更好的可维护性和可读性。


### Gradle Wrapper

https://docs.gradle.org/current/userguide/gradle_wrapper.html




## Build Lifecycle

Gradle是一种基于依赖关系的编程范例，通过定义任务和任务之间的依赖关系来进行构建。Gradle确保这些任务按照它们的依赖关系顺序执行。构建脚本和插件配置了这个依赖图。

### Build Phases

Gradle每次Build都按照先后顺序执行三个阶段，initialization、configuration和execution。

#### Initialization

1. 在当前路径下，定位Gradle项目配置文件，即`settings.gradle (Groovy DSL)` 或 `settings.gradle.kts (Kotlin DSL)`
2. 读取Gradle项目配置文件，决定本次Build过程中需要处理的project，以及其对应的build
3. 给每个Project创建对应的Instance

`settings.gradle` example:
```Groovy
rootProject.name = 'basic'
println 'This is executed during the initialization phase.'
```


##### Detect Settings File

如果当前路径下未定位到`settings.gradle(.kts)`文件，Gradle会在父级目录中定位配置文件

##### Evaluate Settings File

1. 添加libraries到Build脚本的classpath
2. 确定需要添加进当前Build的Sub-build
3. 确定需要添加进当前Project的Sub-project


#### Configuration

1. 读取被添加到当前Build中的每个Project对应的Build脚本文件`build.gradle(.kts)`
2. 针对Build脚本文件中声明的Task，基于依赖关系，构建Task Graph


`build.gradle` example: 
```Groovy
println 'This is executed during the configuration phase.'

tasks.register('configured') {
    println 'This is also executed during the configuration phase, because :configured is used in the build.'
}

tasks.register('test') {
    doLast {
        println 'This is executed during the execution phase.'
    }
}

tasks.register('testBoth') {
	doFirst {
	  println 'This is executed first during the execution phase.'
	}
	doLast {
	  println 'This is executed last during the execution phase.'
	}
	println 'This is executed during the configuration phase as well, because :testBoth is used in the build.'
}

tasks.register('ok')

tasks.register('broken') {
    dependsOn ok
    doLast {
        throw new RuntimeException('broken')
    }
}

gradle.taskGraph.beforeTask { Task task ->
    println "executing $task ..."
}

gradle.taskGraph.afterTask { Task task, TaskState state ->
    if (state.failure) {
        println "FAILED"
    }
    else {
        println "done"
    }
}

```


#### Execution

基于Configuration阶段生成的Task Graph，编排和执行Task。其中可以包含downloading libraries、compiling code、reading和writing。

以下的`build.gradle` example，演示了如何在Task执行过程中添加提示信息: 
```Groovy
tasks.register('ok')

tasks.register('broken') {
    dependsOn ok
    doLast {
        throw new RuntimeException('broken')
    }
}

gradle.taskGraph.beforeTask { Task task ->
    println "executing $task ..."
}

gradle.taskGraph.afterTask { Task task, TaskState state ->
    if (state.failure) {
        println "FAILED"
    }
    else {
        println "done"
    }
}
```


```bash
> gradle -q broken
executing task ':ok' ...
done
executing task ':broken' ...
FAILED

FAILURE: Build failed with an exception.

* Where:
Build file '/home/user/gradle/samples/build.gradle' line: 6

* What went wrong:
Execution failed for task ':broken'.
> broken

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 0s
```

## Gradle Project Example

https://docs.gradle.org/current/samples/index.html

### Java
https://docs.gradle.org/current/samples/sample_building_java_applications.html


## 参考链接
1. [Gradle-Docs-What is Gradle?](https://docs.gradle.org/current/userguide/what_is_gradle.html)
2. [Gradle-Docs-Build Lifecycle](https://docs.gradle.org/current/userguide/build_lifecycle.html)