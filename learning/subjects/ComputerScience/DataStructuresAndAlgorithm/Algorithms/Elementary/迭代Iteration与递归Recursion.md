# 迭代 (Iteration) 与递归 (Recursion)

## 迭代 (Iteration)

## 递归 (Recursion)

「递归 recursion」是一种算法策略，通过函数调用自身来解决问题。它主要包含两个阶段。

1. 递：程序不断深入地调用自身，通常传入更小或更简化的参数，直到达到“终止条件”。
2. 归：触发“终止条件”后，程序从最深层的递归函数开始逐层返回，汇聚每一层的结果。

而从实现的角度看，递归代码主要包含三个要素。

1. 终止条件：用于决定什么时候由“递”转“归”。
2. 递归调用：对应“递”，函数调用自身，通常输入更小或更简化的参数。
3. 返回结果：对应“归”，达到某个终止条件，将当前递归层级的结果返回至上一层。

虽然从计算角度看，迭代（Iteration）与递归（Recursion）可以得到相同的结果，但它们代表了两种完全不同的思考和解决问题的范式。

1. 迭代：“自下而上”地解决问题。从最基础的步骤开始，然后不断重复或累加这些步骤，直到任务完成。
2. 递归：“自上而下”地解决问题。将原问题分解为更小的子问题，这些子问题和原问题具有相同的形式。接下来将子问题继续分解为更小的子问题，直到基本情况时停止（基本情况的解是已知的）。

以上述的求和函数为例，设问题： $f(n) = 1 + 2 + \dots + n$，则有：
1. 迭代：在循环中模拟求和过程，从 1 遍历到 n ，每轮执行求和操作，即可求得 $f(n)$。
2. 递归：将问题分解为子问题 $f(n) = n + f(n-1)$，不断（递归地）分解下去，直至基本情况 $f(0) = 0$ 时终止。

## 参考链接
1. [Hello 算法-2.2 迭代与递归](https://www.hello-algo.com/chapter_computational_complexity/iteration_and_recursion/)