# 服务EurekaClient配置
```text
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PaymentApplication {}

eureka:
  client:
    serviceUrl:
      # 单机版
      defaultZone: http://localhost:7001/eureka/
      # 集群版
      # defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/,http://eureka7004.com:7004/eureka/
  instance:
    // 实例名称
    instance-id: consumer-order
    #访问路径可以显示IP地址
    prefer-ip-address: true
```