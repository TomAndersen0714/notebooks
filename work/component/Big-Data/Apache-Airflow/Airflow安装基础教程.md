# Airflow安装基础教程



## 前言

本文安装 Airflow 为 `Airflow2.x` 版本。目前（2022-12-22），[官方文档](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#)中提供了共计6种安装方案：

1. [Using released sources](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#using-released-sources)
2. [Using PyPI](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#using-pypi)
3. [Using Production Docker Images](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#using-production-docker-images)
4. [Using Official Airflow Helm Chart](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#using-official-airflow-helm-chart)
5. [Using Managed Airflow Services](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#using-managed-airflow-services)
6. [Using 3rd-party images, charts, deployments](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#using-3rd-party-images-charts-deployments)



本文主要采用PyPI工具安装Airflow，并以`standalone`形式初始化各个组件

## 具体步骤


### 1) 创建Python虚拟环境

创建Python虚拟环境，实现环境隔离

```shell
python3 -m venv airflow_venv
```

加载虚拟环境

```shell
source airflow_venv/bin/active
```



### 2) 执行安装命令

```shell
# Airflow needs a home. `~/airflow` is the default, but you can put it
# somewhere else if you prefer (optional)
export AIRFLOW_HOME=~/airflow

# Install Airflow using the constraints file
AIRFLOW_VERSION=2.5.0
PYTHON_VERSION="$(python --version | cut -d " " -f 2 | cut -d "." -f 1-2)"
# For example: 3.7
CONSTRAINT_URL="https://raw.githubusercontent.com/apache/airflow/constraints-${AIRFLOW_VERSION}/constraints-${PYTHON_VERSION}.txt"
# For example: https://raw.githubusercontent.com/apache/airflow/constraints-2.5.0/constraints-3.7.txt
pip install "apache-airflow==${AIRFLOW_VERSION}" --constraint "${CONSTRAINT_URL}"
```


### 3) 初始化并启动Airflow

初始化数据库和创建用户，此命令会自动生成默认的配置文件`$AIRFLOW_HOME/airflow.cfg`

```shell
# 初始化数据库
airflow db init

# 创建admin用户, 并手动设置密码
airflow users create \
    --username admin \
    --firstname Peter \
    --lastname Parker \
    --role Admin \
    --email spiderman@superhero.org
```


修改配置文件airflow.cfg，调整默认配置

```ini
# Whether to load the DAG examples that ship with Airflow. It's good to
# get started, but you probably want to set this to ``False`` in a production
# environment
load_examples = False

# How often (in seconds) to scan the DAGs directory for new files. Default to 5 minutes.
dag_dir_list_interval = 60
```


重置数据库，删除默认的example dag

```shell
airflow db reset
```


启动airflow服务

```shell
airflow webserver -D
airflow scheduler -D


# 如果使用了celery, 可以通过以下命令启动
airflow celery worker -D   #启动celery worker
airflow celery flower -D   #启动flower
```


也可以直接执行`airflow standalone`命令自动执行

执行此命令后，airflow默认会创建`admin`用户，其默认密码会打印在`$AIRFLOW_HOME/standalone_admin_password.txt`文件中

```shell
# The Standalone command will initialise the database, make a user,
# and start all components for you.
airflow standalone

# Visit localhost:8080 in the browser and use the admin account details
# shown on the terminal to login.
# Enable the example_bash_operator dag in the home page
```



### 4) 停止Airflow

```shell
cd $AIRFLOW_HOME

cat airflow-webserver.pid | xargs kill

cat airflow-scheduler.pid | xargs kill

cat airflow-worker.pid | xargs kill

mv *.pid /tmp/
```



## 常见问题

### error: sqlite C library version too old (< 3.15.0)

报错信息：`error: sqlite C library version too old (< 3.15.0)`

问题原因：默认 sqlite3 版本太低，需要升级

解决方案：[sqlite3安装基础教程](work/component/Back-End/SQLite/sqlite3安装基础教程.md)


### Airflow - alembic.util.exc.CommandError: Can't locate revision identified

报错信息：`Airflow - alembic.util.exc.CommandError: Can't locate revision identified`

问题原因：Airflow 数据库存在问题，建议重置 airflow 数据库，`airflow reset db`，如果重置数据库报错无法级联删除，则需要自己手动连接对应数据库执行删除命令。


## 参考链接

1. https://airflow.apache.org/docs/apache-airflow/stable/start.html