package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.abstractFactory.DefaultGameObjectFactory;
import cz.fit.dpo.mvcshooter.abstractFactory.IGameObjectFactory;
import cz.fit.dpo.mvcshooter.observer.IObservable;
import cz.fit.dpo.mvcshooter.observer.IObserver;

import java.util.ArrayList;
import java.util.Random;

public class GameModel implements IObservable {
    private int moveStep = 10;
    private int score = 0;
    private int width = 500;
    private int height = 500;

    private Cannon cannon;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<IObserver> myObservers = new ArrayList<IObserver>();

    private IGameObjectFactory goFact = new DefaultGameObjectFactory();

    public GameModel() {
        this.cannon = this.goFact.createCannon();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int x = 2 * cannon.getPosX() + random.nextInt(this.width - 2 * cannon.getPosX());
            int y = random.nextInt(this.height);

            Enemy enemy = new Enemy();
            enemy.setPosX(x);
            enemy.setPosY(y);

            this.enemies.add(enemy);
        }
    }

    public int getScore() {
        return score;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cannon getCannon() {
        return this.cannon;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ModelInfo getInfo() {
        return this.goFact.createModelInfo(this);
    }

    public void moveCannonDown() {
        cannon.setPosY(cannon.getPosY() + this.moveStep);

        this.notifyObservers();
    }

    public void moveCannonUp() {
        cannon.setPosY(cannon.getPosY() - this.moveStep);

        this.notifyObservers();
    }

    public void attachObserver(IObserver observer) {
        if (this.myObservers.contains(observer)) return;
        this.myObservers.add(observer);
    }

    public void deattachObserver(IObserver observer) {
        this.myObservers.remove(observer);
    }

    public void notifyObservers() {
        for (IObserver observer : this.myObservers) {
            observer.update();
        }
    }
}
