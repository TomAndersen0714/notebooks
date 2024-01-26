# Spark Group By 算子原理基础教程


## Spark SQL



[Underlying implementation of Group By clause in Spark SQL - Stack Overflow](https://stackoverflow.com/a/57720658)
In Spark SQL, if you call groupBy (key). Agg (...) with some aggregation function inside agg, the typical physical plan is HashAggregate -> Exchange -> HashAggregate. The first HashAggregate is responsible for doing partial aggregation (locally on each executor), then the Exchange represents shuffle and then the second HashAggregate represents the final aggregation (final merge) after the shuffle.


[Spark SQL Query Engine Deep Dive (8) – Aggregation Strategy – Azure Data Ninjago & dqops](https://dataninjago.com/2022/01/04/spark-deep-dive-12-aggregation-strategy/)