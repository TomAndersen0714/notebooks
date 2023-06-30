# Cloudera_Manager常见问题及解决方案

## Q&A

**Q1**：**HiveServer启动后角色日志报错，进程终止**

java.lang.RuntimeException: Error applying authorization policy on hive configuration: org.apache.hadoop.security.AccessControlException: Permission denied: user=hive, access=WRITE, inode="/tmp":hdfs:supergroup:drwxr-xr-x

**A1**：使用CM页面直接创建

![img](work/framework/Big-Data/CDH/images/Cloudera_Manager常见问题及解决方案/1668042047372-12.png)

或者手动解决：

```Bash
sudo -u hdfs hdfs dfs -chmod 777 /tmp
```





**Q2：CM Web** **UI中显示某主机未连接Host Monitor**

**A2：检查集群中/etc/hosts文件是否配置正确。如果正确，那么此告警一般等待几分钟后便会自动解除。**



**Q3：CM Web** **UI显示的节点hostname和实际不同**

**A3：检查集群中/etc/hosts文件是否配置正确，本机的ip反向映射唯一**



**Q4：CM中显示某主机NTP服务未同步至任何远程服务器**

**A4：观察是否是ntpd服务未启动，如果是则启动ntpd服务，“systemctl start ntpd”。 等待几分钟后，观察是否消除此告警，如果未消除，则继续重启对应节点的cloudera-scm-agent**