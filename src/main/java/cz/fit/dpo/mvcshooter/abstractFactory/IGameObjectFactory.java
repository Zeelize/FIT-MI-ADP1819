package cz.fit.dpo.mvcshooter.abstractFactory;

import cz.fit.dpo.mvcshooter.model.entity.*;

public interface IGameObjectFactory {
    Cannon createCannon();
    Enemy createEnemy();
    Missile createMissile();
    Collision createCollision();

    GameInfo createGameInfo();
}
