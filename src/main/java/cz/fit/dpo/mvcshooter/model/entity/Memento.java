package cz.fit.dpo.mvcshooter.model.entity;

import java.util.ArrayList;

public class Memento {
    private Cannon cannon;
    private ArrayList<Missile> missiles = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private int score;
    private short level;

    private int activeMovementStrategyIndex;

    public Memento() {
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }


    public Cannon getCannon() {
        return cannon;
    }

    public void setCannon(Cannon cannon) {
        this.cannon = cannon;
    }


    public ArrayList<Missile> getMissiles() {
        return missiles;
    }

    public void setMissiles(ArrayList<Missile> missiles) {
        ArrayList<Missile> newMissiles = new ArrayList<>();
        for (Missile m : missiles) {
            newMissiles.add(new Missile(m));
        }
        this.missiles = newMissiles;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        ArrayList<Enemy> newEnemies = new ArrayList<>();
        for (Enemy e : enemies) {
            newEnemies.add(new Enemy(e));
        }
        this.enemies = newEnemies;
    }

    public int getActiveMovementStrategyIndex() {
        return activeMovementStrategyIndex;
    }

    public void setActiveMovementStrategyIndex(int activeMovementStrategyIndex) {
        this.activeMovementStrategyIndex = activeMovementStrategyIndex;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
