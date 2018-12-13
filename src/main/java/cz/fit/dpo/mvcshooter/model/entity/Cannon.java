package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.abstractFactory.IGameObjectFactory;
import cz.fit.dpo.mvcshooter.config.GameConfig;
import cz.fit.dpo.mvcshooter.sound.SoundPlayer;
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
    private float angleStep = GameConfig.ANGLE_STEP;
    private float speedStep = GameConfig.SPEED_STEP;

    private int magazine;
    private float speed;
    private float angle;
    private IShootingMode shootingMode;
    private IGameObjectFactory goFact;
    private List<Missile> shootBatch;

    public Cannon(IGameObjectFactory goFact) {
        this.setPosX(GameConfig.INIT_CANNON_X);
        this.setPosY(GameConfig.INIT_CANNON_Y);

        this.shootingMode = singleShootingMode;

        this.magazine = GameConfig.MAGAZINE_SIZE;
        this.speed = GameConfig.INIT_SPEED;
        this.angle = GameConfig.INIT_ANGLE;

        this.goFact = goFact;
    }

    public Cannon(Cannon c) {
        this.setPosX(c.getPosX());
        this.setPosY(c.getPosY());

        this.shootingMode = c.getShootingMode();

        this.magazine = c.getMagazine();
        this.speed = c.getSpeed();
        this.angle = c.getAngle();

        this.goFact = c.getGoFact();
    }

    public IShootingMode getShootingMode() {
        return shootingMode;
    }

    public IGameObjectFactory getGoFact() {
        return goFact;
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
        if (magazine <= 0) {
            SoundPlayer.playEmptyMagazineEffect();
            return;
        }
        SoundPlayer.playShootEffect();
        this.shootBatch.add(goFact.createMissile());
        this.magazine--;
    }

    public void setDoubleShootingMode() {
        shootingMode = doubleShootingMode;
    }

    public void setSingleShootingMode() {
        shootingMode = singleShootingMode;
    }

    public void moveUp() {
        setPosY(getPosY() - GameConfig.MOVE_STEP);
    }

    public void moveDown() {
        setPosY(getPosY() + GameConfig.MOVE_STEP);
    }

    public void aimUp() {
        if (this.angle + this.angleStep > 90.0f) return;
        this.angle += this.angleStep;
    }

    public void aimDown() {
        if (this.angle - this.angleStep < -90.0f) return;
        this.angle -= this.angleStep;
    }

    public float getAngleStep() {
        return angleStep;
    }

    public float getSpeedStep() {
        return speedStep;
    }

    public void incPower() {
        this.speed += this.speedStep;
    }

    public void reload() {
        if (magazine > 0) return;
        SoundPlayer.playReloadEffect();
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
