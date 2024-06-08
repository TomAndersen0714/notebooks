# Spark on YARN 基础教程


[Running Spark on YARN - Spark 3.5.1 Documentation](https://spark.apache.org/docs/latest/running-on-yarn.html)

## Running Spark on YARN

### Deploy Mode

There are two deploy modes that can be used to launch Spark applications on YARN. 

1. **Cluster mode** : In `cluster mode`, the Spark driver runs inside an application master process which is managed by YARN on the cluster, and the client can go away after initiating the application. 
2. **Client mode** : In `client mode`, the driver runs in the client process, and the application master is only used for requesting resources from YARN.

Unlike other cluster managers supported by Spark in which the master's address is specified in the `--master` parameter, in YARN mode the ResourceManager's address is picked up from the Hadoop configuration. Thus, the `--master` parameter is yarn.