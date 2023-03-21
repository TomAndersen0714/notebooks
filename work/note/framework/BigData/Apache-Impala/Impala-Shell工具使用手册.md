# Impala Shell工具使用手册

## Impala选项 
[参考链接](https://docs.cloudera.com/runtime/7.0.0/impala-manage/topics/impala-shell-options.html)



## 常用命令


### 数据导出
1. 导出结果为csv：`impala-shell -q "select id,price,name,dtime from config.price_test limit 3" -B --output_delimiter="," --print_header -o /var/testimpalaout.csv`


## 参考链接
1. https://docs.cloudera.com/runtime/7.0.0/impala-manage/topics/impala-impala-shell.html