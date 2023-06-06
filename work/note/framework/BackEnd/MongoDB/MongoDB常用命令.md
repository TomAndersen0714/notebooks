# MongoDB常用命令


## db
1. [官方链接](https://www.mongodb.com/docs/manual/reference/method/js-database/)
2. `show databases | db.getMongo().getDBNames()`：查看所有数据库清单
3. `use <database>`：切换数据库
4. `db.help()`：查看db对象的常用方法
5. `db.hello()`：查看数据库所有副本状态，PS：返回信息中包括主库和从库信息，可以判断当前是否为从库
6. `db.version()`：查看数据库版本
7. `db.stats()`：查看数据库的统计信息
8. `db.currentOp()`：查看集群当前正在执行的操作
9. `db.killOp()`：终止输入的opid对应的操作，对集群都生效


## collections
1. [官方链接](https://www.mongodb.com/docs/manual/reference/method/js-collection/)
2. 语法：
   db.collection.findOne()，将collection替换为对应的collection名称即可，如“db.<collection_name>.findOne()”
3. `show collections | db.getCollectionNames()`：查看当前数据库中所有的Collection
4. `show tables`：同`show collections`
5. `db.collection.findOne()`：查询单个文档，如查询ID为指定值的文档：`db.<collection_name>.findOne({"_id" : ObjectId("619b57f58846b9000182a354")})`
6. `db.collection.getIndexes()`：查看集合collection索引，“db.<collection_name>.getIndexes()”



## Mongo查询常见问题
报错“MongoServerError: not primary and secondaryOk=false”，可以在命令行执行“rs.secondaryOk()”命令解决



## 参考链接
1. [MongoDB Manual - method](https://www.mongodb.com/docs/manual/reference/method/)