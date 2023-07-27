# 字符串 (String)


## 反转字符串

[LeetCode 344. Reverse String](https://leetcode.com/problems/reverse-string/)

问题概述：将字符串反转，要求是 in-place 算法，只能使用常量的额外空间。

解题思路：
1. Two pointers


## Zigzag 字符串转换

[LeetCode 6. Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion/)

问题概述：将输入的字符串，按照指定行数，转换成 Z 字型，并返回字符按行拼接的结果。

解题思路：
1. Line by line，step by step。
	1. 时间复杂度： $O(n)$，空间复杂度：$O(n)$。 


## 回文字符串

回文字符串（Palindromic Substring）

### 最长回文字符子串

[LeetCode 5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

问题概述：输入一个字符串，返回对应字符串的最长回文子串

条件：
1. 答案不唯一

解题思路：
1. 中心扩展（Expanding from centers）：
	1. 方法：由于回文字符串是左右对称的，因此其对称中心，必定是 1 个或 2 个元素。故而可以通过遍历原始字符串，每次分别取当前位置之后的 1 个或 2 个元素，作为回文字符串的中心进行左右扩展，每次遍历保留最大的回文字符串的长度，以及起止索引。直到遍历结束，便可以遍历完所有可能的回文字符串组合，求得最长回文子字符串。
	2. 时间复杂度： $O(n^2)$，空间复杂度：$O(1)$。 
2. 动态规划（DP）：
	1. 方法：先将求最大的回文字符串问题，转换为遍历所有子串、判断子串是否是回文字符串的、保存最长子串的问题。然后将判断是否是回文字符串的问题的解定义为 $dp[i][j]$，其中 $i\leq j$ ，使用 $dp$ 数组来保存子问题的解，以及使用 $res$ 来保存目前所有子问题的最优解，即最长回文子串信息。进而可以构建**状态转移方程（state transfer equation）** $dp[i][j] = \begin{cases} true & \text{if}\ j-i<=1 \\ dp[i-1][j-1]& \text{if}\ chars[i]=chars[j] \end{cases}$ ，将原始问题拆解为多个子问题。从最简单的子问题的子问题开始，逐步求解，直到遍历完所有的子串。同时使用最大值，来保存遍历过程中的每一次计算结果，遍历结束即获得最终结果。
	2. 时间复杂度： $O(n^2)$，空间复杂度：$O(n^2)$。 


### 回文字符子串数量

[LeetCode 647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)

问题概述：输入一个字符串，返回其中回文字符串子串的数量

解题思路：
1. 动态规划（DP）（和“**最长回文字符子串 LeetCode5**”的思路完全相同）：
	1. 按照子串长度从 1 到 n 遍历所有的子串，将原始问题拆解遍历所有子串，以及判断子串是否是回文字符串问题。对于后者，通过**状态转移方程（state transfer equation）** $dp[i][j] = \begin{cases} true & \text{if}\ j-i<=1 \\ dp[i-1][j-1]& \text{if}\ chars[i]=chars[j] \end{cases}$ ，以及构建**状态缓存 (state cache)** 的方式来进行求解。同时使用计数器，来汇总遍历过程中的每一次计算结果，遍历结束即获得最终结果。
	2. 时间复杂度： $O(n^2)$，空间复杂度：$O(n^2)$。 
2. 中心扩展（Expanding from centers）
3. 中心扩展（Expanding from centers）升级版


### 最小回文字符串转换

[LeetCode 214. Shortest Palindrome](https://leetcode.com/problems/shortest-palindrome/)

问题概述：输入一个字符串，在其头部添加尽量少的字符，使其整体变成回文字符串，最后返回对应的回文字符串

解题思路：
1. 找到头部最长回文字符串：
	1. 先找到原始字符串头部中，最长的回文字符串，然后将原始字符串中剩余的内容反转后添加到头部，即可使得头部添加的字符最少。
	2. 时间复杂度： $O(n^2)$，空间复杂度：$O(n)$。 

## 反转整数（属于Math 题）

[数学Math](learning/subjects/Computer/Data-Structures-and-Algorithm/Algorithms/Elementary/数学Math.md)

[LeetCode 7. Reverse Integer](https://leetcode.com/problems/reverse-integer/)


问题概述：将输入的 unsigned 32-bit 的数字进行反转，保留其符号位；如果反转结果溢出，则返回 0，反之则返回反转后的 unsigned 32-bit 数值。

条件：
1. 不能使用可以存储 32 bit 以上整型的数据结构

解题思路：
1. Math：
	1. 方法：每次将 x 作为被除数，除 10 取余获得最低位数字 `remainder`，并执行 `x/=x`；然后先判断 y 是否小于等于 `MAX_VALUE/10`，满足则代表不会溢出，然后执行 `y=y*10`；然后再判断 y 是否小于等于 `MAX_VALUE-remainder`，满足则代表不会溢出，然后执行 `y+=remainder`，直到 `x=0` 时，退出循环。
	2. 时间复杂度： $O(n)$，空间复杂度：$O(1)$。 


