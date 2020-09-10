package com.xcn.util;

/**
 * @author: xupeng.guo
 * @date: 2019/10/10
 * @description
 */
public class OriginalClassLoaderTest {

    public static void main(String[] args) {
        // 打印 各级加载器加载的东西
        OriginalClassLoaderTest.printClassLoaderLodingPath(ClassLoaderEnum.LOADING_PATH_BOOT_CLASSLOADER);
        OriginalClassLoaderTest.printClassLoaderLodingPath(ClassLoaderEnum.LOADING_PATH_EXT_CLASSLOADER);
        OriginalClassLoaderTest.printClassLoaderLodingPath(ClassLoaderEnum.LOADING_PATH_APP_CLASSLOADER);

        // 验证双亲委派的父子关系
        System.out.println(OriginalClassLoaderTest.class.getClassLoader());
        System.out.println(OriginalClassLoaderTest.class.getClassLoader().getParent());
        System.out.println(OriginalClassLoaderTest.class.getClassLoader().getParent().getParent());// 返回null,表明是已经到BOOT了
    }

    public static void printClassLoaderLodingPath(ClassLoaderEnum classLoaderEnum) {
        String[] classLoaderLoadingPath = getClassLoaderLoadingPath(classLoaderEnum.path);
        for (String s : classLoaderLoadingPath) {
            System.out.println("[由" + classLoaderEnum.desc + "加载] " + s);
        }
        System.out.println();
    }

    public static String[] getClassLoaderLoadingPath(String property) {
        String bootClassLoaderLodingPath = System.getProperty(property);
        return bootClassLoaderLodingPath.split(":");
    }

    enum ClassLoaderEnum {
        LOADING_PATH_BOOT_CLASSLOADER("sun.boot.class.path", "启动类加载器"),
        LOADING_PATH_EXT_CLASSLOADER("java.ext.dirs", "扩展类加载器"),
        LOADING_PATH_APP_CLASSLOADER("java.class.path", "APP加载器");

        public String desc;
        public String path;

        ClassLoaderEnum(String path, String desc) {
            this.path = path;
            this.desc = desc;
        }
    }
}
