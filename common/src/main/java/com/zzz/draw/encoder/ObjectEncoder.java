package com.zzz.draw.encoder;

import com.zzz.draw.bean.SendFile;
import com.zzz.draw.bean.User;

import java.awt.*;
import java.io.*;

/**
 * Created by dell_2 on 2016/9/22.
 */
public class ObjectEncoder {

    public static byte[] addPointsBytes(java.util.List<Point> pointList) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);

        try {
            for (Point point : pointList) {

                out.writeInt(point.x);
                out.writeInt(point.y);
            }
            out.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream.toByteArray();
    }

    public static byte[] userBytes(User user) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        byte[] ipBytes = user.getIp().getBytes();
        try {
            out.writeInt(ipBytes.length);
            out.write(ipBytes);
            out.write(user.getName().getBytes());
            out.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream.toByteArray();
    }

    public static byte[] addStringBytes(String string) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.write(string.getBytes());
            out.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream.toByteArray();
    }

    public static byte[] fileToBytes(SendFile file) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeInt(file.getFileName().getBytes().length);
            out.write(file.getFileName().getBytes());
            out.write(file.getBytes());
            out.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream.toByteArray();
    }

    public static byte[] fileToBytes(File file) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            FileInputStream in = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int len = in.read(buf);
            while (len != -1) {
                out.write(buf, 0, len);
                len = in.read(buf);
            }
            in.close();
            out.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream.toByteArray();
    }

    public static void main(String[] args) {
        File f = new File("D:\\download\\oow2010-2.flv");
        System.out.println(fileToBytes(f).length);
    }
}
