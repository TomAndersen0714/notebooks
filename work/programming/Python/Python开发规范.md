# Python 开发规范

## 环境

【强制】每次引入和新建 Python 项目时，都必须创建对应的虚拟环境，进行环境隔离。[Python虚拟环境基础教程](work/programming/Python/Python虚拟环境基础教程.md)

## 编码

【推荐】不建议使用 relative imports，建议统一使用 absolute imports，避免两者混合使用，不利于阅读和理解。

## 最佳实践

初始化开发环境

```
# 创建Python虚拟环境
python -m venv myenv

# 加载虚拟环境
source myenv/bin/activate

# 配置PIP源
pip config set --local global.index-url https://pypi.tuna.tsinghua.edu.cn/simple
pip config set --local global.trusted-host pypi.tuna.tsinghua.edu.cn

# 安装项目依赖
pip install -r requirements.txt
```

## 参考链接

1. [Python 风格指南 - 内容目录 — Google 开源项目风格指南](https://zh-google-styleguide.readthedocs.io/en/latest/google-python-styleguide/contents.html)