# AnalysisRunner

com.amazon.deequ.analyzers.runners.AnalysisRunner

## Run Method

参数列表：
```scala
data: DataFrame,  
analysis: Analysis,  
aggregateWith: Option[StateLoader] = None,  
saveStatesWith: Option[StatePersister] = None,  
storageLevelOfGroupedDataForMultiplePasses: StorageLevel = StorageLevel.MEMORY_AND_DISK
```

- Data：即需要分析的 DataFrame 数据集
- Analysis: 即 analyzer 的集合，旧版本会调用其 run 方法来针对数据集执行分析，但此方法已经 Deprecated
- AggregateWith: 

执行过程：
