package com.xcn.spring.annotation.fg;

import com.xcn.spring.annotation.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xupeng.guo
 * @date: 2019/6/12
 * @description
 */
@Service
public class FgHelloWordService {
    @Autowired
    private HelloService helloService;
//    @Autowired
//    private DemoService demoService;

    public String helloWorld(String param) {
        return helloService.helloWorld(param);
    }

//    public String sayHello(String helloUser) {
//        return demoService.sayHello(helloUser);
//    }
}
