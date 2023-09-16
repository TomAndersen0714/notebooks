# 并查集 Union-Find

## 算法简介

https://en.wikipedia.org/wiki/Disjoint-set_data_structure

Union-Find 算法主要用于解决“动态连通性问题”：
1. 连通性判断问题
2. 集合聚类问题
3. 最小生成树问题（Prim 和 Kruskal 算法）

Union-Find 算法和 Disjoint-set 并查集数据结构的优势在于，只需要使用简单的数据结构，就可以解决问题，而不需要构建完整的数据结构（如：图 Graph）。


Union-Find 算法，是使用 Disjoint-set（并查集、不相交集合）数据结构实现的一种算法，也被称为并查集算法。此算法主要分为 Union（合并） 和 Find（查找） 两个操作。

Union-Find 算法中并查集 Disjoint-set 的底层数据结构常使用数组 Array，或者哈希表 Map 来实现，用于存储，集合中所有结点，及其指向的父结点。数据结构初始化时，所有结点都默认指向自己，表示自己单独属于集合，后续在迭代中，通过 Find 和 Union，不断动态合并集合。

Find 查找操作：查找一个结点所属集合的根结点，用于确定元素属于哪一个集合，或判断两个元素是否属于同一个集合。

Union 合并操作：合并两个集合为一个集合，就是把一个集合的根结点，指向另一个集合的根结点，而在并查集 Disjoint-set 中认为，只要根结点相同，则表示两个集合属于同一个集合。

Union-Find 算法通过路径压缩（Path Compression）、按秩合并（Union by Rank）等优化策略，可以使得 Find 和 Union 操作的平均时间复杂度达到 $O(1)$。

### 应用场景

动态连通性问题：用于求解，那些只关注与判断两个元素之间的连通性，而不关心元素间的距离的问题。

如：
1. 判断图中两个顶点是否连通。
2. 求解图的连通分量。

### 优化策略

#### 路径压缩（Path Compression）

路径压缩（Path Compression）是指在查询（Find）过程中，更改结点的指向，降低整个集合树的高度，提升查询性能。

通常有“隔代压缩”、“完全压缩”这两种方案。其中隔代压缩，一般是在 Find 方法中，每次查询时隔代向上合并路径，如 `parent[x]=parent[parent[x]]`，来优化当前树的高度。

#### 按秩合并（Union by Rank）

按秩合并（Union by Rank）是指在合并（Union）过程中，让“高度”更低的集合树的根结点指向“高度”更高的集合树的根结点，以避免合并后的集合树高度增加。

#### 路径分裂（Path Splitting）



### 解题模式



## 经典例题

LeetCode 547.
LeetCode 684.
LeetCode 200.
LeetCode 128. Longest Consecutive Sequence


### 方程组求解

LeetCode 399. Evaluate Division：带权并查集
LeetCode 990. Satisfiability of Equality Equations


## 参考链接
1. https://leetcode.cn/problems/satisfiability-of-equality-equations/solutions/279091/deng-shi-fang-cheng-de-ke-man-zu-xing-by-leetcode-/
