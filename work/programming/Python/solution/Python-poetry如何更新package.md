# Python poetry 如何更新 package

一般情况下，直接使用 `poetry add <package_name>` 命令更新即可，如果不知道对应的版本号，可以使用 `pip index versions <package_name>` 命令进行查询。

但如果项目中，项目直接依赖的 package（即在 `pyproject.toml` 文件中声明的）和待安装的 package 的递归依赖存在版本冲突时，一般有以下 2 种方式来解决（poetry 只能自动更新间接依赖，不能更新直接依赖）：
- 方式 1，在更新时，连同冲突的 package 显式一起更新 `poetry add <package_name_1> <package_name_2>`
- 方式 2，先删除冲突的 package，然后直接安装待安装的 package，让 poetry 自己选择哪些递归依赖需要安装，以及对应的版本