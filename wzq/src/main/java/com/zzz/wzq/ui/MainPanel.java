package com.zzz.wzq.ui;

import com.zzz.wzq.service.ShowService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by dell_2 on 2016/9/27.
 */
public class MainPanel extends JPanel {

    private boolean grid = true;

    private static int SIZE = 39;
    private static int ROUND_SIZE = (int) (SIZE/1.1);

    private static int BORDER = 50;

    private int player = 0;

    private Color[] colors = {Color.white,Color.black};

    private ShowService showService = new ShowService();

    public MainPanel() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                drawFillGrid(e.getX(),e.getY());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        int w = (this.getWidth()-(BORDER*2)) / SIZE;
        int h = (this.getHeight()-(BORDER*2))/ SIZE;
        showService.setMap(new boolean[w+1][h+1]);
        g.setColor(Color.black);
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                g.drawRect(x * SIZE+BORDER, y * SIZE+BORDER, SIZE, SIZE);
            }
        }
    }

    private void drawFillGrid(int x,int y){
        Graphics graphics = this.getGraphics();
        Point point = getjcPoint(x,y);
        if(point != null){
            int pointX = point.x;
            int pointY = point.y;
            yxFillRect(graphics,pointX,pointY, ROUND_SIZE,ROUND_SIZE);
        }
    }

    public Point getjcPoint(int x, int y) {
       x =  (x +(SIZE/2) - BORDER)/SIZE;
       y =  (y +(SIZE/2) - BORDER)/SIZE;
        if(showService.getMap()[y][x]){
           return null;
        }
        showService.getMap()[y][x] = true;
        Point p = new Point(x * SIZE+BORDER,y * SIZE+BORDER);
        return p;
    }

    private  void yxFillRect(Graphics graphics,int x,int y,int w,int h){
         x = x- w/2;
         y =y- h/2;
        player = (player+1)%2;
        graphics.setColor(colors[player]);
        graphics.fillRoundRect(x,y,w,h,w,h);
    }


}
