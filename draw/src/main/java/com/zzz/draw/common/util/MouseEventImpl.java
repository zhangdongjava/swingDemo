package com.zzz.draw.common.util;

import com.zzz.draw.common.ui.DrawPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dell_2 on 2016/9/20.
 */
public class MouseEventImpl extends MouseAdapter {

    private DrawPanel drawPanel;

    private LinkedList<Point> points = new LinkedList<Point>();

    public MouseEventImpl(DrawPanel drawPanel) {

        this.drawPanel = drawPanel;
    }

    public void mouseDragged(MouseEvent e) {
        points.add(e.getPoint());
        drawPanel.drawLine(points);
    }


    public void mouseReleased(MouseEvent e) {
        drawPanel.randomColor();
        points.clear();
    }

    public List<Point> getPoints() {

        return points;
    }
}
