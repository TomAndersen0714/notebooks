# 图 Graph


## 图的数据结构


### 邻接矩阵（Adjacency Matrix）

二维数组
### 邻接表（Adjacency List）

顶点数组+边链表（出边或者入边）

### 十字链表（Orthogonal List）

顶点数组+出边链表+入边链表

### 邻接多重表（Adjacency Multilist）



## 图的类别


### 无向图 Undirected Graph


### 有向图 Directed Graph


### 带权图 Weighted Graph


### 不带权图 Unweighted Graph



## 图相关算法


### 图的遍历


图的遍历算法中，最常见的就是广度优先搜索算法（BFS） 和深度优先搜索算法（DFS），通常情况下，这两种算法分别被嵌套在其他算法中使用，且通常可以通过调整代码，将 BFS 变换为 DFS，反之亦然。
#### BFS 广度优先搜索算法


#### DFS 深度优先搜索算法


### 最小生成树


#### Union-Find 算法

[并查集Union-Find](learning/subjects/ComputerScience/DataStructuresAndAlgorithm/Algorithms/Intermediate/并查集Union-Find.md)

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

## 经典例题

### 刷题清单

- [ ] LeetCode399、LeetCode990、LeetCode128 [并查集Union-Find](learning/subjects/ComputerScience/DataStructuresAndAlgorithm/Algorithms/Intermediate/并查集Union-Find.md)
- [ ] LeetCode200、LeetCode547、LeetCode684 [并查集Union-Find](learning/subjects/ComputerScience/DataStructuresAndAlgorithm/Algorithms/Intermediate/并查集Union-Find.md)
- [ ] LeetCode310、LeetCode785、LeetCode1584。 https://blog.csdn.net/a435262767/article/details/105204081

### 207 课程安排-图是否成环

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

### 210 课程安排-图的拓扑排序

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


### 399 除法求解-带权图中两点距离

[LeetCode 399. Evaluate Division]( https://leetcode.com/problems/evaluate-division/ )

问题概述：给定图中的一些点（变量），以及某些边的权值（两个变量的比值），试对任意两点（两个变量）求出其路径长（两个变量的比值）。

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