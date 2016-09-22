package com.zzz.draw.handler;

import com.zzz.draw.bean.Message;
import com.zzz.draw.decoder.BeanDecoder;
import com.zzz.draw.ui.ServerMainPanel;
import io.netty.buffer.ByteBuf;

/**
 * Created by dell_2 on 2016/9/22.
 */
public class PointHandler implements MessageHandler {

    public Message handler(ByteBuf byteBuf) {
        ServerMainPanel.getInstance().drawLine(BeanDecoder.pointParseForm(byteBuf));
        return null;
    }
}
