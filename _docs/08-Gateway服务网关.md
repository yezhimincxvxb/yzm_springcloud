# 简介
```text
Gateway是在Spring生态系统之上构建的API网关服务,基于Spring 5,Spring Boot 2和 Project Reactor等技术
Gateway旨在提供一种简单而有效的方式来对API进行路由，以及提供一些强大的过滤器功能，例如: 熔断、限流、重试等
```
```text
SpringCloud Gateway 是 Spring Cloud 的一个全新项目，基于 Spring2.0 和 Project Reactor 等技术开发的网关，
它旨在为5.0+SpringBoot微服务架构提供一种简单有效的统一的 API 路由管理方式
SpringCloud Gateway 作为 SpringCloud 生态系统中的网关，目标是替代 Zuu在 SpringCloud 2.0以上版本中，
没有对新版本的Zuul 2.0以上最新高性能版本进行集成，仍然还是使用的Zuul 1.x非Reactor模式的老版本，
而为了提升网关的性能，SpringCloud Gateway是基于WebFlux框实现的，而WebFlux框架底层则使用了高性能的Reactor模式通信框架Netty。
SpringCloud Gateway的目标提供统一的路由方式且基于 Filter 链的方式提供了网关基本的功能，例如: 安全，监控/指标，和限流.
```
```text
在SpringCloud Finchley 正式版之前，SpringCloud 推荐的网关是 Netflix 提供的Zuul:
1、Zuul 1.x 是一个基于阻塞I/ O 的API Gateway
2、Zuul 1.x 基于Servlet 2.5使用阻塞架构它不支持任何长连接如 WebSocket) Zuul 的设计模式和Nginx较像，每次 /0 操作都是工作线程中选择一个执行，
   请求线程被阻塞到工作线程完成，但是差别是Nginx 用C++ 实现，Zuul 用 Java 实现，而JVM 本身会有第一次加载较慢的情况，使得Zuul 的性能相对较差
3、Zuul 2.x 理念更先进，想基于Netty非阻塞和支持长连接，但SpringCloud目前还没有整合。Zuul 2.x的性能较 Zuul 1.x 有较大提在性能方面，
  根据官方提供的基准测试，SpringCloud Gateway 的 RPS (每秒请求数)是Zuul的 1.6倍
4、SpringCloud Gateway建立在 Spring Framework 5、Project Reactor 和 Spring Boot 2 之上，使用非阻塞 API
5、SpringCloud Gateway 还支持 WebSocket， 并且与Spring紧密集成拥有更好的开发体验
```
# Route路由 gateway-route
```text
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
配置
spring:
  application:
    name: gateway-route-service
  cloud:
    gateway:
      discovery:
        locator:
          # 是否启用根据服务名调用，即lb使用
          enabled: true
          # 是否允许服务吗小写，默认是大写的
          lowerCaseServiceId: true
      routes:
          # id唯一标识 
        - id: to_gateway-provider-payment
          # IP地址调用  
          uri: http://localhost:8001/
          # 断言，路由条件
          predicates:
            - Path=/payment/**
        - id: to_gateway-consumer-order
          # 服务名调用  
          uri: lb://gateway-consumer-order-service
          predicates:
            - Path=/order/**
```
访问：
http://localhost:9011/payment/hello
http://localhost:9011/order/hello等等
### 编程式配置路由规则
```text
RouteConfig.java
访问：
http://localhost:9011/local/payment/hello
http://localhost:9011/bai
http://localhost:9011/du
```
# Predicate断言 gateway-predicate
```text
spring:
  application:
    name: gateway-predicate-service
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
          lowerCaseServiceId: true
      routes:
        #Path
        - id: path_route
          uri: lb://gateway-consumer-order-service
          predicates:
            - Path=/order/hello
        #Method
        - id: method_route
          uri: lb://gateway-consumer-order-service
          predicates:
            - Method=POST
        #Header
        - id: header_route
          uri: lb://gateway-consumer-order-service
          predicates:
            #- Header=id,001 #携带header信息，相当于键值对，id为key，001为value
            - Header=id,\d+ #携带header信息，相当于键值对，id为key，\d+为value，是一个正则表达式，表达为正数
        #Cookie
        - id: cookie_route
          uri: lb://gateway-consumer-order-service
          predicates:
            #- Cookie=username,yzm #携带cookies信息，相当于键值对，username为key，yzm为value
            - Cookie=username,\d+ #携带cookies信息，相当于键值对，username为key，\d+为value
        #Host
        - id: host_route
          uri: lb://gateway-consumer-order-service
          predicates:
            - Host=**.baidu.com,**.another.com
        #Query
        - id: query_route
          uri: lb://gateway-consumer-order-service
          predicates:
            #- Query=number,123 #带查询条件，第一个是查询参数，第二个是可选的值，有参数名为number且值=123
            #- Query=number #带查询条件，有参数名为number，值无所谓
            - Query=number,\d+
        #Time
        - id: time_route
          uri: lb://gateway-consumer-order-service
          predicates:
#            - After=2020-10-30T15:00:22.432+08:00[Asia/Shanghai] #在该时区后发生
#            - Before=2020-10-30T16:00:22.432+08:00[Asia/Shanghai] #在该时区前发生
#            - Between=2020-10-30T15:00:22.432+08:00[Asia/Shanghai],2020-10-30T16:00:22.432+08:00[Asia/Shanghai] #在两个时区内发生
```
```text
curl -X POST http://localhost:9012/order/post
curl http://localhost:9012/order/header -H "id=123"
curl http://localhost:9012/order/cookie --cookie "username=yzm"
curl http://localhost:9012/order/host -H "host=yzm.baidu.com"
curl http://localhost:9012/order/query?number=111
```
# Filter过滤
常用内置过滤器
```text
spring:
  application:
    name: gateway-filter-service
  cloud:
    gateway:
      routes:
        - id: addRequestHeader
          uri: http://httpbin.org:80
          predicates:
            - Path=/get
          filters:
            # 请求头相关
            # 添加
            - AddRequestHeader=X-Request-Foo, Req
            # 移除
            - RemoveRequestHeader=User-Agent
            # 修改(没有就添加)
            - SetRequestHeader=X-Request-Foo, Req
            - SetRequestHeader=X-Forwarded-Host, location:test
            
            # 请求参数相关
            # 添加
            - AddRequestParameter=age, 22
            # 移除
            - RemoveRequestParameter=age 
            
            # 响应头相关
            # 添加
            - AddResponseHeader=X-Response-Foo, Res
            # 移除
            - RemoveResponseHeader=X-Response-Foo, Res
            # 修改
            - SetResponseHeader=X-Response-Foo, Res
            # 设置响应状态
            - SetStatus=401
            
            # url路径相关
            # 截取路径位
            - StripPrefix=2
            # 添加路径前缀
            - PrefixPath=/encoding
            # 修改路径 /a/b/{get} ==> /get
            - SetPath=/{segment}
            # 重写路径 /yzm/get ==> /get
            - RewritePath=/yzm/?(?<segment>.*), /$\{segment}
            # 重定向
            - RedirectTo=302, https://www.baidu.com
```
自定义过滤器
```text
MyGatewayFilter.java
只能通过编程式配置绑定filter，过滤器器工厂则可以通过配置文件

curl http://localhost:9013/get
```
自定义过滤器工厂
```text
MyGatewayFilterFactory.java
spring:
  application:
    name: gateway-filter
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
          lowerCaseServiceId: true
      # 对所有路由有效，MyGatewayFilterFactory 过滤器工厂不需要写全名，前缀My即可，不需要添加GatewayFilterFactory
      default-filters:
        - My=true
      routes:
        - id: myFactory_test
          uri: http://httpbin.org:80
          predicates:
            - Path=/factory/get
          filters:
            - StripPrefix=1
            - AddRequestHeader=X-Request, Yzm
            # 对单一路由有效，就近原则
#           - My=false

curl http://localhost:9013/factory/get
```
全局过滤器
```text
curl http://localhost:9013/factory/get
无法访问
curl http://localhost:9013/factory/get?token
无法访问
curl http://localhost:9013/factory/get?token=123
正常访问
```
# 重试
```text

      routes:
        # 重试
        - id: gateway_retry
          uri: lb://gateway-consumer-order-service
          predicates:
            - Path=/retry/*
          filters:
            - StripPrefix=1
            - name: Retry
              args:
                retries: 3
                series: SERVER_ERROR
                methods: GET,POST

Retry：RetryGatewayFilter是Spring Cloud Gateway对请求重试提供的一个GatewayFilter Factory                
retries：重试次数
series: SERVER_ERROR，下游服务报5XX系列的错误触发重试机制
methods：重试的HTTP方法 

访问：http://localhost:9014/order/retry/1               
访问：http://localhost:9014/order/retry/-1               
```
# 熔断
```text
<!-- hystrix -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>

        # 熔断
        - id: gateway_hystrix
          uri: lb://gateway-provider-payment-service
          predicates:
            - Path=/payment/hystrix/**
          filters:
            - name: Hystrix
              args:
                # 调用网关服务的fallback方法处理
                #name: localFallback
                #fallbackUri: forward:/fallback
                # 调用order服务的fallback方法处理
                name: otherFallback
                fallbackUri: forward:/order/fallback
        - id: gateway_fallback
          uri: lb://gateway-consumer-order-service
          predicates:
            - Path=/order/fallback
            
访问：http://localhost:9014/payment/hystrix            
```
# 限流
```text
        - id: gateway_limit
          uri: lb://gateway-consumer-order-service
          predicates:
            - Path=/limit/**
          filters:
            - StripPrefix=1
            - name: RequestRateLimiter
              args:
                key-resolver: '#{@remoteAddrKeyResolver}'
                redis-rate-limiter.replenishRate: 1 #令牌桶每秒填充平均速率
                redis-rate-limiter.burstCapacity: 3 #令牌桶容量

访问： http://localhost:9014/ip/get                
访问： http://localhost:9014/limit/order/hello
```