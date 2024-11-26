# Spark 资源调度基础原理

默认情况下，Spark Application 运行在不同的 Cluster Managers 下时，所采取的资源调度策略也不尽相同，但对于绝大多数的 Cluster Managers，Spark 都支持默认资源调度、静态资源调度、动态资源调度，共计三种资源调度方式。

## 默认调度方式

Standalone mode:
- 默认情况下，standalone mode 下的 Spark Application 会采用 FIFO (first-in-first-out) 先入先出的方式执行，并且每个 Application 都会尽可能使用所有的节点资源。

Mesos:

YARN:

## 静态资源调度

- **Standalone mode:** You can limit the number of nodes an application uses by setting the `spark.cores.max` configuration property in it, or change the default for applications that don’t set this setting through `spark.deploy.defaultCores`. Finally, in addition to controlling cores, each application’s `spark.executor.memory` setting controls its memory use.
- **Mesos:** To use static partitioning on Mesos, set the `spark.mesos.coarse` configuration property to `true`, and optionally set `spark.cores.max` to limit each application’s resource share as in the standalone mode. You should also set `spark.executor.memory` to control the executor memory.
- **YARN:** The `--num-executors` option to the Spark YARN client controls how many executors it will allocate on the cluster (`spark.executor.instances` as configuration property), while `--executor-memory` (`spark.executor.memory` configuration property) and `--executor-cores` (`spark.executor.cores` configuration property) control the resources per executor. For more information, see the [YARN Spark Properties](https://spark.apache.org/docs/3.5.2/running-on-yarn.html#spark-properties).

## 动态资源调度

### 配置方式

启动资源动态分配:
- 设置 `spark.dynamicAllocation.enabled` 为 true。

配置 Shuffle，避免 executor 被回收后无法获取到其生成的 shuffle 结果:
- your application must set `spark.shuffle.service.enabled` to `true` after you set up an _external shuffle service_ on each worker node in the same cluster, or
- your application must set `spark.dynamicAllocation.shuffleTracking.enabled` to `true`, or
- your application must set both `spark.decommission.enabled` and `spark.storage.decommission.shuffleBlocks.enabled` to `true`, or
- your application must configure `spark.shuffle.sort.io.plugin.class` to use a custom `ShuffleDataIO` who’s `ShuffleDriverComponents` supports reliable storage.

如上面配置，可以通过在集群的所有 Worker 节点上配置外部 Shuffle 服务，来存储和读取 Shuffle 结果，具体配置方式见官网，此处不再赘述。

### Executor 请求策略

当 Spark Application 是通过轮询的方式来请求额外的 executor，当在任务队列中存在未调度 pending 任务达到 `spark.dynamicAllocation.schedulerBacklogTimeout` 秒时触发资源请求，随后如果任务队列中的未调度任务仍然存在，将每隔 `spark.dynamicAllocation.sustainedSchedulerBacklogTimeout` 秒再次触发资源请求。

并且，每次请求的 executor 数量，会呈现指数增长，如第一次请求 1 个，则后续会连续请求 2、4、8 个 executor。

Spark 采用指数增长策略的考量主要有两个方面。首先，应用程序在开始时应该谨慎地请求 executor，以防仅需少量额外执行器就能满足需求，这与 TCP 慢启动的原理类似。其次，应用程序也能够及时地增加资源使用量，以防需要大量执行器来满足实际需求，即随请求次数指数增加，避免多次请求。

### Executor 回收策略

当 Spark Application 中的 Executor 的空闲时长超出 `spark.dynamicAllocation.executorIdleTimeout` 时，则会从当前 Application 中被删除回收。

### 注意事项

在 `Standalone Mode` 下，如果使用了动态资源分配，并且未显式配置 `spark.executor.cores` ，那么在实际 Spark Application 启动时，会请求超出预期很多的 executor，所以并不建议在 Standalone Mode 下启用动态资源分配，建议直接使用静态资源分配，因为这是Spark 的一个 BUG [SPARK-30299](https://issues.apache.org/jira/browse/SPARK-30299)。

## 参考链接

1. [Job Scheduling - Spark 3.5.2 Documentation](https://spark.apache.org/docs/3.5.2/job-scheduling.html#resource-allocation-policy)
2. [Configuration - Spark 3.5.2 Documentation](https://spark.apache.org/docs/3.5.2/configuration.html)