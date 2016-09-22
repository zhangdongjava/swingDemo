package com.zzz.draw.ui;

import com.zzz.draw.bean.Message;
import com.zzz.draw.content.ApplicationContext;
import com.zzz.draw.handler.PointHandler;
import com.zzz.draw.handler.UserHandler;
import com.zzz.draw.tcp.TcpServer;
import javafx.application.Application;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class ServerMainPanel extends JFrame {

    private TcpServer tcpServer;

    private  DrawPanel drawPanel;

    private static ServerMainPanel  INSTANCE;

    public static ServerMainPanel getInstance() {
        return INSTANCE;
    }

    public ServerMainPanel(){
        try {
            tcpServer = new TcpServer(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(700,600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        drawPanel = new DrawPanel(this);
        this.add(drawPanel);
        INSTANCE = this;
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initHandler();
        new ServerMainPanel();

    }
    private static void initHandler(){
        ApplicationContext context = new ApplicationContext();
        context.add("handler_"+ Message.POINT,new PointHandler());
        context.add("handler_"+ Message.LINK,new UserHandler());
    }

    public void drawLine(LinkedList<Point> points){
        drawPanel.drawLine(points);
    }


}