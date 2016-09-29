package com.zzz.game.hub.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.TextMessage;

public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination destination;

    public void sendInfo() {
        jmsTemplate.send(destination, session -> {
            TextMessage message = session.createTextMessage();
            message.setText("你好啊！！！！！！！");
            return message;
        });

    }
}