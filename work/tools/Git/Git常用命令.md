# Git常用命令

## 前言

Git是一种用来实现版本管理的工具，在实际使用过程中，不建议拘泥于命令行这一种使用方式，很多时候通过GUI等方式来使用Git工具，往往更便于记忆、理解和使用。

个人建议，对于Git工具优先记忆基础命令，用于完成Git项目的初步构建，而高级命令通过GUI的方式来使用，用于维护Git项目。


暂存区（stage/index）
工作区（working tree）
版本库（repository）：即仓库根路径下的 `.git` 文件夹。

## 基础命令


### git init


### git clone



`-b <name>, --branch <name>`
```
git clone -b v1.55.1 https://github.com/grpc/grpc-java
```

`--depth <depth>`: Create a shallow clone with a history truncated to the specified number of commits.
```
git clone --depth 1 https://github.com/grpc/grpc-java
```




### git pull


### git add


### git commit


### git push


### git branch


### git tag



## 高级命令

建议使用JetBrains、Git GUI等可视化工具来简化Git高级命令的使用。

### git config

`--global`

`--local
`
`--unset, --unset-all`

`--add`

#### 常用 config

`user.name`, `user.email`
```bash
git config --global user.name "TomAndersen"
git config --global user.email "1040994898@qq.com"
```


`core.autocrlf`
```bash
git config --global core.autocrlf input
```


`http.proxy, https.proxy`
```bash
git config --global http.proxy <protocol://><host:port>
git config --global https.proxy <protocol://><host:port>


git config --global http.proxy http://127.0.0.1:58591
git config --global https.proxy https://127.0.0.1:58591

# PS: WSL中需要使用宿主机IP, 即DNS(cat /etc/resolv.conf), 替换127.0.0.1本机地址
git config --global http.proxy http://172.30.32.1:58591
git config --global https.proxy https://172.30.32.1:58591

git config --global --unset http.proxy
git config --global --unset https.proxy
```

`safe.directory`
```bash
git config --global --add safe.directory <directory>
git config --global safe.directory '*'

git config --global --unset safe.directory <directory>
git config --global --unset-all safe.directory

```


### git rebase

https://waynerv.com/posts/git-rebase-intro/

```
# Commands:
#  p, pick = use commit
#  r, reword = use commit, but edit the commit message
#  e, edit = use commit, but stop for amending
#  s, squash = use commit, but meld into previous commit
#  f, fixup = like "squash", but discard this commit's log message
#  x, exec = run command (the rest of the line) using shell
#
# These lines can be re-ordered; they are executed from top to bottom.
#
# If you remove a line here THAT COMMIT WILL BE LOST.
#
# However, if you remove everything, the rebase will be aborted.
#
# Note that empty commits are commented out
```

Rebase的行为可以大致分为三类：
1. 第一类：保留 commit，不合并
	1. Pick: 标记为 pick 的 commit 会在 rebase 操作后会直接保留下来，不做任何改动，也不会合并，最上面的 commit 最好标记为这一类；
	2. Reword： 这一类 commit 也会保存下来，不过在保存下来之前会有一次修改 commit message 的机会；
	3. Edit：这一类的 commit 也会直接保存下来，不过，当合并到这种类型的 commit 时，整个合并经常会暂停下来，你可以重新修改这次 commit 中的变动内容，比如给这个 commit 继续新增一些代码改动、或者修改 commit message，然后 git add (不要忘记 git add 了)，再继续使用 git rebase —continue，来继续 rebase 操作。
2. 第二类：不保留 commit，与上一次 commit 合并
	1. Squash：标记为 squash 的 commit 在 rebase 操作完成后不会保留，它会与之相邻的上一次 commit 进行合并。同时它的 commit message 也会与上一次 commit 的 message 合并；
	2. Fixup: 这类 commit 不会保留，会直接与相邻的上一次 commit 合并，与 squash 不同之处在于，它的 commit message 回直接丢弃，即这次 commit 会被视为对前一次 commit 的一次小的补充修改（fixup），commit message 就以前一次为准；
3. 第三类：不保留，直接删除 commit
	1. Drop：标记为 skip 的 commit 会直接被删除，就相当于这次 commit 从来没有发生过。同时，这个 commit 中涉及的所有代码修改全部会被删除。


### git rm

```shell
# 删除暂存区(stage/index)中的文件, 保留工作区(working tree)文件
git rm <file>
git rm -r <dir>

# --cached 标志的作用是删除暂存区(stage/index)中的文件, 保留工作区(working tree)文件
git rm --cached <file>
git rm -r --cached <dir>
```


### git diff

查看上次操作中发生过变更的文件名
```bash
git diff --name-only HEAD@{1}..HEAD
```


### git submodule

PS: Git submodule管理起来十分复杂，不建议使用
```bash
git submodule add <remote URL> <directory>
```

## 参考链接
1. [Git Reference](https://git-scm.com/docs)
