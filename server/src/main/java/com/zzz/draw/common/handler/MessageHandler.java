package com.zzz.draw.common.handler;

import com.zzz.draw.common.bean.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by dell_2 on 2016/9/22.
 */
public interface MessageHandler {

    Message handler(ByteBuf byteBuf,ChannelHandlerContext channelHandlerContext);
}
