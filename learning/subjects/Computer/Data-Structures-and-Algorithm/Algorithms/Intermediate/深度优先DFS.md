# 深度优先 (Depth-First-Search)

## 算法介绍


### 算法简介


### 解题模式

一般都是使用栈 Stack 控制访问顺序，或者直接使用递归（Recursion）方法实现，让编程语言自主维护 Stack。


## 相关例题


### 树的遍历

#### 二叉树中序遍历
[LeetCode 94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)

问题概述：经典的二叉树中序遍历问题，输入


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

LeetCode 200. Number of Islands