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
# Route路由
# Predicate断言
# Filter过滤