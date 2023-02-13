# PostgreSQL常用命令

## 前言

由于PostgreSQL客户端版本问题，PostgreSQL某些命令在低版本的客户端上无法执行，个人建议，如果是在能够直连数据库的网络环境下，建议使用Jetbrain IDE的自带功能Database，直接添加Postgre Source，通过UI去管理数据库，并且IDEA也能够自动生成各种SQL命令



## Constraints(约束)

添加约束
ALTER TABLE sxx.shop_day_stats ADD CONSTRAINT unique_1 UNIQUE (day, shop_id, platform, goods_id, stat_label)


删除约束
ALTER TABLE sxx.shop_day_stats DROP CONSTRAINT shop_day_stats_day_shop_id_platform_goods_id_stat_label_key


## 其他

查询表结构
```mysql
select column_name,
    data_type,
    character_maximum_length,
    column_default,
    is_nullable
from INFORMATION_SCHEMA.COLUMNS
where table_name = 'sxx.ft_product_mapping_tm'
```



## 参考链接

1. [PostgreSQL 14.7 Documentation](https://www.postgresql.org/docs/14/index.html)
2. [PostgreSQL 14.7 Documentation - SQL Commands](https://www.postgresql.org/docs/14/sql-commands.html)
4. [数据库教程 - PostgreSQL](https://www.sjkjc.com/postgresql/basic/)
