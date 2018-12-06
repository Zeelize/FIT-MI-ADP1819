package cz.fit.dpo.mvcshooter.abstractFactory;

import cz.fit.dpo.mvcshooter.model.GameModel;
import cz.fit.dpo.mvcshooter.model.entity.*;

import java.util.Random;

public class DefaultGameObjectFactory implements IGameObjectFactory {

    private Random random = new Random();
    private GameModel model;

    public DefaultGameObjectFactory(GameModel model) {
        this.model = model;
    }

    public Cannon createCannon() {
        return new Cannon(this);
    }

    public Enemy createEnemy() {
        int x = (this.model.getConfWidth() / 3) + random.nextInt(this.model.getConfWidth() - (this.model.getConfWidth() / 3));
        int y = random.nextInt(this.model.getConfHeight());

        return new Enemy(x, y, (float) (model.getLevel() - 1));
    }

    public Missile createMissile() {
        return new Missile(
                this.model.getCannon().getPosX(),
                this.model.getCannon().getPosY(),
                this.model.getCannon().getSpeed(),
                this.model.getCannon().getAngle(),
                this.model.getActiveMovementStrategy()
        );
    }

    public Collision createCollision(int x, int y) {
        Collision c = new Collision();
        c.setPosX(x);
        c.setPosY(y);

        return c;
    }

    public GameInfo createGameInfo() {
        return new GameInfo(this.model);
    }
}
