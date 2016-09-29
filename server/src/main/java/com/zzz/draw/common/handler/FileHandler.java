package com.zzz.draw.common.handler;

import com.zzz.draw.common.bean.Message;
import com.zzz.draw.common.bean.MessageCode;
import com.zzz.draw.common.bean.SendFile;
import com.zzz.draw.common.decoder.BeanDecoder;
import com.zzz.draw.common.encoder.ObjectEncoder;
import com.zzz.draw.common.ui.DrawPanel;
import com.zzz.draw.common.ui.ServerMainPanel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by dell_2 on 2016/9/22.
 */
public class FileHandler implements MessageHandler {

    private  static ServerMainPanel mainPanel = ServerMainPanel.getInstance();
    private  static DrawPanel drawPanel = DrawPanel.getInstance();


    public Message handler(ByteBuf byteBuf, ChannelHandlerContext channelHandlerContext) {
        SendFile file = BeanDecoder.fileParseForm(byteBuf);
        System.out.println(file.getFileName());
        System.out.println(file.getBytes().length);
        if(file.getFileName().endsWith(".png")){
            drawPanel.drawImage(file.getBytes());
        }
        Message message = new Message();
        message.setType(MessageCode.STRING);
        message.setBody(ObjectEncoder.addStringBytes("收到文件:！"+"->"+file.getFileName()));
        return message;
    }
}
