package com.zzz.draw.common.bean;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by dell_2 on 2016/9/30.
 */
public class IpPort {

    private  String ip;
    private int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream stream = new DataOutputStream(out);
        stream.writeInt(port);
        stream.writeBytes(ip);
        stream.close();
        out.close();
        return out.toByteArray();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("IpPort{");
        sb.append("ip='").append(ip).append('\'');
        sb.append(", port=").append(port);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        IpPort ipPort = new IpPort();
        ipPort.setIp("localhost");
        ipPort.setPort(6789);
        System.out.println(ipPort.toByteArray());
    }
}
