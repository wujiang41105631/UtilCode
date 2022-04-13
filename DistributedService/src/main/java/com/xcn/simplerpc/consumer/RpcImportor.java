package com.xcn.simplerpc.consumer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 本地服务代理
 * @author: xupeng.guo
 * @date: 2019/11/11
 * @description
 */
public class RpcImportor<S> {


    /**
     * 1. 将本地的接口调用转换成JDK的动态代理,在动态代理中实现接口的远程调用.
     * 2. 创建Socket客户端,根据指定地址链接远程服务提供者.
     * 3. 服务调用所需要的接口类,方法名,参数列表等编码后发送给服务提供者.
     * 4. 同步阻塞等待服务端返回应答,获取应道之后返回
     * @param serviceService
     * @param addr
     * @return
     */
    public S importor(final Class<?> serviceService, final InetSocketAddress addr) {
        // 使用动态代理完成此步骤
        return (S) Proxy.newProxyInstance(serviceService.getClassLoader()
                , new Class<?>[]{serviceService.getInterfaces()[0]}
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = null;
                        ObjectOutputStream output = null;
                        ObjectInputStream input = null;
                        try {

                            socket = new Socket();
                            socket.connect(addr);
                            output = new ObjectOutputStream(socket.getOutputStream());
                            output.writeUTF(serviceService.getName());
                            output.writeUTF(method.getName());
                            output.writeObject(method.getParameterTypes());
                            output.writeObject(args);
                            input = new ObjectInputStream(socket.getInputStream());
                            return input.readObject();
                        } finally {
                            if (socket != null) {
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (input != null) {
                                try {
                                    input.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (output != null) {
                                try {
                                    output.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
    }
}
