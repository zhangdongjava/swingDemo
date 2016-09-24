package com.zzz.draw.handler;

import com.zzz.draw.bean.Message;
import com.zzz.draw.bean.MessageCode;
import com.zzz.draw.bean.User;
import com.zzz.draw.decoder.BeanDecoder;
import com.zzz.draw.encoder.ObjectEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by dell_2 on 2016/9/22.
 */
public class StringHandler implements MessageHandler {

    public Message handler(ByteBuf byteBuf,ChannelHandlerContext channelHandlerContext) {
        String user = BeanDecoder.stringParseForm(byteBuf);
        System.out.println(user);
        return null;
    }
}
