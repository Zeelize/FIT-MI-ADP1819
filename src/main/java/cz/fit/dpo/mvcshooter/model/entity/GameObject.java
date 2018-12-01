package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.config.GameConfig;
import cz.fit.dpo.mvcshooter.visitor.IVisitor;

public abstract class GameObject {
    private int posX = 100;
    private int posY = 100;


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void move() {
    }

    public boolean collidesWith(GameObject other) {
        boolean res = true;

        int ax = this.getPosX();
        int ay = getPosY();
        int bx = other.getPosX();
        int by = other.getPosY();

        res = res && (Math.abs(ax - bx)) < GameConfig.COLLISION_MARGIN;
        res = res && (Math.abs(ay - by)) < GameConfig.COLLISION_MARGIN;

        return res;
    }

    public abstract void acceptVisitor(IVisitor visitor);
}
