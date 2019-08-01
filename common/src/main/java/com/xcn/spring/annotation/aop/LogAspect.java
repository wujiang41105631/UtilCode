package com.xcn.spring.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: xupeng.guo
 * @date: 2019/6/12
 * @description
 */
@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.xcn.spring.annotation.aop.Action))")
    public void annotationPointCut() {

    }

    @Before("annotationPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.info("point cut before :" + signature.getMethod().getAnnotation(Action.class).name());
    }

    @Around("annotationPointCut()")
    public void doAround(ProceedingJoinPoint point){

    }

    @After("execution(* com.xcn.spring.annotation.service.HelloService.*ByRule(..))")
    public void doAfter(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.info("point cut after method :" + signature.getMethod().getName());
    }

//    @AfterReturning
//    @AfterThrowing
}
