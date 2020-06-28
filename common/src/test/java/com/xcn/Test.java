package com.xcn;

import com.alibaba.dubbo.common.compiler.Compiler;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        ExtensionLoader<Compiler> extensionLoader = ExtensionLoader.getExtensionLoader(Compiler.class);
        com.alibaba.dubbo.common.compiler.Compiler compiler = extensionLoader.getAdaptiveExtension();
    }
}
