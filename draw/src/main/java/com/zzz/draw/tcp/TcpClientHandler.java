package com.zzz.draw.tcp;

import com.zzz.draw.bean.Message;
import com.zzz.draw.decoder.BeanDecoder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TcpClientHandler extends SimpleChannelInboundHandler<Message> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg)
			throws Exception {
		if(Message.MSG == msg.getType())
		System.out.println("client接收到服务器返回的消息:"+ BeanDecoder.stringParseForm(msg.getByteBuf()));
		
	}


   
}