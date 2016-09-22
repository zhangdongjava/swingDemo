package com.zzz.draw.decoder;

import com.zzz.draw.bean.User;
import com.zzz.draw.util.ByteToPointsUtil;
import io.netty.buffer.ByteBuf;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/9/22.
 */
public class BeanDecoder  {
    public static User userParseForm(ByteBuf buf) {
        User user = new User();
        int ipLen = buf.readInt();
        byte[] ipBytes = new byte[ipLen];
        buf.readBytes(ipBytes);
        user.setIp(new String(ipBytes));
        byte[] nameBytes = new byte[buf.readableBytes()];
        buf.readBytes(nameBytes);
        user.setName(new String(nameBytes));
        return user;
    }

    public static LinkedList<Point> pointParseForm(ByteBuf buf) {
        return ByteToPointsUtil.decode(buf);
    }

    public static String stringParseForm(ByteBuf buf) {
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        return new String(bytes);
    }
}
