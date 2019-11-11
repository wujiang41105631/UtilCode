package com.xcn.provider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author: xupeng.guo
 * @date: 2019/11/8
 * @description
 */
public class ExporterTask implements Runnable {
    Socket client = null;

    public ExporterTask(Socket client) {
        this.client = client;
    }

    @Override
    /**
     * 1. 将客户端发送的码流反序列化成对象,反射调用服务实践者,获取执行结果
     * 2. 对象序列化,通过Socket发送给客户端
     * 3. 服务调用完成后,释放Socket等链接资源,防止句柄泄漏
     *
     */
    public void run() {
        ObjectOutputStream output = null;
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(client.getInputStream());
            String interfaceName = input.readUTF();
            Class<?> service = Class.forName(interfaceName);
            String methodName = input.readUTF();
            Class<?>[] parameterTypes = (Class<?>[])input.readObject();
            Object[] arguments = (Object[]) input.readObject();
            Method method = service.getMethod(methodName,parameterTypes);
            Object result = method.invoke(service.newInstance(),arguments);
            output = new ObjectOutputStream(client.getOutputStream());
            output.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
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

            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
