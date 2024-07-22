# spark-submit 命令行工具基础教程

## 简介

Spark-submit 命令行工具是 Apache Spark 的一个核心组件，用于提交 Spark 应用程序到 Spark 集群上运行。

Spark-submit 命令行工具支持提交 Scala/Java 代码实现的 jar 文件，也支持 Python 实现的 Py 文件，将其中定义的 Spark Application 提交给 Spark 集群运行。

## spark-submit

### Options

Some of the commonly used options are:
- `--class` : The entry point for your application (e.g. `org.apache.spark.examples.SparkPi`)
- `--master`: The master URL for the cluster (e.g. `spark://23.195.26.187:7077` )  
- `--deploy-mode`: Whether to deploy your driver on the worker nodes (cluster) or locally as an external client (client) (default: `client`)
- `--conf`: Arbitrary Spark configuration property in key=value format. For values that contain spaces wrap “key=value” in quotes (as shown). Multiple configurations should be passed as separate arguments. `(e.g. --conf <key>=<value> --conf <key2>=<value2>)`
- `application-jar` : Path to a bundled jar including your application and all dependencies. The URL must be globally visible inside of your cluster, for instance, an `hdfs://path` or a `file://path` that is present on all nodes.
- `application-arguments` : Arguments passed to the main method of your main class, if any

### Example

```shell
# Demo
./bin/spark-submit \
  --master <master-url> \
  --deploy-mode <deploy-mode> \
  --conf <key<=<value> \
  --driver-memory <value>g \
  --executor-memory <value>g \
  --executor-cores <number of cores>  \
  --jars  <comma separated dependencies>
  --class <main-class> \
  <application-jar> \
  [application-arguments]

# Run application locally on 8 cores
./bin/spark-submit \
  --class org.apache.spark.examples.SparkPi \
  --master local[8] \
  /path/to/examples.jar \
  100

# Run on a Spark standalone cluster in client deploy mode
./bin/spark-submit \
  --class org.apache.spark.examples.SparkPi \
  --master spark://207.184.161.138:7077 \
  --executor-memory 20G \
  --total-executor-cores 100 \
  /path/to/examples.jar \
  1000

# Run on a Spark standalone cluster in cluster deploy mode with supervise
./bin/spark-submit \
  --class org.apache.spark.examples.SparkPi \
  --master spark://207.184.161.138:7077 \
  --deploy-mode cluster \
  --supervise \
  --executor-memory 20G \
  --total-executor-cores 100 \
  /path/to/examples.jar \
  1000

# Run on a YARN cluster in cluster deploy mode
export HADOOP_CONF_DIR=XXX
./bin/spark-submit \
  --class org.apache.spark.examples.SparkPi \
  --master yarn \
  --deploy-mode cluster \
  --executor-memory 20G \
  --num-executors 50 \
  /path/to/examples.jar \
  1000

# Run a Python application on a Spark standalone cluster
./bin/spark-submit \
  --master spark://207.184.161.138:7077 \
  examples/src/main/python/pi.py \
  1000

# Run on a Mesos cluster in cluster deploy mode with supervise
./bin/spark-submit \
  --class org.apache.spark.examples.SparkPi \
  --master mesos://207.184.161.138:7077 \
  --deploy-mode cluster \
  --supervise \
  --executor-memory 20G \
  --total-executor-cores 100 \
  http://path/to/examples.jar \
  1000

# Run on a Kubernetes cluster in cluster deploy mode
./bin/spark-submit \
  --class org.apache.spark.examples.SparkPi \
  --master k8s://xx.yy.zz.ww:443 \
  --deploy-mode cluster \
  --executor-memory 20G \
  --num-executors 50 \
  http://path/to/examples.jar \
  1000
```


## spark-submit PySpark Application

### Options

- `--py-files` : This is used to specify `.py `, `.zip ` or . Egg files which are uploaded to the cluster before it runs the application. You also upload these files ahead and refer them in your PySpark application.
- `--config spark.executor.pyspark.memory` : Specify the executor memory for the PySpark application.
- `–-config spark.pyspark.python` : Specifies the Python binary executable (Python Interpreter) path that should be used for PySpark in both the driver and the executors. This property allows you to specify a custom Python binary executable path if the default Python interpreter needs to be overridden.

### Example

```shell
# Example 1:
# Spark submit pyspark application
./bin/spark-submit \
   --master yarn \
   --deploy-mode cluster \
   wordByExample.py

# Example 2 : Below example uses other python files as dependencies.
./bin/spark-submit \
   --master yarn \
   --deploy-mode cluster \
   --py-files file1.py,file2.py,file3.zip
   wordByExample.py
```

## 参考链接

1. [spark-submit command options](https://docs.cloudera.com/runtime/7.2.18/running-spark-applications/topics/spark-submit-options.html)
2. [Spark Submit Command Explained with Examples - Spark By {Examples}](https://sparkbyexamples.com/spark/spark-submit-command/)
