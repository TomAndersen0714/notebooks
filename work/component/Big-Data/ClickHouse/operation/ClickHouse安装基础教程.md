# ClickHouse 安装基础教程


## 拉取 CH 镜像

PS：由于本次平台搭建所在的线上环境无法直接拉取 Docker 公共仓库的 image，因此需要在线下拉取然后导出导入的方式实现 image 的加载

```shell
# 线下pull镜像
docker pull yandex/clickhouse-server:21.8.3
# 保存镜像为tar文件
docker save yandex/clickhouse-server:21.8.3 -o clickhouse-server-21.8.3-image.tar
# 压缩镜像文件
zip clickhouse-server-21.8.3-image.tar.zip clickhouse-server-21.8.3-image.tar
# 上传镜像文件到线上环境
...
# 解压镜像文件
unzip clickhouse-server-21.8.3-image.tar.zip
# 从文件中加载镜像
docker load -i clickhouse-server-21.8.3-image.tar
# 恢复镜像标签
docker tag <image_id> yandex/clickhouse-server:21.8.3
```


## 配置 CH 集群

### 节点列表


| host                                      | ip            | CH       |          |
|-------------------------------------------|---------------|----------|----------|
| znzjk-133213-prod-mini-bigdata-clickhouse | 10.22.133.213 | ch-s1-r1 | ch-s2-r2 |
| znzjk-133214-prod-mini-bigdata-clickhouse | 10.22.133.214 | ch-s2-r1 | ch-s1-r2 |


### Zookeeper 集群

PS：由于本次实验中，某个 ZK 节点上的 2181 端口已经占用，因此本次实验采用的是 2182 端口，其中 CH 的配置文件 config. Xml 中 ZK 的客户端端口也作出了相应调整

| host                                      | ip            | ZK client port |
| ----------------------------------------- | ------------- | -------------- |
| znzjk-133213-prod-mini-bigdata-clickhouse | 10.22.133.213 | 2182           |
| znzjk-133214-prod-mini-bigdata-clickhouse | 10.22.133.214 | 2182           |
| znzjk-133215-prod-mini-bigdata-cdh        | 10.22.133.215 | 2182           | 


### 节点 ch-s1-r1

#### 创建容器数据卷对应宿主机路径
```bash
#!/bin/bash
mkdir -p /data/clickhouse/ch-s1-r1/conf/ /etc/clickhouse/
```

#### 创建配置文件
`在/data/clickhouse/ch-s1-r1/conf/路径下创建CH配置文件config.xml和users.xml`
```bash
vim /data/clickhouse/ch-s1-r1/conf/config.xml
vim /data/clickhouse/ch-s1-r1/conf/users.xml
```

##### config.xml

