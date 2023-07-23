# 数学 Math


## 反转整数

[LeetCode 7. Reverse Integer](https://leetcode.com/problems/reverse-integer/)

问题概述：将输入的 unsigned 32-bit 的数字进行反转，保留其符号位；如果反转结果溢出，则返回 0，反之则返回反转后的 unsigned 32-bit 数值。


注意事项：
1. 在 Java 等编程语言中，取余运算符 (%)，支持任意整数和浮点数，充当被除数（dividend）和除数（divisor），如 `-8 % -3`、 `-1.3 % 2`、`2.3 % 2.1` 等。最后余数（remainder）的符号，和被除数相同，而余数的数值部分，统一等于被除数，以除数为步长，逐渐靠近数轴原点的一个极限距离，如 `-2.3 % 2.1` 的结果约等于 `-0.2`。