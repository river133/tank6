package com.mashibing.tank;

import java.awt.*;

public class Tank {
    private int x,y;
    private Dir dir =Dir.DOWN;
    private static final int SPEED=5;//坦克速度

    private boolean moving = false;//坦克禁止不动

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    //画坦克
    public void paint(Graphics g){
        g.fillRect(x,y,50,50);
        move();
    }
    //根据方向移动坦克
    private void move(){
        if(!moving)return;//坦克禁止则返回

        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }
    }
}
