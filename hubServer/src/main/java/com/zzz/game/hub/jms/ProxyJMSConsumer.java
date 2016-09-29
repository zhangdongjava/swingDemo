package com.zzz.game.hub.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * JMS消费者
 * 消息题的内容定义
 * 消息对象 接收消息对象后： 接收到的消息体* <p> 
 */
public class ProxyJMSConsumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private  Destination destination;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    
    /**
     * 监听到消息目的有消息后自动调用onMessage(Message message)方法
     */
    public void recive() {
        while (true) {
            try {
                TextMessage txtmsg = (TextMessage) jmsTemplate
                        .receive(destination);
                if (null != txtmsg) {
                    System.out.println("[DB Proxy] " + txtmsg);
                    System.out.println("[DB Proxy] 收到消息内容为: "
                            + txtmsg.getText());
                } else
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}