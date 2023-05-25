# 正则表达式转换为DFA常用算法


## 简介

DFA的特点：
1. 确定性（Deterministic）：对于每个输入字符，DFA在给定的状态下只有一种转换选择，即出度为1
2. 有限状态（Finite）：DFA的状态集是有限的，可以通过状态转换来处理任意长度的输入
3. 接受状态（Accepting States）：DFA中有一个或多个接受状态，也称为终态。当DFA处理完输入字符串时，如果最终处于某个接受状态，则表示输入字符串符合语言规则。


## 直接法

适合通过简单的正则表达式，直接构造

三步走：
1. 基于正则表达式构建抽象语法树
2. 根据语法树，计算nullable、firstpos、lastpos和followpos辅助函数结果，并确定终止状态（使用符号#表示），构建DFA状态图
3. DFA最小化

## 先转换成NFA，再转换成DFA

三步走：
1. 基于正则表达式构建NFA，常用的算法是**Thompson's Construction(汤普森构造法)**
2. 基于NFA构建DFA，常用的算法是**Subset Construction(子集构造法)**
3. DFA最小化，常用的算法是**Hopcroft(霍普克罗夫特)**


## 参考链接
1. OpenAI-ChatGPT3.5
2. [正则表达式直接转换为DFA](https://wangwangok.github.io/2019/10/28/compiler_regular2dfa/)
3. 编译原理(第2版), 3.7.4