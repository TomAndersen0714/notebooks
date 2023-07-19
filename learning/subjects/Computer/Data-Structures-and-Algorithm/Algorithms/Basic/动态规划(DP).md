# 动态规划 (Dynamic Programming)

https://zh.wikipedia.org/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92

动态规划是一种解决具有"重叠子问题" 和 "最优化子结构"性质问题的思路或方法, 而非具体的算法

动态规划的主要思想是将大问题拆解为子问题进行求解, 各个子问题之间可能存在重叠部分, 并通过缓存已解决的子问题答案, 

最优子结构 (optimal substructure): 问题能够分解为多个子问题, 并且基于子问题的解来求解原始问题
最优子结构问题 = 状态转移方程+边界条件

动态规划和分治的主要区别在于, 分治的主要思想是将大问题拆解为互不重叠的子问题进行求解
动态规划和递归的主要区别在于, 动态规划给使用额外空间换时间, 即缓存子问题的解, 当遇到相同子问题时能直接获取到对应的解


## 正则表达式

[LeetCode 10. Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/)

