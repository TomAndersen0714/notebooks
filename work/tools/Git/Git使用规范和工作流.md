# Git 使用规范


## Git Branch 规范

### 主分支


每个 git 项目固定含有上述所有分支类型。主分支 master 和 develop 是保护分支，只能进行 Merge 合并请求，均不可直接 Commit 提交代码。

#### Master

Master 为主分支，是部署在生产环境 PRO 的分支，需要确保分支的绝对稳定。

Master 分支一般是保护分支，仅支持 release 以及 hotfix 分支进行合并，任何情况下都不应该直接提交 commit 到 master 分支。

Master 分支保存官方发布版本历史，每次 merge 之后，都需要使用 tag 标识不同的发布版本（release version），用于正式发布或者回滚。

Master 分支在同一个项目中，唯一存在。

#### Develop

Develop 是开发环境分支，部署在开发 DEV 环境，一般包含有当前阶段需要准备上线的所有新特性（下个阶段上线的功能，以 feature 分支的形式保存，暂时不会合并到 develop），以及 bug 修复的代码，可以用于开发团队联调，可以用于创建 test 和 release 分支。

Develop 分支是基于 master 分支创建的，一般也是保护分支。

Develop 分支不允许直接提交 git commit 或者 git push，仅支持从 feature 分支和 hotfix 分支合并。

Develop 分支不能与 master 分支直接交互。

Develop 分支在同一个项目中，唯一存在。

### 辅助分支


辅助分支是用于组织解决特定问题的各种软件活动的分支。辅助分支主要用于组织软件新功能的并行开发、简化新功能开发代码的跟踪、辅助完成版本发布工作以及对生产代码的缺陷进行紧急修复工作、以及对版本代码的测试。这些分支与主分支不同，通常只会在有限的时间范围内存在。

辅助分支包括:
1. Feature：用于开发新功能时所使用的 feature 分支
2. Release：用于辅助版本发布的 release 分支
3. Hotfix：用于修正生产代码中的缺陷的 hotfix 分支

这三种分支都属于临时性需要，使用完以后，应该删除，使得代码库的常设分支始终只有 master 和 develop。

#### Feature

Feature 是开发新功能时使用的分支，通常部署在个人开发环境，在开发人员自测完成后，会合并到 develop 分支，并在合并时删除仓库对应 feature 分支。

分支命名时以 feature 开头，后面可以加上日期，以及开发的功能模块，命名示例：feature/2022-07-08-user_module、feature/2022-07-08-cart_module。

Feature 分支是基于 develop 分支创建的，支持开发者提交任意 git commit。

Feature 分支开发完成后需要合并到 develop 分支，等到某个阶段的所有 feature 都合并完成之后，并进行后续的联调，则创建 test 分支、测试、预发布、正式发布等流程。

每个 feature 分支颗粒要尽量小，以利于快速迭代和避免冲突。

当一个功能因为各种原因不开发了或者放弃了，这个分支直接废弃，不影响 develop 分支。

Feature 分支代码可以保存在开发者自己的代码库中而不强制提交到主代码库里。

Feature 分支只与 develop 分支交互，不能与 master 分支直接交互。

#### Release

Release 是预上线分支（预发布分支），部署在 UAT 环境，在测试阶段使用。

Release 分支是基于 devlop 分支创建的，一旦建立就将独立，不建议在此分支上直接提交 git commit 或者 git push。

Release 分支仅支持 hotfix 分支合并。

Release 分支主要用来为当前版本的测试、修复做准备。

#### Hotfix

线上出现紧急问题时，需要及时修复，以 master 分支为基线，创建 hotfix 分支。修复完成后，需要合并到 master 分支和 develop 分支。

分支命名以 `hotfix/` 开头的为修复分支，它的命名规则与 feature 分支类似。


### 其他分支


#### Test

Test 为测试环境分支，外部用户无法访问，专门给测试人员使用。

Test 分支部署在测试环境 FAT，用于进行内部功能测试。

Test 分支是基于 develop 分支创建的，为了和 develop 分支的代码保持完全一致，一般也不允许直接提交 git commit 或者 git push。


### 分支与部署环境对应关系

在系统开发过程中常用代码部署环境：

1. DEV 环境（Development environment）：用于开发者调试使用
2. FAT 环境（Feature Acceptance Test environment）：功能验收测试环境，用于测试环境下的软件测试者测试使用
3. UAT 环境 （User Acceptance Test environment）：产品、用户验收测试环境，用于生产环境下的软件测试者测试使用
4. PRO 环境（Production environment）：生产环境


分支与部署环境对应关系：

| 分支    | 功能                      | 环境 | 可访问 |
| ------- | ------------------------- | ---- | ------ |
| master  | 主分支，稳定版本          | PRO  | 是     |
| release | 预上线分支，发布新版本    | UAT  | 是     |
| test    | 测试分支，功能测试        | FAT  | 是     |
| feature | 开发分支，实现新特性      |      | 否     |
| hotfix  | 紧急修复分支，修复线上bug |      | 否     |
| develop | 开发分支，最新版本        | DEV  | 是     |


### 分支合并流程规范


不同开发团队可能有不同的规范，可自行灵活定义，例如：
- Develop 分支和 hotfix 分支，每次都必须基于 master 分支创建
- Feature 分支基于 develop 分支创建，开发完成后合并到 develop 分支
- Test 分支由 develop 分支合并，或基于 develop 分支创建
- 功能测试无误后，由 test 分支合并到 release 分支
- UAT 测试通过后，由 release 分支合并到 master 分支
- 对于工作量小的功能开发（工时小于 1 天），可以直接在 devolop 分支进行开发，否则由 develop 分支检出 feature 分支进行开发，开发完后合并到 develop 分支


个人分支用 git rebase，公共分支用 git merge：
对于公共分支，采用 git merge 操作；而在提交个人分支之前，需要使用 git rebase 合并 master/main 分支上的 commit，处理可能存在的冲突。


## Git Tag 规范



## Git Commit Message 规范



## 参考链接
1. [大厂的 Git 代码管理规范是怎样的？]( https://mp.weixin.qq.com/s/LWQolvFQQndBhFQ2lP2vhQ )
2. [Git 使用规范终极版](https://mp.weixin.qq.com/s/mAH8WWU510_7jC2pp4g0YA)