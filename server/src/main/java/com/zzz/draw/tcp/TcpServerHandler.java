package com.zzz.draw.tcp;

import com.zzz.draw.bean.Message;
import com.zzz.draw.content.ApplicationContext;
import com.zzz.draw.handler.MessageHandler;
import com.zzz.draw.ui.ServerMainPanel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class TcpServerHandler extends SimpleChannelInboundHandler<Message> {

    private ServerMainPanel serverMainPanel;

    private int count ;
    private int dataCount ;

    public TcpServerHandler(ServerMainPanel serverMainPanel) {

        this.serverMainPanel = serverMainPanel;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        //System.out.println(dataCount+=message.getByteBuf().readableBytes());
        MessageHandler handler = (MessageHandler) ApplicationContext.getInstance().get("handler_" + message.getType());
        System.out.println("服务器收到消息:"+((++count)+"->")+message.getByteBuf().readableBytes());
        message = handler.handler(message.getByteBuf(),channelHandlerContext);
        if(message !=null){
            channelHandlerContext.writeAndFlush(message);
        }
    }
}
