package com.zzz.draw.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class MessageEncoder  extends MessageToByteEncoder<LinkedList<Point>> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, LinkedList<Point> points, ByteBuf byteBuf) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);
        for (Point point : points) {
            dataOut.writeInt(point.x);
            dataOut.writeInt(point.y);
        }
        byte[] bytes = out.toByteArray();
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }


}
