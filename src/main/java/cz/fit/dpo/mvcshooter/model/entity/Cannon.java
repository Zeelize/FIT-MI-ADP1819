package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.abstractFactory.IGameObjectFactory;
import cz.fit.dpo.mvcshooter.config.GameConfig;
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
    private static final IShootingMode singleShootingMode = new SingleShootingMode();
    private static final IShootingMode doubleShootingMode = new DoubleShootingMode();

    private int magazine = GameConfig.MAGAZINE_SIZE;
    private float speed = GameConfig.INIT_SPEED;
    private float angle = GameConfig.INIT_ANGLE;
    private IShootingMode shootingMode;
    private IGameObjectFactory goFact;
    private List<Missile> shootBatch;

    private float angleStep = GameConfig.ANGLE_STEP;
    private float speedStep = GameConfig.SPEED_STEP;


    public Cannon(IGameObjectFactory goFact) {
        this.setPosX(GameConfig.INIT_CANNON_X);
        this.setPosY(GameConfig.INIT_CANNON_Y);

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

    public int getMagazine() {
        return magazine;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public List<Missile> shoot() {
        this.shootBatch = new ArrayList<>();

        this.shootingMode.shoot(this);

        return this.shootBatch;
    }

    public void toggleShootingMode() {
        this.shootingMode.nextMode(this);
    }

    public void primitiveShoot() {
        if (magazine <= 0) return;
        this.shootBatch.add(goFact.createMissile());
        this.magazine--;
    }

    public void setDoubleShootingMode() {
        shootingMode = doubleShootingMode;
    }

    public void setSingleShootingMode() {
        shootingMode = singleShootingMode;
    }

    public void aimUp() {
        if (this.angle + this.angleStep > 90.0f) return;
        this.angle += this.angleStep;
    }

    public void aimDown() {
        if (this.angle - this.angleStep < -90.0f) return;
        this.angle -= this.angleStep;
    }

    public void incPower() {
        this.speed += this.speedStep;
    }

    public void reload() {
        magazine = GameConfig.MAGAZINE_SIZE;
    }

    public void decPower() {
        if (this.speed - this.speedStep < 0) return;
        this.speed -= this.speedStep;
    }

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCannon(this);
    }
}
