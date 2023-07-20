# 双指针 (Two Pointers)


## 左右指针

两个指针一左一右，相向移动。通过使用两个指针分别指向数组的起始位置和结束位置，根据两个指针指向的元素和目标值之间的关系，逐步缩小问题的规模。

如双数求和、回文判断、字符串匹配、二分查找等。


### 双数求和

#### 双数求和-解唯一

[LeetCode 1. Two Sum](https://leetcode.com/problems/two-sum/)

问题概述：在一个数组中查找两个数，使得它们的和等于目标值。

条件：
1. 解唯一

解题思路：
1. Brute Force：
	1. 双重循环
	2. 时间复杂度为 $O(n^2)$
2. HashMap：
	1. 使用 HashMao 存储数组中的数值-索引对。遍历数组，创建并添加元素。每次添加元素时，检索 Map 中是否包含对应的另一个元素，如果包含则直接输出，否则添加进 Map，继续迭代。
	2. 由于 HashMap get/put 方法的平均时间复杂度为 $O(1)$，故整体算法时间复杂度为 $O(n)$

#### 双数求和-有序数组+解唯一

[LeetCode 167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

问题概述：在一个有序数组中查找两个数，使得它们的和等于目标值。

条件： 
1. 数组是有序的
2. 解唯一

解题思路： 
1. Two pointers：
	1. 方法：将左右指针 left 和 right 的初始位置分别设置为数组的起止位置，假设两指针分别指向双数中的最小数 a 和最大数 b。以 left 指针为游标指针，当两指针元素数值之和大于目标值，即 $a+b>target$ 时，其中 $target=res[0]+res[1]$，则 right 左移 1 位。当满足条件  $a+b<target$ 时，则说明以 left 指针元素为最小元素的所有可能值都已遍历完成，故而 left 指针作为游标要右移 1 位，继续遍历，直到出现 $a+b=target$。
	2. 证明 1：
		1. 由条件 1 可得，未发生移动时，$a\leq res[0]$，$b\geq res[1]$；
		2. 由条件 2 可得，在移动过程两个指针的范围在不断缩小，中若某个指针指向了正确答案，即出现 $a=res[0], a+b\geq target$ 或 $b=res[1], a+b\leq target$ 时，即则后续必定会一直移动另一个指针，直到找到正确答案。[参考链接](https://blog.csdn.net/weixin_43445477/article/details/117561685)
	3. 证明 2：
		1. 
	4. 空间复杂度：$O(1)$，时间复杂度： $O(n)$。 
2. Binary Search：
	1. 循环，依次检索
	2. 空间复杂度：$O(1)$，时间复杂度： $O(nlog_{2}{n})$。 
3. HashMap：
	1. 方法：使用 HashMap 将元素值-元素索引作为 KV 存储。每次遍历时，判断 HashMap 中是否存在另一个 $target-a$，存在则返回对应结果，不存在则
	2. 证明 1：由条件 2 可得，HashMap 中的 Key 不会重复，即结果值唯一。
	3. 空间复杂度：$O(n)$，时间复杂度：$O(n)$。
4. Brute Force：
	1. 双重循环，依次配对


#### 双数求和-输入为 BST

[LeetCode 653. Two Sum IV - Input is a BST](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/)


#### 双数求和-值最接近+解不唯一

条件：
1. 解不唯一

[LeetCode 1099. Two Sum Less Than K](https://leetcode.com/problems/two-sum-less-than-k/)
[LeetCode 1099. Two Sum Less Than K](https://wentao-shao.gitbook.io/leetcode/two-pointers/1099.two-sum-less-than-k)



### 三数求和


#### 三数求和-解不唯一

[LeetCode 15. 3Sum](https://leetcode.com/problems/3sum/)

问题概述：在一个数组中查找三个数，使得它们的和等于目标值，并将所有三元组返回。

条件：
1. 解不唯一
2. 解不能重复

解题思路：
1. Two pointers：
	1. 思路：根据三个指针指向的元素和目标值之间的关系，先将数组进行排序 Sort，然后遍历数组，假设当前游标指向的元素为结果向量中的最小元素。那么接下来的任务就是判断，游标之后的元素中是否有某两个元素的值之和等于特定值（或满足某特定条件），故而将三数问题，转换为了双数问题，从而应用 Two Pointers 进行求解。
	2. 证明 1：根据条件 1 和条件 2，在移动指针时，为了避免结果中出现重复的解，每次指针移动时，如果移动后指针的元素值和之前相同，则需要不断跳过，避免对应位置使用重复的元素。
	3. 空间复杂度：$O(1)$，时间复杂度： $O(n^2)$。 
2. Binary Search：
	1. 思路：根据三个指针指向的元素和目标值之间的关系，先将数组进行排序 Sort，然后遍历数组，假设当前游标指向的元素为结果向量中的最小元素。那么接下来的任务就是判断，游标之后的元素中是否有某两个元素的值之和等于特定值（或满足某特定条件），故而将三数问题，转换为了双数问题，从而可以应用 Binary Search 进行求解。


#### 三数求和-值最接近+解不唯一

[LeetCode 16. 3Sum Closest](https://leetcode.com/problems/3sum-closest/)

问题概述：在一个数组中查找三个数，使得它们的和最接近目标值，并返回这三元素之和。

条件：
1. 解不唯一

解题思路：
1. Two pointers：
	1. 思路：根据三个指针指向的元素和目标值之间的关系，先将数组进行排序 Sort，然后遍历数组，假设当前游标指向的元素为结果向量中的最小元素。那么接下来的任务就是判断，游标之后的元素中是否有某两个元素的值之和等于特定值（或满足某特定条件），故而将三数问题，转换为了双数问题，从而应用 Two Pointers 进行求解。
	2. 证明 1：由于条件 1，因此在进行排序 Sort 之后，此问题便变成了多个子问题 [双数求和-值最接近+解不唯一](learning/subjects/Computer/Data-Structures-and-Algorithm/Algorithms/Basic/双指针Two-Pointers.md#双数求和-值最接近+解不唯一) 。


### 四数求和

[LeetCode 18. 4Sum](https://leetcode.com/problems/4sum/)


### 回文字符串

例题：
[LeetCode 344. Reverse String](https://leetcode.com/problems/reverse-string/)


## 快慢指针

两个指针一前一后，同向移动。

### 链表环路

例题：

问题概述：判断链表中是否有环路存储在。

解题思路：通过使用两个指针，一个指针每次移动两步，另一个指针每次移动一步，根据两个指针的位置和关系，判断链表是否有环、找到链表的中间节点等。


### 滑动窗口

问题概述：通过使用两个指针，一个指针指向窗口的起始位置，另一个指针指向窗口的结束位置，通过移动窗口来找到满足特定条件的子数组或子串。


