# 排序 (Sorting)


## 算法简介

### 基础排序算法

[常见排序Sorting算法汇总](learning/subjects/Computer/Data-Structures-and-Algorithm/Algorithms/Elementary/常见排序Sorting算法汇总.md)


## 经典例题

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


### 求众数

#### 重复数量大于 n/2 的元素

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
	2. 证明：当一个数组中某个主要元素个数超过 n/2 时，只要通过**两两抵消**的方式**每次来清除两个不同的元素**，就能使得最终的众数元素得以保留。

#### 重复数量大于 n/3 的元素

[LeetCode 229. Majority Element II](https://leetcode.com/problems/majority-element-ii/)

题目概述：给定一个长度为 n 的整型数组，返回出现次数大于 n/3 的元素

解题思路：
1. HashMap：
	1. 方法：统计频次，不再赘述
	2. 时间复杂度： $O(n)$，空间复杂度：$O(n)$。 
2. Sorting：
	1. 方法：排序后遍历时统计次数，不再赘述
	2. $O(nlog_{2}{n})$，空间复杂度：$O(1)$。 
3. Boyer–Moore majority vote algorithm：摩尔投票算法。
	1. 方法：Boyer–Moore majority vote algorithm：摩尔投票算法。缓存两个不同元素，以及对应的个数。在迭代时，如果是缓存元素，则对应次数+1，如果不是且缓存个数大于 0，则这**三个不同的元素相互抵消**，两个缓存元素的个数都-1，否则，当前遍历的元素接替缓存个数为 0 的元素成为新的缓存元素，如此不断跌倒，直到结束。缓存的 2 个元素中，可能包含有题目要求的元素，再次遍历数组，统计这两个元素的个数，如果满足条件，则加入到 List 中，最后返回 List。
	2. 证明：**每次将三个不同的数字进行消除**，最多消除 n/3 次，如果某个元素的重复个数大于 n/3，则代表在所有数组尽可能消除后，必定存在于剩余的元素中。

### 奇偶性排序


[LeetCode 905. Sort Array By Parity]( https://leetcode.com/problems/sort-array-by-parity/ )

问题概述：输入一个整型数组，将所有偶数放在左侧，奇数放在右侧，并返回排序后的数组。

解题思路：
1. 双指针（Two Pointers）：左右指针


[LeetCode 922. Sort Array By Parity II](https://leetcode.com/problems/sort-array-by-parity-ii/)

问题概述：输入一个整型数组，将所有偶数放在偶数位置上，将奇数放在奇数位置上，并返回排序后的数组。

解题思路：
1. 双指针（Two Pointers）：快慢指针

