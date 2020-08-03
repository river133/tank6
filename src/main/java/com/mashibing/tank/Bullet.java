package com.mashibing.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 3;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();//子弹宽度高度
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    private int x,y;
    private Dir dir;

    private boolean live=true;//子弹存活状态，true：存活
    TankFrame tf =null;

    public Bullet( int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g){
        if(!live){//子弹出边界，删除自己
            this.tf.bullets.remove(this);
        }

        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }
        move();
    }
    //根据方向移动子弹
    private void move(){
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
        //子弹出边界则消失，容器size--
        if(x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT){
            live=false;
        }
    }
}
