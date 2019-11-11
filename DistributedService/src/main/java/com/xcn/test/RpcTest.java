package com.xcn.test;

import com.xcn.biz.EchoService;
import com.xcn.biz.EchoServiceImpl;
import com.xcn.consumer.RpcImportor;
import com.xcn.provider.RpcExportor;

import java.net.InetSocketAddress;

/**
 * 测试栗子
 * @author: xupeng.guo
 * @date: 2019/11/11
 * @description
 */
public class RpcTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RpcExportor.exporter("localhost",8088);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(10*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----server start ..");

        RpcImportor<EchoService> importor = new RpcImportor<>();
        EchoService echo = importor.importor(EchoServiceImpl.class,new InetSocketAddress("localhost",8088));
        System.out.println(echo.echo("Are you ok?"));
    }
}
