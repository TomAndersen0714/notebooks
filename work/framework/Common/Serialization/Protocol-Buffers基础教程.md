# Protocol Buffers 基础教程


## Introduction

Protocol Buffers是一种由Google开源的，与语言无关（language-neutral）、与平台无关（platform-neutral）的序列化框架，提供了一种通用、轻量且高效的数据序列化和反序列化方式。

Protocol buffers，提供了一种接口定义语言Interface Definition Language (IDL)，可以用于定义数据结构（`Messages`），以及对应的接口方法签名（`Services`），支持向前、向后兼容，而这些代码通常保存`.proto`文件中。

Protocol Buffers代码使用protoc编译器（`protoc`）进行编译，可以生成各种编程语言的运行时库或者说接口，以供代码调用，由于各个语言都使用同一份protocol buffers，故而实现了跨语言的特性。

PS：向前兼容（forward-compatible），指的是新版本中可以使用历史版本，向后兼容（backward-compatible）指的是历史版本，可以使用之后的新版本。


### Terminology

#### Proto File 

`.proto`


#### Message

使用proto编译器`protoc`来编译`.proto`文件，以生成对应编程语言的源代码，用于处理protocol buffer对象。其中主要包含基本的accessor和setter方法，以及序列化和反序列化方法。



The following code samples show you an example of this flow in Java. As shown earlier, this is a `.proto` definition:
```bash
syntax = "proto3";

message SearchRequest {
  string query = 1;
  int32 page_number = 2;
  int32 results_per_page = 3;
}
```

Compiling this `.proto` file creates a `Builder class` that you can use to create new instances, as in the following Java code: 
```Java
Person john = Person.newBuilder()
    .setId(1234)
    .setName("John Doe")
    .setEmail("jdoe@example.com")
    .build();

output = new FileOutputStream(args[0]);
john.writeTo(output);
```

You can then deserialize data using the methods protocol buffers creates in other languages, like `C++`:
```C++
Person john;
fstream input(argv[1], ios::in | ios::binary);
john.ParseFromIstream(&input);
int id = john.id();
std::string name = john.name();
std::string email = john.email();
```

#### Field


#### Service

https://protobuf.dev/programming-guides/proto3/#services



### What Problems do Protocol Buffers Solve?

现有的数据序列化和反序列化框架下存在的性能、扩展性、跨平台和跨语言支持等问题


### What are the Benefits of Using Protocol Buffers?

1. Compact data storage
2. Fast parsing
3. Availability in many programming languages
4. Optimized functionality through automatically-generated classes
5. 扩展性（Expansibility）和兼容性（compatibility）


### When are Protocol Buffers not a Good Fit?

Protocol buffers do not fit all data. In particular:
1. **单个Protocol buffer message的大小不建议超出MB级别**：Protocol buffers tend to assume that entire messages can be loaded into memory at once and are not larger than an object graph. For data that exceeds a few megabytes, consider a different solution; when working with larger data, you may effectively end up with several copies of the data due to serialized copies, which can cause surprising spikes in memory usage.
2. **同样的protocol buffers对象可能会序列化成不同的二进制格式，因此不能通过对比二进制数据来判断对象是否相同**：When protocol buffers are serialized, the same data can have many different binary serializations. You cannot compare two messages for equality without fully parsing them.
3. **Protocol buffer message不会被压缩**：Messages are not compressed. While messages can be zipped or gzipped like any other file, special-purpose compression algorithms like the ones used by JPEG and PNG will produce much smaller files for data of the appropriate type.
4. **Protocol buffer message不适合存储大型浮点型数组**：Protocol buffer messages are less than maximally efficient in both size and speed for many scientific and engineering uses that involve large, multi-dimensional arrays of floating point numbers. For these applications, FITS and similar formats have less overhead.
5. **Protocol buffers不适合非面向对象的编程语言（non-object-oriented languages）**：Protocol buffers are not well supported in non-object-oriented languages popular in scientific computing, such as Fortran and IDL.
6. **Protocol buffers message并不提供自解释能力**：Protocol buffer messages don't inherently self-describe their data, but they have a fully reflective schema that you can use to implement self-description. That is, you cannot fully interpret one without access to its corresponding .proto file.
7. **Protocol buffers并不属于特定的标准组织**：Protocol buffers are not a formal standard of any organization. This makes them unsuitable for use in environments with legal or other requirements to build on top of standards.


### How do Protocol Buffers Work?

![](resources/images/Pasted%20image%2020230603233616.png)


### What's Generated From Your .proto?
https://protobuf.dev/programming-guides/proto3/#generated


## Installation

https://github.com/protocolbuffers/protobuf#protocol-compiler-installation

### For C++ users

compile the source code
https://github.com/protocolbuffers/protobuf/blob/main/src/README.md

### For non-C++ users

Just download the pre-built binary file in zip



## Programming Guides (proto 3)

https://protobuf.dev/programming-guides/


### Language Guide (proto2)


### Language Guide (proto3)


#### Message

https://protobuf.dev/programming-guides/proto3/#simple


##### Scalar Data Types
https://protobuf.dev/programming-guides/proto3/#scalar

##### Additional Data Types
https://protobuf.dev/overview/#common-types


#### Service

https://protobuf.dev/programming-guides/proto3/#services

## Examples

https://protobuf.dev/getting-started/


## Reference Guides
https://protobuf.dev/reference/

## 参考链接
1. [Protocol Buffers Documentation](https://protobuf.dev/)