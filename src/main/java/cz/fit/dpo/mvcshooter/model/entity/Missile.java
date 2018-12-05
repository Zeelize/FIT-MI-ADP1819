package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.strategy.IMovementStrategy;
import cz.fit.dpo.mvcshooter.visitor.IVisitor;

public class Missile extends GameObject {

    private int initY;
    private int initX;
    private float speed;
    private float angle;
    private long time;
    private IMovementStrategy strategy;

    public Missile(int x, int y, float s, float a, IMovementStrategy strategy) {
        this.angle = a;
        this.speed = s;
        this.initX = x;
        this.initY = y;
        this.time = 0;
        this.strategy = strategy;

        this.setPosX(x);
        this.setPosY(y);
    }

    public void move() {
        long lifetime = ++time;

        int nx = strategy.nextPosX(initX, speed, angle, lifetime);
        int ny = strategy.nextPosY(initY, speed, angle, lifetime);

        this.setPosX(nx);
        this.setPosY(ny);
    }

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitMissile(this);
    }
}
