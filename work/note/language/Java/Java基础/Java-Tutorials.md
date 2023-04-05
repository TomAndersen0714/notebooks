# Java Tutorials

The Java Tutorials have been written for JDK 8. Examples and practices described in this page don't take advantage of improvements introduced in later releases and might use technology no longer available.
https://docs.oracle.com/javase/tutorial/index.html


## 1. Trails Covering the Basics


### Getting Started


#### The Java Technology Phenomenon

##### About the Java Technology

**The Java Programming Language**

In the Java programming language, all source code is first written in plain text files ending with the `.java` extension. Those source files are then compiled into `.class` files by the `javac` compiler.

A .class file does not contain code that is native to your processor; it instead contains bytecodes — the machine language of the Java Virtual Machine (Java VM).

The `java` launcher tool then runs your application with an instance of the Java Virtual Machine.

Because the Java VM is available on many different operating systems, the same .class files are capable of running on every platform.


**The Java Platform**

A `platform` is the hardware or software environment in which a program runs. The Java platform differs from most other platforms in that it's a software-only platform that runs on top of other hardware-based platforms.

The `Java platform` has two components:
- The Java Virtual Machine (`JVM`)
- The Java Application Programming Interface (`API`)

The API is a large collection of ready-made software components that provide many useful capabilities. It is grouped into libraries of related classes and interfaces; these libraries are known as `packages`.

As a platform-independent environment, the Java platform can be a bit slower than `native code`. However, advances in compiler and virtual machine technologies are bringing performance close to that of native code without threatening portability.


##### What Can Java Technology Do?

The general-purpose, high-level Java programming language is a powerful software platform. Every full implementation of the Java platform gives you the following features:
- Development Tools
- Application Programming Interface (API)
- Deployment Technologies
- User Interface Toolkits
- Integration Libraries

##### How Will Java Technology Change My Life?

Get started quickly
Write less code
Write better code
Develop programs more quickly
Avoid platform dependencies
Write once, run anywhere
Distribute software more easily



#### The "Hello World!" Application 


#### A Closer Look at "Hello World!"


#### Common Problems (and Their Solutions)


### Learning the Java Language


#### Object-Oriented Programming Concepts


#### Language Basics


#### Classes and Objects


#### Annotations


#### Interfaces and Inheritance


#### Numbers and Strings


#### Generics


#### Packages


### Essential Java Classes

#### Exceptions

#### Basic I/O


##### I/O Streams

An `I/O Stream` represents an input source or an output destination. A stream can represent many different kinds of sources and destinations, including disk files, devices, other programs, and memory arrays.

Streams support many different kinds of data, including simple bytes, primitive data types, localized characters, and objects. Some streams simply pass on data; others manipulate and transform the data in useful ways.

No matter how they work internally, all streams present the same simple model to programs that use them: A stream is a sequence of data.


###### Byte Streams

Programs use `byte streams` to perform input and output of `8-bit bytes`. All byte stream classes are descended from InputStream and OutputStream.


##### File I/O (Featuring NIO.2)



#### Concurrency

#### The Platform Environment

#### Regular Expressions

### Collections


### Date-Time APIs


### Deployment


### Preparation for Java Programming Language Certification 



## 2. Creating Graphical User Interfaces


## 3. Specialized Trails and Lessons



### JavaBeans