package com.zzz.draw.ui;

import com.zzz.draw.util.MouseEventImpl;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class DrawPanel extends JPanel {

    private Image image;

    private static Random random = new Random();

    private Color color;
    private ClientMainPanel mainPanel;

    public DrawPanel(ClientMainPanel mainPanel) {
        this.mainPanel = mainPanel;
        randomColor();
        MouseEventImpl mouseEvent = new MouseEventImpl(this);
        this.setSize(mainPanel.getSize());
        this.setLocation(0,0);
        this.addMouseMotionListener(mouseEvent);
        this.addMouseListener(mouseEvent);
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
        if (points.size() > 3) {
            mainPanel.sendMsg(points);
            Point first = points.getFirst();
            Graphics2D g = (Graphics2D)image.getGraphics();  //g是Graphics对象
            g.setStroke(new BasicStroke(3.0f));

            g.setColor(color);
            for (Point point : points) {
                g.drawLine(first.x, first.y, point.x, point.y);
                first = point;
            }
            points.clear();
            points.add(first);
        }
        this.repaint();
    }

    public void randomColor(){
        int a = random.nextInt(255);
        int b = random.nextInt(255);
        int c = random.nextInt(255);
        color = new Color(a,b,c);
    }

}
