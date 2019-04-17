package com.xcn.code.designmodel.singleton;

/**
 * @author: xupeng.guo
 * @date: 2019/4/17
 * @description
 */
public class SingletonDCL2 {

    public SingletonDCL2() {

    }

    private volatile static SingletonDCL2 ins = null;

    /**
     * DCL方式获取单例
     *
     * @return
     */
    public static SingletonDCL2 getInstance() {
        if (ins == null) {
            synchronized (SingletonDCL2.class) {
                if (ins == null) {
                    ins = new SingletonDCL2();
                }
            }
        }
        return ins;
    }
}
