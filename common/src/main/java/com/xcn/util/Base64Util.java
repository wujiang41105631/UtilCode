package com.xcn.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class Base64Util {

    private static final String ENCODE = "utf-8";

    public static void main(String[] args) {
        System.out.println(getBase64("13822881988"));
        System.out.print(getFromBase64("MTM4MjI4ODE5ODg="));
    }

    public static String getBase64(String data) {
        try {
            byte[] dataBytes = Base64.encodeBase64(data.getBytes(ENCODE));
            String dataBase64 = new String(dataBytes, ENCODE);
            if (dataBase64 != null) {
                dataBase64 = dataBase64.trim();
            }
            return dataBase64;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFromBase64(String data) {
        try {
            byte[] dataBytes = Base64.decodeBase64(data.getBytes(ENCODE));
            String dataStr = new String(dataBytes, ENCODE);
            return dataStr;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
