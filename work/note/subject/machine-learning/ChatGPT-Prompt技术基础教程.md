# ChatGPT Prompt技术基础教程


## 前言

本文主要基于OpenAI ChatGPT，讲解Prompt和LLM的基础知识。


## LLM简介

Base LLM，基于训练数据，可以预测下一个可能出现的词语。

Instrument Tuned LLM，可以遵循指令，通过指令控制输入输出，如ChatGPT。

Instrument Tuned LLM的一个常见训练方式，是在Base LLM的基础之上，进一步训练和Finetune。而其中的进阶训练通常需要使用一种RLHF（Reinforcement Learning with Human Feedback）强化学习方法。 


## Prompt Principles and Strategies



#### Principle1: write clear and specific instruction

##### tactic1: Use delimiters

```
Triple quotes: """
Triple backticks: ```
Triple dashes: ---
Angle brackets: <>
XML tags: <tag></tag>
```

在Prompt中使用特殊的分隔符来标识文本，可以更加精确地指定需要处理的文本。

##### tactic2: Ask for structured output

在Prompt中可以要求Model以指定格式返回数据，such as HTML, JSON e.g.

##### tactic3: Check whether conditions are satisfied

check assumptions required to do the task，即Promt中最好包含对意外情况的检查，避免在意外情况下返回的结果不符合需求。Prompt中，最好尽可能对所有情况的处理方式阐述清楚。

##### tactic4: Few-shot prompting

通过提供一些成功的任务样本给Model，让Model可以简单的学习样本，进而正确执行任务。

#### Principle2: give the module time to think

##### tactic1: Specify the steps to complete a task

通过显式声明Task的执行步骤，来定义Task，会使得Task的运行结果更加准确。

##### tactic2: Instruct the model to work out its own solution before rushing to a conclusion

如果在Prompt中直接让Model来判断一个复杂问题的解答过程正确与否，如：求解方程，Model很容易会造成误判。建议通过指令来引导Model自己一步步思考，最终来确定问题的答案，并与Prompt之前提供的解答过程进行对比。


## Model Limitations



## 参考链接
1. [Wiki - Large language model](https://en.wikipedia.org/wiki/Large_language_model)
2. [Bilibili-【中文完整版全9集】ChatGPT提示工程师｜AI大神吴恩达教你写提示词｜prompt engineering](https://www.bilibili.com/video/BV14M4y147yH)
3. [YouTube-ChatGPT Prompt Engineering for Developers: A short course from OpenAI and DeepLearning.AI](https://www.youtube.com/watch?v=H4YK_7MAckk
