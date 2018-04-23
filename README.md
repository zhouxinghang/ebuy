## 工程说明

### solr服务在  D:\download\apache-tomcat-7.0.78  tomcat服务器中

### activemq，zookeeper,redis，ftp在centos7中，windows虚拟机中 
    现已放在zhouxinghang服务器中

### mysql在windows中

### dubbo-admin在F:\workspace下

### nginx 在windows F:\workspace下
    nginx -c nginx.conf 带配置文件启动
    
### 通过两个tomcat。一个是服务提供者，一个是服务消费者，用路径来区分每个服务。

### solr放在了单独的tomcat中，solr很容易出问题，且不知道问题出在何处，待后续解决
    mac的workspace/myworkspace/tomcat7下面，端口号8888

### 本想把Producter和Consumer独立开来（这样先启动Producter，后启动Consumer），但是会出错。很奇怪

### 利用路径来区分每个服务，真的特别麻烦，涉及到的一系列路径都需要修改。

### 使用的是视图解析器拦截.jsp请求，没有使用ftl模板
```xml
<!-- 视图解析器 -->
<bean
    class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/jsp/" />
    <property name="suffix" value=".jsp" />
</bean>
```    

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
    
### 新增商品没有同步到索引库
    activemq是正常的，消息监听者能正常运行，报空指针
    
```java
    2018-04-15 12:00:15ItemMessageListener.onMessage.itemAddTopic.new item : 152376481467081
    2018-04-15 12:00:16Execution of JMS message listener failed, and no ErrorHandler has been set.
    java.lang.NullPointerException
    	at com.ebuy.search.listener.ItemAddMessageListener.onMessage(ItemAddMessageListener.java:43)
    	at org.springframework.jms.listener.AbstractMessageListenerContainer.doInvokeListener(AbstractMessageListenerContainer.java:746)
```

### 图片服务器部署异常
    阿里云安全组未部署
    上传文件路径不对
    上传文件没有write权限
    

### dubbo序列化、反序列化异常
```java
2018-04-15 10:41:59 [DUBBO] Decode argument failed: expected binary at 0x41 [B ([B@2a0de55d), dubbo version: 2.5.3, current host: 127.0.0.1
com.alibaba.com.caucho.hessian.io.HessianProtocolException: expected binary at 0x41 [B ([B@2a0de55d)
	at com.alibaba.com.caucho.hessian.io.Hessian2Input.error(Hessian2Input.java:2720)
	at com.alibaba.com.caucho.hessian.io.Hessian2Input.expect(Hessian2Input.java:2691)
	at com.alibaba.com.caucho.hessian.io.Hessian2Input.readInputStream(Hessian2Input.java:2562)
	at com.alibaba.com.caucho.hessian.io.InputStreamDeserializer.readObject(InputStreamDeserializer.java:65)
	at com.alibaba.com.caucho.hessian.io.Hessian2Input.readObject(Hessian2Input.java:1696)
	at com.alibaba.dubbo.common.serialize.support.hessian.Hessian2ObjectInput.readObject(Hessian2ObjectInput.java:94)
	at com.alibaba.dubbo.rpc.protocol.dubbo.DecodeableRpcInvocation.decode(DecodeableRpcInvocation.java:109)
	at com.alibaba.dubbo.rpc.protocol.dubbo.DecodeableRpcInvocation.decode(DecodeableRpcInvocation.java:71)
	at com.alibaba.dubbo.rpc.protocol.dubbo.DubboCodec.decodeBody(DubboCodec.java:137)
	at com.alibaba.dubbo.remoting.exchange.codec.ExchangeCodec.decode(ExchangeCodec.java:126)
	at com.alibaba.dubbo.remoting.exchange.codec.ExchangeCodec.decode(ExchangeCodec.java:87)
	at com.alibaba.dubbo.rpc.protocol.dubbo.DubboCountCodec.decode(DubboCountCodec.java:46)
	at com.alibaba.dubbo.remoting.transport.netty.NettyCodecAdapter$InternalDecoder.messageReceived(NettyCodecAdapter.java:134)
	at org.jboss.netty.channel.SimpleChannelUpstreamHandler.handleUpstream(SimpleChannelUpstreamHandler.java:70)
	at org.jboss.netty.channel.DefaultChannelPipeline.sendUpstream(DefaultChannelPipeline.java:564)
	at org.jboss.netty.channel.DefaultChannelPipeline.sendUpstream(DefaultChannelPipeline.java:559)
	at org.jboss.netty.channel.Channels.fireMessageReceived(Channels.java:268)
	at org.jboss.netty.channel.Channels.fireMessageReceived(Channels.java:255)
	at org.jboss.netty.channel.socket.nio.NioWorker.read(NioWorker.java:88)
	at org.jboss.netty.channel.socket.nio.AbstractNioWorker.process(AbstractNioWorker.java:109)
	at org.jboss.netty.channel.socket.nio.AbstractNioSelector.run(AbstractNioSelector.java:312)
	at org.jboss.netty.channel.socket.nio.AbstractNioWorker.run(AbstractNioWorker.java:90)
	at org.jboss.netty.channel.socket.nio.NioWorker.run(NioWorker.java:178)
	at org.jboss.netty.util.ThreadRenamingRunnable.run(ThreadRenamingRunnable.java:108)
	at org.jboss.netty.util.internal.DeadLockProofWorker$1.run(DeadLockProofWorker.java:42)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
2018-04-15 10:41:59 [DUBBO] Got unchecked and undeclared exception which called by 192.168.1.131. service: com.ebuy.service.PicService, method: uploadFile, exception: java.lang.NullPointerException: null, dubbo version: 2.5.3, current host: 127.0.0.1
java.lang.NullPointerException
	at org.apache.commons.net.io.Util.copyStream(Util.java:100)
	at org.apache.commons.net.ftp.FTPClient._storeFile(FTPClient.java:653)
	at org.apache.commons.net.ftp.FTPClient.__storeFile(FTPClient.java:624)
	at org.apache.commons.net.ftp.FTPClient.storeFile(FTPClient.java:1976)
	at com.ebuy.common.util.FtpUtil.uploadFile(FtpUtil.java:69)
	at com.ebuy.service.impl.PicServiceImpl.uploadFile(PicServiceImpl.java:32)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:333)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:190)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)
	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:99)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:282)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:213)
	at com.sun.proxy.$Proxy41.uploadFile(Unknown Source)
	at com.alibaba.dubbo.common.bytecode.Wrapper2.invokeMethod(Wrapper2.java)
	at com.alibaba.dubbo.rpc.proxy.javassist.JavassistProxyFactory$1.doInvoke(JavassistProxyFactory.java:46)
	at com.alibaba.dubbo.rpc.proxy.AbstractProxyInvoker.invoke(AbstractProxyInvoker.java:72)
	at com.alibaba.dubbo.rpc.protocol.InvokerWrapper.invoke(InvokerWrapper.java:53)
	at com.alibaba.dubbo.rpc.filter.ExceptionFilter.invoke(ExceptionFilter.java:64)
	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91)
	at com.alibaba.dubbo.monitor.support.MonitorFilter.invoke(MonitorFilter.java:75)
	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91)
	at com.alibaba.dubbo.rpc.filter.TimeoutFilter.invoke(TimeoutFilter.java:42)
	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91)
	at com.alibaba.dubbo.rpc.protocol.dubbo.filter.TraceFilter.invoke(TraceFilter.java:78)
	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91)
	at com.alibaba.dubbo.rpc.filter.ContextFilter.invoke(ContextFilter.java:60)
	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91)
	at com.alibaba.dubbo.rpc.filter.GenericFilter.invoke(GenericFilter.java:112)
	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91)
	at com.alibaba.dubbo.rpc.filter.ClassLoaderFilter.invoke(ClassLoaderFilter.java:38)
	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91)
	at com.alibaba.dubbo.rpc.filter.EchoFilter.invoke(EchoFilter.java:38)
	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91)
	at com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol$1.reply(DubboProtocol.java:108)
	at com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeHandler.handleRequest(HeaderExchangeHandler.java:84)
	at com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeHandler.received(HeaderExchangeHandler.java:170)
	at com.alibaba.dubbo.remoting.transport.DecodeHandler.received(DecodeHandler.java:52)
	at com.alibaba.dubbo.remoting.transport.dispatcher.ChannelEventRunnable.run(ChannelEventRunnable.java:82)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
```
- 原因
    由于InputStream不能被序列化导致的异常，会引起服务NullpointerException
