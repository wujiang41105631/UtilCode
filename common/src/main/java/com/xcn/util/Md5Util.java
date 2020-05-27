package com.xcn.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class Md5Util {
    private static final long MAX_FILE_LENGTH = 1024 * 1024 * 1024 * 1024;

    public final static String MD5(String s) {
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            mdInst.update(btInput);

            byte[] md = mdInst.digest();
            return byteArrayToHex(md);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String byteArrayToHex(byte[] bytes) {
        // 把密文转换成十六进制的字符串形式
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int j = bytes.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = bytes[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }

    public static void genMd5File(String fileName, String md5Key,
                                  String signFileName) throws IOException {
        FileInputStream is = null;
        FileOutputStream os = null;
        try {
            File f = new File(fileName);
            if(f.length() > MAX_FILE_LENGTH) {
                throw new IOException("The length of file more than 1G.");

            }
            is = new FileInputStream(fileName);
            byte[] csvFileNameBytes = IOUtils.toByteArray(is);
            byte[] bs = ArrayUtils.addAll(md5Key.getBytes(), csvFileNameBytes);
            String md5Val = DigestUtils.md5Hex(bs);
            os = new FileOutputStream(signFileName);
            os.write(md5Val.getBytes());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static String getFileMD5(String inputFile, String md5Key) throws IOException {

        FileInputStream is = null;
        FileOutputStream os = null;
        try {
            File f = new File(inputFile);
            if(f.length() > MAX_FILE_LENGTH) {
                throw new IOException("The length of file more than 1G.");
            }
            is = new FileInputStream(inputFile);
            byte[] csvFileNameBytes = IOUtils.toByteArray(is);
            byte[] bs = ArrayUtils.addAll(md5Key.getBytes(), csvFileNameBytes);
            String md5Val = DigestUtils.md5Hex(bs);
            return md5Val;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Md5Util.MD5("20121221"));
        System.out.println(Md5Util.MD5("加密"));
    }
}
