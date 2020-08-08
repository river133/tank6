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
    //责任链模式ColliderChain
    ColliderChain chain = new ColliderChain();

    //坦克、子弹、爆炸容器
    private List<GameObject> objects = new ArrayList<>();


    public GameModel() {
        int initTankCount = PropertyMgr.get("initTankCount");
        System.out.println(initTankCount);

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            add(new Tank(50+i*80,200, Dir.DOWN, Group.BAD,this));
        }
    }

    public void add(GameObject go){
        this.objects.add(go);
    }
    public void remove(GameObject go){
        this.objects.remove(go);
    }

    public void paint(Graphics g) {
        Color c=g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹数量："+bullets.size(),10,60);
//        g.drawString("敌方坦克数量："+tanks.size(),10,80);
//        g.drawString("爆炸的数量："+explodes.size(),10,100);
        g.setColor(c);

        myTank.paint(g);//画坦克、子弹、爆炸
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        //策略模式：子弹-坦克 碰撞检测
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1,o2);
            }
        }
    }

    public Tank getMainTank(){
        return myTank;
    }
}