```xml
<?xml version="1.0"?>
<yandex>
    <tcp_port>19000</tcp_port>
    <http_port>8123</http_port>
    <interserver_http_port>9008</interserver_http_port>

    <macros>
        <layer>01</layer>
        <shard>01</shard>
        <replica>cluster01-01-01</replica>
    </macros>

    <!-- 集群配置 -->
    <remote_servers>
        <cluster_3s_2r>
            <!-- 数据分片1  -->
            <shard>
                <internal_replication>true</internal_replication>
                <replica>
                    <host>znzjk-133213-prod-mini-bigdata-clickhouse</host>
                    <port>19000</port>
                </replica>
                <replica>
                    <host>znzjk-133214-prod-mini-bigdata-clickhouse</host>
                    <port>29000</port>
                </replica>
            </shard>

            <!-- 数据分片2  -->
            <shard>
                <internal_replication>true</internal_replication>
                <replica>
                    <host>znzjk-133214-prod-mini-bigdata-clickhouse</host>
                    <port>19000</port>
                </replica>
                <replica>
                    <host>znzjk-133213-prod-mini-bigdata-clickhouse</host>
                    <port>29000</port>
                </replica>
            </shard>
        </cluster_3s_2r>
    </remote_servers>

    <!-- 存储策略 -->
    <storage_configuration>
        <disks>
            <data0>
                <path>/var/data/clickhouse-server/storage/data0/</path>
                <keep_free_space_bytes>1073741824</keep_free_space_bytes>
            </data0>
        </disks>

        <policies>
            <rr>
                <volumes>
                    <single>
                        <disk>data0</disk>
                    </single>
                </volumes>
            </rr>
        </policies>
    </storage_configuration>

    <!-- zk -->
    <zookeeper>
        <node>
            <host>znzjk-133213-prod-mini-bigdata-clickhouse</host>
            <port>2182</port>
        </node>

        <node>
            <host>znzjk-133214-prod-mini-bigdata-clickhouse</host>
            <port>2182</port>
        </node>

        <node>
            <host>znzjk-133215-prod-mini-bigdata-cdh</host>
            <port>2182</port>
        </node>
    </zookeeper>

    <!-- 数据压缩算法  -->
    <compression>
        <case>
            <min_part_size>10000000000</min_part_size>
            <min_part_size_ratio>0.01</min_part_size_ratio>
            <method>lz4</method>
        </case>
    </compression>

    <!-- Listen wildcard address to allow accepting connections from other containers and host network. -->
    <listen_host>::</listen_host>
    <listen_host>0.0.0.0</listen_host>
    <listen_try>1</listen_try>

    <logger>
        <level>trace</level>
        <log>/var/log/clickhouse-server/clickhouse-server.log</log>
        <errorlog>/var/log/clickhouse-server/clickhouse-server.err.log</errorlog>
        <size>1000M</size>
        <count>10</count>
    </logger>


    <max_connections>4096</max_connections>
    <keep_alive_timeout>3</keep_alive_timeout>
    <max_concurrent_queries>100</max_concurrent_queries>
    <uncompressed_cache_size>8589934592</uncompressed_cache_size>
    <mark_cache_size>5368709120</mark_cache_size>


    <path>/var/data/clickhouse-server/data/</path>
    <tmp_path>/var/data/clickhouse-server/data/tmp/</tmp_path>
    <user_files_path>/var/data/clickhouse-server/data/user_files/</user_files_path>
    <users_config>users.xml</users_config>
    <default_profile>default</default_profile>
    <default_database>default</default_database>
    <dictionaries_config>*_dictionary.xml</dictionaries_config>


    <timezone>Asia/Shanghai</timezone>
    <mlock_executable>true</mlock_executable>


    <builtin_dictionaries_reload_interval>3600</builtin_dictionaries_reload_interval>
    <max_session_timeout>3600</max_session_timeout>
    <default_session_timeout>60</default_session_timeout>


    <query_log>
        <database>system</database>
        <table>query_log</table>
        <partition_by>toYYYYMM(event_date)</partition_by>
        <flush_interval_milliseconds>7500</flush_interval_milliseconds>
    </query_log>

    <trace_log>
        <database>system</database>
        <table>trace_log</table>
        <partition_by>toYYYYMM(event_date)</partition_by>
        <flush_interval_milliseconds>7500</flush_interval_milliseconds>
    </trace_log>

    <query_thread_log>
        <database>system</database>
        <table>query_thread_log</table>
        <partition_by>toYYYYMM(event_date)</partition_by>
        <flush_interval_milliseconds>7500</flush_interval_milliseconds>
    </query_thread_log>

    <!-- Allow to execute distributed DDL queries (CREATE, DROP, ALTER, RENAME) on cluster.
         Works only if ZooKeeper is enabled. Comment it if such functionality isn't required. -->
    <distributed_ddl>
        <!-- Path in ZooKeeper to queue with DDL queries -->
        <path>/clickhouse/task_queue/ddl</path>
    </distributed_ddl>

    <!-- Directory in <clickhouse-path> containing schema files for various input formats.
         The directory will be created if it doesn't exist.-->
    <format_schema_path>/var/data/clickhouse-server/data/format_schemas/</format_schema_path>

</yandex>
```

##### users.xml

