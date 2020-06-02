package com.xcn.io.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description: nio 读写文件,主要通过Channel和Buffer来搞这个事情
 * 详见 https://blog.csdn.net/forezp/article/details/88414741
 * @author: xupeng.guo
 * @create: 2020-06-02 13:43
 **/
public class NioReadFile {

    public static void main(String[] args) {
        RandomAccessFile aFile = null;
        try {
            // 此处也能通过FileInputStream.getChannel()来搞
            aFile = new RandomAccessFile("src/nio.txt", "rw");
            FileChannel fileChannel = aFile.getChannel();
            //分配空间
            ByteBuffer buf = ByteBuffer.allocate(1024);
            // 写入数据到Buffer
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                // 调用filp()方法
                buf.flip();
                while (buf.hasRemaining()) {
                    // 从Buffer中读取数据 读取数据
                    System.out.print((char) buf.get());
                }
                // 将有用数据移到Buffer的数组的前面.
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
