package com.zzz.draw.ui;


import com.zzz.draw.bean.Message;
import com.zzz.draw.bean.MessageCode;
import com.zzz.draw.bean.SendFile;
import com.zzz.draw.encoder.ObjectEncoder;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.File;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class LeftPanel extends JPanel {

    private ClientMainPanel mainPanel;

    /**
     * 选择文件btn
     */
    private JButton fileButton;

    public LeftPanel(ClientMainPanel mainPanel) {
        this.mainPanel = mainPanel;
        fileButton = new JButton("选择文件");
        this.setSize(200,mainPanel.getHeight());
        this.setLocation(0,0);
        this.setBorder(new BevelBorder(3, Color.RED,Color.BLUE));
        fileButton.addActionListener(e -> chooseFile());
        fileButton.setBounds(10,10,80,30);
        this.add(fileButton);

    }


    private void chooseFile(){
        JFileChooser chooser =  new JFileChooser();
        chooser.showOpenDialog(mainPanel);
        File file = chooser.getSelectedFile();
        if(file == null)return;
        SendFile sendFile = new SendFile(file);
        Message message = new Message();
        message.setType(MessageCode.FILE);
        message.setBody(ObjectEncoder.fileToBytes(sendFile));
        mainPanel.sendMsg(message);
    }

}