```xml
<?xml version="1.0"?>
<yandex>
    <!-- Profiles of settings. -->
    <profiles>
        <!-- Default settings. -->
        <default>
            <!-- Maximum memory usage for processing single query, in bytes. -->
            <max_memory_usage>10000000000</max_memory_usage>
            <joined_subquery_requires_alias>0</joined_subquery_requires_alias>
            <max_partitions_per_insert_block>1000</max_partitions_per_insert_block>
            <distributed_product_mode>global</distributed_product_mode>

            <!-- Use cache of uncompressed blocks of data. Meaningful only for processing many of very short queries. -->
            <use_uncompressed_cache>0</use_uncompressed_cache>
            <log_queries>1</log_queries>
            <!-- How to choose between replicas during distributed query processing.
                                  random - choose random replica from set of replicas with minimum number of errors
                 nearest_hostname - from set of replicas with minimum number of errors, choose replica
                  with minimum number of different symbols between replica's hostname and local hostname
                  (Hamming distance).
                 in_order - first live replica is chosen in specified order.
                 first_or_random - if first replica one has higher number of errors, pick a random one from replicas with minimum number of errors.
            -->
            <load_balancing>random</load_balancing>
        </default>

        <!-- Profile that allows only read queries. -->
        <readonly>
            <readonly>1</readonly>
        </readonly>

        <xd_read>
            <max_memory_usage>10000000000</max_memory_usage>
            <use_uncompressed_cache>0</use_uncompressed_cache>
            <load_balancing>random</load_balancing>
            <log_queries>1</log_queries>
            <readonly>1</readonly>
            <allow_ddl>0</allow_ddl>
            <max_memory_usage_for_user>10000000000</max_memory_usage_for_user>
            <max_result_rows>100000</max_result_rows>
            <joined_subquery_requires_alias>0</joined_subquery_requires_alias>
            <max_partitions_per_insert_block>1000</max_partitions_per_insert_block>
            <distributed_product_mode>global</distributed_product_mode>
        </xd_read>
    </profiles>

    <!-- Users and ACL. -->
    <users>
        <!-- If user name was not specified, 'default' user is used. -->
        <default>
            <!-- Password could be specified in plaintext or in SHA256 (in hex format).

                 If you want to specify password in plaintext (not recommended), place it in 'password' element.
                 Example: <password>qwerty</password>.
                 Password could be empty.

                 If you want to specify SHA256, place it in 'password_sha256_hex' element.
                 Example: <password_sha256_hex>65e84be1GV9Jm2u7rmsCe65wKzPTw5jtS38n2tVEGic0ea744b2cf58ee02337c5</password_sha256_hex>
                 Restrictions of SHA256: impossibility to connect to ClickHouse using MySQL JS client (as of July 2019).

                 If you want to specify double SHA1, place it in 'password_double_sha1_hex' element.
                 Example: <password_double_sha1_hex>e395796d6546b1b65db9d665cd43f0e858dd4303</password_double_sha1_hex>

                 How to generate decent password:
                 Execute: PASSWORD=$(base64 < /dev/urandom | head -c8); echo "$PASSWORD"; echo -n "$PASSWORD" | sha256sum | tr -d '-'
                 In first line will be password and in second - corresponding SHA256.

                 How to generate double SHA1:
                 Execute: PASSWORD=$(base64 < /dev/urandom | head -c8); echo "$PASSWORD"; echo -n "$PASSWORD" | openssl dgst -sha1 -binary | openssl dgst -sha1
                 In first line will be password and in second - corresponding double SHA1.
            -->
            <password></password>

            <!-- List of networks with open access.

                 To open access from everywhere, specify:
                    <ip>::/0</ip>

                 To open access only from localhost, specify:
                    <ip>::1</ip>
                    <ip>127.0.0.1</ip>

                 Each element of list has one of the following forms:
                 <ip> IP-address or network mask. Examples: 213.180.204.3 or 10.0.0.1/8 or 10.0.0.1/255.255.255.0
                     2a02:6b8::3 or 2a02:6b8::3/64 or 2a02:6b8::3/ffff:ffff:ffff:ffff::.
                 <host> Hostname. Example: server01.yandex.ru.
                     To check access, DNS query is performed, and all received addresses compared to peer address.
                 <host_regexp> Regular expression for host names. Example, ^server\d\d-\d\d-\d\.yandex\.ru$
                     To check access, DNS PTR query is performed for peer address and then regexp is applied.
                     Then, for result of PTR query, another DNS query is performed and all received addresses compared to peer address.
                     Strongly recommended that regexp is ends with $
                 All results of DNS requests are cached till server restart.
            -->
            <networks incl="networks" replace="replace">
                <ip>::/0</ip>
            </networks>

            <!-- Settings profile for user. -->
            <profile>default</profile>

            <!-- Quota for user. -->
            <quota>default</quota>

            <!-- For testing the table filters -->
            <databases>
                <test>
                    <!-- Simple expression filter -->
                    <filtered_table1>
                        <filter>a = 1</filter>
                    </filtered_table1>

                    <!-- Complex expression filter -->
                    <filtered_table2>
                        <filter>a + b &lt; 1 or c - d &gt; 5</filter>
                    </filtered_table2>

                    <!-- Filter with ALIAS column -->
                    <filtered_table3>
                        <filter>c = 1</filter>
                    </filtered_table3>
                </test>
            </databases>
        </default>

        <xd_read>
            <password>xd_read_2020</password>
            <networks incl="networks" replace="replace">
                <ip>::/0</ip>
            </networks>
            <profile>xd_read</profile>
            <quota>default</quota>
        </xd_read>

        <!-- Example of user with readonly access. -->
        <!-- <readonly>
                         <password></password>
            <networks incl="networks" replace="replace">
                <ip>::1</ip>
                <ip>127.0.0.1</ip>
            </networks>
            <profile>readonly</profile>
            <quota>default</quota>
        </readonly> -->
    </users>

    <!-- Quotas. -->
    <quotas>
        <!-- Name of quota. -->
        <default>
            <!-- Limits for time interval. You could specify many intervals with different limits. -->
            <interval>
                <!-- Length of interval. -->
                <duration>3600</duration>

                <!-- No limits. Just calculate resource usage for time interval. -->
                <queries>0</queries>
                <errors>0</errors>
                <result_rows>0</result_rows>
                <read_rows>0</read_rows>
                <execution_time>0</execution_time>
            </interval>
        </default>
    </quotas>
</yandex>
```

