package com.zzz.draw.common.decoder;

import com.zzz.draw.common.bean.SendFile;
import com.zzz.draw.common.bean.User;
import com.zzz.draw.common.util.ByteToPointsUtil;
import io.netty.buffer.ByteBuf;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/9/22.
 */
public class BeanDecoder {
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

    public static SendFile fileParseForm(ByteBuf buf) {
        SendFile file = new SendFile();
        int nameLen = buf.readInt();
        byte[] nameBytes = new byte[nameLen];
        buf.readBytes(nameBytes);
        file.setFileName(new String(nameBytes));
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        file.setBytes(bytes);
        return file;
    }
}
