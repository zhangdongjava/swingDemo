package com.zzz.draw.tcp;

import com.zzz.draw.bean.Message;
import com.zzz.draw.content.ApplicationContext;
import com.zzz.draw.handler.MessageHandler;
import com.zzz.draw.ui.ServerMainPanel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class TcpServerHandler extends SimpleChannelInboundHandler<Message> {

    private ServerMainPanel serverMainPanel;

    public TcpServerHandler(ServerMainPanel serverMainPanel) {

        this.serverMainPanel = serverMainPanel;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        MessageHandler handler = (MessageHandler) ApplicationContext.getInstance().get("handler_" + message.getType());
        message = handler.handler(message.getByteBuf(),channelHandlerContext);
        if(message !=null){
            channelHandlerContext.writeAndFlush(message);
        }
    }
}
