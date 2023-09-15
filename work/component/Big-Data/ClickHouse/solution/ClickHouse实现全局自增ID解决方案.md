# ClickHouse实现全局自增ID解决方案


## 算法

第一步：查询表所有表中已经保存的最大ID，即maxId

第二步：基于maxId，使用`rowNumberInAllBlocks`函数，生成新增ID



## 测试用例

### 测试环境

ClickHouse Version: 21.8.14.5


### 创建测试数据表

```sql
CREATE DATABASE IF NOT EXISTS test ON CLUSTER cluster_3s_2r
ENGINE=Ordinary;

-- DROP TABLE test.visits_local ON CLUSTER cluster_3s_2r NO DELAY
CREATE TABLE IF NOT EXISTS test.visits_local ON CLUSTER cluster_3s_2r
(
    `StartDate` UInt32,
    `UserName` String,
    `UserID` UInt32
)
ENGINE = ReplicatedMergeTree(
    '/clickhouse/{database}/tables/{layer}_{shard}/{table}',
    '{replica}'
)
PARTITION BY StartDate
ORDER BY (UserName, UserID)
SETTINGS index_granularity = 8192, storage_policy = 'rr';


-- DROP TABLE test.visits_all ON CLUSTER cluster_3s_2r NO DELAY
CREATE TABLE IF NOT EXISTS test.visits_all ON CLUSTER cluster_3s_2r
AS test.visits_local
ENGINE = Distributed('cluster_3s_2r', 'test', 'visits_local', rand());


CREATE DATABASE IF NOT EXISTS buffer ON CLUSTER cluster_3s_2r
ENGINE=Ordinary;

-- DROP TABLE buffer.test_visits_buffer ON CLUSTER cluster_3s_2r NO DELAY
CREATE TABLE IF NOT EXISTS buffer.test_visits_buffer ON CLUSTER cluster_3s_2r
AS test.visits_all
ENGINE = Buffer('test', 'visits_all', 16, 15, 35, 81920, 409600, 16777216, 67108864);
```


### 生成随机测试数据

```sql
INSERT INTO test.visits_all
SELECT
    toYYYYMMDD(yesterday()) AS StartDate,
    randomPrintableASCII(2) AS UserName,
    rowNumberInAllBlocks() AS UserID
FROM numbers(100000)
```


### 新增随机测试数据

```sql
INSERT INTO test.visits_all
WITH (
    SELECT max(UserID)+1 FROM test.visits_all
) AS max_id
SELECT
    StartDate,
    UserName,
    max_id+rowNumberInAllBlocks() AS UserID
FROM (
    SELECT
        toYYYYMMDD(yesterday()) AS StartDate,
        randomPrintableASCII(2) AS UserName
    FROM numbers(100)
    WHERE UserName NOT IN (
        SELECT DISTINCT
            UserName
        FROM test.visits_all
        WHERE StartDate = toYYYYMMDD(yesterday())
    )
)
```


### 验证算法结果

```sql
SELECT
    COUNT(DISTINCT UserID),
    COUNT(1)
FROM test.visits_all
WHERE StartDate = toYYYYMMDD(yesterday())
```