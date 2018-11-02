package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.abstractFactory.IGameObjectFactory;
import cz.fit.dpo.mvcshooter.visitor.IVisitor;

/**
 * @author Ondrej Stuchlik
 */
public class Cannon extends GameObject {
    protected float speed = 10.0f;
    protected float angle = 0.0f;

    protected float confAngle = 1.0f;
    protected float confPower = 1.0f;


    public Cannon() {
        this.setPosX(30);
        this.setPosY(250);

    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Missile shoot(IGameObjectFactory goFact) {
        return goFact.createMissile();
    }

    public void aimUp() {
        if (this.angle + this.confAngle > 90.0f) return;
        this.angle += this.confAngle;
    }

    public void aimDown() {
        if (this.angle - this.confAngle < -90.0f) return;
        this.angle -= this.confAngle;
    }

    public void incPower() {
        this.speed += this.confPower;
    }

    public void decPower() {
        if (this.speed - this.confPower < 0) return;
        this.speed -= this.confPower;
    }

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCannon(this);
    }
}
