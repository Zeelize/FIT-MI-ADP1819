package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.visitor.IVisitor;

public class Missile extends GameObject {

    protected int initX;
    protected int initY;
    protected float initSpeed;
    protected float initAngle;
    protected long initTime;

    public Missile(int x, int y, float s, float a) {
        this.initAngle = a;
        this.initSpeed = s;
        this.initX = x;
        this.initY = y;
        this.initTime = System.currentTimeMillis();

        this.setPosX(x);
        this.setPosY(y);
    }

    public void move() {
        long lifetime = System.currentTimeMillis() - this.initTime;

        int nx = (int) (initX + (initSpeed + lifetime * Math.cos(initAngle)));
        int ny = (int) (initX + (initSpeed + lifetime * Math.cos(initAngle)));

        this.setPosX(nx);
        this.setPosY(ny);
    }

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitMissile(this);
    }
}