#### 创建容器

PS：本次创建 Docker 容器，使用的是 Docker 的 Host 网络模式（--network=host）

```bash
vim /etc/clickhouse/ch-s1-r1_start.sh
chmod 744 /etc/clickhouse/ch-s1-r1_start.sh
/etc/clickhouse/ch-s1-r1_start.sh
```


```bash
#!/bin/bash
docker run -d \
--name=ch-s1-r1 \
--hostname=znzjk-133213-prod-mini-bigdata-clickhouse-ch-s1-r1 \
--ulimit nofile=262144:262144 \
--volume=/data/clickhouse/ch-s1-r1/data/:/var/data/clickhouse-server/data/ \
--volume=/data/clickhouse/ch-s1-r1/storage/:/var/data/clickhouse-server/storage/data0/ \
--volume=/data/clickhouse/ch-s1-r1/log/:/var/log/clickhouse-server/ \
--volume=/data/clickhouse/ch-s1-r1/conf/:/etc/clickhouse-server/ \
--network=host \
--restart=always \
yandex/clickhouse-server:21.8.3
```


### 节点 ch-s2-r2

#### 创建容器数据卷对应宿主机路径

```bash
#!/bin/bash
mkdir -p /data/clickhouse/ch-s2-r2/conf/ /etc/clickhouse/
```


#### 创建配置文件

`在/data/clickhouse/ch-s2-r2/conf/路径下创建CH配置文件config.xml和users.xml`

```bash
vim /data/clickhouse/ch-s2-r2/conf/config.xml
vim /data/clickhouse/ch-s2-r2/conf/users.xml
```

##### config.xml

