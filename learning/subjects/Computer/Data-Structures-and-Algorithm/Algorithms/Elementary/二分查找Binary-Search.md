# 二分查找 (Binary Search)

只要是在 **有序数组（Sorted Array）** 中查找元素，可以优先尝试二分查找。

## 算法概览

### 算法简介

二分查找（Binary Search）是一种依靠三指针（Three Poiners）来在有序（Sorted）序列中，查找指定元素的算法。


### 应用模式

假设三指针分别为 left、mid、right，则二分查找的应用模式如下：

1. 设置循环条件：
	1. 设置循环条件为 `left<=right` 
2. 每次循环时通过左右双指针来确定下一次比较的元素位置
	1. `mid = left + (right - left) / 2`，注意这里需要先执行减法和除法，再执行加法，避免精度溢出。 
3. 通过对比 `mid` 指针指向的元素值与目标值的大小，调整下次检索范围
	1. 如果 `element[mid]>target`，则 `right=mid-1`
	2. 如果 `element[mid]<target`，则 `left=mid+1`
	3. 否则中断循环，即找到了目标值，`mid` 指针指向的元素值即为目标值
4. 循环结束时，再次判断 `mid` 指针指向的元素值是否等于目标值，`element[mid] ? target`
	1. 因为循环有可能是正常结束（未找到），有可能是直接中断（已找到）。

## 相关例题

### 二分查找

例题：
[LeetCode 34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

问题概述：输入一个递增的整型数组和一个整数，返回这个整数在这个递增数组中的起止位置。

解题思路：
1. 二分查找（Binary Search）+中心扩展（Expanding from Center）。二分查找定位目标元素的其中一个位置，然后从中心扩展，找到所有相同元素，以及对应的起止范围。

### K 数求和

#### 双数求和

##### 双数求和-输入为有序数组+解唯一

[LeetCode 167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

#### 三数求和

##### 三数求和-解不唯一

[LeetCode 15. 3Sum](https://leetcode.com/problems/3sum/)



