package com.xcn.code.designmodel.singleton;

/**
 * @description: 饿汉式,只要这个类用到了，那么 singleton就有了
 * @author: xupeng.guo
 * @create: 2020-06-04 15:46
 **/
public class Singleton {

    private Singleton() {

    }

    private static final Singleton singleton = new Singleton();

    public static Singleton getInstance() {
        return singleton;
    }
}
