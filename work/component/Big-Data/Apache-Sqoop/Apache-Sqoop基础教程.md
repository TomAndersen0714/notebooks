# Apache Sqoop 基础教程

## 简介

Sqoop is a tool designed to transfer data between Hadoop and relational databases or mainframes. You can use Sqoop to import data from a relational database management system (RDBMS) such as MySQL or Oracle or a mainframe into the Hadoop Distributed File System (HDFS), transform the data in Hadoop MapReduce, and then export the data back into an RDBMS.

Sqoop automates most of this process, relying on the database to describe the schema for the data to be imported. Sqoop uses MapReduce to import and export the data, which provides parallel operation as well as fault tolerance.

## sqoop-import

Sqoop imports data in parallel from most database sources. You can specify the number of map tasks (parallel processes) to use to perform the import by using the `-m` or `--num-mappers` argument. Each of these arguments takes an integer value which corresponds to the degree of parallelism to employ.

**By default, four tasks are used. Some databases may see improved performance by increasing this value to 8 or 16.**

Do not increase the degree of parallelism greater than that available within your MapReduce cluster; tasks will run serially and will likely increase the amount of time required to perform the import. Likewise, do not increase the degree of parallism higher than that which your database can reasonably support. Connecting 100 concurrent clients to your database may increase the load on the database server to a point where performance suffers as a result.

When performing parallel imports, Sqoop needs a criterion by which it can split the workload. Sqoop uses a splitting column to split the workload. By default, Sqoop will identify the primary key column (if present) in a table and use it as the splitting column. The low and high values for the splitting column are retrieved from the database, and the map tasks operate on evenly-sized components of the total range. For example, if you had a table with a primary key column of id whose minimum value was 0 and maximum value was 1000, and Sqoop was directed to use 4 tasks, Sqoop would run four processes which each execute SQL statements of the form SELECT * FROM sometable WHERE id >= lo AND id < hi, with (lo, hi) set to (0, 250), (250, 500), (500, 750), and (750, 1001) in the different tasks.

### Controlling Parallelism
https://sqoop.apache.org/docs/1.4.6/SqoopUserGuide.html#_controlling_parallelism

## sqoop-export


## 参考链接
1. https://sqoop.apache.org/
2. https://sqoop.apache.org/docs/1.4.6/index.html
3. https://sqoop.apache.org/docs/1.4.6/SqoopUserGuide.html#_introduction