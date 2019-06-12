package com.xcn.spring.javaconfig.config;

import com.xcn.spring.javaconfig.fg.FgHelloWordService2;
import com.xcn.spring.javaconfig.service.HelloService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DI 配置类
 * 1. 注解方式: 只需要 @Configuration 和 @ComponentScan 即可,@Bean对应的方法不需要有
 * 2. JavaConfig方式: 只需要@Configuration 和@Bean对应的方法。@ComponentScan 不需要有。@Bean方法需要实现注入的方式
 *
 * @author: xupeng.guo
 * @date: 2019/6/11
 * @description
 */
@Configuration // 声明此类是一个配置类
public class Config {

    @Bean //说明返回是一个bean
    public HelloService2 helloService2() {
        return new HelloService2();
    }

    @Bean
    public FgHelloWordService2 fgHelloWordService2() {
        FgHelloWordService2 func = new FgHelloWordService2();
        func.setHelloService2(helloService2());
        return func;
    }
}
