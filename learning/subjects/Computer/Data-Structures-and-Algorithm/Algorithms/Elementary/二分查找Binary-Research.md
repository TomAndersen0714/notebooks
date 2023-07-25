# 二分查找 (Binary-Research)

只要是有序数组，则优先尝试二分查找。

## 注意事项

1. 二分查找时，根据题目要求，一般情况下每次检索时的范围，不能包含当前元素。
2. 二分查找时，因为可能存在重复元素，所以一般情况下，当 $target < key$ 时，则在下一次检索范围为左侧，当 $target>=key$ 时，则下一次检索范围为右侧
3. 计算下一个检索元素的数组下标时，不能直接两边下标求和后整数，而应该是左下标加上两者差值的一半后再整除，出现精度溢出


## 二分查找

例题：
[LeetCode 34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

问题概述：双指针根据下一个位置的值，不断缩小范围，最终确定目标值的位置。

解题思路：经典二分查找。


## 双数求和

### 双数求和-输入为有序数组+解唯一

[LeetCode 167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

## 三数求和

### 三数求和-解不唯一

[LeetCode 15. 3Sum](https://leetcode.com/problems/3sum/)



