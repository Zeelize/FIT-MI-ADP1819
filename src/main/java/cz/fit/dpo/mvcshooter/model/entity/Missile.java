package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.strategy.IMovementStrategy;
import cz.fit.dpo.mvcshooter.visitor.IVisitor;

public class Missile extends MovingObject {

    private IMovementStrategy strategy;

    public Missile(int x, int y, float s, float a, IMovementStrategy strategy) {
        this.setAngle(a);
        this.setSpeed(s);
        this.setInitX(x);
        this.setInitY(y);
        this.setTime(0);
        this.strategy = strategy;

        this.setPosX(x);
        this.setPosY(y);
    }

    public Missile(Missile m) {
        this.setAngle(m.getAngle());
        this.setSpeed(m.getSpeed());
        this.setInitX(m.getInitX());
        this.setInitY(m.getInitY());
        this.setTime(m.getTime());
        this.setStrategy(m.getStrategy());

        this.setPosX(m.getPosX());
        this.setPosY(m.getPosY());
    }

    public IMovementStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IMovementStrategy strategy) {
        this.strategy = strategy;
    }

    public void move() {
        setTime(getTime() + 1);
        long lifetime = getTime();

        int nx = strategy.nextPosX(this.getInitX(), getSpeed(), getAngle(), lifetime);
        int ny = strategy.nextPosY(this.getInitY(), this.getSpeed(), getAngle(), lifetime);

        this.setPosX(nx);
        this.setPosY(ny);
    }

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitMissile(this);
    }
}
