# Pyinstaller 基础教程

Pyinstaller 不支持跨平台编译，即如果需要生成对应操作系统的可执行文件则需要在对应操作系统的环境中运行此命令工具，即 Windows 操作系统的可执行文件只能在 Windows 上编译生成。

## System Requirements

[Requirements — PyInstaller 6.10.0 documentation](https://pyinstaller.org/en/stable/requirements.html#pyinstaller-requirements)

## Install

```bash
pip install -U pyinstaller
```

## CLI

[Using PyInstaller — PyInstaller 6.10.0 documentation](https://pyinstaller.org/en/stable/usage.html)

Demo:
```bash
# windows中dll文件和exe文件分开打包
pyinstaller your_program.py

# windows中dll文件和exe文件打包到同一文件中, 这样可执行文件相对较大
pyinstaller --onefile your_program.py

# --name: 指定生成的可执行文件名称
pyinstaller --onefile --clean your_program.py --name your_program
```

常用选项：
1. `-F | --onefile`
	1. Create a one-file bundled executable. Windows 中 dll 文件和 exe 文件打包到同一文件中, 这样生成的可执行文件可执行较好，但文件体积相对较大。
	2. `pyinstaller --onefile your_program.py`
2. `--clean`
	1. Clean PyInstaller cache and remove temporary files before building.
	2. `pyinstaller --onefile --clean your_program.py`
3. `-n NAME, --name NAME`
	1. Name to assign to the bundled app and spec file (default: first script’s basename). 指定输出的可执行文件前缀名。
	2. `pyinstaller your_program.py --name your_program`
4. `-p DIR, --paths DIR`
	1. A path to search for imports (like using PYTHONPATH). Multiple paths are allowed, separated by `':'`, or use this option multiple times. Equivalent to supplying the `pathex` argument in the spec file. 用于指定各种依赖的额外检索路径。
	2. `pyinstaller your_program.py -p C:\Windows\System32\downlevel --name your_program`

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

报错信息
- `cannot get architecture from file: xxx.dll. Reason: 'The file is empty'`
解决方案
- 找到对应可用的（非空的） `dll` 文件路径，并通过 `-p` 参数来指定依赖导入的文件夹路径，如： `-p C:\Windows\System32\downlevel`
参考链接
- [【Python】解决使用pyinstaller打包Tkinker程序报错问题 - 郑立赛 - 博客园](https://www.cnblogs.com/zhenglisai/p/11418144.html)

## 参考链接

1. [PyInstaller Manual — PyInstaller 6.10.0 documentation](https://pyinstaller.org/en/stable/)
2. [Using PyInstaller — PyInstaller 6.10.0 documentation](https://pyinstaller.org/en/stable/usage.html)