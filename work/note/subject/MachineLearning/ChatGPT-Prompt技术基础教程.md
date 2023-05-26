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

在Prompt中可以要求Model以指定格式返回数据，such as HTML, JSON e.g.

##### tactic3: Check whether conditions are satisfied

check assumptions required to do the task，即Prompt中最好包含对意外情况的检查，避免在意外情况下返回的结果不符合需求。Prompt中，最好尽可能对所有情况的处理方式阐述清楚。

##### tactic4: Few-shot prompting

通过提供一些成功的任务样本给Model，让Model可以简单的学习样本，进而正确执行任务。

#### Principle2: give the module time to think

##### tactic1: Specify the steps to complete a task

通过显式声明Task的执行步骤，来定义Task，会使得Task的运行结果更加准确。

##### tactic2: Instruct the model to work out its own solution before rushing to a conclusion

如果在Prompt中直接让Model来判断一个复杂问题的解答过程正确与否，如：求解方程，Model很容易会造成误判。

建议通过指令来引导Model自己一步步思考，最终来确定问题的解答过程和答案，并可以将Model返回的答案与Prompt之前提供的解答过程进行对比。


## Model Limitations

### Hallucination: makes statements that sounds plausible but not true

尽管LLM是基于海量的只是进行训练的，但是在它的训练过程中，并没有完全记住训练时使用的数据，所以它对于一些知识的边界不是很了解。

当Model在回答一些比较晦涩和冷门的问题时，很可能会出现编造内容，返回一些拼凑起来的、看起来有道理的，但实际上却不正确的内容。如，在咨询某些软件的高级配置参数的使用指南时。

#### Reducing hallucination

应对Model Hallucination的常见方案是，先通过Prompt让Model找到需要咨询内容的相关信息，然后从中选取高质量的信息，并要求Model后续回答的内容基于此信息。


## Iterative Prompt Development

机器学习训练基本流程：
Idea、Implementation、Experimental result、Error analysis、Idea...

Prompt开发基本流程，和机器学习的训练过程类似：
Idea、Prompt、Experimental result、Error analysis、Idea...

在大部分情况下，需求都是个性化的，一些通用的Prompt并不能很好地解决这类问题，因此需要针对应用将Prompt进行个性化调整，并通过不断迭代，最终生成满足需求的Prompt。

**Model无法精确限制数据长度，但会将结果限制在阈值附近。**

**PS：机器学习模型的预测结果必然存在误差，在实际运用中，通常还需要搭配额外的程序来兜底，来保证应用的准确率尽可能接近百分百。**


### Example

一个针对产品描述的Prompt开发示例：
1. 要求Model创建一个针对特定产品的文本描述
2. 限制Model返回的文本内容长度
3. 限制Model返回的文本中需要包含特定内容


## Capabilities

https://platform.openai.com/docs/guides/

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

ChatGPT Model和其他的NLP模型类似，也支持使用Temperature参数，来控制Model在相同的输入下，输出结果之间的相关性，或者说输出结果的概率分布的平滑程度。

在ChatGPT Model中，Temperature越小那么相同输入下，返回的输出结果之间就越接近，反之则区别越大，Temperature的默认值也是最小值为0，即在相同的输入下，每次都会返回选择最高概率的内容返回。
https://platform.openai.com/docs/quickstart/adjust-your-settings


### Chatbot

构建自定义聊天机器人

如，点餐机器人、聊天机器人

使用OpenAI API（如Python openai.ChatCompletion对象），可以使用Prompt构建聊天自定义聊天机器人。
https://platform.openai.com/docs/guides/chat


## 参考链接
1. [Wiki - Large language model](https://en.wikipedia.org/wiki/Large_language_model)
2. [Bilibili-【中文完整版全9集】ChatGPT提示工程师｜AI大神吴恩达教你写提示词｜prompt engineering](https://www.bilibili.com/video/BV14M4y147yH)
3. [YouTube-ChatGPT Prompt Engineering for Developers: A short course from OpenAI and DeepLearning.AI](https://www.youtube.com/watch?v=H4YK_7MAckk&list=RDCMUCcIXc5mJsHVYTZR1maL5l9w&index=1)
4. [ChatGPT Prompt Engineering for Developers](https://www.youtube.com/playlist?list=PLZnV2TKiv3wC0F5AQJwL3RGCfInRpu-LA)
5. [DeepLearning.AI](https://learn.deeplearning.ai/chatgpt-prompt-eng/lesson/1/introduction)
6. [OpenAI Documentation](https://platform.openai.com/docs/introduction)
7. [OpenAI Documentation Guides](https://platform.openai.com/docs/guides/)
