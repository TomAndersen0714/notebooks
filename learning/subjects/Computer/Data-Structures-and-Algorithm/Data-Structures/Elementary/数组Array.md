# 数组 (Array)



## 有序数组（sorted array）


常用解题算法
1. Two (or three) pointers
2. Binary Search
3. A sliding window

## 无序数组 (unsorted array)

常用解题算法
1. HashMap
2. Sort


## 相关例题

### 有序数组

#### 二分查找

[LeetCode 704. Binary Search](https://leetcode.com/problems/binary-search/)

解题思路：
1. Binary Search+Two pointers：使用双指针实现的二分查找算法。设置循环条件为 `left<=right` ，退出条件为找到了对应元素，每次循环时通过左右双指针来确定下一次检索的位置，`index = left + (right - left) / 2`（注意这里先执行减法和除法，再执行加法，避免精度溢出）。循环退出时，再判断一次最后一次位置指向的元素是否等于指定数值，因为有可能没找到元素循环就结束了。



