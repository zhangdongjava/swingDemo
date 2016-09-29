package com.zzz.draw.common.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class DrawPanel extends JPanel {

    private Image image;

    private static Random random = new Random();

    private Color color;
    private ServerMainPanel mainPanel;

    private static DrawPanel  INSTANCE;

    public static DrawPanel getInstance() {
        return INSTANCE;
    }

    public DrawPanel(ServerMainPanel mainPanel) {
        INSTANCE = this;
        this.mainPanel = mainPanel;
        this.setSize(mainPanel.getSize());
        this.setLocation(0, 0);

    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = this.createImage(this.getWidth(), this.getHeight());
            Graphics gg = image.getGraphics();
            gg.setColor(Color.red);
        }
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }


    public void drawLine(LinkedList<Point> points) {
        Point first = points.getFirst();
        Graphics2D g = (Graphics2D) image.getGraphics();  //g是Graphics对象
        g.setStroke(new BasicStroke(3.0f));

        g.setColor(color);
        for (Point point : points) {
            g.drawLine(first.x, first.y, point.x, point.y);
            first = point;
        }
        this.repaint();
    }

    public void drawImage(byte[] buf){
        InputStream in  = new ByteArrayInputStream(buf);
        try {
            image.getGraphics().drawImage(ImageIO.read(in),0,0,null);
            this.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
