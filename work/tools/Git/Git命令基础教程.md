# Git 命令基础教程


## 简介

Git是一种用来实现版本管理的工具，在实际使用过程中，不建议拘泥于命令行这一种使用方式，很多时候通过GUI等方式来使用Git工具，往往更便于记忆、理解和使用。

个人建议，对于Git工具优先记忆基础命令，用于完成Git项目的初步构建，而高级命令通过GUI的方式来使用，用于维护Git项目。

暂存区（stage/index）
工作区（working tree）
版本库（repository）：即仓库根路径下的 `.git` 文件夹。

## 基础命令

### git help

```
usage: git [--version] [--help] [-C <path>] [-c <name>=<value>]
           [--exec-path[=<path>]] [--html-path] [--man-path] [--info-path]
           [-p | --paginate | -P | --no-pager] [--no-replace-objects] [--bare]
           [--git-dir=<path>] [--work-tree=<path>] [--namespace=<name>]
           <command> [<args>]

These are common Git commands used in various situations:

start a working area (see also: git help tutorial)
   clone             Clone a repository into a new directory
   init              Create an empty Git repository or reinitialize an existing one

work on the current change (see also: git help everyday)
   add               Add file contents to the index
   mv                Move or rename a file, a directory, or a symlink
   restore           Restore working tree files
   rm                Remove files from the working tree and from the index
   sparse-checkout   Initialize and modify the sparse-checkout

examine the history and state (see also: git help revisions)
   bisect            Use binary search to find the commit that introduced a bug
   diff              Show changes between commits, commit and working tree, etc
   grep              Print lines matching a pattern
   log               Show commit logs
   show              Show various types of objects
   status            Show the working tree status

grow, mark and tweak your common history
   branch            List, create, or delete branches
   commit            Record changes to the repository
   merge             Join two or more development histories together
   rebase            Reapply commits on top of another base tip
   reset             Reset current HEAD to the specified state
   switch            Switch branches
   tag               Create, list, delete or verify a tag object signed with GPG

collaborate (see also: git help workflows)
   fetch             Download objects and refs from another repository
   pull              Fetch from and integrate with another repository or a local branch
   push              Update remote refs along with associated objects
```

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


`--amend` ：将当前 stage 内的内容，提交并合并到最近的一次 commit 中，此命令只能合并到最近一次的 commit，如果要合并到更远的 commit 中，则需要使用 git rebase 命令，先跳转到历史的 commit，然后再进行合并，并重新生成新的后续的历史 commit。


### git stash


### git push


### git branch


`-vv` ：显示分支详细信息
`-D <branch>` ：删除指定分支，但不能是删除当前分支。
`-u` ：设置分支对应的远程分支
```bash
git branch -u <remote-name>/<remote-branch>
git branch -u <remote-name>/<remote-branch> <local-branch>
```


### git merge

`--suqash` : This allows you to create a single commit on top of the current branch whose effect is the same as merging another branch (or more in case of an octopus). 使用此选项时，会将源分支的代码保先存在 stage 区域，而不是直接复制 commit log 到目标分支。因为，使用此命令后，你可以选择任意内容进行 commit，或者仅提交一次 commit，来减小 merge 时的日志。
### git tag

`git tag <tag_name>`

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

Rebase 的行为可以大致分为三类：
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
