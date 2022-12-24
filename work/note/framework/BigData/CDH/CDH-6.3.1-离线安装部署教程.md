# CDH 6.3.1 离线安装部署教程

## 前言

- Linux 版本：CentOS 7.6.1810 x86_64
- Cloudera Manager 版本：cdh 6.3.1
- 由于Cloudera增加了收费墙，以及云主机自带防火墙等原因，大部分远程url都无法直接访问，故本教程中展示的所有安装过程，皆采用离线安装方式，即使用相应的RPM安装包手动安装CM，使用Parcel安装CDH。而非构建yum repository和使用yum。同时由于是手动离线安装CM Agent，并不需要提供SSH登录方式，或者再次添加主机，只需要添加主机到集群
- yum repository的方式安装在添加节点时，需要声明cloudera manager和cdh的repository
- CM（Cloudera Manager）和CDH（Cloudera Distributed Hadoop）的区别，简单理解，后者则是指的是Cloudera基于开源大数据组件的发行版（包含以Hadoop为主的一系列组件），而前者是后者集群和安装包的统一运维管理工具
- 离线安装官方链接：[Adding a Host to the Cluster - Adding a Host by Installing the Packages Using Your Own Method | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/cm_mc_adding_hosts.html#cmug_topic_7_5_2)



## 一、集群规划

| HOST                                      | IP                                     | CPU     | Memory | Disk | OS         | CM Role   |
| ----------------------------------------- | -------------------------------------- | ------- | ------ | ---- | ---------- | --------- |
| znzjk-134218-test-mini-bigdata-clickhouse | [10.22.134.218](http://10.22.134.218/) | 4 cores | 14G    | 1T   | CentOS 7.6 | CM Server |
| znzjk-134219-test-mini-bigdata-clickhouse | [10.22.134.219](http://10.22.134.219/) | 4 cores | 14G    | 1T   | CentOS 7.6 | CM Agent  |
| znzjk-134220-test-mini-bigdata-clickhouse | [10.22.134.220](http://10.22.134.220/) | 4 cores | 14G    | 1T   | CentOS 7.6 | CM Agent  |

**PS：CDH集群规划官方文档**，[Recommended Cluster Hosts and Role Distribution | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/cm_ig_host_allocations.html#concept_f43_j4y_dw)



## 二、安装包准备

**版本信息**：

- Cloudera Manager：cm6.3.1-redhat7.tar.gz
- Oracle Java JRE：jre-8u181-linux-x64.tar.gz
- MySQL：
  - mysql-5.7.11-linux-glibc2.5-x86_64.tar.gz
  - mysql-connector-java-5.1.47.jar（部署时，需更名为mysql-connector-java.jar，未测试）
- CDH Parcel：
  - CDH-6.3.1-1.cdh6.3.1.p0.1470567-el7.parcel
  - CDH-6.3.1-1.cdh6.3.1.p0.1470567-el7.parcel.sha1（部署时需更名为CDH-6.3.1-1.cdh6.3.1.p0.1470567-el7.parcel.sha，未测试）
  - PS：此文件为CDH-6.3.1-1.cdh6.3.1.p0.1470567-el7.parcel的sha1校验和计算结果，建议保证`sha1sum CDH-6.3.1-1.cdh6.3.1.p0.1470567-el7.parcel`的结果与其中内容一致，如果不一致，可以新建，避免踩坑

- manifest.json



**下载链接**：

6.3.1 百度云链接：

-  https://pan.baidu.com/s/16wYx16QwQ9QcVawEV47TMQ?pwd=hp45 提取码: hp45 复制这段内容后打开百度网盘手机App，操作更方便哦

6.2.x 镜像源：

- Cloudera Manger：[Index of /cloudera-repos/cm6/redhat/7/x86_64/cm/](https://ro-bucharest-repo.bigstepcloud.com/cloudera-repos/cm6/redhat/7/x86_64/cm/)
- CDH Parcel：[Index of /cloudera-repos/cdh6/parcels/](https://ro-bucharest-repo.bigstepcloud.com/cloudera-repos/cdh6/parcels/)
- CDH：[Index of /cloudera-repos/cdh6/redhat/7/x86_64/cdh/](https://ro-bucharest-repo.bigstepcloud.com/cloudera-repos/cdh6/redhat/7/x86_64/cdh/)

PS：本次安装所需CDH各组件包，皆在对应的Parcel包中，故不需要再下载额外的组件包



## 三、CDH安装环境准备



**参考链接**：[Before You Install | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/installation_reqts.html#pre-install)



### 1.（集群）设置hosts主机名解析

查看当前节点主机名

```Bash
hostname
```



各节点**/etc/hosts**文件中新增集群节点映射

**PS：请确保同各个节点的/etc/hosts，hostname到ip的映射是唯一，避免出现CM Agent给CM Server上报hostname时，值与对应节点的hostname输出不符。**

**PS：CM Agent在未声明时，默认会采用Python的socket.getfqdn()方法来获取当前节点的hostname，实测获取的是/etc/hosts中的首个ip反向映射对应的hostname，应保证对应的hostname的ip映射在文件首位。**

```Bash
10.22.134.218 znzjk-134218-test-mini-bigdata-clickhouse
10.22.134.219 znzjk-134219-test-mini-bigdata-clickhouse
10.22.134.220 znzjk-134220-test-mini-bigdata-clickhouse
```



### 2.（集群）关闭防火墙firewalld

在CentOS 7、RHEL 7或Fedora中防火墙主要由**firewalld**来管理

```SQL
systemctl stop firewalld && systemctl disable firewalld
```



### 3.（集群）设置时区，使用ntp实现时钟同步

使用**timedatectl**，将各节点时钟的时区设置为上海时区

```C%2B%2B
timedatectl set-timezone Asia/Shanghai
```



可以使用ntpdate命令直接手动同步时间，或者使用ntp服务同步时间

```Shell
ntpdate ntp.api.bz
```



各节点安装**ntp**时钟同步工具（可选）

```Nginx
yum install -y ntp
```

修改各节点**ntp**配置文件（**/etc/ntp.conf**）

```Bash
# 添加阿里云的ntp服务器
server ntp1.aliyun.com iburst minpoll 4 maxpoll 10
server ntp1.cloud.aliyuncs.com iburst minpoll 4 maxpoll 10
server ntp10.cloud.aliyuncs.com iburst minpoll 4 maxpoll 10
server ntp11.cloud.aliyuncs.com iburst minpoll 4 maxpoll 10
server ntp12.cloud.aliyuncs.com iburst minpoll 4 maxpoll 10
server ntp2.aliyun.com iburst minpoll 4 maxpoll 10
server ntp2.cloud.aliyuncs.com iburst minpoll 4 maxpoll 10
server ntp3.aliyun.com iburst minpoll 4 maxpoll 10
server ntp3.cloud.aliyuncs.com iburst minpoll 4 maxpoll 10
server ntp4.aliyun.com iburst minpoll 4 maxpoll 10
server ntp4.cloud.aliyuncs.com iburst minpoll 4 maxpoll 10
server ntp5.aliyun.com iburst minpoll 4 maxpoll 10
server ntp5.cloud.aliyuncs.com iburst minpoll 4 maxpoll 10
server ntp6.aliyun.com iburst minpoll 4 maxpoll 10
server ntp6.cloud.aliyuncs.com iburst minpoll 4 maxpoll 10
server ntp7.cloud.aliyuncs.com iburst minpoll 4 maxpoll 10
server ntp8.cloud.aliyuncs.com iburst minpoll 4 maxpoll 10
server ntp9.cloud.aliyuncs.com iburst minpoll 4 maxpoll 10

# 添加当前节点到ntp服务器列表, 且当系统时间不可⽤用时，可使⽤用硬件时间
server 127.127.1.0 local clock
```

开启ntpd服务器及查看状态

```SQL
systemctl start ntpd
systemctl status ntpd
```

### 4.（集群）关闭透明大页面压缩

避免出现可能的重大性能问题

```Bash
echo never > /sys/kernel/mm/transparent_hugepage/enabled
echo never > /sys/kernel/mm/transparent_hugepage/defrag
```

建议同时添加到``/etc/rc.local``中，节点重启后同样生效此设置





### 5.（集群）部署JRE

```Bash
sudo mkdir -p /usr/java

tar -zxf jre-8u181-linux-x64.tar.gz -C /usr/java/

chown -R root:root /usr/java/jre1.8.0_181

echo "export JAVA_HOME=/usr/java/jre1.8.0_181" >> /etc/profile

echo "export PATH=/usr/java/jre1.8.0_181/bin:${PATH}" >> /etc/profile 

source /etc/profile

which java
/usr/java/jre1.8.0_181/bin/java

java -version
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
```



### 6.（主节点）部署MySQL

#### **a) 主节点安装MySQL**

**参考链接**：

https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/cm_ig_mysql.html#cmig_topic_5_5_2

https://blog.csdn.net/m0_64684588/article/details/121636825

https://github.com/Hackeruncle/MySQL/blob/master/MySQL%205.7.11%20Install.txt





#### **b) 创建CM Server所需数据库和用户**

**创建所需数据库，以及对应的用户和权限，密码和用户名相同，格式为：**

```SQL
CREATE DATABASE <database> DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

GRANT ALL ON <database>.* TO '<user>'@'%' IDENTIFIED BY '<password>';
```



**创建Cloudera Manager Server，以及其他所需服务对应的数据库（如：Hive Metastore Server），此处所有账户的用户名和密码同名**

```SQL
CREATE DATABASE scm DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
GRANT ALL ON scm.* TO 'scm'@'%' IDENTIFIED BY 'scm';

CREATE DATABASE metastore DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
GRANT ALL ON metastore.* TO 'hive'@'%' IDENTIFIED BY 'hive';

flush privileges;
```

**参考链接**：[Install and Configure MySQL for Cloudera Software | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/cm_ig_mysql.html#concept_dsg_3mq_bl)



#### **c) 部署JDBC**

```Shell
sudo mkdir -p /usr/share/java

# 注意此处需要重命名(未测试必要性)
cp mysql-connector-java-5.1.47.jar /usr/share/java/mysql-connector-java.jar
```





### 7.（集群）安装Python3

在低版本的Linux系统中，默认的Python版本为Python2，需要手动安装Python3。便于在使用如pyspark、spark-shell时，支持python3语法。

```shell
yum install python3
```



## 四、CDH部署

### 1.离线部署CM Server和CM Agent



#### a)（集群）创建CDH解压路径

```Bash
sudo mkdir -p /opt/cloudera-manager

tar -zxf cm6.3.1-redhat7.tar.gz -C /opt/cloudera-manager
```



#### b)（主节点）安装CM Server

```Bash
cd /opt/cloudera-manager/cm6.3.1/RPMS/x86_64/

rpm -ivh cloudera-manager-daemons-6.3.1-1466458.el7.x86_64.rpm

rpm -ivh cloudera-manager-server-6.3.1-1466458.el7.x86_64.rpm
```

**PS：可能存在各个依赖的package依赖未安装而报错，挨个使用yum安装即可**



#### c)（主节点）初始化CM Server数据库

执行CM Server的数据库配置初始化脚本**scm_prepare_database.sh**

```Bash
sudo /opt/cloudera/cm/schema/scm_prepare_database.sh <databaseType> <databaseName> <databaseUser>
```

本次采用的是MySQL数据库，且之前创建的数据库和用户皆为scm，故命令为：

```Bash
sudo /opt/cloudera/cm/schema/scm_prepare_database.sh mysql scm scm
```

脚本运行完成之后会自动生成**/etc/cloudera-scm-server/db.properties**配置文件，配置示例内容大致如下

```Python
# Auto-generated by scm_prepare_database.sh on Fri May 13 19:31:46 CST 2022
#
# For information describing how to configure the Cloudera Manager Server
# to connect to databases, see the "Cloudera Manager Installation Guide."
#
com.cloudera.cmf.db.type=mysql
com.cloudera.cmf.db.host=localhost
com.cloudera.cmf.db.name=scm
com.cloudera.cmf.db.user=scm
com.cloudera.cmf.db.setupType=EXTERNAL
com.cloudera.cmf.db.password=scm
```

**参考链接**：[Step 5: Set up the Cloudera Manager Database | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/prepare_cm_database.html#cmig_topic_5_2)



#### d)（集群）安装CM Agent

```Bash
cd /opt/cloudera-manager/cm6.3.1/RPMS/x86_64

rpm -ivh cloudera-manager-daemons-6.3.1-1466458.el7.x86_64.rpm --nodeps --force

rpm -ivh cloudera-manager-agent-6.3.1-1466458.el7.x86_64.rpm --nodeps --force
```

**一般情况下都会出现“error: Failed dependencies”错误，即需要安装前置依赖，使用“`yum -y install <package1> <package2>...`”命令挨个安装即可**

**其中“/lib/lsb/init-functions is needed”报错，需要安装lsb，即“`yum -y install lsb`”**



#### e)（集群）修改CM Agent配置，手动指定Server节点

如，本次测试使用的server节点hostname为````znzjk-134218-test-mini-bigdata-clickhouse````

```Nginx
sed -i "s/server_host=localhost/server_host=znzjk-134218-test-mini-bigdata-clickhouse/g" /etc/cloudera-scm-agent/config.ini
```



### 2.集群离线部署CDH Parcel

集群各节点部署本地Parcel源

```Shell
sudo mkdir -p /opt/cloudera/parcel-repo/

cp CDH-6.3.1-1.cdh6.3.1.p0.1470567-el7.parcel /opt/cloudera/parcel-repo/

# 注意此处需要重命名(未测试必要性)
cp CDH-6.3.1-1.cdh6.3.1.p0.1470567-el7.parcel.sha1 /opt/cloudera/parcel-repo/CDH-6.3.1-1.cdh6.3.1.p0.1470567-el7.parcel.sha

cp manifest.json /opt/cloudera/parcel-repo/
```

**PS**：parcel是CDH内置的软件包管理工具，支持软件包的分发和升级，且支持增加用户自定义的软件包

**PS**：后续在CDH中创建集群-选择存储库步骤中，parcel的更多选项的配置里，本地parcel库的路径需要与此值一致，同时也是默认值`/opt/cloudera/parcel-repo/`

![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047378-13.png)

![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047370-1.png)



**PS**：sha1文件表示针对对应的parcel文件，使用sha1 Hash算法（sha1sum）生成的校验和文本文件，建议保持sha文件中的内容是sha1sum的输出结果



### 3.启动主节点CM Server

```SQL
systemctl start cloudera-scm-server
```



### 4.启动集群CM Agent

```SQL
systemctl start cloudera-scm-agent
```



### 5.管理CDH集群

#### a) 登录CM Server的Web管理页面

http://znzjk-134218-test-mini-bigdata-clickhouse:7180，初始账号密码皆为admin

![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047371-2.png)



#### b) 选择免费版本

选择免费版的Cloudera Express

![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047371-3.png)

####  c) 创建集群

点击集群-创建集群

![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047371-4.png)



![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047371-5.png)



由于是离线安装agent，并手动指定server，以及启动了agent服务，与Server构建了连接，故此时节点已经存在于节点列表中，且不再需要提供SSH登录凭证，也不需要为CM和CDH指定yum repository来安装CM agent，以及CDH中的组件

![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047371-6.png)



安装Parcel库，需要保证在Parcel的更多选项中，保证Parcel的本地源和之前部署的源路径是相同的。此版本Parcel中其中包含有各种服务（如Impala、Hive、HDFS、YARN等）的软件包

![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047371-7.png)



等待Parcel离线安装

![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047371-8.png)



之后进行网络性能和主机检查，即可完成集群创建

![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047372-9.png)

![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047372-10.png)



最终添加完Parcel中各种服务之后的示例结果页面

![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047372-11.png)



#### d) 删除集群节点

Cloudera Manager从集群中删除节点有两个步骤，与添加节点进入集群是相反的两个步骤：

第一个步骤是从CDH集群中删除节点，即针对对应节点上的各个CDH角色进行解除授权，停止并删除这些角色，但此时对应的节点依旧被管理在当前的CM集群中；

第二个步骤是从CM集群中删除节点，在第一阶段结束之后，对应的节点依旧存在CM集群中，能够看到对应主机的各种物理资源使用状态，后续依旧可以将其添加到CDH集群中，或者将其删除

PS：[官方链接](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/cm_mc_delete_hosts.html#cmug_topic_7_9)

###### 步骤一：从CDH集群中删除节点

https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/cm_mc_delete_hosts.html#cmug_topic_7_9__section_c5k_xlm_4n

###### 步骤二：从CM集群中删除节点

https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/cm_mc_delete_hosts.html#cmug_topic_7_9__section_u1w_wlm_4n





### 6.修改pyspark使用的Python版本（可选）

修改Spark配置路径下的`spark-env.sh`文件，增加环境变量，以修改pyspark使用的Python版本

如：修改`/etc/spark/conf/spark-env.sh`文件

```shell
export PYSPARK_PYTHON=/usr/bin/python3
export PYSPARK_DRIVER_PYTHON=/usr/bin/python3
```



## 五、Q&A

**Q1**：**HiveServer启动后角色日志报错，进程终止**

java.lang.RuntimeException: Error applying authorization policy on hive configuration: org.apache.hadoop.security.AccessControlException: Permission denied: user=hive, access=WRITE, inode="/tmp":hdfs:supergroup:drwxr-xr-x

**A1**：使用CM创建

![img](images/CDH_6.3.1_%E7%A6%BB%E7%BA%BF%E5%AE%89%E8%A3%85%E9%83%A8%E7%BD%B2%E6%95%99%E7%A8%8B/1668042047372-12.png)

或者手动解决：

```Bash
sudo -u hdfs hdfs dfs -chmod 777 /tmp
```





**Q2：CM Web** **UI中显示某主机未连接Host Monitor**

**A2：检查集群中/etc/hosts文件是否配置正确**



**Q3：CM Web** **UI显示的节点hostname和实际不同**

**A3：检查集群中/etc/hosts文件是否配置正确，本机的ip反向映射唯一**



**Q4：CM中显示某主机NTP服务未同步至任何远程服务器**

**A4：观察是否是ntpd服务未启动，如果是则启动ntpd服务，“systemctl start ntpd”。 等待几分钟后，观察是否消除此告警，如果未消除，则继续重启对应节点的cloudera-scm-agent**



## 六、参考链接

1. [Adding a Host to the Cluster - Adding a Host by Installing the Packages Using Your Own Method | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/cm_mc_adding_hosts.html#cmug_topic_7_5_2)
2. [Cloudera Installation Guide | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/installation.html)
3. [Install and Configure MySQL for Cloudera Software | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/cm_ig_mysql.html#concept_dsg_3mq_bl)
4. [Step 5: Set up the Cloudera Manager Database | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/prepare_cm_database.html#cmig_topic_5_2)
5. [Parcels | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/cm_ig_parcels.html)
6. [Recommended Cluster Hosts and Role Distribution | 6.3.x | Cloudera Documentation](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/cm_ig_host_allocations.html#concept_f43_j4y_dw)



## End~