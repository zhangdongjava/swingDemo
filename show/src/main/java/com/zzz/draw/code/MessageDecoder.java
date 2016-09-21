package com.zzz.draw.code;

import com.zzz.draw.bean.Message;
import com.zzz.draw.util.ByteToPointsUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    private ByteBuf byteBuf = Unpooled.buffer(1024);
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf buf, List list) throws Exception {
        byteBuf.writeBytes(buf);
        byteBuf.markReaderIndex();
        int len = byteBuf.readableBytes();
        int  trueLength = byteBuf.readInt();
        if(trueLength<=len){
            byte[] dst = new byte[trueLength];
            byteBuf.readBytes(dst);
            list.add(Message.parseForm(dst));
            byteBuf.discardReadBytes();
        }else{
            byteBuf.resetReaderIndex();
        }

    }



}
