# ClickHouse Projections 投影

从本质上来看，Projection 实际上就是一种索引 Index，只是相比于普通的索引，要更加灵活。

> So basically projection is a kind of a row db index, but more flexible.

但值得注意的时，索引 Index 并不是万能的，很多时候还是需要通过新建表，来降低单表的复杂度，提升数据的易用性，其次才是考虑索引 Index 来提升查询性能。

## 参考链接
1. https://medium.com/datadenys/using-projections-to-speedup-queries-in-clickhouse-cd58e393b1cd
2. https://clickhouse.com/docs/en/sql-reference/statements/alter/projection
3. https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/mergetree#projections