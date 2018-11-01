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
        return new Cannon();
    }

    public Enemy createEnemy() {
        int x = (this.model.getConfWidth() / 3) + random.nextInt(this.model.getConfWidth() - (this.model.getConfWidth() / 3));
        int y = random.nextInt(this.model.getConfHeight());

        Enemy enemy = new Enemy();
        enemy.setPosX(x);
        enemy.setPosY(y);

        return enemy;
    }

    public Missile createMissile() {
        return new Missile(
                this.model.getCannon().getPosX(),
                this.model.getCannon().getPosY(),
                this.model.getCannon().getSpeed(),
                this.model.getCannon().getAngle()
        );
    }

    public Collision createCollision() {
        return new Collision();
    }

    public GameInfo createGameInfo() {
        return new GameInfo(this.model);
    }
}
