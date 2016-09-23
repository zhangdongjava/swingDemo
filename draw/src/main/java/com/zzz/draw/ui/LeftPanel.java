package com.zzz.draw.ui;


import com.zzz.draw.bean.Message;
import com.zzz.draw.bean.MessageCode;
import com.zzz.draw.bean.SendFile;
import com.zzz.draw.encoder.ObjectEncoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class LeftPanel extends JPanel {

    private ClientMainPanel mainPanel;
    private DrawPanel drawPanel = DrawPanel.getInstance();

    /**
     * 选择文件btn
     */
    private JButton fileButton;

    /**
     * 保存图片
     */
    private JButton saveImg;

    public LeftPanel(ClientMainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.setLayout(null);
        fileButton = new JButton("选择文件");
        saveImg = new JButton("保存");
        this.setSize(200,mainPanel.getHeight());
        this.setLocation(0,0);
        this.setBorder(new BevelBorder(3, Color.RED,Color.BLUE));
        fileButton.addActionListener(e -> chooseFile());
        saveImg.addActionListener(e -> saveImg());
        fileButton.setBounds(10,10,80,30);
        saveImg.setBounds(10,80,80,30);
        this.add(fileButton);
        this.add(saveImg);

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

    private void saveImg(){
        Image image = drawPanel.getImage();
        JFileChooser jChooser2 = new JFileChooser();
        jChooser2.showSaveDialog(mainPanel);
        File f = jChooser2.getSelectedFile();
        if(f != null){
            try {
                ImageIO.write((RenderedImage) image,"png",f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
