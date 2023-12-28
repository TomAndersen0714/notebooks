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

- 限制 `depth` 后，会导致项目只会获取特定数量 commit 相关的分支，而不是全部分支，这样可以大大加快克隆的速度，但同时也无法获取其他 commit 相关的分支信息。
- 如果要解除限制，可以使用 `git remote set-branches origin '*'` 命令，修改 `.git/Config` 中的配置。
- 适合用 `git clone --depth=1` 的场景，是想 clone 最新版本来使用或学习，而不是参与整个项目的开发工作。

`--depth <depth>`: Create a shallow clone with a history truncated to the specified number of commits.
```
git clone --depth 1 https://github.com/grpc/grpc-java
```



### git fetch

`--all` ：获取所有 remote 的跟新到本地
### git pull


### git add


### git commit


### git stash


### git push


### git branch


`-vv` ：显示分支详细信息
`-D <branch>` ：删除指定分支，但不能是删除当前分支。

### git tag

`-l | --list` : List tags. With optional `<pattern>...`, e.g.  git tag --list `'v-*'`, list only the tags that match the Pattern (s).


### git remote


`git remote set-url` ：给现有 remote 添加 push URL，即支持 `git push` 时同时推送多个仓库：
```bash
git remote set-url --add origin git@gitee.com:TomAndersen/notebooks.git
```

`git remote prune` ：清理 remote 中的已删除分支
```bash
git remote prune origin
```

### git show


## 高级命令

建议使用JetBrains、Git GUI等可视化工具来简化Git高级命令的使用。

### git config

`--global`

`--local
`
`--unset, --unset-all`

`--add`

[Git配置基础教程](work/tools/Git/Git配置基础教程.md)

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
