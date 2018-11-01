package cz.fit.dpo.mvcshooter.visitor;

import cz.fit.dpo.mvcshooter.model.entity.*;

public interface IVisitor {
    void visitCannon(Cannon cannon);

    void visitEnemy(Enemy enemy);

    void visitCollision(Collision collision);

    void visitMissile(Missile missile);

    void visitGameInfo(GameInfo info);
}