```xml
<?xml version="1.0"?>
<yandex>
    <tcp_port>29000</tcp_port>
    <http_port>8124</http_port>
    <interserver_http_port>9009</interserver_http_port>

    <macros>
        <layer>01</layer>
        <shard>02</shard>
        <replica>cluster01-02-02</replica>
    </macros>

    <!-- 集群配置 -->
    <remote_servers>
        <cluster_3s_2r>
            <!-- 数据分片1  -->
            <shard>
                <internal_replication>true</internal_replication>
                <replica>
                    <host>znzjk-133213-prod-mini-bigdata-clickhouse</host>
                    <port>19000</port>
                </replica>
                <replica>
                    <host>znzjk-133214-prod-mini-bigdata-clickhouse</host>
                    <port>29000</port>
                </replica>
            </shard>

            <!-- 数据分片2  -->
            <shard>
                <internal_replication>true</internal_replication>
                <replica>
                    <host>znzjk-133214-prod-mini-bigdata-clickhouse</host>
                    <port>19000</port>
                </replica>
                <replica>
                    <host>znzjk-133213-prod-mini-bigdata-clickhouse</host>
                    <port>29000</port>
                </replica>
            </shard>
        </cluster_3s_2r>
    </remote_servers>

    <!-- 存储策略 -->
    <storage_configuration>
        <disks>
            <data0>
                <path>/var/data/clickhouse-server/storage/data0/</path>
                <keep_free_space_bytes>1073741824</keep_free_space_bytes>
            </data0>
        </disks>

        <policies>
            <rr>
                <volumes>
                    <single>
                        <disk>data0</disk>
                    </single>
                </volumes>
            </rr>
        </policies>
    </storage_configuration>

    <!-- zk -->
    <zookeeper>
        <node>
            <host>znzjk-133213-prod-mini-bigdata-clickhouse</host>
            <port>2182</port>
        </node>

        <node>
            <host>znzjk-133214-prod-mini-bigdata-clickhouse</host>
            <port>2182</port>
        </node>

        <node>
            <host>znzjk-133215-prod-mini-bigdata-cdh</host>
            <port>2182</port>
        </node>
    </zookeeper>

    <!-- 数据压缩算法  -->
    <compression>
        <case>
            <min_part_size>10000000000</min_part_size>
            <min_part_size_ratio>0.01</min_part_size_ratio>
            <method>lz4</method>
        </case>
    </compression>

    <!-- Listen wildcard address to allow accepting connections from other containers and host network. -->
    <listen_host>::</listen_host>
    <listen_host>0.0.0.0</listen_host>
    <listen_try>1</listen_try>

    <logger>
        <level>trace</level>
        <log>/var/log/clickhouse-server/clickhouse-server.log</log>
        <errorlog>/var/log/clickhouse-server/clickhouse-server.err.log</errorlog>
        <size>1000M</size>
        <count>10</count>
    </logger>


    <max_connections>4096</max_connections>
    <keep_alive_timeout>3</keep_alive_timeout>
    <max_concurrent_queries>100</max_concurrent_queries>
    <uncompressed_cache_size>8589934592</uncompressed_cache_size>
    <mark_cache_size>5368709120</mark_cache_size>


    <path>/var/data/clickhouse-server/data/</path>
    <tmp_path>/var/data/clickhouse-server/data/tmp/</tmp_path>
    <user_files_path>/var/data/clickhouse-server/data/user_files/</user_files_path>
    <users_config>users.xml</users_config>
    <default_profile>default</default_profile>
    <default_database>default</default_database>
    <dictionaries_config>*_dictionary.xml</dictionaries_config>


    <timezone>Asia/Shanghai</timezone>
    <mlock_executable>true</mlock_executable>


    <builtin_dictionaries_reload_interval>3600</builtin_dictionaries_reload_interval>
    <max_session_timeout>3600</max_session_timeout>
    <default_session_timeout>60</default_session_timeout>


    <query_log>
        <database>system</database>
        <table>query_log</table>
        <partition_by>toYYYYMM(event_date)</partition_by>
        <flush_interval_milliseconds>7500</flush_interval_milliseconds>
    </query_log>

    <trace_log>
        <database>system</database>
        <table>trace_log</table>
        <partition_by>toYYYYMM(event_date)</partition_by>
        <flush_interval_milliseconds>7500</flush_interval_milliseconds>
    </trace_log>

    <query_thread_log>
        <database>system</database>
        <table>query_thread_log</table>
        <partition_by>toYYYYMM(event_date)</partition_by>
        <flush_interval_milliseconds>7500</flush_interval_milliseconds>
    </query_thread_log>

    <!-- Allow to execute distributed DDL queries (CREATE, DROP, ALTER, RENAME) on cluster.
         Works only if ZooKeeper is enabled. Comment it if such functionality isn't required. -->
    <distributed_ddl>
        <!-- Path in ZooKeeper to queue with DDL queries -->
        <path>/clickhouse/task_queue/ddl</path>
    </distributed_ddl>

    <!-- Directory in <clickhouse-path> containing schema files for various input formats.
         The directory will be created if it doesn't exist.-->
    <format_schema_path>/var/data/clickhouse-server/data/format_schemas/</format_schema_path>

</yandex>
```

##### users.xml

同上

#### 创建容器

```bash
vim /etc/clickhouse/ch-s2-r2_start.sh
chmod 744 /etc/clickhouse/ch-s2-r2_start.sh
/etc/clickhouse/ch-s2-r2_start.sh
```


```bash
#!/bin/bash
docker run -d \
--name ch-s2-r2 \
--hostname znzjk-133213-prod-mini-bigdata-clickhouse-ch-s2-r2 \
--ulimit nofile=262144:262144 \
--volume=/data/clickhouse/ch-s2-r2/data/:/var/data/clickhouse-server/data/ \
--volume=/data/clickhouse/ch-s2-r2/storage/:/var/data/clickhouse-server/storage/data0/ \
--volume=/data/clickhouse/ch-s2-r2/log/:/var/log/clickhouse-server/ \
--volume=/data/clickhouse/ch-s2-r2/conf/:/etc/clickhouse-server/ \
--network=host \
--restart=always \
yandex/clickhouse-server:21.8.3
```


### 节点 ch-s2-r1

#### 创建容器数据卷对应宿主机路径

```bash
#!/bin/bash
mkdir -p /data/clickhouse/ch-s2-r1/conf/ /etc/clickhouse/ 
```

#### 创建配置文件

`在/data/clickhouse/ch-s2-r1/conf/路径下创建CH配置文件config.xml和users.xml`

```bash
vim /data/clickhouse/ch-s2-r1/conf/config.xml
vim /data/clickhouse/ch-s2-r1/conf/users.xml
```

