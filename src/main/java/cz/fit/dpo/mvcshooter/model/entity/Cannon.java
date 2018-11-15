package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.abstractFactory.IGameObjectFactory;
import cz.fit.dpo.mvcshooter.state.DoubleShootingMode;
import cz.fit.dpo.mvcshooter.state.IShootingMode;
import cz.fit.dpo.mvcshooter.state.SingleShootingMode;
import cz.fit.dpo.mvcshooter.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ondrej Stuchlik
 */
public class Cannon extends GameObject {
    protected static final IShootingMode singleShootingMode = new SingleShootingMode();
    protected static final IShootingMode doubleShootingMode = new DoubleShootingMode();

    protected float speed = 10.0f;
    protected float angle = 0.0f;
    protected IShootingMode shootingMode;
    protected IGameObjectFactory goFact;
    protected List<Missile> shootBatch;

    protected float confAngle = 1.0f;
    protected float confPower = 1.0f;


    public Cannon(IGameObjectFactory goFact) {
        this.setPosX(30);
        this.setPosY(250);

        this.shootingMode = singleShootingMode;

        this.goFact = goFact;

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

    public List<Missile> shoot() {
        this.shootBatch = new ArrayList<Missile>();

        this.shootingMode.shoot(this);

        return this.shootBatch;
    }

    public void toggleShootingMode() {
        this.shootingMode.nextMode(this);
    }

    public void primitiveShoot() {
        this.shootBatch.add(goFact.createMissile());
    }

    public void setDoubleShootingMode() {
        shootingMode = doubleShootingMode;
    }

    public void setSingleShootingMode() {
        shootingMode = singleShootingMode;
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
