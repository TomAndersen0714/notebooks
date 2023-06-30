# Gradle常见问题


**Gradle启动时报错，Could not create parent directory for lock file**

通常是文件夹权限问题，Linux上直接chown，Windows上直接文件夹属性-安全，修改组和用户名权限即可。