package com.zzz.game.gameServer.lunch;

import com.zzz.draw.common.bean.IpPort;
import com.zzz.draw.common.bean.Message;
import com.zzz.draw.common.bean.MessageCode;
import com.zzz.draw.common.tcp.TcpClient;
import com.zzz.draw.common.tcp.TcpServer;
import com.zzz.game.gameServer.tcp.ClientHandler;
import com.zzz.game.gameServer.tcp.ServerHandler;

/**
 * Created by dell_2 on 2016/9/30.
 */
public class Lunch {

    public static void main(String[] args) {
        TcpClient tcpClient = new TcpClient(6666,"localhost",new ClientHandler());
        try {
            TcpServer server = new TcpServer("localhost",6789,new ServerHandler());
            Message message = new Message();
            message.setType(MessageCode.IP_PORT);
            IpPort ipPort = new IpPort();
            ipPort.setIp("localhost");
            ipPort.setPort(6789);
            message.setBody(ipPort.toByteArray());
            tcpClient.sendMsg(message);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("启动服务失败!");
        }

    }
}
