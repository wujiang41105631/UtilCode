package com.xcn.io.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @description: bio server
 * @author: xupeng.guo
 * @create: 2020-06-02 13:56
 **/
public class BioServer {

    private BioServer(int port) {
        ServerSocket serverSocket = null;
        InputStream in = null;
        try {
            serverSocket = new ServerSocket(port);
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];
            while (true) {
                Socket clntSocket = serverSocket.accept();
                SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
                System.out.println("Handling client at " + clientAddress);
                in = clntSocket.getInputStream();
                while ((recvMsgSize = in.read(recvBuf)) != -1) {
                    byte[] temp = new byte[recvMsgSize];
                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
                    System.out.println(new String(temp));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 获取Server
     *
     * @param port
     * @return
     */
    public static BioServer getServer(int port) {
        return new BioServer(port);
    }
}
