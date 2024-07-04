## IDEA Maven 基础教程


## 常见问题


问题：Maven 项目编译完成后，点击 Run 时，抛异常 `ClassNotFoundException`

原因：Maven 项目的配置文件 pom.xml 中对应的 Dependency 中的 scope 被命名为了非 `compile` 或 `runtime` 的 scope，声明为其他的 scope 的 Dependency 在编译时是不会一起打包的 
解决方案：在 IDEA Run Configuration 中，设置 `Modify options - Add dependencies with 'provided' scope” to classpath`，这样就能避免 Maven 项目中 scope 为 provided 的 dependency，因为打包时未被引入，在 IDEA 中直接运行时出现 ClassNotFoundException 的异常。