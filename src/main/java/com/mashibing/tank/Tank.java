package com.mashibing.tank;

import java.awt.*;

public class Tank {
    private int x,y;
    private Dir dir =Dir.DOWN;
    private static final int SPEED=5;//坦克速度

    public static int WIDTH = ResourceMgr.tankD.getWidth();//坦克宽度高度
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

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

    //画坦克,根据按键方向
    public void paint(Graphics g){
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
        }

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

        int bx = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        //坦克和子弹方向一致
        tf.bullets.add(new Bullet(bx,by,this.dir,this.tf)) ;
    }
}
