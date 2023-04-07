# Java中Class.getResource和ClassLoader.getResource的区别



## JDK11源码展示

### Class.getResource

```java
    /**
     * Finds a resource with a given name.
     *
     * <p> If this class is in a named {@link Module Module} then this method
     * will attempt to find the resource in the module. This is done by
     * delegating to the module's class loader {@link
     * ClassLoader#findResource(String,String) findResource(String,String)}
     * method, invoking it with the module name and the absolute name of the
     * resource. Resources in named modules are subject to the rules for
     * encapsulation specified in the {@code Module} {@link
     * Module#getResourceAsStream getResourceAsStream} method and so this
     * method returns {@code null} when the resource is a
     * non-"{@code .class}" resource in a package that is not open to the
     * caller's module.
     *
     * <p> Otherwise, if this class is not in a named module then the rules for
     * searching resources associated with a given class are implemented by the
     * defining {@linkplain ClassLoader class loader} of the class.  This method
     * delegates to this object's class loader. If this object was loaded by
     * the bootstrap class loader, the method delegates to {@link
     * ClassLoader#getSystemResource}.
     *
     * <p> Before delegation, an absolute resource name is constructed from the
     * given resource name using this algorithm:
     *
     * <ul>
     *
     * <li> If the {@code name} begins with a {@code '/'}
     * (<code>'&#92;u002f'</code>), then the absolute name of the resource is the
     * portion of the {@code name} following the {@code '/'}.
     *
     * <li> Otherwise, the absolute name is of the following form:
     *
     * <blockquote>
     *   {@code modified_package_name/name}
     * </blockquote>
     *
     * <p> Where the {@code modified_package_name} is the package name of this
     * object with {@code '/'} substituted for {@code '.'}
     * (<code>'&#92;u002e'</code>).
     *
     * </ul>
     *
     * @param  name name of the desired resource
     * @return A {@link java.net.URL} object; {@code null} if no resource with
     *         this name is found, the resource cannot be located by a URL, the
     *         resource is in a package that is not
     *         {@linkplain Module#isOpen(String, Module) open} to at least the caller
     *         module, or access to the resource is denied by the security
     *         manager.
     * @throws NullPointerException If {@code name} is {@code null}
     * @since  1.1
     * @revised 9
     * @spec JPMS
     */
    @CallerSensitive
    public URL getResource(String name) {
        name = resolveName(name);

        Module thisModule = getModule();
        if (thisModule.isNamed()) {
            // check if resource can be located by caller
            if (Resources.canEncapsulate(name)
                && !isOpenToCaller(name, Reflection.getCallerClass())) {
                return null;
            }

            // resource not encapsulated or in package open to caller
            String mn = thisModule.getName();
            ClassLoader cl = getClassLoader0();
            try {
                if (cl == null) {
                    return BootLoader.findResource(mn, name);
                } else {
                    return cl.findResource(mn, name);
                }
            } catch (IOException ioe) {
                return null;
            }
        }

        // unnamed module
        ClassLoader cl = getClassLoader0();
        if (cl == null) {
            return ClassLoader.getSystemResource(name);
        } else {
            return cl.getResource(name);
        }
    }
```


### ClassLoader.getResource

