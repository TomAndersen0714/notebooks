# 贪心 Greedy

## 算法简介

贪心 Greedy 算法的应用场景在于针对那些求全局最优解，同时支持拆分为多个子问题的问题。比如：买卖股票最大收益、跳跃游戏等，原问题或者经过转换后的原问题，都是求最优解。

贪心算法的主要思路是：
1. 分析原问题，将其转换为求最优解问题
2. 将原问题拆分为多个最优子问题
3. 迭代每个子问题，并递推求解，直到求出原始问题解

起始贪心算法求解的问题，即便具备相应的特征也不一定可以找到对应的求解路径，很多时候都是试出来的，并没有统一的套路。

当然，如果贪心算法无法找到对应的解决思路，可以尝试使用动态规划求解，贪心算法可以看做是动态规划算法的一种特殊剪枝情况，能够用于加速动态规划求解过程。

## 经典例题

### 子序列问题

#### 300，最长子序列长度

### 股票买卖问题

#### 122，买卖股票最大收益 2

### 跳跃游戏

#### 55，跳跃游戏 1

问题描述：
1. 输入一个数组，代表每次从该位置可以跳跃的距离，从 0 开始跳跃，返回是否能够跳跃到结尾 length-1
2. [Jump Game - LeetCode](https://leetcode.com/problems/jump-game/description/?envType=problem-list-v2&envId=greedy)
解题思路：
1. 动态规划 Dynamic Programming
	1. 假设 `dp[i]` 代表从 i 开始跳跃，是否能到结尾，即从 i=length-1 开始遍历到 0，最终 `dp[0]` 则为最终结果
2. 贪心 Greedy + 指针 Pointer
	1. 从头开始遍历每个位置，使用指针 nextStepMaxAccess 指向下一步所能访问的最远距离，如果当前位置超出了 nextStepMaxAccess，则代表无法到达终点返回 false，否则更新 nextStepMaxAccess，当 nextStepMaxAccess 超过终点时，则直接返回 true。

#### 45，跳跃游戏 2

问题描述：
1. 输入一个数组，代表每次从该位置可以跳跃的距离，从 0 开始跳跃，返回到达终点所需的最小次数
特殊条件：
1. 题目的测试用例会确保可以到达终点
解题思路：
1. 动态规划 Dynamic Programming
	1. 假设 `dp[i]` 代表从 i 开始跳跃到结尾的最小步数，则状态转移表达式为 `dp[i] = min { dp[i+j] + 1 for 0<j<nums[i] }  if i + nums[i] < nums.length - 1, else dp[i] = 1;`
	2. 初始状态下，`dp[length-1]` 等于 0，从 i=length-2 开始逆向遍历，直到求得 `dp[0]` 即为原题结果
2. 贪心 Greedy + 双指针 Two Pointers
	1. 使用指针 curStepMaxAccess 指向当前这一步，所能访问的最远距离，使用指针 nextStepMaxAccess 指向下一步所能访问的最远距离，step 记录最小步数
	2. 初始化所有变量 `curStepMaxAccess = nums[0], nextStepMaxAccess = 0, minimumSteps = 1`
	3. 从 i=1 开始遍历 nums，每次尝试更新 nextStepMaxAccess，即 `nextStepMaxAccess = Math.max(nextStepMaxAccess, i + nums[i])`，如果 i 到达了 curStepMaxAccess，则代表当前步数最大距离已经走完，则 step 加 1，将 `curStepMaxAccess = nextStepMaxAccess`，继续循环，即每次都尝试在最远距离的范围内，找下一个最远距离，直到找到终点。
	4. 结束时，step 即为原题结果。

## 参考链接

1. [代码随想录](https://programmercarl.com/%E8%B4%AA%E5%BF%83%E7%AE%97%E6%B3%95%E6%80%BB%E7%BB%93%E7%AF%87.html)
2. [代码随想录](https://programmercarl.com/%E8%B4%AA%E5%BF%83%E7%AE%97%E6%B3%95%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html#%E8%B4%AA%E5%BF%83%E7%9A%84%E5%A5%97%E8%B7%AF-%E4%BB%80%E4%B9%88%E6%97%B6%E5%80%99%E7%94%A8%E8%B4%AA%E5%BF%83)