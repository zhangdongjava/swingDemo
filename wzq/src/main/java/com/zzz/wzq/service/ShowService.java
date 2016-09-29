package com.zzz.wzq.service;

import java.awt.*;

/**
 * 计算格子位置
 * Created by dell_2 on 2016/9/27.
 */
public class ShowService {

    private boolean[][] map;

    /**
     * 根据 xy 获取对应区域的格子交叉点(就是放棋子的地方)
     *
     * @param x
     * @param y
     * @return
     */
    public Point getjcPoint(int x, int y) {
        Point p = new Point();
        return p;
    }

    public boolean[][] getMap() {
        return map;
    }

    public void setMap(boolean[][] map) {
        this.map = map;
    }
}
