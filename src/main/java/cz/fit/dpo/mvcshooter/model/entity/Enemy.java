package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.visitor.IVisitor;

public class Enemy extends GameObject {
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }
}
