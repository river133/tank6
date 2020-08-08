package com.mashibing.tankFacade;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
门面设计模式
 */
public class GameModel {
    //自己坦克
    Tank myTank = new Tank(200,500, Dir.DOWN, Group.GOOD,this);
    List<Bullet> bullets = new ArrayList<>();//子弹容器
    List<Tank> tanks = new ArrayList<>();//敌方坦克容器
    List<Explode> explodes = new ArrayList<>();//爆炸容器

    public GameModel() {
        int initTankCount = PropertyMgr.get("initTankCount");

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(50+i*80,200, Dir.DOWN, Group.BAD,this));
        }
    }

    public void paint(Graphics g) {
        Color c=g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量："+bullets.size(),10,60);
        g.drawString("敌方坦克数量："+tanks.size(),10,80);
        g.drawString("爆炸的数量："+explodes.size(),10,100);
        g.setColor(c);

        myTank.paint(g);//画坦克
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        //画敌方坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        //画爆炸
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        //子弹-坦克 碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collidewith(tanks.get(j));
            }
        }
    }

    public Tank getMainTank(){
        return myTank;
    }
}
