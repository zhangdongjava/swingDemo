package com.zzz.game.hub.handler;

import com.zzz.draw.common.api.MessageHandler;
import com.zzz.draw.common.bean.IpPort;
import com.zzz.draw.common.bean.Message;
import com.zzz.draw.common.bean.MessageCode;
import com.zzz.draw.common.decoder.BeanDecoder;
import com.zzz.game.hub.util.HubConstans;
import io.netty.buffer.ByteBuf;

/**
 * Created by dell_2 on 2016/9/30.
 */
public class IpPortHandler implements MessageHandler{
    @Override
    public Message exec(ByteBuf buf) {
        IpPort ip = BeanDecoder.ipPortParseForm(buf);
        HubConstans.ipPorts.add(ip);
        System.out.println(HubConstans.ipPorts);
        Message message = new Message();
        message.setType(MessageCode.STRING);
        message.setBody("success".getBytes());
        return message;
    }
}
