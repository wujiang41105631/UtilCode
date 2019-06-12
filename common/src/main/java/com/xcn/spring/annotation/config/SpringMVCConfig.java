package com.xcn.spring.annotation.config;

import com.xcn.spring.annotation.web.InterceptorDemoByClass;
import com.xcn.spring.annotation.web.InterceptorDemoByInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * spring mvc的配置有2中方式 继承 WebMvcConfigurerAdapter 或 实现WebMvcConfigurer
 * spring mvc 配置模版
 * 拦截器实现：
 * 1. 拦截器实现方式有两种: 详见InterceptorDemoByInterface 和InterceptorDemoByClass
 * 2. 配置拦截器Bean,如本类中的@Bean getInterceptorsByXXXXX
 * 3. 继承WebMvcConfigurerAdapter 重写addInterceptors方法。如本类
 *
 * @author: xupeng.guo
 * @date: 2019/6/12
 * @description
 */
@EnableWebMvc // 允许SpringMVC,若无此句，重写 WebMvcConfigurerAdapter 方法无效
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 对spring mvc配置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }

    @Bean
    public InterceptorDemoByInterface getInterceptorDemoByInterface() {
        return new InterceptorDemoByInterface();
    }

    @Bean
    public InterceptorDemoByClass getInterceptorDemoByClass() {
        return new InterceptorDemoByClass();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptorDemoByInterface());
        registry.addInterceptor(getInterceptorDemoByClass());
    }

    /**
     * springmvc 在配置后类似于http://www.itdaan.com/blog/2009/05/05/xx.yy 中的yy会被忽略，配置器不让忽略
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }
}
