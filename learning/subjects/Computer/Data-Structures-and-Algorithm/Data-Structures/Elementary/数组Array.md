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
1. 二分查找 Binary Search (Two pointers)：使用双指针实现的二分查找算法。设置循环条件为 `left<=right` ，退出条件为找到了对应元素，每次循环时通过左右双指针来确定下一次检索的位置，`index = left + (right - left) / 2`（注意这里先执行减法和除法，再执行加法，避免精度溢出）。循环退出时，再判断一次最后一次位置指向的元素是否等于指定数值，因为有可能没找到元素循环就结束了。


#### 有序数组的平方

[LeetCode 977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/)

问题概述：输入一个有序数组，数组元素可正可负，输出每个元素的平方值组成的数组，要求返回的数组是升序

解题思路：
1. 三指针（Three poiners）：使用三指针，left 和 right 分别指向原始数组的起止元素，index 初始值为结果数组的待计算元素位置。创建循环条件为 left <= right 的循环，每次迭代时移动原始数组中 left 和 right 两者中绝对值较大者，计算其平方值后放置于 index 处，并将原始数组中对应指针朝着中心移动，同时 index-=1。 


### 无序数组

#### 最小子数组之和

[LeetCode 209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)

解题思路：
1. 双指针（Two Pointers）：使用双指针，分别指向子数组的起止元素，右指针 right 作为游标在迭代中依次前进。


#### 螺旋矩阵

LeetCode 54. Spiral Matrix
LeetCode 59. Spiral Matrix II