# Apache Zookeeper 运维常见问题

## Java. Io. IOException: The current epoch, 1a5 is less than the accepted epoch

```
Unable to load database on disk
java.io.IOException: The current epoch, 1a5 is less than the accepted epoch, ad
	at org.apache.zookeeper.server.quorum.QuorumPeer.loadDataBase(QuorumPeer.java:575)
	at org.apache.zookeeper.server.quorum.QuorumPeer.start(QuorumPeer.java:521)
	at org.apache.zookeeper.server.quorum.QuorumPeerMain.runFromConfig(QuorumPeerMain.java:169)
	at org.apache.zookeeper.server.quorum.QuorumPeerMain.initializeAndRun(QuorumPeerMain.java:118)
	at org.apache.zookeeper.server.quorum.QuorumPeerMain.main(QuorumPeerMain.java:81)
```

**原因解析：**
当前 Zookeeper Server 的 dataDir 已经损坏，版本落后太多

**解决方案 1：** 备份并重建 Zookeeper 的 dataDir 中的 `version-2` 文件夹
**解决方案 2：** 更换 Zookeeper dataDir，并新建 `version-2` 文件夹（注意用户权限），重启当前 Zookeeper Server 节点，让 Zookeeper Server 自动同步新的副本。

**参考链接：**
https://serverfault.com/questions/950181/zookeeper-not-starting-because-acceptedepoch-is-less-than-the-currentepoch