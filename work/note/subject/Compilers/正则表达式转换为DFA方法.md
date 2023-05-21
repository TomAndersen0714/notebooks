# 正则表达式转换为DFA方法


## 直接法

适合通过简单的正则表达式，直接构造

两步走：
1. 基于正则表达式构建抽象语法树
2. 根据语法树，构建DFA状态图

## 先转换成NFA，再转换成DFA

三步走：
1. 基于正则表达式构建NFA，常用的算法是**Thompson's Construction(汤普森构造法)**
2. 基于NFA构建DFA，常用的算法是**Subset Construction(子集构造法)**
3. DFA最小化，常用的算法是Hopcroft


## 参考链接
1. OpenAI-ChatGPT3.5