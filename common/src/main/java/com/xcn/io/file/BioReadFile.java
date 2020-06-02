package com.xcn.io.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description: bio 读写文件,主要通过Stream来搞这个事
 * @author: xupeng.guo
 * @create: 2020-06-02 13:41
 **/
public class BioReadFile {

    public static void main(String[] args) {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("src/nomal_io.txt"));
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
