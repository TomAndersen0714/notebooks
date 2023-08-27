# 广度优先 (Breadth-First-Search)


## 算法介绍


### 算法简介


### 应用模式

一般都是使用队列 Queue 来实现。

当需要记录层次时，一般还会使用辅助变量 level（记录当前层次）和 breadth（记录当前层次的宽度），每次循环时，会一次性遍历当前层次的 breadth 个元素，并更新设置 breadth 和 level 变量。

## 相关例题


