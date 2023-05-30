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

Gradle官方推荐使用Gradle Wrapper来辅助执行Build。Wrapper是一个脚本，用于获取和下载指定版本的Gradle，并使用此版本执行Build过程。因此，使用Wrapper脚本，可以不需要事先手动安装Gradle，可以直接通过调用脚本来构建Project。


![](work/note/framework/BigData/Visualization/Pasted%20image%2020230530221731.png)

**使用Gradle Wrapper的优势，主要有以下几点**：
1. 版本兼容：在特定版本的Gradle下编译Project，使得Build过程更加可靠。由于Gradle Wrapper将Gradle的版本与Project一起绑定，可以确保Project在不同的开发环境中使用相同的Gradle版本执行Build，从而避免由于Gradle版本不一致而导致的构建问题。
2. 环境隔离：使用Gradle Wrapper，项目可以独立管理自己的Gradle版本和插件版本，不会与其他项目的配置发生冲突，实现了环境的隔离。
3. 简化Build环境配置：Gradle Wrapper允许项目将Gradle的版本和配置文件捆绑在一起，使得每个项目都有自己独立的构建环境，不依赖于全局的Gradle安装。这样可以简化项目的配置和迁移，并且确保每个开发人员使用的是相同的Gradle版本。

**PS：解耦可以使系统更灵活，聚合可以使系统更简单，切忌教条主义。**


#### Adding the Gradle Wrapper

通过执行Gradle内置的Task，给当前Project创建Wrapper脚本
```bash
gradle wrapper
```

#### Using the Gradle Wrapper

Windows: 
```bash
gradlew.bat build
```

Unix: 
```
gradlew build
```


#### Upgrading the Gradle Wrapper

```bash
# Example: Upgrading the Wrapper to the latest version
./gradlew wrapper --gradle-version latest

# Example: Upgrading the Wrapper to a specific version
./gradlew wrapper --gradle-version 8.1.1
```


## Build Lifecycle

Gradle是一种基于依赖关系的编程范例，通过定义任务和任务之间的依赖关系来进行构建。Gradle确保这些任务按照它们的依赖关系顺序执行。构建脚本和插件配置了这个依赖图。

### Build Phases

Gradle每次Build都按照先后顺序执行三个阶段，initialization、configuration和execution。
**PS: 直接命令行执行gradle，即是开始执行Build过程。**


#### Initialization

1. 在当前路径下，定位Gradle Project的配置文件，即`settings.gradle (Groovy DSL)` 或 `settings.gradle.kts (Kotlin DSL)`
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


## Build Script Basics

https://docs.gradle.org/current/userguide/tutorial_using_tasks.html

### Gradle Directories and Files

Gradle主要使用`gradle user home directory`和`project root directory`两个文件夹，来存放配置文件，和构建过程中生成的文件。

#### Gradle user home directory

`Gradle user home directory`的默认值为`<home directory of the current user>/.gradle`，主要用于存储全局配置文件、initialization脚本、cache、log文件等。

可以通过修改`GRADLE_USER_HOME`环境变量，来改变`Gradle user home directory`的值。
https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_environment_variables
https://blog.mrhaki.com/2010/09/gradle-goodness-changing-gradle-user.html


此文件夹的路径树，如下所示：
```bash
├── caches 
│   ├── 4.8 
│   ├── 4.9 
│   ├── ⋮
│   ├── jars-3 
│   └── modules-2 
├── daemon 
│   ├── ⋮
│   ├── 4.8
│   └── 4.9
├── init.d 
│   └── my-setup.gradle
├── jdks 
│   ├── ⋮
│   └── jdk-14.0.2+12
├── wrapper
│   └── dists 
│       ├── ⋮
│       ├── gradle-4.8-bin
│       ├── gradle-4.9-all
│       └── gradle-4.9-bin
└── gradle.properties 
```

各文件夹功能介绍如下：
```
caches: Global cache directory (for everything that’s not project-specific)

caches/4.8: Version-specific caches (e.g. to support incremental builds)

caches/jars-3, caches/modules-2: Shared caches (e.g. for artifacts of dependencies)

daemon: Registry and logs of the Gradle Daemon

init.d: Global initialization scripts

jdks: JDKs downloaded by the toolchain support

wrapper/dists: Distributions downloaded by the Gradle Wrapper

gradle.properties: Global Gradle configuration properties
```

默认情况下，Gradle会自动清理`user home directory`。


#### Project root directory

`Project root directory`指的是Gradle Project项目的根路径。其中包含Gradle Project的各种配置文件，以及Gradle在编译过程中生成的`.gradle`、`build`等文件夹。

Gradle Build过程中生成的文件，都是临时文件，不应该纳入版本管理工具。Gradle Project root directory的路径树大致如下：

```
├── .gradle 
│   ├── 4.8 
│   ├── 4.9 
│   └── ⋮
├── build 
├── gradle
│   └── wrapper 
├── gradle.properties 
├── gradlew 
├── gradlew.bat 
├── settings.gradle or settings.gradle.kts 
├── subproject-one 
|   └── build.gradle or build.gradle.kts 
├── subproject-two 
|   └── build.gradle or build.gradle.kts 
└── ⋮
```

其中各文件的作用如下：

```
.gradle: Project-specific cache directory generated by Gradle

.gradle/4.8, .gradle/4.9: Version-specific caches (e.g. to support incremental builds)

build: The build directory of this project into which Gradle generates all build artifacts.

gradle/wrapper: Contains the JAR file and configuration of the Gradle Wrapper

gradle.properties: Project-specific Gradle configuration properties

gradlew, gradlew.bat: Scripts for executing builds using the Gradle Wrapper

settings.gradle or settings.gradle.kts: The project’s settings file where the list of subprojects is defined

subproject-one, subproject-two: Usually a project is organized into one or multiple subprojects

build.gradle or build.gradle.kts: Each subproject has its own Gradle build script
```

和`user home directory`类似，默认情况下，Gradle会自动清理`project root directory`。


## Gradle Project Building Example

https://docs.gradle.org/current/samples/index.html

### Java
https://docs.gradle.org/current/samples/sample_building_java_applications.html


## 参考链接
1. [Gradle-Docs-What is Gradle?](https://docs.gradle.org/current/userguide/what_is_gradle.html)
2. [Gradle-Docs-Build Lifecycle](https://docs.gradle.org/current/userguide/build_lifecycle.html)
3. [Gradle-Docs-The Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html#sec:using_wrapper)