package com.xcn.code.designmodel.singleton;

/**
 * @description: 枚举单例，线程安全,且防止反序列化
 * @author: xupeng.guo
 * @create: 2020-06-04 15:49
 **/
public enum SingletonEnum {
    INSTANCE;

    public void test(){

    }

    public static void main(String[] args) {
        SingletonEnum.INSTANCE.test();
    }

}
