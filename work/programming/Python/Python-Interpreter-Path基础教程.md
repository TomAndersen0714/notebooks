# Python Interpreter Path 基础教程

## sys.path

Python 命令行运行脚本时，默认会将脚本其所在的路径添加到 `sys.path` 中（而不是将当前工作路径 work directory 添加到 `sys.path`）。


## PYTHONPATH


`PYTHONPATH` 是一个逗号分隔的环境变量，Python 程序运行开始时，会自动读取环境变量 `PYTHONPATH` 中的值，并将其加载到 `sys.path` 中。

如果 `PYTHONPATH` 中包含相对路径，则会先将其转换为绝对路径，之后再加载到 `sys.path` 中。URL 转换时，是以当前工作路径（即提交 Python 命令时的所在路径）为基准进行相对路径转换。

```
export PYTHONPATH='.'
python3 -m site
```
