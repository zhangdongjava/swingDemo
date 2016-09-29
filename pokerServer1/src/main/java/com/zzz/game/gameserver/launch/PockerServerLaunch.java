package com.zzz.game.gameserver.launch;

import com.zzz.game.gameserver.jms.Consumer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dell_2 on 2016/9/29.
 */
public class PockerServerLaunch {

    public static void main(String[] args) {
        ExecutorService exec  = Executors.newFixedThreadPool(10);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
