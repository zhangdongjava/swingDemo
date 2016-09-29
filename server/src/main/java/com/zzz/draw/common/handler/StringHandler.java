package com.zzz.draw.common.handler;

import com.zzz.draw.common.bean.Message;
import com.zzz.draw.common.decoder.BeanDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by dell_2 on 2016/9/22.
 */
public class StringHandler implements MessageHandler {

    public Message handler(ByteBuf byteBuf, ChannelHandlerContext channelHandlerContext) {
        String user = BeanDecoder.stringParseForm(byteBuf);
        System.out.println(user);
        return null;
    }
}
