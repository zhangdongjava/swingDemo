package com.zzz.draw.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by dell_2 on 2016/9/22.
 */
public class SystemUtil {
    public static String getIp() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
