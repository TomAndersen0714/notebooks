# DBeaver 基础教程


## 安装

https://github.com/dbeaver/dbeaver

## 配置

### 修改 Maven 镜像

DBeaver 默认 Maven 镜像为 Maven 官方镜像，一般为了加速或者私有化，需要修改 Maven 镜像。

操作步骤：
1. 打开 DBeaver 点击窗口->窗口->首选项->驱动->Maven->添加
2. 禁用所有其他的 Maven 镜像地址

国内常用 Maven 镜像地址：
1. 阿里云： http://maven.aliyun.com/nexus/content/groups/public/
2. 腾讯云： http://mirrors.cloud.tencent.com/nexus/repository/maven-public/

参考：
https://blog.csdn.net/cuichongxin/article/details/131414589

### 手动设置数据库驱动下载链接

DBeaver 中的数据库驱动的默认下载链接，并非全都来自于 Maven，比如有些也来自于 Github ，因此某些网络受限的情况下，需要手动设置对应驱动程序的下载地址。

#### Hive JDBC Driver

操作步骤：
1. 连接设置->驱动设置->库->添加工件（artifact）
2. 输入对应驱动 Maven 的 jar 包信息，如 `org.apache.hive:hive-jdbc:2.3.3`
3. 点击“下载/更新”

注意事项：
1. 编辑“驱动设置”时，如果有默认的驱动链接，需要先删除
2. 添加“工件”时，不要选择“忽略依赖”，同时“版本 version” 和“回退版本”都输入对应的驱动 jar 包版本
3. 设置完“工件”之后，点击“下载/更新”。注意观察检索框的“version”字段，一般还需要手动下拉并选择对应的“Version”，因为 DBeaver 此处可能存在 BUG，之前填写的 version 并未同步到检索框，进而导致检索出错误的 jar 包版本。
4. “下载/更新”过程中，如果出现某个依赖的 Maven 包不存在，则直接忽略。

参考：
https://www.cnblogs.com/sunpengblog/p/11858764.html

## 参考链接
1. https://github.com/dbeaver/dbeaver
2. https://blog.csdn.net/cuichongxin/article/details/131414589
3. https://www.cnblogs.com/sunpengblog/p/11858764.html