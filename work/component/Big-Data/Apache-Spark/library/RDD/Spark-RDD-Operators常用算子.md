# Spark RDD Operators 常用算子

由于在 Spark 大多数情况下，DataFrame、DataSet，还是 SparkSQL，最后都会转换成调用 RDD 算子的 API。

## Transform 算子

- `repartition`：修改 RDD 的 partition 数量
- `map`：对RDD中的每个元素进行操作，并返回一个新的RDD。
- `filter`：筛选RDD中的元素，返回满足条件的元素组成的新RDD。
- `flatMap`：类似于`map`，但每个输入元素可以映射到0个或多个输出元素。
- `union`：合并两个RDD。
- `intersection`：返回两个RDD的交集。

### Shuffle 算子

- `reduceByKey`：对每个键对应的值进行聚合操作。
- `groupByKey`：将具有相同键的值分组。
- `distinct`：去重，返回一个包含唯一元素的新 RDD。

## Action 算子

- `collect`：将RDD的所有元素收集到驱动程序中，返回一个数组。
- `show`
- `count`：计算RDD中的元素数量。
- `take`：返回RDD的前n个元素。
- `reduce`：对RDD中的元素进行聚合操作。
- `foreach`：对RDD中的每个元素执行指定操作。
- `saveAsTextFile`：将RDD保存到文本文件中。
- `saveAsSequenceFile`：将RDD保存到序列文件中。
### Shuffle 算子

CountByKey