package com.mashibing.tankFactory.abstractfactory;

import com.mashibing.tankFactory.Tank;

import java.awt.*;

public abstract class BaseBullet {
    public abstract void paint(Graphics g);
    public abstract void collidewith(Tank tank);
}
