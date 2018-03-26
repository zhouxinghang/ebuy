### solr服务在  D:\download\apache-tomcat-7.0.78  tomcat服务器中

### activemq，zookeeper,redis，ftp在centos7中，windows虚拟机中 
    现已放在zhouxinghang服务器中

### mysql在windows中

### dubbo-admin在F:\workspace下

### nginx 在windows F:\workspace下
    nginx -c nginx.conf 带配置文件启动
    
### 通过两个tomcat。一个是服务提供者，一个是服务消费者，用路径来区分每个服务。

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


### 4.启动虚拟机报内部错误
很有可能是VM服务没有启动（可能是没有设置为开机自启或认为关闭），你可以进入 “服务”将VMAuthdService、VMnetDHCP、VMware NAT Service、VMwareHostd（VMware Workstation Server）、VMUSBArbService 这5个服务启动即可。

### 5.部署项目的时候，要将原来的war包删除

### 6.部署项目到线上，用路径区分每个服务。这样会出现路径的错误
    /images/blank.gif 改为
    /images/blank.gif
### 
    https://blog.csdn.net/li396864285/article/details/54574955

## 服务端口
    
### 1.ZK端口
    1、2181：对cline端提供服务
    
    2、3888：选举leader使用
    
    3、2888：集群内机器通讯使用（Leader监听此端口）
    zkServer.sh {start|stop|status|restart}
     
### 2.activemq
    1、8161：管理控制台
    2、61616：消息通讯
    启动：activemq start
    
### 3.redis
    6379
    redis-server
    redis-cli shutdown  
    