```java
    /**
     * Finds the resource with the given name.  A resource is some data
     * (images, audio, text, etc) that can be accessed by class code in a way
     * that is independent of the location of the code.
     *
     * <p> The name of a resource is a '{@code /}'-separated path name that
     * identifies the resource. </p>
     *
     * <p> Resources in named modules are subject to the encapsulation rules
     * specified by {@link Module#getResourceAsStream Module.getResourceAsStream}.
     * Additionally, and except for the special case where the resource has a
     * name ending with "{@code .class}", this method will only find resources in
     * packages of named modules when the package is {@link Module#isOpen(String)
     * opened} unconditionally (even if the caller of this method is in the
     * same module as the resource). </p>
     *
     * @implSpec The default implementation will first search the parent class
     * loader for the resource; if the parent is {@code null} the path of the
     * class loader built into the virtual machine is searched. If not found,
     * this method will invoke {@link #findResource(String)} to find the resource.
     *
     * @apiNote Where several modules are defined to the same class loader,
     * and where more than one module contains a resource with the given name,
     * then the ordering that modules are searched is not specified and may be
     * very unpredictable.
     * When overriding this method it is recommended that an implementation
     * ensures that any delegation is consistent with the {@link
     * #getResources(java.lang.String) getResources(String)} method.
     *
     * @param  name
     *         The resource name
     *
     * @return  {@code URL} object for reading the resource; {@code null} if
     *          the resource could not be found, a {@code URL} could not be
     *          constructed to locate the resource, the resource is in a package
     *          that is not opened unconditionally, or access to the resource is
     *          denied by the security manager.
     *
     * @throws  NullPointerException If {@code name} is {@code null}
     *
     * @since  1.1
     * @revised 9
     * @spec JPMS
     */
    public URL getResource(String name) {
        Objects.requireNonNull(name);
        URL url;
        if (parent != null) {
            url = parent.getResource(name);
        } else {
            url = BootLoader.findResource(name);
        }
        if (url == null) {
            url = findResource(name);
        }
        return url;
    }
```


## JDK11源码解析



## Demo

```java
        // getResource()
        // NOTE: The getResource method of the Class object only accepts a file path string like
        //  "/path/to/resource_file" or "resource_file". If the string starts with "/", it will be trimmed.
        //  Otherwise, the package name of the corresponding class will be added to the beginning of the
        //  path string.
        //  And then the method will invoke getResource of ClassLoader to get the URL of resource file.
        Class<ClassAPI> classApiClass = ClassAPI.class;
        System.out.println("classApiClass.getResource(\"\") = " + classApiClass.getResource(""));
        System.out.println("classApiClass.getResource(\"/\") = " + classApiClass.getResource("/"));
        System.out.println();

        // search the file in classpath
        String filePath = "/text/test.txt";
        System.out.println("filePath = " + filePath);
        System.out.println("classAPIClass.getResource(filePath) = " + classApiClass.getResource(filePath));
        System.out.println("classAPIClass.getResource(filePath).getPath() = " + Objects.requireNonNull(classApiClass.getResource(filePath)).getPath());
        System.out.println();

        // search the file in classpath+package path
        filePath = "ClassAPI.class";
        System.out.println("filePath = " + filePath);
        System.out.println("classAPIClass.getResource(filePath) = " + classApiClass.getResource(filePath));
        System.out.println("classAPIClass.getResource(filePath).getPath() = " + Objects.requireNonNull(classApiClass.getResource(filePath)).getPath());
        System.out.println();

        // getResourceAsStream()
        // NOTE: same as getResource()
        System.out.println("classApiClass.getResourceAsStream(\"/\") = " + classApiClass.getResourceAsStream("/"));
        System.out.println("classApiClass.getResourceAsStream(\"\") = " + classApiClass.getResourceAsStream(""));
        System.out.println("classAPIClass.getResourceAsStream(filePath) = " + classApiClass.getResourceAsStream(filePath));

```


## 总结

1. 实例方法ClassLoader.getResource，其作用是在classpath下寻找对应文件，且仅支持输入基于classpath的相对路径，如果同时存在多个classpath匹配成功，则会近随机选择
2. 实例方法Class.getResource
	1. 实际上仍然是调用ClassLoader.getResource方法在classpath路径下寻找对应的文件，即在ClassLoader.getResource方法的基础上进行了一次封装
	2. Class.getResource方法，相比于ClassLoader.getResource，额外还支持在当前Package路径下检索文件，而不用声明冗长的Package名称
	3. 当输入的文件路径，是以斜杠'/'开头时，则在classpath路径下检索对应的文件；
	4. 当输入的文件路径，不是以斜杠'/'开头时，则是在classpath+Package路径下检索对应的文件