##### config.xml
```xml
<?xml version="1.0"?>
<yandex>
    <tcp_port>19000</tcp_port>
    <http_port>8123</http_port>
    <interserver_http_port>9008</interserver_http_port>

    <macros>
        <layer>01</layer>
        <shard>02</shard>
        <replica>cluster01-02-01</replica>
    </macros>

    <!-- 集群配置 -->
    <remote_servers>
        <cluster_3s_2r>
            <!-- 数据分片1  -->
            <shard>
                <internal_replication>true</internal_replication>
                <replica>
                    <host>znzjk-133213-prod-mini-bigdata-clickhouse</host>
                    <port>19000</port>
                </replica>
                <replica>
                    <host>znzjk-133214-prod-mini-bigdata-clickhouse</host>
                    <port>29000</port>
                </replica>
            </shard>

            <!-- 数据分片2  -->
            <shard>
                <internal_replication>true</internal_replication>
                <replica>
                    <host>znzjk-133214-prod-mini-bigdata-clickhouse</host>
                    <port>19000</port>
                </replica>
                <replica>
                    <host>znzjk-133213-prod-mini-bigdata-clickhouse</host>
                    <port>29000</port>
                </replica>
            </shard>
        </cluster_3s_2r>
    </remote_servers>

    <!-- 存储策略 -->
    <storage_configuration>
        <disks>
            <data0>
                <path>/var/data/clickhouse-server/storage/data0/</path>
                <keep_free_space_bytes>1073741824</keep_free_space_bytes>
            </data0>
        </disks>

        <policies>
            <rr>
                <volumes>
                    <single>
                        <disk>data0</disk>
                    </single>
                </volumes>
            </rr>
        </policies>
    </storage_configuration>

    <!-- zk -->
    <zookeeper>
        <node>
            <host>znzjk-133213-prod-mini-bigdata-clickhouse</host>
            <port>2182</port>
        </node>

        <node>
            <host>znzjk-133214-prod-mini-bigdata-clickhouse</host>
            <port>2182</port>
        </node>

        <node>
            <host>znzjk-133215-prod-mini-bigdata-cdh</host>
            <port>2182</port>
        </node>
    </zookeeper>

    <!-- 数据压缩算法  -->
    <compression>
        <case>
            <min_part_size>10000000000</min_part_size>
            <min_part_size_ratio>0.01</min_part_size_ratio>
            <method>lz4</method>
        </case>
    </compression>

    <!-- Listen wildcard address to allow accepting connections from other containers and host network. -->
    <listen_host>::</listen_host>
    <listen_host>0.0.0.0</listen_host>
    <listen_try>1</listen_try>

    <logger>
        <level>trace</level>
        <log>/var/log/clickhouse-server/clickhouse-server.log</log>
        <errorlog>/var/log/clickhouse-server/clickhouse-server.err.log</errorlog>
        <size>1000M</size>
        <count>10</count>
    </logger>


    <max_connections>4096</max_connections>
    <keep_alive_timeout>3</keep_alive_timeout>
    <max_concurrent_queries>100</max_concurrent_queries>
    <uncompressed_cache_size>8589934592</uncompressed_cache_size>
    <mark_cache_size>5368709120</mark_cache_size>


    <path>/var/data/clickhouse-server/data/</path>
    <tmp_path>/var/data/clickhouse-server/data/tmp/</tmp_path>
    <user_files_path>/var/data/clickhouse-server/data/user_files/</user_files_path>
    <users_config>users.xml</users_config>
    <default_profile>default</default_profile>
    <default_database>default</default_database>
    <dictionaries_config>*_dictionary.xml</dictionaries_config>


    <timezone>Asia/Shanghai</timezone>
    <mlock_executable>true</mlock_executable>


    <builtin_dictionaries_reload_interval>3600</builtin_dictionaries_reload_interval>
    <max_session_timeout>3600</max_session_timeout>
    <default_session_timeout>60</default_session_timeout>


    <query_log>
        <database>system</database>
        <table>query_log</table>
        <partition_by>toYYYYMM(event_date)</partition_by>
        <flush_interval_milliseconds>7500</flush_interval_milliseconds>
    </query_log>

    <trace_log>
        <database>system</database>
        <table>trace_log</table>
        <partition_by>toYYYYMM(event_date)</partition_by>
        <flush_interval_milliseconds>7500</flush_interval_milliseconds>
    </trace_log>

    <query_thread_log>
        <database>system</database>
        <table>query_thread_log</table>
        <partition_by>toYYYYMM(event_date)</partition_by>
        <flush_interval_milliseconds>7500</flush_interval_milliseconds>
    </query_thread_log>

    <!-- Allow to execute distributed DDL queries (CREATE, DROP, ALTER, RENAME) on cluster.
         Works only if ZooKeeper is enabled. Comment it if such functionality isn't required. -->
    <distributed_ddl>
        <!-- Path in ZooKeeper to queue with DDL queries -->
        <path>/clickhouse/task_queue/ddl</path>
    </distributed_ddl>

    <!-- Directory in <clickhouse-path> containing schema files for various input formats.
         The directory will be created if it doesn't exist.-->
    <format_schema_path>/var/data/clickhouse-server/data/format_schemas/</format_schema_path>

</yandex>
```


