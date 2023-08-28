# 广度优先 (Breadth-First-Search)

## 算法介绍


### 算法简介


### 解题模式

一般都是使用队列 Queue 来实现。

当需要记录层次时，一般还会使用辅助变量 level（记录当前层次）和 breadth（记录当前层次的宽度），每次循环时，会一次性遍历当前层次的 breadth 个元素，并更新设置 breadth 和 level 变量。

## 相关例题


### 岛屿遍历

#### 岛屿的数量

[LeetCode 200. Number of Islands](https://leetcode.com/problems/number-of-islands/description/)

问题概述：
输入一个二维字符型数组，其中字符为 "1" 或 "0"，相邻的 "1" 代表岛屿，要求计算并范围输入的二维字符串数组中，岛屿的数量。

解题思路：
1. 基于 Queue 实现的 BFS (Breadth First Search) 算法：
	1. 使用辅助变量，Queue 来保存 BFS 搜索时的待访问节点，以及计数器 counter 来统计最终结果。
	2. 遍历所有点，每次遇到新的未访问且对应位置满足条件（本题中是其对应字符为 "1"），则从该点开始执行 BFS 方法，直到遍历完所有点。
	3. BFS 方法中，如果 Queue 不为空，则一直循环访问 Queue 中保存的节点，如果节点未访问且对应位置满足条件（本题中是其对应字符为 "1"），则将该节点四个方向上的下一个节点位置也加入到 Queue 中。直到所有相邻节点都已经访问，即 Queue 为空。

### 二叉树遍历

[LeetCode 102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/description/)

问题概述：
输入一个二叉树的根节点，要求自顶向下层次遍历二叉树，并范围层次遍历节点的值组成的 List。

解题思路：
1. 基于 Queue 实现的 BFS (Breadth First Search) 算法