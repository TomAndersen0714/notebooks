# 数列(Sequence)和级数(Series)


## 数列（Sequence）

$$
\text{Sequence}: \, a_1, a_2, a_3, \ldots
$$

数列（sequence）是按照一定规律排列的一系列数值的集合。数列中的每个数值称为项（term）。

数列可以有无限多个项，也可以有有限个项。数列中的项可以按照顺序进行排列，例如 1, 2, 3, 4, 5，也可以具有特定的规律，例如 2, 4, 6, 8, 10，其中每个项都是前一项加上一个固定的数值。

### 等差数列（Arithmetic Sequence）


### 调和数列


### 等比数列


### 整数数列


#### 平方数（Square）
https://zh.wikipedia.org/zh-hans/%E5%B9%B3%E6%96%B9%E6%95%B0

#### 立方数（Cube）
https://zh.wikipedia.org/zh-hans/%E5%B9%B3%E6%96%B9%E6%95%B0


#### 斐波那契数（Successione di Fibonacci）



## 级数（Series）

$$
\text{Series}: \, S_n = \sum_{i=1}^{n} a_i = a_1 + a_2 + a_3 + \ldots
$$

级数（series）是数列（sequence）中所有项的和。级数可以由有限个项或无限个项组成，当项的个数n是有限整数时，即有穷级数（finite series），当项的个数趋近于无穷大时，即无穷级数（infinite series）。

级数可以是等差级数（arithmetic series）、等比级数（geometric series）或其他类型的级数，具体取决于数列中的项之间的关系。而级数的值可以是有限值（收敛），也可以是无限值（发散）。


### 等差级数（Arithmetic Series）

例如，数列 `1, 2, 3, 4, 5` 和 `2, 4, 6, 8, 10` 都是等差数列。级数 `1 + 2 + 3 + 4 + 5` 是数列 `1, 2, 3, 4, 5` 的和，结果为 15。

其中等差数列（arithmetic sequence）的求和公式，或者说等差级数（arithmetic series）的计算公式为：

$$
S_n = \sum_{i=1}^{n} a_1+(i-1)d = a_1 + (a_1+d) + (a_1+2d) + \ldots = \frac{n}{2}(a_1 + a_n) = {na_1} + \frac{n(n-1)}{2}d
$$

### 调和级数（Harmonic Series）

调和级数是发散级数，即无求和公式。

$$
S_n = \sum_{i=1}^{n} \frac{1}{i} = 1 + \frac{1}{2} + \frac{1}{3} + \frac{1}{4} + \ldots + \frac{1}{n}
$$


### 等比级数（Geometric Series）

等比级数，又叫做几何级数。

$$
S_n = \sum_{i=1}^{n} {a_1}{r^{i-1}} = a_1+a_1r+a_1r^2+\dots+a_1r^{n-1}=\frac{a_1(1 - r^n)}{1 - r}
$$

### 幂级数（Power Series）

$$
S_n = \sum_{i=1}^{n} a_i(x-c)^{i-1} = a_1 + a_2(x-c) + \ldots + a_n(x-c)^{n-1}
$$

### 平方数级数（Square Series）


$$
S_n = \sum_{k=1}^{n} k^2 = 1^2 + 2^2 + \ldots +n^2 = \frac{n(n+1)(2n+1)}{6}
$$


### 立方数级数（Cube Series）


$$
S_n = \sum_{k=1}^{n} k^3 = 1^3 + 2^3 + \ldots +n^3  = \frac{n^2(n+1)^2}{4}
$$


## 参考链接
1. 《算法导论（原书第3版）》机械工业出版社，附录: 数学基础知识