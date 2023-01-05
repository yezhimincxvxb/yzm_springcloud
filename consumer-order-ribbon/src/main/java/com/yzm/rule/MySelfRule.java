package com.yzm.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 替换默认负载策略，不能在@ComponentScan当前包或子包扫描路径下
 */
@Configuration
public class MySelfRule {

    @Bean
    IRule myRule() {
        // 随机
        return new RandomRule();
    }
}
