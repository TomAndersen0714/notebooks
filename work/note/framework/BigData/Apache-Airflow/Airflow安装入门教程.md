# Airflow安装入门教程





## 前言

目前（2022-12-22），[官方文档](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#)中提供了共计6种安装方案：

1. [Using released sources](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#using-released-sources)
2. [Using PyPI](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#using-pypi)
3. [Using Production Docker Images](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#using-production-docker-images)
4. [Using Official Airflow Helm Chart](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#using-official-airflow-helm-chart)
5. [Using Managed Airflow Services](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#using-managed-airflow-services)
6. [Using 3rd-party images, charts, deployments](https://airflow.apache.org/docs/apache-airflow/stable/installation/index.html#using-3rd-party-images-charts-deployments)



本文主要采用PyPI工具安装Airflow，并以standalone形式初始化各个组件



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

```shell
# The Standalone command will initialise the database, make a user,
# and start all components for you.
airflow standalone

# Visit localhost:8080 in the browser and use the admin account details
# shown on the terminal to login.
# Enable the example_bash_operator dag in the home page
```



airflow默认会创建`admin`用户，而其默认密码会打印在`$AIRFLOW_HOME/standalone_admin_password.txt`文件中



## 常见问题

### Q1: 默认sqlite3版本太低，需要升级

报错信息：`error: sqlite C library version too old (< 3.15.0)`





## 参考链接

1. https://airflow.apache.org/docs/apache-airflow/stable/start.html