package cz.fit.dpo.mvcshooter.model.entity;

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

    public void move() {
        // todo fix IN BOUND
        int dirX = rnd.nextBoolean() ? 1 : -1;
        int dirY = rnd.nextBoolean() ? 1 : -1;

        int nx = (int) (getPosX() + dirX * getSpeed());
        int ny = (int) (getPosY() + dirY * getSpeed());

        this.setPosX(nx);
        this.setPosY(ny);
    }

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }
}
