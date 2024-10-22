# MetricExample

com.amazon.deequ.examples.MetricExample

## Main Method

生成 DataFrame 测试数据

```scala
val data = itemsAsDataframe(  
  session,  
  Item(1, "Thingy A", "10409983787", "high", 0),  
  Item(2, "Thingy B", "13409983787", "low", 0),  
  Item(3, "欧阳娜娜", null, null, 5),  
  Item(4, "王志开", null, "null", 10),  
  Item(5, "黄志远", "110", "null", 10),  
)
```

创建 Analyzer 和 Analysis，并将 Analyzer 添加到 Analysis 同时生成新的 Analysis

```scala
val analysis = Analysis()  
  .addAnalyzer(Size())  
  .addAnalyzer(ApproxCountDistinct("id"))
  ...
```

通过 AnalysisRunner 针对数据进行分析，并返回 AnalyzerContext，即数据分析结果

```scala
val metricsForData: AnalyzerContext = AnalysisRunner.run(  
  data = data,  
  analysis = analysis,  
  //        saveStatesWith = Some(stateStore) // persist the internal state of the computation  
)
```

打印 AnalyzerContext 数据分析结果

```scala
println(s"Metrics for the first ${data.count()} records:\n")  
metricsForData.metricMap.foreach {  
  case (analyzer, metric)  
  => println(s"\t$analyzer: ${metric.value.get}")  
}
```