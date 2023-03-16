# MongoDB常用命令


## db
1. [官方链接](https://www.mongodb.com/docs/manual/reference/method/js-database/)
2. `show database`：查看所有数据库清单
3. `use <database>`：切换数据库
4. `db.help()`：查看db对象的常用方法
5. `db.hello()`：查看数据库所有副本状态
6. `db.version()`：查看数据库版本
7. `db.stats()`：查看数据库的统计信息

## Collections
1. [官方链接](https://www.mongodb.com/docs/manual/reference/method/js-collection/)
2. 调用格式：db.collection.findOne()，将collection替换为对应的collection即可，如“db.<collection_name>.findOne()”
3. `show collections`：查看当前数据库中所有的Collection
4. `show tables`：同`show collections`
5. 



## 参考链接
1. [MongoDB Manual](https://www.mongodb.com/docs/manual/)