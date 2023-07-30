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

### Chains

#### LLM Chain

LLM Chain 是最简单的 Chain，可以将 LLM 与 Pormpt 组合在一起使用，简化用户的操作。

#### Sequential Chain

Sequential Chain 可以将各种类型的 Chain 拼接成为一个序列，上一个 Chain 的输出即为下一个 Chain 的输入。

Sequential Chain 有两个子类型：
1. SimpleSequentialChain：其中的每个子 Chain 仅支持单个输入输出
2. SequentialChain：其中的每个子 Chain 支持多个输入输出的

#### Router Chain

在多个 Chain 之间提供输出路由功能，用于链接不同的 Chain。


### Indexes



### Agents


#### Question and Answer Over Documents

问题现状：
LLM 一次性只能查询上千个单词，如果有非常大的文档该如何回答问题呢？

规律：
相似的文本，其在映射到某向量空间时，对应向量之间的某种空间距离也相近

解决思路：
1. 文档切分，使用 Embeddings 技术将文本向量化
2. 使用向量数据库（Vector Database）存储这些文本向量，并构建索引
3. 通过 Vector Database 与查询问题文本相匹配，获取文档中的相似的内容
4. 将这些内容作为上下文和原始问题，一起提交给 LLM，进而获得近似答案

#### Stuff Method

将部分相关的文档内容和问题一起发送给 LLM

#### Map Reduce Method

将所有的相关的文档内容和问题依次发送给 LLM，最后再通过 LLM 将这些多次的回答进行汇总

#### Refine Method

将所有的相关的文档内容和问题依次发送给 LLM，与 Map Reduce Method 不同的是 Refine Method 的每次提问，都会带上上一次问题的回答结果，即会将所有相关的文档内容都迭代后，才会得到最终答案。


#### Map Re-rank Method

将所有的相关的文档内容和问题依次发送给 LLM，并给每次回答判分，最终选取分数最高的那个回答。


## 参考连接
1. [LangChain for LLM Application Development]( https://learn.deeplearning.ai/langchain )
2. [LangChain Documentation - getting start](https://python.langchain.com/en/latest/getting_started/getting_started.html)
3. [Github-LangChain](https://github.com/hwchase17/langchain)
4. [BiliBili-吴恩达最新 ChatGPT 课程《LLM 应用程序开发的 LangChain》](https://www.bilibili.com/video/BV1zu4y1Z7mc/?p=1&vd_source=2c8ffe4f87b0f9d96f6386c909e5ac1d) 
5. [稀土掘金-精华笔记：吴恩达 x LangChain《基于 LangChain 的大语言模型应用开发》(上)](https://juejin.cn/post/7248599585735114789#heading-31)