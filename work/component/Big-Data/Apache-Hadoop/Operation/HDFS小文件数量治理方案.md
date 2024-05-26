# HDFS 小文件数量治理方案





## Spark SQL

```sql
-- coalesce_files_num 变量指的是合并后的文件数量
insert into ...
select ...
from ...
distribute by ceiling(rand()*${coalesce_files_num})
```

## 参考链接
1. [知乎-数据治理实践 | 小文件治理](https://zhuanlan.zhihu.com/p/626957276)
2. [微信-网易有数-数据治理实践 | 小文件治理](https://mp.weixin.qq.com/s/HDxAGhGIPvXF38wPiXZ7xg)