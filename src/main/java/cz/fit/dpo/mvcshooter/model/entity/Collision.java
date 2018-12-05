package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.visitor.IVisitor;

public class Collision extends GameObject {

    private int lifetime = 0;

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCollision(this);
    }

    public void incLifetime() {
        this.lifetime++;
    }

    public int getLifetime() {
        return lifetime;
    }
}
