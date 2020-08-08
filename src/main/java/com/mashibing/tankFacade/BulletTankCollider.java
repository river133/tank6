package com.mashibing.tankFacade;
/*
比较器策略模式
坦克与炮弹相撞
当第二个是坦克时，再次调用collide
 */
public class BulletTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;
            if (b.collidewith(t)) {
                return false;//如果相撞不再往下
            }
            /*
            //排除自己的炮弹
        if(this.group == tank.getGroup())return false;

        if(rect.intersects(tank.rect)){
            tank.die();//坦克死掉
            this.die();//子弹死掉
            //调整爆炸中心位置
            int ex = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int ey = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            gm.add(new Explode(ex,ey,gm));
            return true;
        }
        return false;//未相撞
             */
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2,o1);
        }
            return true;
    }
}
