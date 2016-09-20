package com.zzz.draw.ui;

import com.zzz.draw.tcp.TcpServer;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class ServerMainPanel extends JFrame {

    private TcpServer tcpServer;

    private  DrawPanel drawPanel;

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
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new ServerMainPanel();

    }

    public void drawLine(LinkedList<Point> points){
        drawPanel.drawLine(points);
    }


}
