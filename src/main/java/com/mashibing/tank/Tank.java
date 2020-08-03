package com.mashibing.tank;

import java.awt.*;

public class Tank {
    private int x,y;
    private Dir dir =Dir.DOWN;
    private static final int SPEED=5;//坦克速度
    private boolean moving = false;//坦克禁止不动
    TankFrame tf =null;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
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
        Color c=g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,50,50);
        g.setColor(c);
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

    //发射子弹
    public void fire() {
        //坦克和子弹方向一致
        tf.bullets.add(new Bullet(this.x,this.y,this.dir,this.tf)) ;
    }
}
