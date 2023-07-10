# 双指针算法 (Two pointers)


## 左右指针

两个指针一左一右，相向移动。通过使用两个指针分别指向数组的起始位置和结束位置，根据两个指针指向的元素和目标值之间的关系，逐步缩小问题的规模。

如双数求和、回文判断、字符串匹配、二分查找等。


### 双数求和

问题概述：在一个数组中查找两个数，使得它们的和等于目标值。

解题思路：
1. Brute Force
2. HashMap：使用 HashMao 存储数组中的数值-索引对。遍历数组，创建并添加元素。每次添加元素时，检索 Map 中是否包含对应的另一个元素，如果包含则直接输出，否则添加进 Map，继续迭代。
3. Two pointers：

例题：
[LeetCode 1. Two Sum](https://leetcode.com/problems/two-sum/)


### 双数求和-有序数组

问题概述：在一个有序数组中查找两个数，使得它们的和等于目标值。

解题思路： https://blog.csdn.net/weixin_43445477/article/details/117561685

例题：
[LeetCode 167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)



### 二分查找

问题概述：双指针根据下一个位置的值，不断缩小范围，最终确定目标值的位置。

解题思路：经典二分查找，注意求下一个元素位置时，不要直接左右指针位置求和均分，而是应该左指针位置增加一半距离，避免精度溢出。

注意事项：
1. 避免在求下一个元素时出现精度溢出。

例题：
[LeetCode 34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)


### 回文字符串

[LeetCode 344. Reverse String](https://leetcode.com/problems/reverse-string/)



### 三数求和

问题概述：在一个数组中查找两个数，使得它们的和等于目标值。

解题思路：根据三个指针指向的元素和目标值之间的关系，先将数组进行排序，进而将三元素问题，转换为双元素问题，从而将其简化为“双数求和”问题进行求解。

例题：
[LeetCode 15. 3Sum](https://leetcode.com/problems/3sum/)
[LeetCode 16. 3Sum Closest](https://leetcode.com/problems/3sum-closest/)
[LeetCode 18. 4Sum](https://leetcode.com/problems/4sum/)

## 快慢指针

两个指针一前一后，同向移动。

### 链表环路

问题概述：判断链表中是否有环路存储在。

解题思路：通过使用两个指针，一个指针每次移动两步，另一个指针每次移动一步，根据两个指针的位置和关系，判断链表是否有环、找到链表的中间节点等。

例题：


### 滑动窗口


滑动窗口指针技术通常用于解决字符串或数组的子数组或子串问题。通过使用两个指针，一个指针指向窗口的起始位置，另一个指针指向窗口的结束位置，通过移动窗口来找到满足特定条件的子数组或子串。


