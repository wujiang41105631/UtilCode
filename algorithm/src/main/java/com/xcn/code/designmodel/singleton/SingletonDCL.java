package com.xcn.code.designmodel.singleton;

/**
 * @author: xupeng.guo
 * @date: 2019/4/17
 * @description jvm 保证线程安全
 */
public class SingletonDCL {

    private SingletonDCL() {

    }

    private static class SingleHolder {
        private static final SingletonDCL ins = new SingletonDCL();
    }

    /**
     * 内部类方式获取单例
     *
     * @return
     */
    public static SingletonDCL getInstance() {
        return SingleHolder.ins;
    }

    public static void main(String[] args) {

    }
}
