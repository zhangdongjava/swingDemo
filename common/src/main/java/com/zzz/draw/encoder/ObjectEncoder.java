package com.zzz.draw.encoder;

import com.zzz.draw.bean.User;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by dell_2 on 2016/9/22.
 */
public class ObjectEncoder {

    public static   byte[] addPointsBytes(java.util.List<Point> pointList) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        for (Point point : pointList) {
            try {
                out.writeInt(point.x);
                out.writeInt(point.y);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return stream.toByteArray();
    }

    public static   byte[] userBytes(User user) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        byte[] ipBytes = user.getIp().getBytes();
        try {
            out.writeInt(ipBytes.length);
            out.write(ipBytes);
            out.write(user.getName().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream.toByteArray();
    }
    public static   byte[] addStringBytes(String string) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream.toByteArray();
    }
}
