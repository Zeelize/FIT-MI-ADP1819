package cz.fit.dpo.mvcshooter.model.entity;

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

    public abstract void acceptVisitor(IVisitor visitor);
}
