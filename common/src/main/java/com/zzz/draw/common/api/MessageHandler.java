package com.zzz.draw.common.api;

import com.zzz.draw.common.bean.Message;
import io.netty.buffer.ByteBuf;

/**
 * Created by dell_2 on 2016/9/30.
 */
public interface MessageHandler {

     Message exec(ByteBuf buf);
}
