package com.zzz.game.hub.test;

import com.zzz.game.hub.jms.Sender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dell_2 on 2016/9/29.
 */
public class Test {

    public static void main(String[] args) {
        ExecutorService exec  = Executors.newFixedThreadPool(10);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Sender sender = applicationContext.getBean(Sender.class);
        for (int i = 0; i < 10; i++) {
            exec.submit(()-> sender.sendInfo());
        }

    }
}
