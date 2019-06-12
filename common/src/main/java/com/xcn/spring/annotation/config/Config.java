package com.xcn.spring.annotation.config;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

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
@EnableAspectJAutoProxy // 注解方式设置aop
@PropertySource("classpath:common.properties")
@EnableAsync
@EnableScheduling
//@Profile("dev")
@ComponentScan("com.xcn.spring.annotation")
public class Config {
    @Value("#{systemProperties['username']}")
    public String dbUserName;
    @Value("#{systemProperties['password']}")
    public String dbPassword;
    @Value("testUrl")
    public String dbUrl;

    private Environment environment;




    /**
     * 实现自动注入需要添加如下方法
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
