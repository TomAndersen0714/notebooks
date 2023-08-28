# 深度优先 (Depth-First-Search)

## 算法介绍


### 算法简介


### 解题模式

一般都是使用栈 Stack 控制访问顺序，或者直接使用递归（Recursion）方法实现，让编程语言自主维护 Stack。

通常情况下，如果 DFS 和 BFS 都可以用于解决问题，则一般使用递归（Recursion）方法来实现 DFS 是最快捷的。

## 相关例题


### 树的遍历

#### 二叉树中序遍历
[LeetCode 94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)

问题概述：经典的二叉树中序遍历问题，输入一个二叉树对象，返回中序遍历的 List。


解题思路：
1. 递归（Recursion）
	1. 方法：
2. 用栈遍历（Iteration using stack）：
	1. 方法：
		1. 每次遍历时，主要包含两方面工作，访问节点、处理节点，用栈来保存访问过的节点。
		2. 每次遍历时
			1. 首先不断访问左节点，并将访问后的节点加入栈中，直到当前访问节点为 null；
			2. 然后开始处理已经访问过的节点，即排出 Stack 栈顶元素作为当前节点，并处理该节点，将节点的 value 放置到 path 中；
			3. 最后访问当前节点的右节点，并将右节点添加到 Stack 中。


### 岛屿遍历

#### 岛屿的数量

[LeetCode 200. Number of Islands](https://leetcode.com/problems/number-of-islands/description/)

问题概述：
输入一个二维字符型数组，其中字符为 "1" 或 "0"，相邻的 "1" 代表岛屿，要求计算并范围输入的二维字符串数组中，岛屿的数量。

解题思路：
1. 递归（Recursion）算法：
	1. 使用辅助数组 isVisited ，来保存一个点是否被访问过，以及计数器 counter 来统计最终结果。
	2. 遍历所有点，每次遇到新的未访问且对应位置满足条件（本题中是其对应字符为 "1"），则从该点开始执行 DFS 递归方法，直到遍历完所有点。
	3. 递归方法中，每次都会访问该点，以及递归访问该点在四个方向的下一个点。递归退出条件为，当前不符合访问条件，或者已访问。