# 数组 (Array)



## 有序数组（sorted array）

常用解题算法
1. Two (or three) pointers
2. Binary Search
3. A sliding window

## 无序数组 (unsorted array)

常用解题算法
1. HashMap
2. Sorting


## 相关例题

### 有序数组

#### 二分查找

[LeetCode 704. Binary Search](https://leetcode.com/problems/binary-search/)

解题思路：
1. 二分查找 Binary Search (Two pointers)：使用双指针实现的二分查找算法。设置循环条件为 `left<=right` ，退出条件为找到了对应元素，每次循环时通过左右双指针来确定下一次检索的位置，`index = left + (right - left) / 2`（注意这里先执行减法和除法，再执行加法，避免精度溢出）。循环退出时，再判断一次最后一次位置指向的元素是否等于指定数值，因为有可能没找到元素循环就结束了。


#### 有序数组的平方数数组

[LeetCode 977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/)

问题概述：输入一个有序数组，数组元素可正可负，输出每个元素的平方值组成的数组，要求返回的数组是升序

解题思路：
1. 三指针（Three poiners）：使用三指针，left 和 right 分别指向原始数组的起止元素，index 初始值为结果数组的待计算元素位置。创建循环条件为 left <= right 的循环，每次迭代时移动原始数组中 left 和 right 两者中绝对值较大者，计算其平方值后放置于 index 处，并将原始数组中对应指针朝着中心移动，同时 index-=1。 


### 无序数组

#### 最小子数组之和

[LeetCode 209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)

问题概述：输入一个正整数数组和一个正整数，找到该数组中连续元素组成的最小子数组，子数组的元素之和要求大于等于输入的正整数，返回此子数组的长度。

解题思路：
1. 双指针（Two Pointers）：
	1. 方法：使用双指针，分别指向子数组的起止元素。开启循环，右指针 right 作为游标在迭代中依次右移，每次移动时子数组之和 sum 依次增加，如果满足 sum>=target 则不断移动 left 指针，同时 sum 依次减小。
	2. 证明 1：在双指针 left 和 right 的移动过程中，left 始终指向子数组中的最左侧元素，当 sum>=target 出现时，即已经找到了以 left 指向的元素为开始元素的最小子数组，因此就需要移动 left，继续寻找以后续元素为起始元素的子数组，判断其是否满足条件。
	3. 证明 2：只要满足 sum>=target ，就可以不断移动 left 指针，是因为题目要找的是最小数组，如果 left 在当前位置时的子数组之和已经满足条件，那么肯定不会继续移动 right，因为移动 right，子数组必然会继续满足条件，但同时也会变得更长，因此并不需要移动 right，只需要继续移动 left 即可。


#### 螺旋矩阵

[LeetCode 54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/description/)

问题概述：输入一个 $m*n$ 的矩阵 matrix，从左上角开始，以顺时针螺旋的方式从边界开始逐渐向中心遍历矩阵中的元素，按顺序返回遍历过的所有元素值。

解题思路：
1. 暴力破解（Brute Force）+状态缓存（State Cache）+计数器（Counter）+方向数组（Direction Array）
	1. 方法：本题主要通过循环迭代的方式进行求解，可以理解为 brute force，且基本只有一种解法，其中 state cache 用来存储是否访问过对应的位置，counter 用来保证循环终止，direction array 用来控制方向切换。每次迭代时，都会沿着同一个 direction 一直移动，直到遇见 state cache 中标明已经访问过的元素，或者到达 matrix 的四个边界。当沿着当前 direction 不能继续前进时，就获取下一个 direction，继续此迭代过程，最终返回沿途路过的元素值。

[LeetCode 59. Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii/)

问题概述：输入一个正整数 $n$，构建一个 $n*n$ 数组，按照螺旋遍历的路径依次给每个元素赋值，最终返回此数组。

解题思路：
1. 和 LeetCode54 的思路基本相同，不再赘述。
