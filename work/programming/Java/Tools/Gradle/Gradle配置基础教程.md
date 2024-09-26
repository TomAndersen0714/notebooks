# Gradle 配置基础教程

## Gradle Wrapper 配置 Gradle 下载地址

Gradle Wrapper 是和 Gradle 项目强制绑定的脚本工具，用于下载和使用指定版本的 Gradle 来执行各种 Gradle Task，其中 Gradle Wrapper 的配置文件默认为项目中的 `gradle/wrapper/gradle-wrapper.properties`。

其中 `distributionUrl` 默认值为 Gradle 官方 URL，下载速度很慢，如：
```
https\://services.gradle.org/distributions/gradle-8.10.1-bin.zip
```

建议更换为国内下载地址，或者企业内网地址，如腾讯云：
```
https\://mirrors.cloud.tencent.com/gradle/gradle-8.10.1-bin.zip
```

## Gradle 配置 Maven 仓库地址

此小结内容未测试~
### 单项目生效

在项目根目录的的 `build.gradle` 脚本中添加以下内容：

```groovy
allprojects {
  repositories {
    maven {
      url 'https://maven.aliyun.com/repository/public/'
    }
    mavenLocal()
    mavenCentral()
  }
}
```

如果想使用其它代理仓，以使用 central 仓为例，代码如下:
```groovy
allprojects {
  repositories {
    maven {
      url 'https://maven.aliyun.com/repository/public/'
    }
    maven {
      url 'https://maven.aliyun.com/repository/central'
    }
    mavenLocal()
    mavenCentral()
  }
}
```

加入你要引用的依赖信息：
```groovy
dependencies {
  compile '[GROUP_ID]:[ARTIFACT_ID]:[VERSION]'
}
```

执行命令，安装依赖：
```
gradle dependencies
./gradlew dependencies
```

### 全局生效

对所有项目生效，在 `${USER_HOME}/.gradle/` 下创建 `init.gradle` 文件：

```groovy
allprojects{
    repositories {
        def ALIYUN_REPOSITORY_URL = 'https://maven.aliyun.com/repository/public/'
        def ALIYUN_JCENTER_URL = 'https://maven.aliyun.com/repository/jcenter/'
        def ALIYUN_GOOGLE_URL = 'https://maven.aliyun.com/repository/google/'
        def ALIYUN_GRADLE_PLUGIN_URL = 'https://maven.aliyun.com/repository/gradle-plugin/'
        all { ArtifactRepository repo ->
            if(repo instanceof MavenArtifactRepository){
                def url = repo.url.toString()
                if (url.startsWith('https://repo1.maven.org/maven2/')) {
                    project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_REPOSITORY_URL."
                    remove repo
                }
                if (url.startsWith('https://jcenter.bintray.com/')) {
                    project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_JCENTER_URL."
                    remove repo
                }
                if (url.startsWith('https://dl.google.com/dl/android/maven2/')) {
                    project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_GOOGLE_URL."
                    remove repo
                }
                if (url.startsWith('https://plugins.gradle.org/m2/')) {
                    project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_GRADLE_PLUGIN_URL."
                    remove repo
                }
            }
        }
        maven { url ALIYUN_REPOSITORY_URL }
        maven { url ALIYUN_JCENTER_URL }
        maven { url ALIYUN_GOOGLE_URL }
        maven { url ALIYUN_GRADLE_PLUGIN_URL }
    }
}

```
## 参考链接

1. [gradle安装包国内下载以及maven仓库配置以及其他注意点\_gradle下载-CSDN博客](https://blog.csdn.net/zlpzlpzyd/article/details/134383326)
2. [阿里云仓库服务](https://developer.aliyun.com/mvn/guide?spm=a2c40.maven_devops2020_goldlog_.0.0.275c3054yut6Ld)
3. [gradle配置国内镜像\_gradle配置国内源-CSDN博客](https://blog.csdn.net/lj402159806/article/details/78422953)