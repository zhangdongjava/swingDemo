package com.zzz.draw.ui;

import com.zzz.draw.bean.Message;
import com.zzz.draw.bean.MessageCode;
import com.zzz.draw.bean.User;
import com.zzz.draw.encoder.ObjectEncoder;
import com.zzz.draw.tcp.TcpClient;
import com.zzz.draw.util.SystemUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.UnknownHostException;
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
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.add(new DrawPanel(this));
        this.add(new LeftPanel(this));
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClientMainPanel cm = new ClientMainPanel();
        cm.sendTest(1);
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        service.submit( cm.new Mthread(10));
//        service.submit( cm.new Mthread(50));
//        service.submit( cm.new Mthread(130));
//        service.submit( cm.new Mthread(180));
//        service.submit( cm.new Mthread(250));
//        service.submit( cm.new Mthread(300));
//        service.submit( cm.new Mthread(370));
//        service.submit( cm.new Mthread(480));
//        service.submit( cm.new Mthread(590));
//        service.shutdown();
    }

    public   void sendTest(int x){
        Message message = new Message();
        message.setType(MessageCode.LINK);
        User user = new User();
        try {
            user.setIp(SystemUtil.getIp());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        user.setName("张三!");

        message.setBody(ObjectEncoder.userBytes(user));
        tcpClient.sendMsg(message);
    }

    public void sendMsg(LinkedList<Point> points){
        Message message = new Message();
        message.setType(MessageCode.POINT);
        message.setBody(ObjectEncoder.addPointsBytes(points));
        tcpClient.sendMsg(message);
    }
    public void sendMsg(Message message){
        tcpClient.sendMsg(message);
    }


    private  class  Mthread implements  Runnable{
        private int x;

        public Mthread(int x){
            this.x = x;
        }
        public void run() {
            ClientMainPanel.this.sendTest(x);
            System.out.println(x);
        }
    }
}
