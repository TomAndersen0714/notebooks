# 图 Graph


## 图的数据结构


### 邻接矩阵（Adjacency Matrix）

二维数组
### 邻接表（Adjacency List）

顶点数组+边链表

### 十字链表（Orthogonal List）

顶点数组+出边链表+入边链表


## 图的类别


### 无向图 Undirected Graph


### 有向图 Directed Graph


## 常见问题

### 图中是否有环图

参考链接: https://blog.csdn.net/qq_38943651/article/details/108396570

方法一：DFS（深度优先遍历）
方法二：Topological Sort（拓扑排序）

## 相关例题

### 课程安排

[LeetCode 207. Course Schedule](https://leetcode.com/problems/course-schedule/)

问题概述：
输入课程数量，以及课程之间的依赖关系，判断是否能够顺利完成课程，即：输入一个有向图 Graph 的顶点个数 vertex，和有向边数组 edge，判断 Graph 中是否有环路。

解题思路：
1. DFS Recursion（深度优先递归遍历）：
	1. 
	2. 时间复杂度：$O(m+n)$，空间复杂度：$O(m+n)$
2. Topological Sort（拓扑排序）
	1. 时间复杂度：$O(m+n)$，空间复杂度：$O(m+n)$