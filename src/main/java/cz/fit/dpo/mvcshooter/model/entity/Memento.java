package cz.fit.dpo.mvcshooter.model.entity;

import java.util.ArrayList;

public class Memento {
    private Cannon cannon;
    private ArrayList<Missile> missiles = new ArrayList<Missile>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private int score;
    private int activeMovementStrategyIndex;

    public Memento() {
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
        this.missiles = new ArrayList<Missile>(missiles);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = new ArrayList<Enemy>(enemies);
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
