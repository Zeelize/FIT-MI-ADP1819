package cz.fit.dpo.mvcshooter.abstractFactory;

import cz.fit.dpo.mvcshooter.model.*;
import cz.fit.dpo.mvcshooter.model.entity.*;

public class DefaultGameObjectFactory implements IGameObjectFactory {

    public Cannon createCannon() {
        return new Cannon();
    }

    public Enemy createEnemy() {
        return new Enemy();
    }

    public Missile createMissile() {
        return new Missile();
    }

    public Collision createCollision() {
        return new Collision();
    }

    public GameInfo createGameInfo(GameModel model) {
        return new GameInfo(model.getScore());
    }
}
