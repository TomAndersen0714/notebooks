# 图 Graph


## 图的数据结构


### 邻接矩阵（Adjacency Matrix）

二维数组
### 邻接表（Adjacency List）

顶点数组+边链表

### 十字链表（Orthogonal List）

顶点数组+出边链表+入边链表

### 邻接多重表



## 图的类别


### 无向图 Undirected Graph


### 有向图 Directed Graph


## 图相关算法


### 图的遍历

#### BFS 广度优先搜索算法


#### DFS 深度优先搜索算法


### 最小生成树


#### Union-Find 算法

https://en.wikipedia.org/wiki/Disjoint-set_data_structure

Union-Find 算法，是使用 Disjoint-set（并查集、不相交集合）数据结构实现的一种算法，也被称为并查集算法。此算法主要分为 Union（合并） 和 Find（查找） 两个操作，Union（合并）操作用于将两个集合合并成为一个集合，Find（查找）操作用于确定元素属于哪一个集合，或判断两个元素是否属于同一个集合。

Union-Find 算法常用于解决以下问题：
1. 连通性判断问题
2. 集合分组问题
3. 最小生成树问题（Prim 和 Kruskal 算法）

Union-Find 算法和 Disjoint-set 并查集数据结构的优势在于，只需要使用简单的数据结构，就可以解决问题，而不需要构建完整的数据结构（如：图 Graph）。

#### Prim 算法


#### Kruskal 算法


### 最短路径算法


#### Dijkstra 算法

求解单源最短路径：寻找从一个指定源节点到图中所有其他节点的最短路径，不支持包含负权边的图。

#### Bellman-Ford 算法

求解单源最短路径：寻找从一个指定源节点到图中所有其他节点的最短路径，支持包含负权边的图。

#### Floyd-Warshall 算法

求解多源最短路径：寻找图中所有节点对之间的最短路径，支持任何图中使用。

## 常见问题

### 图中是否有环

参考链接: https://blog.csdn.net/qq_38943651/article/details/108396570

方法一：DFS（深度优先遍历）
方法二：Topological Sort（拓扑排序）

## 相关例题

### 207 课程安排-是否成环

[LeetCode 207. Course Schedule](https://leetcode.com/problems/course-schedule/)

问题概述：
输入课程数量，以及课程之间的依赖关系，判断是否能够顺利完成课程，即：输入一个有向图 Graph 的顶点个数 vertex，和有向边数组 edge，判断 Graph 中是否有环路。

解题思路：
1. DFS Recursion（深度优先递归遍历）：
	1. 执行步骤：
		1. 根据输入参数，使用**邻接表（adjacency list）** 构建图（Graph），使用状态数组（state array）来保存遍历过程中，各个顶点的状态，即 0-未访问，1-访问中，2-未访问。
		2. 遍历所有顶点，并使用 DFS Recursion 深度优先递归算法，访问每个顶点
		3. DFS Recursion 深度优先递归算法中，传入参数为邻接表、状态数组、当前顶点序号，返回值为 bool 类型，执行逻辑如下：
			1. 如果当前顶点，被访问过，即 `state==2`，则返回 true，则代表当前节点已经成功访问过
			2. 如果当前节点，正在被访问，即 `state==1`，则返回 false，即访问过程中出现环路
			3. 否则，当前节点满足 `state==0`，于是将当前节点状态值设为 `state=1`，然后 DFS Recursion 访问当前节点所在边，对应的其他节点，如果某个节点 DFS 访问时返回 false，则返回当前 Recursion 递归也返回 false。访问结束时，将当前节点状态值设为 `state=2`。
	2. 时间复杂度：$O(m+n)$，空间复杂度：$O(m+n)$
2. Topological Sort（拓扑排序）
	1. 时间复杂度：$O(m+n)$，空间复杂度：$O(m+n)$

### 210 课程安排-拓扑排序，层次遍历

[LeetCode 210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/)

问题概述：
输入课程数量，以及课程之间的依赖关系，返回课程之间的拓扑排序，如果

解题思路：
1. Topological Sort（拓扑排序）
	1. 执行步骤：
		1. 根据输入参数，使用**邻接表（adjacency list）** 构建图（Graph），使用**入度数组（indegree array）** 保存各个顶点的入度，使用 list 来保存**访问路径（path）**。PS：与递归方法不同的是，拓扑排序法构建图时，邻接表存储的是**出边（Outgoing Edge）**，边的方向与递归方法中定义的是相反的。
		2. 遍历入度数组（indegree array），将入度为 0 的顶点加入到**拓扑排序队列 Queue** 中。当 Queue 不为空时，遍历 Queue 中的顶点，每次取出一个顶点，将此顶点加入到**访问路径 Path** 中，并访问**邻接表（adjacency list）** ，将其**出边（Outgoing Edge）** 所有对应顶点的**入度（indegree）** 减一，此时如果某个顶点的入度变为 0，则也将其加入到 Queue 中。
		3. 遍历结束时，如果存在顶点没有被访问，则表明图中有环，则返回空数组，反之，则返回访问路径 Path。
	2. 时间复杂度：$O(m+n)$，空间复杂度：$O(m+n)$
2. DFS Recursion（深度优先递归遍历）
	1. 时间复杂度：$O(m+n)$，空间复杂度：$O(m+n)$


### 399 除法求解

[LeetCode 399. Evaluate Division]( https://leetcode.com/problems/evaluate-division/ )

问题概述：给一组边和这组边的权重，要求计算指定两点之间各边权重的类乘结果。

解题思路：参考 https://leetcode.cn/problems/evaluate-division/solutions/548585/chu-fa-qiu-zhi-by-leetcode-solution-8nxb/
1. 广度优先遍历 BFS、深度优先遍历 DFS
	1. 主要步骤
	2. 时间复杂度，空间复杂度
2. Floyd-Warshall 算法
	1. 主要步骤
	2. 时间复杂度，空间复杂度
3. 带权 Union-Find 算法
	1. 主要步骤
	2. 时间复杂度，空间复杂度