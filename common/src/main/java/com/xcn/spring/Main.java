package com.xcn.spring;

import com.xcn.spring.annotation.fg.FgHelloWordService;
import com.xcn.spring.annotation.service.HelloService;
import com.xcn.spring.javaconfig.fg.FgHelloWordService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 主入口
 *
 * @author: xupeng.guo
 * @date: 2019/6/11
 * @description
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static AnnotationConfigApplicationContext context;

    static RunType runType = RunType.ANNOTATION;
    static {
        switch (runType) {
            case ANNOTATION:
                context = new AnnotationConfigApplicationContext(com.xcn.spring.annotation.config.Config.class);
                break;
            case JAVACONFIG:
                context = new AnnotationConfigApplicationContext(com.xcn.spring.javaconfig.config.Config.class);
        }

    }

    public static void main(String[] args) {
        switch (runType) {
            case ANNOTATION:
                iocUseAnnotation();;
                break;
            case JAVACONFIG:
                iocUseJavaConfig();;
        }


//        context.close();
    }


    private static void iocUseAnnotation() {
        FgHelloWordService service = context.getBean(FgHelloWordService.class);
        System.out.println(service);
        logger.info("useAnnotation: {}", service.helloWorld("useAnnotation"));
        HelloService helloService = context.getBean(HelloService.class);
        helloService.interceptByAnnotion("tt");
        helloService.interceptByRule("tt");
        helloService.asyncTask();

        System.out.println("finish");
    }

    private static void iocUseJavaConfig() {
        FgHelloWordService2 service = context.getBean(FgHelloWordService2.class);
        logger.info("useJavaConfig: {}", service.helloWorld("useJavaConfig"));
    }
}
