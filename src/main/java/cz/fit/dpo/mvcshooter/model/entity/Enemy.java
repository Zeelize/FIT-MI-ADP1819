package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.config.GameConfig;
import cz.fit.dpo.mvcshooter.visitor.IVisitor;

import java.util.Random;

public class Enemy extends MovingObject {
    private Random rnd = new Random();

    public Enemy(int x, int y, float s) {
        this.setSpeed(s);
        this.setInitX(x);
        this.setInitY(y);

        this.setPosX(x);
        this.setPosY(y);
    }

    public Enemy(Enemy e) {
        this.setSpeed(e.getSpeed());
        this.setInitX(e.getInitX());
        this.setInitY(e.getInitY());

        this.setPosX(e.getPosX());
        this.setPosY(e.getPosY());
    }

    public void move() {
        int dirX = rnd.nextBoolean() ? 1 : -1;
        int dirY = rnd.nextBoolean() ? 1 : -1;

        int nx = (int) (getPosX() + dirX * getSpeed());
        int ny = (int) (getPosY() + dirY * getSpeed());

        if (nx < (GameConfig.MAX_WIDTH / 3)) nx = (GameConfig.MAX_WIDTH / 3);
        if (nx > GameConfig.MAX_WIDTH) nx = GameConfig.MAX_WIDTH;
        if (ny < 0) ny = 0;
        if (ny > GameConfig.MAX_HEIGHT) ny = GameConfig.MAX_HEIGHT;

        this.setPosX(nx);
        this.setPosY(ny);
    }

    public void incSpeed() {
        if (getSpeed() < GameConfig.MAX_ENEMIES_SPEED) {
            setSpeed(getSpeed() + 1.0f);
        }
    }

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }
}
