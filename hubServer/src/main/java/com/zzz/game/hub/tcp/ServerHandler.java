package com.zzz.game.hub.tcp;

import com.zzz.draw.common.api.MessageHandler;
import com.zzz.draw.common.bean.Message;
import com.zzz.game.hub.lunch.Lunch;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by dell_2 on 2016/9/30.
 */
public class ServerHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        MessageHandler handler = (MessageHandler) Lunch.applicationContext.getBean("handler_"+msg.getType());
        msg = handler.exec(msg.getByteBuf());
        if(msg != null){
            ctx.writeAndFlush(msg);
        }
    }
}
