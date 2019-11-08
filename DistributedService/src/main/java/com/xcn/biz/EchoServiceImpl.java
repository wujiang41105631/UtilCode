package com.xcn.biz;

/**
 * @author: xupeng.guo
 * @date: 2019/11/8
 * @description
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String ping) {
        return ping != null ? ping + "--> I am OK." : "I am OK";
    }
}
