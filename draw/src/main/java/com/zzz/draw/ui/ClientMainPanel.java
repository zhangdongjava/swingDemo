package com.zzz.draw.ui;

import com.zzz.draw.bean.Message;
import com.zzz.draw.tcp.TcpClient;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class ClientMainPanel extends JFrame {

    private  TcpClient tcpClient;

    public ClientMainPanel(){
        System.out.println("启动服务!");
        tcpClient = new TcpClient();
        System.out.println("启动完毕!");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(700,600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.add(new DrawPanel(this));
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new ClientMainPanel();

    }

    public void sendMsg(LinkedList<Point> points){
        Message message = new Message();
        message.setType(Message.POINT);
        message.setBody(points);
        tcpClient.sendMsg(message);
    }
}
