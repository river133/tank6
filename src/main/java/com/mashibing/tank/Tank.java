package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private static final int SPEED=1;//坦克速度

    public static int WIDTH = ResourceMgr.tankD.getWidth();//坦克宽度高度
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    private Random random=new Random();

    private int x,y;

    private Dir dir =Dir.DOWN;

    private boolean moving =true;//坦克禁止不动
    private TankFrame tf =null;
    private boolean living = true;//true：坦克或者
    private Group group = Group.BAD;//默认为敌方坦克

    public Tank(int x, int y, Dir dir,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tf = tf;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //画坦克,根据按键方向
    public void paint(Graphics g){
        if(!living){
            this.tf.tanks.remove(this);//碰撞死后不画坦克
        };
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
        //敌人坦克自动开火
        if (random.nextInt(10) > 8){
            this.fire();
        }
    }

    //发射子弹
    public void fire() {
        //调整子弹居中
        int bx = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(bx,by,this.dir,this.group,this.tf)) ;
    }

    //坦克碰撞后
    public void die() {
        this.living=false;
    }
}
