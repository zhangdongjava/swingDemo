package com.zzz.draw.handler;

import com.zzz.draw.bean.Message;
import io.netty.buffer.ByteBuf;

/**
 * Created by dell_2 on 2016/9/22.
 */
public interface MessageHandler {

    Message handler(ByteBuf byteBuf);
}