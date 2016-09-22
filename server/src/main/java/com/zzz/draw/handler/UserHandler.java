package com.zzz.draw.handler;

import com.zzz.draw.bean.Message;
import com.zzz.draw.bean.User;
import com.zzz.draw.decoder.BeanDecoder;
import com.zzz.draw.encoder.ObjectEncoder;
import io.netty.buffer.ByteBuf;

/**
 * Created by dell_2 on 2016/9/22.
 */
public class UserHandler implements MessageHandler {

    public Message handler(ByteBuf byteBuf) {
        User user = BeanDecoder.userParseForm(byteBuf);
        System.out.println(user);
        Message message = new Message();
        message.setType(Message.MSG);
        message.setBody(ObjectEncoder.addStringBytes("你好！"+user.getName()));
        return message;
    }
}
