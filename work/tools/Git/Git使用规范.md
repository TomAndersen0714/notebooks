# Git 使用规范


个人分支用 git rebase，公共分支用 git merge：
对于公共分支，采用 git merge 操作；而在提交个人分支之前，需要使用 git rebase 合并 master/main 分支上的 commit，处理可能存在的冲突。
