# 排序 (Sorting)

## 基础排序算法

[基础排序算法汇总](learning/subjects/Computer/Data-Structures-and-Algorithm/Algorithms/基础排序算法汇总.md)



## 相关例题

### Top K 问题

[LeetCode 347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)
[LeetCode 692. Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/)

### K 数求和问题

#### 2 Sum

[双指针Two-Pointers](learning/subjects/Computer/Data-Structures-and-Algorithm/Algorithms/Elementary/双指针Two-Pointers.md) -2 Sum 

#### 3 Sum

[双指针Two-Pointers](learning/subjects/Computer/Data-Structures-and-Algorithm/Algorithms/Elementary/双指针Two-Pointers.md) -3 Sum 

#### 4 Sum

[双指针Two-Pointers](learning/subjects/Computer/Data-Structures-and-Algorithm/Algorithms/Elementary/双指针Two-Pointers.md) -4 Sum 


### 多数元素

[LeetCode 169. Majority Element](https://leetcode.com/problems/majority-element/)

问题概述：输入一个整型数组，返回出现次数超过 $⌊n / 2⌋$ 的元素，此元素被称为 Majority Element

解题思路：
1. Sorting：
	1. 方法：先排序，然后直接返回元素 `nums[leng/2]` 即可，因为此题中的 Majority Element 必定超过 n/2 ，因此中心位置上必定是此元素。
	2. 时间复杂度： $O(nlog_{2}{n})$，空间复杂度：$O(1)$。 
2. HashMap：
	1. 方法：遍历数组，每次遍历时统计数字出现次数，超出 n/2 即返回。
	2. 时间复杂度： $O(n)$，空间复杂度：$O(n)$。 
3. Boyer–Moore majority vote algorithm：摩尔投票算法。
	1. 方法：使用 candidate 和 vote 分别用于记录一个数组元素和充当计数器，vote 初始值为 0。遍历整个数组，当 vote 为 0 时，则将当前元素赋值给 candidate，否则，将当前元素与 candidate 进行比较，如果相同，则 vote+=1，如果不相同，则 vote-=1。迭代结束时，即可找到多数元素（n/2）。
	2. 证明：当一个数组中某个主要元素个数超过 n/2 时，只要通过两两抵消的方式来清除元素，就能使得最终的主要元素得以保留。

[LeetCode 229. Majority Element II](https://leetcode.com/problems/majority-element-ii/)

题目概述：给定一个长度为 n 的整型数组，返回出现次数大于 n/3 的元素

解题思路：
1. HashMap：
	1. 方法：统计频次，不再赘述
	2. 时间复杂度： $O(n)$，空间复杂度：$O(n)$。 
2. Sorting：
	1. 方法：排序后遍历时统计次数，不再赘述
	2. $O(nlog_{2}{n})$，空间复杂度：$O(1)$。 

### 奇偶性排序


[LeetCode 905. Sort Array By Parity]( https://leetcode.com/problems/sort-array-by-parity/ )

问题概述：输入一个整型数组，将所有偶数放在左侧，奇数放在右侧，并返回排序后的数组。

解题思路：
1. 双指针（Two Pointers）：左右指针


[LeetCode 922. Sort Array By Parity II](https://leetcode.com/problems/sort-array-by-parity-ii/)

问题概述：输入一个整型数组，将所有偶数放在偶数位置上，将奇数放在奇数位置上，并返回排序后的数组。

解题思路：
1. 双指针（Two Pointers）：快慢指针

