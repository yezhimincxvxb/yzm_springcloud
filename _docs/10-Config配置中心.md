# 简介
```text
微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小， 因此系统中会出现大量的服务。
由于每个服务 都需要必要的配置信息才能运行，所以一套集中式的、动态的配置管理设施是必不可少的

SpringCloud ConfigServer解决的问题：
1、微服务数量较大，需同时修改配置文件，极其浪费时间
2、配置信息修改后，必须重启服务才能生效

是什么
SpringCloud Config为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置。

怎么玩
SpringCloud Config分为服务端和客户端两部分。

服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，加密/解密信息等访问接口
客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息配置服务器默认采用ait来存储配置信息，这样就有助于对环境配置进行版木管理，并目可以通过ait客户端丅且来方便的管理和访问配置内容
```
# 流程
```text
1、把配置文件放在Git Repository中。
2、Config Server从Git repository中读取配置信息。
3、其他客户端再从Config Server中加载配置文件
```
# 配置中心 config-server
Spring Cloud Config 有它的一套访问规则，我们通过这套规则在浏览器上直接访问就可以。

```text
http://localhost:6001/config-client-test.yml
http://localhost:6001/config-client-prod.yml
```
# 读取配置 config-client
```text
config-client配置必须是bootstrap.properties
spring.application.name必须对应git路径config目录下的文件名，不对应就找不到

http://localhost:6601/hello
```
实现自动刷新
```text
1、
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
2、在Controller上添加@RefreshScope注解
@RefreshScope
3、
# 暴露监控
management:
  endpoints:
    web:
      exposure:
        include: "*"
4、
curl -X POST localhost:6601/actuator/refresh
```
# 消息总线
```text
什么是总线
在微服务架构的系统中，通常会使用轻量级的消息代理来构建一个共用的消息主题，并让系统中。所有微服务实例都连接上来。由于该主题中产生的消息会被所有实例监听和消费，所以称它为消息总线。在总线上的各个实例，都可以方便地广播一些需要让其他连接在该主题上的实例都知道的消息。

基本原理
ConfigClient实例都监听MQ中同一个topic(默认是springCloudBus)。当一个服务刷新数据的时时候，它会把这个信息放入到Topic中，这样其它监听同一Topic的服务就能得到通知，然后去更新自身的配置。
```
```text
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>

spring:
  # MQ
  rabbitmq:
    host: 192.168.101.129
    port: 5672
    username: admin
    password: 123456

management:
  endpoints:
    web:
      exposure:
        include: "bus-refresh"
        #include: "*"

广播式通知所有config-client        
curl -X POST localhost:6001/actuator/bus-refresh   
或任意一个配置了bus总线的服务实例
curl -X POST localhost:6601/actuator/bus-refresh   
定点通知单一服务实例
curl -X POST localhost:6001/actuator/bus-refresh/config-client:6602
```