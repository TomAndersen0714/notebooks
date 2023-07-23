# 基于 LangChain 开发 LLM 应用基础教程


## Overview

LangChain 是一个用于开发 LLM 应用的开源框架，对于在 LLM 应用开发过程中常见对象进行了抽象，并进行模块化，使得开发人员可以通过组合这些模块快速进行 LLM 应用开发。目前主要提供了 Python 和 JavaScript (TypeScript) 开发包。

## Components

### Models, Prompts and Parsers

**Models**

LangChain 中的 Model 即指的是 LLM Model。

**Prompts**

Prompt 主要用于向模型传递信息，描述 Task。

LangChain 中的 Prompt 模块支持将 Prompt 模板化，提高了 Prompt 可复用性：
1. Prompts can be long and detailed.
2. Reuse good prompts when you can!
3. LangChain also provides prompts for common operations.

**Parsers**

Parser 主要用于解析 LLM Model 的返回结果，提取特定信息，并以特定的格式（如 JSON）返回。

### Memory

LLM Model 通常都是无状态（Stateless），每次 API 调用之间都是相互独立的，通过 LangChain 中的 Memory 模块，可以记住每次会话中每次调用 API 时的上下文，当然这也会占用更多的 Token。

为了避免会话不断增长而导致单次调用时 Token 开销不断增加，可以通过设置 Memory 中的上下文对话次数或 Token 数量等参数，限制 Token 开销。

当上下文的长度超出对应 Memory 的 Token 参数时，LangChain 可以总结之前的对话内容，在尽量保存之前的对话信息的同时，减小 Memory 占用的 Token 数量。

LangChain 也支持同时使用多个不同类型的 Memory，你也可以将对话中的内容存储在数据库中（如 K-V 数据库等）。

Memory Types:
1. ConversationBufferMemory
2. ConversationBufferWindowMemory
3. ConversationTokenBufferMemory
4. ConversationSymmaryMemory
5. Vector Data Memory
6. Entity Memory

### Indexes



### Chains


### Agents


## 参考连接
1. [LangChain for LLM Application Development]( https://learn.deeplearning.ai/langchain )
2. [LangChain Documentation - getting start](https://python.langchain.com/en/latest/getting_started/getting_started.html)
2. [Github-LangChain](https://github.com/hwchase17/langchain)
3. [BiliBili-吴恩达最新 ChatGPT 课程《LLM 应用程序开发的 LangChain》](https://www.bilibili.com/video/BV1zu4y1Z7mc/?p=1&vd_source=2c8ffe4f87b0f9d96f6386c909e5ac1d) 
4. [YouTuber-LangChain for LLM Application Development]( https://www.youtube.com/playlist?list=PLnZF6_W2vM2o_-a8FcC24DZNkmEe3Crrm )
5. [稀土掘金-精华笔记：吴恩达 x LangChain《基于 LangChain 的大语言模型应用开发》(上)](https://juejin.cn/post/7248599585735114789#heading-31)