package com.zzz.draw.tcp;

import com.zzz.draw.bean.Message;
import com.zzz.draw.bean.MessageCode;
import com.zzz.draw.decoder.BeanDecoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class TcpClientHandler extends SimpleChannelInboundHandler<Message> {

    private TcpClient tcpClient;

    public TcpClientHandler(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg)
            throws Exception {
        if (MessageCode.STRING == msg.getType())
            System.out.println("client接收到服务器返回的消息:" + BeanDecoder.stringParseForm(msg.getByteBuf()));

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof IOException) {
            try {
                System.out.println("IOException");
                ctx.channel().disconnect();
                TcpClient.reConnect();
            } catch (Exception e) {
                TcpClient.reConnect();
                System.out.println(e.toString());
            }
        }
    }


}