##### users.xml

同上

#### 创建容器

```bash
vim /etc/clickhouse/ch-s2-r1_start.sh
chmod 744 /etc/clickhouse/ch-s2-r1_start.sh
/etc/clickhouse/ch-s2-r1_start.sh
```


```bash
#!/bin/bash
docker run -d \
--name ch-s2-r1 \
--hostname znzjk-133214-prod-mini-bigdata-clickhouse-ch-s2-r1 \
--ulimit nofile=262144:262144 \
--volume=/data/clickhouse/ch-s2-r1/data/:/var/data/clickhouse-server/data/ \
--volume=/data/clickhouse/ch-s2-r1/storage/:/var/data/clickhouse-server/storage/data0/ \
--volume=/data/clickhouse/ch-s2-r1/log/:/var/log/clickhouse-server/ \
--volume=/data/clickhouse/ch-s2-r1/conf/:/etc/clickhouse-server/ \
--network=host \
--restart=always \
yandex/clickhouse-server:21.8.3
```


### 节点 ch-s1-r2

#### 创建容器数据卷对应宿主机路径

```bash
#!/bin/bash
mkdir -p /data/clickhouse/ch-s1-r2/conf/  /etc/clickhouse/
```


#### 创建配置文件

`在/data/clickhouse/ch-s1-r2/conf/路径下创建CH配置文件config.xml和users.xml`

```bash
vim /data/clickhouse/ch-s1-r2/conf/config.xml
vim /data/clickhouse/ch-s1-r2/conf/users.xml
```

##### config.xml

