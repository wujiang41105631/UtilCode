package com.xcn.spring;

import com.xcn.spring.annotation.config.Config;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * WebApplicationInitializer 是spring mvc 提供 servlet 3.0配置的接口,用来替代web.xml.实现此接口会自动被SpringServletContainserInitializer获取到
 * 需要tomcat
 * @author: xupeng.guo
 * @date: 2019/6/12
 * @description
 */
public class WebMain implements WebApplicationInitializer{

    public static void main(String[] args) {

    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(Config.class);
        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher",new DispatcherServlet(ctx));
        dynamic.addMapping("/");
        dynamic.setAsyncSupported(true);
        dynamic.setLoadOnStartup(1);
    }
}
