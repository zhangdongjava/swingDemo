package com.zzz.draw.common.code;

import com.zzz.draw.common.bean.Message;
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
        //一次数据可能是2个甚至多个message的粘包 所以要循环读取
        while (readMessage(list));
        byteBuf.discardReadBytes();
    }

    /**
     * 读取数据转化为message
     * @param list
     * @throws IOException
     * @throws IllegalAccessException
     */
    private  boolean readMessage(List list) throws IOException, IllegalAccessException {
        byteBuf.markReaderIndex();
        int len = byteBuf.readableBytes();
        if(len<4)return false;
        int  trueLength = byteBuf.readInt();
        //已经读取了一个int 所以要减去4
        if(trueLength<=(len-4)){
            byte[] dst = new byte[trueLength];
            byteBuf.readBytes(dst);
            list.add(Message.parseForm(dst));
            return true;
        }else{
            byteBuf.resetReaderIndex();
            return false;
        }
    }


}
