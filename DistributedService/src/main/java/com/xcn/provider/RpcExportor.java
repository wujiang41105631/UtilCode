package com.xcn.provider;

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
