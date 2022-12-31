# PostgreSQL常用命令

## 前言

PostgreSQL某些命令在低版本的客户端上无法执行，个人建议，如果是在能够直连数据库的网络环境下，建议使用Jetbrain客户端的DataBase功能，直接添加Postgre Source，通过界面去管理数据库，并且能够自动生成各种SQL命令



## Constrain(约束)

添加约束

ALTER TABLE sxx.shop_day_stats ADD CONSTRAINT unique_1 UNIQUE (day, shop_id, platform, goods_id, stat_label)



删除约束

ALTER TABLE sxx.shop_day_stats DROP CONSTRAINT shop_day_stats_day_shop_id_platform_goods_id_stat_label_key