- 解决
    1.封装InputStream，实现序列化。不能解决
    2.改为字节数组上传，可以

### cart.ebuy.com 会跳转到ww25.cart.ebuy.com   ip就不会这样

-原因  
    cart.ebuy.com是一个本来存在的域名，且本机/etc/hosts没有加入cart 



## 服务端口
    
### 1.ZK端口
    1、2181：对cline端提供服务
    
    2、3888：选举leader使用
    
    3、2888：集群内机器通讯使用（Leader监听此端口）
    zkServer.sh {start|stop|status|restart}
    zkServer start 
     
### 2.activemq
    1、8161：管理控制台   user：admin  passwd：admin
    2、61616：消息通讯
    启动：activemq start
    
### 3.redis
    6379
    redis-server
    redis-cli shutdown  
    
    
### 4.nginx
    sudo nginx
    sudo ngixn -s reload
    
    
## 进度

###  manager管理模块商品规格参数管理开发 doing
    tb_item_param ，tb_item_param_item
    
### 商品详情页 左侧小广告
    ```
    <div class="left">
    		<div id="miaozhen7886" class="m"><a href="http://c.nfa.jd.com/adclick?sid=2&amp;cid=163&amp;aid=817&amp;bid=7853&amp;unit=69570&amp;advid=156740&amp;guv=&amp;url=http://sale.jd.com/act/IFkpQYSVnG1Jet.html" target="_blank"><img data-img="2" width="211" height="261" src="http://image.zhouxinghang.com/hope.jpg" class="loading-style2"></a></div>
    		<div id="miaozhen7886" class="m"><a href="http://c.nfa.jd.com/adclick?sid=2&amp;cid=163&amp;aid=817&amp;bid=7853&amp;unit=69570&amp;advid=156740&amp;guv=&amp;url=http://sale.jd.com/act/IFkpQYSVnG1Jet.html" target="_blank"><img data-img="2" width="211" height="261" src="http://image.zhouxinghang.com/hope.jpg" class="loading-style2"></a></div>
    </div><!--left end-->
    ```
    
### 图片服务器开发 done
    利用nginx+vsftpd搭建图片服务器
    
### 商品详情页，商品类目开发
    新增商品的类目（不包含父类目）
    
### 商品详情页，新增微博分享功能 done
    item.jsp页面，引入微博的js，添加自己的appkey
    
     
### 登录注册页面优化 todo

### 商品详情评价功能 todo

### 添加mycat数据库中间件  todo
