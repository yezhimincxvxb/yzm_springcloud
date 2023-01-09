# Feign
```text
Feign旨在使编写Java Http客户端变得更容易。
前面在使用Ribbon+RestTemplate时，利用RestTemplate对http请求的封装外理，形成了一套模版化的调用方法。
但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务的调用。
所以，Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。
在Feign的实现下，我们只需创建一个接口并使用注解的方式来配置它(以前是Dao接口上面标注Mapper注解,现在是一个微服务接口上面标注一个 Feign注解即可)，
即可完成对服务提供方的接口绑定，简化了使用Spring cloud Ribbon时，自动封装服务调用客户端的开发量。

Feign集成了Ribbon
利用Ribbon维护了Payment的服务列表信息，并且通过轮询实现了客户端的负载均衡。
而与Ribbon不同的是，通过feign只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用
```
# OpenFeign
```text
Feign是Spring Cloud组件中的一个轻量级RESTFul的HTTP服务客户端 Feign内置了Ribbon，用来做客户端负载均衡，去调用服务注册中心的服务。
Feign的使用方式是:使用Feign的注解定义接口，调用这个接口就可以调用服务注册中心的服务

OpenFeign是Spring Cloud 在Feign的基础上支持了SpringMVC的注解如@RequesMapping等等。
OpenFeign的@FeignClient可以解析 SpringMVC的@RequestMapping注解下的接口，并通过动态代理的方式产生实现类，实现类中做负载均衡并调用其他服务。
```
# 配置流程
```text
1、引入依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

2、开启OpenFeign
@SpringBootApplication
@EnableFeignClients
public class FeignApplication {}

3、调用服务
@FeignClient(value = "PROVIDER-PAYMENT-SERVICE", path = "/provider/payment")
public interface OrderFeign {

    @GetMapping("/hello")
    CommonResult<String> hello();

}
```
# 日志打印功能
```text
NONE:默认的，不显示任何日志;
BASIC:仅记录请求方法、URL、响应状态码及执行时间;
HEADERS:除了 BASIC 中定义的信息之外，还有请求和响应的头信息;
FULL:除了 HEADERS 中定义的信息之外，还有请求和响应的正文及元数据。

@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }
}

logging:
  level:
    com.yzm.openfeign.feign.OrderFeign: debug
```
# 超时
```text
#设置feign客户端超时时间(OpenFeign默认支持ribbon)，默认1秒
ribbon:
  #指的是建立连接所用的时间，适用于网络状况正常的情况下，两端连接所用的时间
  ReadTimeout: 5000
  #指的是建立连接后从服务器读取到可用资源所用的时间
  ConnectTimeout: 5000
或者  
feign:
  client:
    config:
      # default指的所有被加载的默认FeignClient实现的服务配置都生效
      default:
        readTimeout: 5000
        connectTimeout: 5000
      # 只对@FeignClient(contextId="payment-core")的有效  
      payment-core:
        connectTimeout: 3000
        readTimeout: 3000
```
