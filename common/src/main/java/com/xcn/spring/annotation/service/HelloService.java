package com.xcn.spring.annotation.service;


import com.xcn.spring.annotation.aop.Action;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author: xupeng.guo
 * @date: 2019/6/12
 * @description
 */
@Service
public class HelloService {


    @Scheduled(fixedDelay = 3000)
    public void refresh(){
        System.out.println(Calendar.getInstance().getTimeInMillis()/1000);
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String helloWorld(String param) {
        return "hello " + param + "!";
    }

    @Action(name = "Aop by annotation helloWorld")
    public String interceptByAnnotion(String param) {
        return "interceptByAnnotion: hello " + param + "!";
    }

    public String interceptByRule(String param) {
        return "interceptByRule: hello " + param + "!";
    }

    @Async
    public void asyncTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
