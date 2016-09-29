package com.zzz.draw.common.ui;

import com.zzz.draw.common.bean.MessageCode;
import com.zzz.draw.common.handler.UserHandler;
import com.zzz.draw.common.content.ApplicationContext;
import com.zzz.draw.common.handler.FileHandler;
import com.zzz.draw.common.handler.PointHandler;
import com.zzz.draw.common.handler.StringHandler;
import com.zzz.draw.common.tcp.TcpServer;

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
        new ServerMainPanel();
        initContext();

    }
    private static void initContext(){
        ApplicationContext context = new ApplicationContext();
        context.add("handler_"+ MessageCode.POINT,new PointHandler());
        context.add("handler_"+ MessageCode.LINK,new UserHandler());
        context.add("handler_"+ MessageCode.FILE,new FileHandler());
        context.add("handler_"+ MessageCode.STRING,new StringHandler());
    }

    public void drawLine(LinkedList<Point> points){
        drawPanel.drawLine(points);
    }


}