```xml
<?xml version="1.0"?>
<yandex>
    <tcp_port>29000</tcp_port>
    <http_port>8124</http_port>
    <interserver_http_port>9009</interserver_http_port>

    <macros>
        <layer>01</layer>
        <shard>01</shard>
        <replica>cluster01-01-02</replica>
    </macros>

    <!-- 集群配置 -->
    <remote_servers>
        <cluster_3s_2r>
            <!-- 数据分片1  -->
            <shard>
                <internal_replication>true</internal_replication>
                <replica>
                    <host>znzjk-133213-prod-mini-bigdata-clickhouse</host>
                    <port>19000</port>
                </replica>
                <replica>
                    <host>znzjk-133214-prod-mini-bigdata-clickhouse</host>
                    <port>29000</port>
                </replica>
            </shard>

            <!-- 数据分片2  -->
            <shard>
                <internal_replication>true</internal_replication>
                <replica>
                    <host>znzjk-133214-prod-mini-bigdata-clickhouse</host>
                    <port>19000</port>
                </replica>
                <replica>
                    <host>znzjk-133213-prod-mini-bigdata-clickhouse</host>
                    <port>29000</port>
                </replica>
            </shard>
        </cluster_3s_2r>
    </remote_servers>

    <!-- 存储策略 -->
    <storage_configuration>
        <disks>
            <data0>
                <path>/var/data/clickhouse-server/storage/data0/</path>
                <keep_free_space_bytes>1073741824</keep_free_space_bytes>
            </data0>
        </disks>

        <policies>
            <rr>
                <volumes>
                    <single>
                        <disk>data0</disk>
                    </single>
                </volumes>
            </rr>
        </policies>
    </storage_configuration>

    <!-- zk -->
    <zookeeper>
        <node>
            <host>znzjk-133213-prod-mini-bigdata-clickhouse</host>
            <port>2182</port>
        </node>

        <node>
            <host>znzjk-133214-prod-mini-bigdata-clickhouse</host>
            <port>2182</port>
        </node>

        <node>
            <host>znzjk-133215-prod-mini-bigdata-cdh</host>
            <port>2182</port>
        </node>
    </zookeeper>

    <!-- 数据压缩算法  -->
    <compression>
        <case>
            <min_part_size>10000000000</min_part_size>
            <min_part_size_ratio>0.01</min_part_size_ratio>
            <method>lz4</method>
        </case>
    </compression>

    <!-- Listen wildcard address to allow accepting connections from other containers and host network. -->
    <listen_host>::</listen_host>
    <listen_host>0.0.0.0</listen_host>
    <listen_try>1</listen_try>

    <logger>
        <level>trace</level>
        <log>/var/log/clickhouse-server/clickhouse-server.log</log>
        <errorlog>/var/log/clickhouse-server/clickhouse-server.err.log</errorlog>
        <size>1000M</size>
        <count>10</count>
    </logger>


    <max_connections>4096</max_connections>
    <keep_alive_timeout>3</keep_alive_timeout>
    <max_concurrent_queries>100</max_concurrent_queries>
    <uncompressed_cache_size>8589934592</uncompressed_cache_size>
    <mark_cache_size>5368709120</mark_cache_size>


    <path>/var/data/clickhouse-server/data/</path>
    <tmp_path>/var/data/clickhouse-server/data/tmp/</tmp_path>
    <user_files_path>/var/data/clickhouse-server/data/user_files/</user_files_path>
    <users_config>users.xml</users_config>
    <default_profile>default</default_profile>
    <default_database>default</default_database>
    <dictionaries_config>*_dictionary.xml</dictionaries_config>


    <timezone>Asia/Shanghai</timezone>
    <mlock_executable>true</mlock_executable>


    <builtin_dictionaries_reload_interval>3600</builtin_dictionaries_reload_interval>
    <max_session_timeout>3600</max_session_timeout>
    <default_session_timeout>60</default_session_timeout>


    <query_log>
        <database>system</database>
        <table>query_log</table>
        <partition_by>toYYYYMM(event_date)</partition_by>
        <flush_interval_milliseconds>7500</flush_interval_milliseconds>
    </query_log>

    <trace_log>
        <database>system</database>
        <table>trace_log</table>
        <partition_by>toYYYYMM(event_date)</partition_by>
        <flush_interval_milliseconds>7500</flush_interval_milliseconds>
    </trace_log>

    <query_thread_log>
        <database>system</database>
        <table>query_thread_log</table>
        <partition_by>toYYYYMM(event_date)</partition_by>
        <flush_interval_milliseconds>7500</flush_interval_milliseconds>
    </query_thread_log>

    <!-- Allow to execute distributed DDL queries (CREATE, DROP, ALTER, RENAME) on cluster.
         Works only if ZooKeeper is enabled. Comment it if such functionality isn't required. -->
    <distributed_ddl>
        <!-- Path in ZooKeeper to queue with DDL queries -->
        <path>/clickhouse/task_queue/ddl</path>
    </distributed_ddl>

    <!-- Directory in <clickhouse-path> containing schema files for various input formats.
         The directory will be created if it doesn't exist.-->
    <format_schema_path>/var/data/clickhouse-server/data/format_schemas/</format_schema_path>

</yandex>
```


##### users.xml

同上


#### 创建容器

```bash
vim /etc/clickhouse/ch-s1-r2_start.sh
chmod 744 /etc/clickhouse/ch-s1-r2_start.sh
/etc/clickhouse/ch-s1-r2_start.sh
```

```bash
#!/bin/bash
docker run -d \
--name ch-s1-r2 \
--hostname znzjk-133214-prod-mini-bigdata-clickhouse-ch-s1-r2 \
--ulimit nofile=262144:262144 \
--volume=/data/clickhouse/ch-s1-r2/data/:/var/data/clickhouse-server/data/ \
--volume=/data/clickhouse/ch-s1-r2/storage/:/var/data/clickhouse-server/storage/data0/ \
--volume=/data/clickhouse/ch-s1-r2/log/:/var/log/clickhouse-server/ \
--volume=/data/clickhouse/ch-s1-r2/conf/:/etc/clickhouse-server/ \
--network=host \
--restart=always \
yandex/clickhouse-server:21.8.3
```

## 集群连接测试

```bash
docker exec -it ch-s1-r1 clickhouse-client --host=znzjk-133213-prod-mini-bigdata-clickhouse  --port=19000 -m
docker exec -it ch-s1-r1 clickhouse-client --host=znzjk-133213-prod-mini-bigdata-clickhouse  --port=29000 -m
docker exec -it ch-s1-r1 clickhouse-client --host=znzjk-133214-prod-mini-bigdata-clickhouse  --port=19000 -m
docker exec -it ch-s1-r1 clickhouse-client --host=znzjk-133214-prod-mini-bigdata-clickhouse  --port=29000 -m
```

## End~
