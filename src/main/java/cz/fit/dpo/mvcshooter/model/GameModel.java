package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.abstractFactory.DefaultGameObjectFactory;
import cz.fit.dpo.mvcshooter.abstractFactory.IGameObjectFactory;
import cz.fit.dpo.mvcshooter.config.GameConfig;
import cz.fit.dpo.mvcshooter.model.entity.*;
import cz.fit.dpo.mvcshooter.observer.IObservable;
import cz.fit.dpo.mvcshooter.observer.IObserver;
import cz.fit.dpo.mvcshooter.proxy.IGameModel;
import cz.fit.dpo.mvcshooter.strategy.IMovementStrategy;
import cz.fit.dpo.mvcshooter.strategy.RandomMovementStrategy;
import cz.fit.dpo.mvcshooter.strategy.SimpleMovementStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameModel implements IObservable, IGameModel {
    private int score = 0;


    private Cannon cannon;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Missile> missiles = new ArrayList<Missile>();
    private ArrayList<IObserver> myObservers = new ArrayList<IObserver>();

    private IGameObjectFactory goFact = new DefaultGameObjectFactory(this);
    private Timer timer;

    private final List<IMovementStrategy> movementStrategies = new ArrayList<IMovementStrategy>();
    private int activeMovementStrategyIndex = 0;

    public GameModel() {
        movementStrategies.add(new SimpleMovementStrategy());
        movementStrategies.add(new RandomMovementStrategy());

        this.initTimer();
        this.initGameObjects();
    }

    public void initTimer() {
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveGameObjects();
            }
        }, 0, GameConfig.TIME_PERIOD);
    }

    protected void moveGameObjects() {
        moveMissiles();

        removeBadMissiles();

        handleCollisions();

        this.notifyObservers();
    }

    private void handleCollisions() {
        for (Missile m : missiles) {
            for (Enemy e : enemies) {
                if (m.collidesWith(e)) {
                    // todo remove e and m
                    // todo create collision
                    // todo increment score
                }
            }
        }
    }

    protected void moveMissiles() {
        for (Missile m : this.missiles) {
            m.move();
        }


    }

    protected void removeBadMissiles() {
        ArrayList<Missile> rem = new ArrayList<Missile>();

        for (Missile m : this.missiles) {
            if (m.getPosX() > this.getConfWidth() || m.getPosX() < 0) {
                rem.add(m);
            } else if (m.getPosY() > this.getConfHeight() || m.getPosY() < 0) {
                rem.add(m);
            }
        }

        for (Missile m : rem) {
            this.missiles.remove(m);
        }
    }

    public void initGameObjects() {
        this.cannon = this.goFact.createCannon();

        this.enemies.clear();
        for (int i = 0; i < GameConfig.MAX_ENEMIES; i++) {
            this.enemies.add(this.goFact.createEnemy());
        }
    }

    public int getScore() {
        return score;
    }

    public int getConfHeight() {
        return GameConfig.MAX_HEIGHT;
    }

    public int getConfWidth() {
        return GameConfig.MAX_WIDTH;
    }

    public Cannon getCannon() {
        return this.cannon;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }

    public GameInfo getInfo() {
        return this.goFact.createGameInfo();
    }

    public void moveCannonDown() {
        if (cannon.getPosY() + GameConfig.MOVE_STEP > GameConfig.MAX_HEIGHT) return;
        cannon.setPosY(cannon.getPosY() + GameConfig.MOVE_STEP);

        this.notifyObservers();
    }

    public void moveCannonUp() {
        if (cannon.getPosY() + GameConfig.MOVE_STEP - 50 < 0) return;
        cannon.setPosY(cannon.getPosY() - GameConfig.MOVE_STEP);

        this.notifyObservers();
    }

    public void shootCanon() {
        this.missiles.addAll(this.cannon.shoot());

        this.notifyObservers();
    }

    public void attachObserver(IObserver observer) {
        if (this.myObservers.contains(observer)) return;
        this.myObservers.add(observer);
    }

    public void detachObserver(IObserver observer) {
        this.myObservers.remove(observer);
    }

    public void notifyObservers() {
        for (IObserver observer : this.myObservers) {
            observer.update();
        }
    }

    public void aimCanonUp() {
        this.cannon.aimUp();
        this.notifyObservers();
    }

    public void aimCanonDown() {
        this.cannon.aimDown();
        this.notifyObservers();
    }

    public void incCanonPower() {
        this.cannon.incPower();
        this.notifyObservers();
    }

    public void decCannonPower() {
        this.cannon.decPower();
        this.notifyObservers();
    }

    public void switchMovementStrategy() {
        activeMovementStrategyIndex = (activeMovementStrategyIndex + 1) % this.movementStrategies.size();
    }

    public ArrayList<GameObject> getGameObjects() {
        ArrayList<GameObject> go = new ArrayList<GameObject>();

        go.addAll(this.missiles);
        go.addAll(this.enemies);
        go.add(this.cannon);
        go.add(this.getInfo());

        return go;
    }

    public IMovementStrategy getActiveMovementStrategy() {
        return this.movementStrategies.get(this.activeMovementStrategyIndex);
    }

    public void toggleShootingMode() {
        this.cannon.toggleShootingMode();
        notifyObservers();
    }
}
