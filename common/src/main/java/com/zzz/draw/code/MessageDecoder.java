package com.zzz.draw.code;

import com.zzz.draw.bean.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.IOException;
import java.util.List;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class MessageDecoder extends ByteToMessageDecoder {
    private ByteBuf byteBuf = null;
    private static int count;
    private int dataCount;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf buf, List list) throws Exception {
        if (byteBuf == null) {
            byteBuf = buf;
        } else {
            byteBuf.writeBytes(buf);
        }
        readMessage(list);
        byteBuf.discardReadBytes();
    }

    private  void readMessage(List list) throws IOException, IllegalAccessException {
        byteBuf.markReaderIndex();
        int len = byteBuf.readableBytes();
        if(len<4)return;
        int  trueLength = byteBuf.readInt();
        if(trueLength<=len){
            byte[] dst = new byte[trueLength];
            byteBuf.readBytes(dst);
            list.add(Message.parseForm(dst));
            readMessage(list);
        }else{
            byteBuf.resetReaderIndex();
        }
    }


}
