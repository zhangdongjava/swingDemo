package com.zzz.wzq.ui;

import javax.swing.*;

/**
 * Created by dell_2 on 2016/9/27.
 */
public class MainFrame extends JFrame {

    public MainFrame(){
        this.setSize(600,700);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainPanel mainPanel = new MainPanel();
        mainPanel.setBounds(0,100,600,600);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
