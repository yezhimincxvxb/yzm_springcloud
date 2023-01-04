# 什么是服务治理
```text
Spring Cloud 封装了 Netflix 公司开发的 Eureka 模块来实现服务治理

在传统的rpc远程调用框架中，管理每个服务与服务之间依赖关系比较复杂，管理比比较复杂，所以需要使用服务治理，
管理服务于服务之间依赖关系，可以实现服务调用、负载均衡、容错等，实现服务发现与注册。
```
# 什么是服务注册与发现
```text
Eureka采用了CS的设计架构，Eureka Server 作为服务注册功能的服务器，它是服务注册中心。
而系统中的其他电微服务，使用 Eureka的客户端连接到 Eureka Server并维持心跳连接。这样系统的维护人员就可以通过 Eureka Server 来监控系统中各个微服务是否正常运行。
在服务注册与发现中，有一个注册中心。当服务器启动的时候，会把当前自己服务器的信息 比如 服务地址通讯地地址等以别名方式注册到注册中心上。
另一方(消费者|服务提供者)，以该别名的方式去注册中心上获取到实际的服务通讯地址，然后再实现本地RPC调用
RPC远程调用框架核心设计思想:在于注册中心，因为使用注册中心管理每个服务与服务之间的一个依赖关系(服务治理概念)。
在任何rpc远程框架中，都会有一个注册中心(存放服务地址相关信息(接口地址))
```
# Eureka包含两个组件:Eureka Server和Eureka Client
```text
Eureka Server提供服务注册服务
各个微服务节点通过配置启动后，会在EurekaServer中进行注册，这样EurekaServer中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观看到。

EurekaClient通过注册中心进行访问
是一个Java客户端，用干简化Eureka Server的交互，客户端同时也具备一个内置的，使用轮询(round-robin)负载算法的负载均衡器。
在应用启动后，将会向Eureka Server发送心跳(默认周期为30秒)。
如果Eureka Server在多个心跳周期内没有接收到其个节点的心跳，EurekaServer将会从服务注册表中把这个服务节点移除(默认90秒)
```
# 单机模式
```text
添加依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>

启动Eureka Server
@SpringBootApplication
@EnableEurekaServer
public class Eureka7001Application {}

eureka:
  instance:
    hostname: localhost #eureka服务端的实例名称
  client:
    #false表示不向注册中心注册自己。
    register-with-eureka: false
    #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    fetch-registry: false
    service-url:
      #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址。
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/    
```
浏览器访问：http://localhost:7001/

# 集群模式
```text
spring:
  profiles:
    active: peer1

---
spring:
  profiles: peer1
  application:
    name: eureka-server7002

server:
  port: 7002

eureka:
  instance:
    prefer-ip-address: true
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://localhost:7003/eureka/,http://localhost:7004/eureka/
---
spring:
  profiles: peer2
  application:
    name: eureka-server7003

server:
  port: 7003

eureka:
  instance:
    prefer-ip-address: true
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://localhost:7002/eureka/,http://localhost:7004/eureka/
 
---
spring:
  profiles: peer3
  application:
    name: eureka-server7004

server:
  port: 7004

eureka:
  instance:
    prefer-ip-address: true
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://localhost:7002/eureka/,http://localhost:7003/eureka/    
```
浏览器访问：http://localhost:7002/、http://localhost:7003/、http://localhost:7004/
或 http://eureka7004.com:7004/