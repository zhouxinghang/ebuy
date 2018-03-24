### solr服务在  D:\download\apache-tomcat-7.0.78  tomcat服务器中

### activemq，zookeeper,redis，ftp在centos7中，windows虚拟机中 


### mysql在windows中

### dubbo-admin在F:\workspace下

### nginx 在windows F:\workspace下


## bug纪录

### 1. 8082附近端口被占用，显示unknow request

```
这个是被qq的相关服务占用了。

1、首先查找到占用8080端口的进程号PID是多少

CMD>netstat -ano | findstr 8080

这个命令输出的最后一列表示占用8080端口的进程号是多少，假设为1234

2、kill掉这个进程

CMD>taskkill /F /PID 1234
```

### 2.将localhost:port改为域名之后，登录时保存的cookie，在别的系统中没有


> http://blog.csdn.net/cj_kris/article/details/2017079 
> 关于二级域名cookie的问题


- 在CookieUtils中添加下面代码

```java
Cookie cookie = new Cookie(cookieName, cookieValue);
cookie.setDomain(".ebuy.com");
```

### 3.在计算时，需要校验用户登录，添加购物车，查看购物车不需要

添加修改记录测试
