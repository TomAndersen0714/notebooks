# Python poetry如何安装 requirements.txt 依赖

```
# linux
cat requirements.txt | xargs poetry add

# windows
Get-Content requirements.txt | ForEach-Object { poetry add $_ }
```

## 参考链接

1. [python - How to import an existing requirements.txt into a Poetry project? - Stack Overflow](https://stackoverflow.com/questions/62764148/how-to-import-an-existing-requirements-txt-into-a-poetry-project)