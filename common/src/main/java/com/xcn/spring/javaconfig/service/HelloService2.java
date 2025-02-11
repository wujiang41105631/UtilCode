package com.xcn.spring.javaconfig.service;

import org.springframework.stereotype.Service;

/**
 * @author: xupeng.guo
 * @date: 2019/6/12
 * @description
 */
public class HelloService2 {

    public String helloWorld(String param) {
        return "hello " + param + "!";
    }
}
