package com.zzz.draw.common.handler;

import com.zzz.draw.common.bean.Message;
import com.zzz.draw.common.decoder.BeanDecoder;
import com.zzz.draw.common.ui.ServerMainPanel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by dell_2 on 2016/9/22.
 */
public class PointHandler implements MessageHandler {

    public Message handler(ByteBuf byteBuf, ChannelHandlerContext channelHandlerContext) {
        ServerMainPanel.getInstance().drawLine(BeanDecoder.pointParseForm(byteBuf));
        return null;
    }
}
