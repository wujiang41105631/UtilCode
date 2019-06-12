package com.xcn.spring.javaconfig.fg;

import com.xcn.spring.javaconfig.service.HelloService2;

/**
 * @author: xupeng.guo
 * @date: 2019/6/12
 * @description
 */
public class FgHelloWordService2 {
    private HelloService2 helloService2;

    public void setHelloService2(HelloService2 helloService2) {
        this.helloService2 = helloService2;
    }

    public String helloWorld(String param) {
        return helloService2.helloWorld(param);
    }
}
