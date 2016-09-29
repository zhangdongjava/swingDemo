package com.zzz.game.gameserver.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * JMS消费者
 * 消息题的内容定义
 * 消息对象 接收消息对象后： 接收到的消息体* <p> 
 */
public class ProxyJMSConsumer implements Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private  Destination destination;

    /**
     * 监听到消息目的有消息后自动调用onMessage(Message message)方法
     */
    public void recive() {

    }

}