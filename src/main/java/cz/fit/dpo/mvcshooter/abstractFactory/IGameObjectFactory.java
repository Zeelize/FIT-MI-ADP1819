package cz.fit.dpo.mvcshooter.abstractFactory;

import cz.fit.dpo.mvcshooter.model.*;

public interface IGameObjectFactory {
    Cannon createCannon();
    Enemy createEnemy();
    Missile createMissile();
    Collision createCollision();
    ModelInfo createModelInfo(GameModel model);
}
