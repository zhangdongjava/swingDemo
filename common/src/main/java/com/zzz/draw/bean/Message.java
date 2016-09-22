package com.zzz.draw.bean;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by dell_2 on 2016/9/21.
 */
public class Message {

    public final static int  MSG = 1;
    public final static int  LINK = 3;
    public final static int  POINT = 2;
    private int type;
    private byte[] body;
    private ByteBuf byteBuf;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public ByteBuf getByteBuf() {
        return byteBuf;
    }

    public void setByteBuf(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream out  = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);
        dataOut.writeInt(type);
        dataOut.write(body);
        return out.toByteArray();
    }



    public static Message parseForm(byte[] bytes) throws IOException, IllegalAccessException {
        ByteBuf byteBuf = Unpooled.buffer(bytes.length);
        byteBuf.writeBytes(bytes);
        Message message = new Message();
        message.setType(byteBuf.readInt());
        message.setByteBuf(byteBuf);
        return message;
    }

}
