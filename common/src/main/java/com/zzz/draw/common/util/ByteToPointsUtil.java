package com.zzz.draw.common.util;

import io.netty.buffer.ByteBuf;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class ByteToPointsUtil {

    public static LinkedList<Point> decode(byte[] buf) throws IOException {
        LinkedList<Point> points = new LinkedList<Point>();
        DataInputStream dataInputStream = dataInputStream(buf);
        int len = buf.length / 8;
        for (int i = 0; i < len; i++) {
            points.add(new Point(dataInputStream.readInt(), dataInputStream.readInt()));

        }
        return points;
    }

    public static LinkedList<Point> decode(DataInputStream dataInputStream) throws IOException {
        LinkedList<Point> points = new LinkedList<Point>();
        int len = (dataInputStream.available() - 4) / 8;
        for (int i = 0; i < len; i++) {
            points.add(new Point(dataInputStream.readInt(), dataInputStream.readInt()));

        }
        return points;
    }

    public static DataInputStream dataInputStream(byte[] buf) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buf);
        return new DataInputStream(inputStream);
    }

    public static LinkedList<Point> decode(ByteBuf byteBuf) {
        LinkedList<Point> points = new LinkedList<Point>();
        int len = byteBuf.readableBytes() / 8;
        for (int i = 0; i < len; i++) {
            points.add(new Point(byteBuf.readInt(), byteBuf.readInt()));

        }
        return points;
    }


}
