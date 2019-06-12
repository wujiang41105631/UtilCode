package com.xcn.spring.annotation.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: xupeng.guo
 * @date: 2019/6/12
 * @description
 */
@Component
public class TaskConfig implements AsyncConfigurer{
    @Override
    public Executor getAsyncExecutor() {
        return Executors.newFixedThreadPool(3);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        System.err.println("youwenti");
        return null;
    }
}
