package com.xcn.simplerpc.provider;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: xupeng.guo
 * @date: 2019/11/8
 * @description
 */
public class RpcExportor {

    static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()) ;

    /**
     * 1. 作为服务端,监听客户端的TCP链接,接收到新的客户端链接之后,将其封装成Task,由线程池执行
     * @param hostName
     * @param port
     * @throws Exception
     */
    public static void exporter(String hostName,int port) throws Exception{
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(hostName,port));
        try{
            while(true){
                executor.execute(new ExporterTask(server.accept()));
            }
        }finally {
            server.close();
        }
    }
}
