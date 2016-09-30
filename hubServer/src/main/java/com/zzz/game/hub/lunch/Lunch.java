package com.zzz.game.hub.lunch;

import com.zzz.draw.common.tcp.TcpServer;
import com.zzz.draw.common.util.Constans;
import com.zzz.game.hub.tcp.ServerHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dell_2 on 2016/9/30.
 */
public class Lunch {

    public static  ApplicationContext applicationContext;

    public static void main(String[] args) {
        initApplication();
        ServerHandler serverHandler = new ServerHandler();
        try {
           new TcpServer("localhost",Constans.hubPort,serverHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initApplication(){
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

}
