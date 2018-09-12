package com.xcn.util;

import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: xupeng.guo
 * @date: 2018/9/12
 * @description
 */
public class CommonUtils {

    private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符

    /**
     * 获取本地IP
     *
     * @return
     */
    public static String getLocalHost() {
        Enumeration allNetInterfaces = null;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress ip = null;
        String localHost = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address && !"127.0.0.1".equals(ip.getHostAddress())) {
                    localHost = ip.getHostAddress();
                    // log.info("本机的IP = " + ip.getHostAddress());
                }
            }
        }
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(localHost);
        localHost = m_space.replaceAll("");
        localHost = localHost.trim();
        return localHost;
    }

    /**
     * 加密
     * @param source
     * @return
     */
    public static String encode(String source) throws UnsupportedEncodingException {
        return new String(ConvertUtil.bytesToBase64(CompressUtil.compress(source.getBytes("UTF-8")))).replace("\r\n","").replace("\n","");
    }

    /**
     * 解密
     * @param source
     * @return
     */
    public static String decode(String source) throws UnsupportedEncodingException {
        return new String(CompressUtil.decompress(ConvertUtil.base64ToBytes(source)),"UTF-8");
    }
}
