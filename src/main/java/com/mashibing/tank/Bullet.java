package com.mashibing.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 14;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();//子弹宽度高度
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    private int x,y;
    private Dir dir;

    private boolean living=true;//子弹存活状态，true：存活
    TankFrame tf =null;

    public Bullet( int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g){
        if(!living){//子弹出边界，删除自己
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
            living=false;
        }
    }

    //坦克-子弹 碰撞检测
    public void collidewith(Tank tank) {
        Rectangle rect1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),tank.WIDTH,tank.HEIGHT);
        if(rect1.intersects(rect2)){
            tank.die();//坦克死掉
            this.die();//子弹死掉
        }
    }
    //子弹碰撞后消失
    private void die() {
        this.living=false;
    }
}
