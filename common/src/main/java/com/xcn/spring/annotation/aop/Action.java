package com.xcn.spring.annotation.aop;

import java.lang.annotation.*;

/**
 * @author: xupeng.guo
 * @date: 2019/6/12
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {

    String name() default "";
}
