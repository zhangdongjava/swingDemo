package com.zzz.draw.common.tcp;

import com.zzz.draw.common.bean.Message;
import com.zzz.draw.common.handler.MessageHandler;
import com.zzz.draw.common.ui.ServerMainPanel;
import com.zzz.draw.common.content.ApplicationContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class TcpServerHandler extends SimpleChannelInboundHandler<Message> {

    private ServerMainPanel serverMainPanel;

    private int count ;

    public TcpServerHandler(ServerMainPanel serverMainPanel) {

        this.serverMainPanel = serverMainPanel;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        System.out.println(this);
        //System.out.println(dataCount+=message.getByteBuf().readableBytes());
        MessageHandler handler = (MessageHandler) ApplicationContext.getInstance().get("handler_" + message.getType());
        System.out.println("服务器收到消息:"+((++count)+"->")+message.getByteBuf().readableBytes());
        message = handler.handler(message.getByteBuf(),channelHandlerContext);
        if(message !=null){
            channelHandlerContext.writeAndFlush(message);
        }
    }
}
