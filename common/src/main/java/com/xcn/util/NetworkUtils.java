package com.xcn.util;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xupeng.guo on 2016/9/1.
 */
public class NetworkUtils {

    public static InetAddress getLocalInetAddress() {
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            List<InetAddress> ipv4Result = new ArrayList();
            List<InetAddress> ipv6Result = new ArrayList();

            while (e.hasMoreElements()) {
                NetworkInterface localHost = (NetworkInterface) e.nextElement();
                Enumeration ip = localHost.getInetAddresses();

                while (ip.hasMoreElements()) {
                    InetAddress address = (InetAddress) ip.nextElement();
                    if (!address.isLoopbackAddress()) {
                        if (address instanceof Inet6Address) {
                            ipv6Result.add(address);
                        } else {
                            ipv4Result.add(address);
                        }
                    }
                }
            }

            if (!ipv4Result.isEmpty()) {
                Iterator<InetAddress> localHost2 = ipv4Result.iterator();
                InetAddress ip1;
                do {
                    if (!localHost2.hasNext()) {
                        return ipv4Result.get(ipv4Result.size() - 1);
                    }
                    ip1 = localHost2.next();
                }
                while (normalizeHostAddress(ip1).startsWith("127.0") || normalizeHostAddress(ip1).startsWith("192.168"));

                return ip1;
            }

            if (!ipv6Result.isEmpty()) {
                return ipv6Result.get(0);
            }

            InetAddress localHost1 = InetAddress.getLocalHost();
            return localHost1;
        } catch (SocketException var6) {
            var6.printStackTrace();
        } catch (UnknownHostException var7) {
            var7.printStackTrace();
        }

        return null;
    }

    public static String getLocalAddress() {
        return normalizeHostAddress(getLocalInetAddress());
    }

    public static String normalizeHostAddress(InetAddress localHost) {
        if (localHost == null) {
            return null;
        }
        return localHost instanceof Inet6Address ? "[" + localHost.getHostAddress() + "]" : localHost.getHostAddress();
    }
}
