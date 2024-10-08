# Pyinstaller 基础教程

Pyinstaller 不支持跨平台编译，即如果需要生成对应操作系统的可执行文件则需要在对应操作系统的环境中运行此命令工具，即 Windows 操作系统的可执行文件只能在 Windows 上编译生成。

## System Requirements

[Requirements — PyInstaller 6.10.0 documentation](https://pyinstaller.org/en/stable/requirements.html#pyinstaller-requirements)

## Install

```bash
pip install -U pyinstaller
```

## Demo

```bash
# windows中dll文件和exe文件分开打包
pyinstaller your_program.py

# windows中dll文件和exe文件打包到同一文件中, 这样可执行文件相对较大
pyinstaller --onefile your_program.py
```

## 常见问题

报错信息：
- `The 'typing' package is an obsolete backport of a standard library package and is incompatible with PyInstaller. Please remove this package`
原因排查：
- Python 包 `typing` 已经是 python 标准包，即 python 自带的核心包，而 typing 的旧版本已和 pyinstaller 不兼容，需要删除
解决方案：
- `poetry remove typing`
- `pip uninstall typing`
参考链接：
- [python - The 'typing' package is an obsolete backport of a standard library package and is incompatible with PyInstaller - Stack Overflow](https://stackoverflow.com/questions/70710731/the-typing-package-is-an-obsolete-backport-of-a-standard-library-package-and-i)
## 参考链接

1. [PyInstaller Manual — PyInstaller 6.10.0 documentation](https://pyinstaller.org/en/stable/)