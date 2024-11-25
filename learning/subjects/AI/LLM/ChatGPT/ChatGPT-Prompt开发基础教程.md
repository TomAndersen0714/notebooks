# ChatGPT Prompt 开发基础教程


## 前言

本文主要基于 OpenAI ChatGPT，讲解 Prompt 和 LLM 的技巧，而本文的内容主要来自于 DeepLearning AI 的课程 ChatGPT Prompt Engineering for Developers。

## Introduction

Base LLM，基于训练数据，可以预测下一个可能出现的词语。

Instrument Tuned LLM，可以遵循指令，通过指令控制输入输出，如 ChatGPT。

Instrument Tuned LLM 的一个常见训练方式，是在 Base LLM 的基础之上，进一步训练和微调 Finetune。而其中的进阶训练通常需要使用一种 RLHF（Reinforcement Learning with Human Feedback）强化学习方法。 

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

在 Prompt 中使用特殊的分隔符来标识文本，可以更加精确地指定需要处理的文本。

```python
text = f"""
You should express what you want a model to do by \ 
providing instructions that are as clear and \ 
specific as you can possibly make them. \ 
This will guide the model towards the desired output, \ 
and reduce the chances of receiving irrelevant \ 
or incorrect responses. Don't confuse writing a \ 
clear prompt with writing a short prompt. \ 
In many cases, longer prompts provide more clarity \ 
and context for the model, which can lead to \ 
more detailed and relevant outputs.
"""
prompt = f"""
Summarize the text delimited by triple dashes \ 
into a single sentence.
--- {text} ---
"""

response = get_completion(prompt)
print(response)
```

##### tactic2: Ask for structured output

在 Prompt 中可以要求 Model 以指定格式返回数据，such as HTML, JSON e.g.

##### tactic3: Check whether conditions are satisfied

Check assumptions required to do the task，即 Prompt 中最好包含对意外情况的检查，避免在意外情况下返回的结果不符合需求。Prompt 中，最好尽可能对所有情况的处理方式阐述清楚。

##### tactic4: Few-shot prompting

通过提供一些成功的任务样本给 Model，让 Model 可以简单的学习样本，进而正确执行任务。

#### Principle2: give the module time to think

##### tactic1: Specify the steps to complete a task

通过显式声明 Task 的执行步骤，来定义 Task，会使得 Task 的运行结果更加准确。

##### tactic2: Instruct the model to work out its own solution before rushing to a conclusion

如果在 Prompt 中直接让 Model 来判断一个复杂问题的解答过程正确与否，如：求解方程，Model 很容易会造成误判。

建议通过指令来引导 Model 自己一步步思考，最终来确定问题的解答过程和答案，并可以将 Model 返回的答案与 Prompt 之前提供的解答过程进行对比。


## Model Limitations

### Hallucination: makes statements that sounds plausible but not true

尽管 LLM 是基于海量的知识进行训练的，但是在它的训练过程中，并没有完全记住训练时使用的数据，所以它对于一些知识的边界不是很了解。

当 Model 在回答一些比较晦涩和冷门的问题时，很可能会出现编造内容，返回一些拼凑起来的、看起来有道理的，但实际上却不正确的内容。如，在咨询某些软件的高级配置参数的使用指南时。

### Reducing hallucination

应对 Model Hallucination 的常见方案是，先通过 Prompt 让 Model 找到需要咨询内容的相关信息，然后从中选取高质量的信息，并要求 Model 后续回答的内容基于此信息。


## Iterative Prompt Development

机器学习训练基本流程：
Idea、Implementation、Experimental result、Error analysis、Idea...

Prompt 开发基本流程，和机器学习的训练过程类似：
Idea、Prompt、Experimental result、Error analysis、Idea...

在大部分情况下，需求都是个性化的，一些通用的 Prompt 并不能很好地解决这类问题，因此需要针对应用将 Prompt 进行个性化调整，并通过不断迭代，最终生成满足需求的 Prompt。

**Model无法精确限制数据长度，但会将结果限制在阈值附近。**

**PS：机器学习模型的预测结果必然存在精度问题，在实际运用中，通常还需要搭配额外的程序，乃至人工来兜底，来保证应用的准确率在需求的可接受范围。**

### Example

一个针对产品描述的Prompt开发示例：
1. 要求Model创建一个针对特定产品的文本描述
2. 限制Model返回的文本内容长度
3. 限制Model返回的文本中需要包含特定内容

## Capabilities

[OpenAI Platform](https://platform.openai.com/docs/guides/)

### Summarizing

文本提取

如，针对文章、针对评论等文本内容的提炼总结。

### Inferring

文本打标、分类等

如，提取标签、提取名称、提取情感、推测是否包含指定内容等等。

大语言模型相比于传统的机器学习模型开发流程的优势：
1. 更快捷：在LLM中只需要通过简短的Prompt就可以生成类似的结果
2. 更简单：一个LLM就可以应对多种不同的任务

PS：可能存在的缺陷：
1. 更低的精度
2. 更多的计算资源
3. 更长时间的update，即更长的迭代周期

### Transforming

文本转换

如，语言识别、语言翻译、拼写和语法检查修正、转换格式、语气转换

### Expanding

文本扩展

如，基于标题写文章、基于短文本写文章、基于预设配置回复Email

ChatGPT Model 和其他的 NLP 模型类似，也支持使用 Temperature 参数，来控制 Model 在相同的输入下，输出结果之间的相关性，或者说输出结果的概率分布的平滑程度。

在 ChatGPT Model 中，Temperature 越小那么相同输入下，返回的输出结果之间就越接近，反之则区别越大，Temperature 的默认值也是最小值为 0，即在相同的输入下，每次都会返回选择最高概率的内容返回。

[OpenAI Platform](https://platform.openai.com/docs/quickstart/adjust-your-settings)

### Chatbot

构建自定义聊天机器人

如，点餐机器人、聊天机器人

使用 OpenAI API（如 Python openai. ChatCompletion 对象），可以使用 Prompt 构建聊天自定义聊天机器人。
https://platform.openai.com/docs/guides/chat


## 参考链接

1. [Wiki - Large language model](https://en.wikipedia.org/wiki/Large_language_model)
2. [Bilibili-【中文完整版全9集】ChatGPT提示工程师｜AI大神吴恩达教你写提示词｜prompt engineering](https://www.bilibili.com/video/BV14M4y147yH)
3. [YouTube-ChatGPT Prompt Engineering for Developers: A short course from OpenAI and DeepLearning.AI](https://www.youtube.com/watch?v=H4YK_7MAckk&list=RDCMUCcIXc5mJsHVYTZR1maL5l9w&index=1)
4. [ChatGPT|万字长文总结吴恩达prompt-engineering课](https://mp.weixin.qq.com/s/gUtB71uWI7Dg_tfRzaidCA)
5. [GitHub - datawhalechina/llm-cookbook: 面向开发者的 LLM 入门教程，吴恩达大模型系列课程中文版](https://github.com/datawhalechina/llm-cookbook)
6. [ChatGPT Prompt Engineering for Developers](https://www.youtube.com/playlist?list=PLZnV2TKiv3wC0F5AQJwL3RGCfInRpu-LA)
7. [DeepLearning.AI-ChatGPT Prompt Engineering for Developers](https://learn.deeplearning.ai/chatgpt-prompt-eng/lesson/1/introduction)
8. [OpenAI Documentation](https://platform.openai.com/docs/introduction)
9. [OpenAI Documentation Guides](https://platform.openai.com/docs/guides/)
