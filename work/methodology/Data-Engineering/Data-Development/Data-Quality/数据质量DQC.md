# 数据质量 DQC

如果将数据的自动加工流程，看做是工厂中一个个产品的流水线，那么每条流水线上，都应该做好产品（数据）的数据质量校验，保证流水线上生产出来的产品（数据）是符合数据预期的。

数据质量，出了数据本身外，还包含数据的实时性（产出时间）是否满足下游的要求。

## 数据质量校验的类别

### 表内数据质量

字段的数据类型

字段的数据长度

字段的数据内容

主键唯一性

数据量阈值

数据量波动

### 表间数据质量

外键

## 如何进行数据质量校验

可以直接通过 SQL 查询数据质量，进而判断查询结果，是否符合预期, 来进行数据质量校验。

Google Aviator 表达式。

[GitHub - killme2008/aviatorscript: A high performance scripting language hosted on the JVM.](https://github.com/killme2008/aviatorscript)

## 数据质量的量化

### 数据基线和服务等级协议 SLA

[数据基线和服务等级协议SLA](work/methodology/Data-Engineering/Data-Development/Data-Quality/数据基线和服务等级协议SLA.md)