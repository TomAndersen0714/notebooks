# Spring Annotation 注解基础教程

Spring Framework:
@Value, @Bean, @Configuration, @Service, @Autowired

@Transactional:
- @Transactional 是 Spring 框架中基于 AOP 的一个注解，用于在方法级别控制事务。这个注解告诉 Spring 框架在方法执行过程中，使用事务管理功能。如果该方法正常执行，则事务将被提交；如果方法抛出异常，则事务将被回滚。
- [Using @Transactional :: Spring Framework](https://docs.spring.io/spring-framework/reference/data-access/transaction/declarative/annotations.html)
- [Spring--@Transactional解析\_spring transactional注解原理-CSDN博客](https://blog.csdn.net/qq_33807380/article/details/135539199)
- [Spring注解@Transactional - 角刀牛Java - 博客园](https://www.cnblogs.com/jiaodaoniujava/p/17661395.html)

Spring MVC:
- Controller
	- @Controller
	- @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping
	- @RequestMapping:
		- `@RequestMapping (value="/path", method=RequestMethod.GET)` 是用于将被修饰的方法绑定到指定接口上
	- @RequestBody:
		- @RequestBody 注解修饰的参数，可以将 HTTP Request Body 反序列化为对应的 Java 对象，一般用于处理 `Content-Type=application/json` 类型的请求体
		- [@RequestBody :: Spring Framework](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/requestbody.html)
	- @RequestParam:
		- @RequestParam 注解主要用于从 HTTP Request Body 的 **URI 查询参数（query parameters）、表单数据（form-data）** 中获取参数值
		- [@RequestParam :: Spring Framework](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/requestparam.html)
	- @ResponseBody:
		- @ResponseBody 注解的作用是将 Controller 层方法的返回结果直接序列化到 HTTP Response Body 中
		- [@ResponseBody :: Spring Framework](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/responsebody.html)

## 参考链接

1. [Spring Framework Annotations - Spring Framework Guru](https://springframework.guru/spring-framework-annotations/)