package com.mashibing.tankDecorator;

import java.awt.*;

/*
中介模式(调停者)
 */
public abstract class GameObject {
    protected int x,y;
    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
}
