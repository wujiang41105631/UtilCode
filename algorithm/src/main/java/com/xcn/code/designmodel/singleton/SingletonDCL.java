package com.xcn.code.designmodel.singleton;

/**
 * @author: xupeng.guo
 * @date: 2019/4/17
 * @description
 */
public class SingletonDCL {

    public SingletonDCL(){

    }

    private static class SingleHolder{
        private static final SingletonDCL ins = new SingletonDCL();
    }

    /**
     * 内部类方式获取单例
     * @return
     */
    public static SingletonDCL getInstance(){
        return SingleHolder.ins;
    }
}
