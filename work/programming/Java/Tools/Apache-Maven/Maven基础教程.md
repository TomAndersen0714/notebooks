# Maven 基础教程


## Maven 安装基础教程

[Maven安装基础教程](work/programming/Java/Tools/Apache-Maven/Maven安装基础教程.md)

## Maven 配置教程


### 所有配置文件

https://maven.apache.org/configure.html#maven_args-environment-variable

### settings.xml

settings.xml 文件是 Maven 命令执行时使用的配置文件。

安装 Maven 后，通常会调整配置文件中的 localRepository 参数，即配置 jar 包的安装路径；以及 mirror 参数，即调整 Maven 仓库的镜像地址为国内公开、企业或个人私有的镜像地址。

参考文档：
- 英文版: https://maven.apache.org/settings.html
- 中文版: https://www.cnblogs.com/jingmoxukong/p/6050172.html#profiles

User Level：
- Located in `USER_HOME/.m2` the settings files is designed to contain any configuration for Maven usage across projects of current user.
- 在 `USER_HOME/.m2` 路径下创建 `settings.xml`，实现用户级别 Maven 配置。

Global Level:
- Located in `${MAVEN_HOME}/conf/settings.xml`.
- 在 Maven 安装路径的 conf 文件夹下配置 `settings.xml`，实现全局系统级别 Maven 配置。

Demo:
```xml

<?xml version="1.0" encoding="UTF-8"?>

<!--
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
-->

<!--
 | This is the configuration file for Maven. It can be specified at two levels:
 |
 |  1. User Level. This settings.xml file provides configuration for a single user,
 |                 and is normally provided in ${user.home}/.m2/settings.xml.
 |
 |                 NOTE: This location can be overridden with the CLI option:
 |
 |                 -s /path/to/user/settings.xml
 |
 |  2. Global Level. This settings.xml file provides configuration for all Maven
 |                 users on a machine (assuming they're all using the same Maven
 |                 installation). It's normally provided in
 |                 ${maven.conf}/settings.xml.
 |
 |                 NOTE: This location can be overridden with the CLI option:
 |
 |                 -gs /path/to/global/settings.xml
 |
 | The sections in this sample file are intended to give you a running start at
 | getting the most out of your Maven installation. Where appropriate, the default
 | values (values used when the setting is not specified) are provided.
 |
 |-->
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <!-- localRepository
   | The path to the local repository maven will use to store artifacts.
   |
   | Default: ${user.home}/.m2/repository
  <localRepository>/path/to/local/repo</localRepository>
  -->

  <!-- servers
   | This is a list of authentication profiles, keyed by the server-id used within the system.
   | Authentication profiles can be used whenever maven must make a connection to a remote server.
   |-->
  <servers>
    <!-- server
     | Specifies the authentication information to use when connecting to a particular server, identified by
     | a unique name within the system (referred to by the 'id' attribute below).
     |
     | NOTE: You should either specify username/password OR privateKey/passphrase, since these pairings are
     |       used together.
     |
    <server>
      <id>deploymentRepo</id>
      <username>repouser</username>
      <password>repopwd</password>
    </server>
    -->

    <!-- Another sample, using keys to authenticate.
    <server>
      <id>siteServer</id>
      <privateKey>/path/to/private/key</privateKey>
      <passphrase>optional; leave empty if not used.</passphrase>
    </server>
    -->
  </servers>


  <!-- mirrors
   | This is a list of mirrors to be used in downloading artifacts from remote repositories.
   |
   | It works like this: a POM may declare a repository to use in resolving certain artifacts.
   | However, this repository may have problems with heavy traffic at times, so people have mirrored
   | it to several places.
   |
   | That repository definition will have a unique id, so we can create a mirror reference for that
   | repository, to be used as an alternate download site. The mirror site will be the preferred
   | server for that repository.
   |-->
  <mirrors>
    <!-- mirror
     | Specifies a repository mirror site to use instead of a given repository. The repository that
     | this mirror serves has an ID that matches the mirrorOf element of this mirror. IDs are used
     | for inheritance and direct lookup purposes, and must be unique across the set of mirrors.
     |
    <mirror>
      <id>mirrorId</id>
      <mirrorOf>repositoryId</mirrorOf>
      <name>Human Readable Name for this Mirror.</name>
      <url>http://my.repository.com/repo/path</url>
    </mirror>
     -->
  </mirrors>
```

### .mvn

Located within the project's top level directory, the files
- `maven.config`
- `jvm.config`
- `extensions.xml`


## Maven in IDE

### IDEA

在 JetBrains IDEA 中通常会内置某个对应版本的 Maven，但此内置工具通常会存在一些奇怪的 BUG（比如：无法识别本地依赖），通常是建议自己手动安装独立的 Maven 之后，再去 `File | Settings | Build, Execution, Deployment | Build Tools | Maven` 中调整使用的 Maven 路径，指向独立安装的 Maven。


## 参考链接

1. [Maven – Welcome to Apache Maven](https://maven.apache.org/)
2. [Maven初始settings.xml以及国内Maven镜像站设置 - Biem - 博客园](https://www.cnblogs.com/biem/p/15656111.html)
3. [maven全局配置文件settings.xml详解 - 静默虚空 - 博客园](https://www.cnblogs.com/jingmoxukong/p/6050172.html)
