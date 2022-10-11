# Trino安装部署入门教程

## 安装部署

### 创建数据路径

PS：注意文件权限

```Shell
mkdir /data1/trino/data
```



### 创建配置文件

PS：注意文件权限

```Shell
mkdir /data1/trino/conf
```



#### config.properties

```Shell
#single node install config
coordinator=true
node-scheduler.include-coordinator=true
http-server.http.port=8080
discovery.uri=http://localhost:8080
```

#### jvm.config

```Shell
-server
-agentpath:/usr/lib/trino/bin/libjvmkill.so
-XX:InitialRAMPercentage=80
-XX:MaxRAMPercentage=80
-XX:G1HeapRegionSize=32M
-XX:+ExplicitGCInvokesConcurrent
-XX:+HeapDumpOnOutOfMemoryError
-XX:+ExitOnOutOfMemoryError
-XX:-OmitStackTraceInFastThrow
-XX:ReservedCodeCacheSize=256M
-XX:PerMethodRecompilationCutoff=10000
-XX:PerBytecodeRecompilationCutoff=10000
-Djdk.attach.allowAttachSelf=true
-Djdk.nio.maxCachedBufferSize=2000000
# Improve AES performance for S3, etc. on ARM64 (JDK-8271567)
-XX:+UnlockDiagnosticVMOptions
-XX:+UseAESCTRIntrinsics
```

#### log.properties

```Shell
# Enable verbose logging from Trino
#io.trino=DEBUG
```

#### node.properties

```Shell
node.environment=docker
node.data-dir=/data/trino
```

### 配置connector

**配置路径(容器)：/etc/trino/conf**



#### mongodb.properties

```Shell
connector.name=mongodb
mongodb.connection-url=mongodb://root:CDxddev2189@10.0.2.1:27017
```

#### clickhouse.properties

```Shell
connector.name=clickhouse
connection-url=jdbc:clickhouse://10.0.2.1:8123
#connection-user=
#connection-password=
```

## 创建镜像

```Shell
docker run --name trino -d -p 18080:8080 \
    --volume /data1/trino/conf:/etc/trino \
    --volume /data1/trino/data:/data/trino \
    trinodb/trino:399
```

## 入门教程

### 基础功能



**1. 查看帮助命令**

```sql
help;
```



**2. 查看所有的catalog，PS：相当于查看所有的connector**

```sql
SHOW CATALOGS [LIKE <pattern>];
```



**3. 查看指定catalog下的所有schema，PS：相当于查看指定connector下的database**

```sql
SHOW SCHEMAS FROM <catalog>;
```



**4. 查看指定schema下的所有table，PS：相当于常见的RDBMS中查看指定database下的所有table**

```sql
SHOW TABLES [FROM <schema>] [LIKE <pattern>];
```



**5. 查看指定table的表结构**

```sql
DESCRIBE <table>
```



### 单数据源查询

```sql
SELECT DISTINCT
    from_utf8(shop_id) AS shop_id_str
FROM clickhouse.tmp.order_event_dis
LIMIT 10
```



### 跨数据源关联查询

```sql
SELECT *
FROM mongodb.xqc.shop
WHERE cast(shop_id as varchar) IN (
    SELECT DISTINCT
        from_utf8(shop_id) AS shop_id_str
    FROM clickhouse.tmp.order_event_dis
    LIMIT 10
)
```



### 跨数据源ETL

```sql
trino> DESCRIBE mongodb.xqc.shop;
     Column     |     Type     | Extra | Comment 
----------------+--------------+-------+---------
 create_time    | timestamp(3) |       |         
 update_time    | timestamp(3) |       |         
 company_id     | ObjectId     |       |         
 shop_id        | ObjectId     |       |         
 platform       | varchar      |       |         
 seller_nick    | varchar      |       |         
 plat_shop_name | varchar      |       |         
 plat_shop_id   | varchar      |       |         
(8 rows)
```



```sql
trino> DESCRIBE clickhouse.trino.xqc_shop_all;
     Column     |   Type    | Extra | Comment 
----------------+-----------+-------+---------
 create_time    | varbinary |       |         
 update_time    | varbinary |       |         
 company_id     | varbinary |       |         
 shop_id        | varbinary |       |         
 platform       | varbinary |       |         
 seller_nick    | varbinary |       |         
 plat_shop_name | varbinary |       |         
 plat_shop_id   | varbinary |       |         
(8 rows)
```



```sql
INSERT INTO clickhouse.trino.xqc_shop_all
SELECT
    to_utf8(CAST(create_time AS varchar)) AS create_time,
    to_utf8(CAST(update_time AS varchar)) AS update_time,
    to_utf8(CAST(company_id AS varchar)) AS company_id,
    to_utf8(CAST(shop_id AS varchar)) AS shop_id,
    to_utf8(CAST(platform AS varchar)) AS platform,
    to_utf8(CAST(seller_nick AS varchar)) AS seller_nick,
    to_utf8(CAST(plat_shop_name AS varchar)) AS plat_shop_name,
    to_utf8(CAST(plat_shop_id AS varchar)) AS plat_shop_id
FROM mongodb.xqc.shop
LIMIT 10
```



## 参考链接

[Trino in containers - Trino 399 Documentation](https://trino.io/docs/current/installation/containers.html)