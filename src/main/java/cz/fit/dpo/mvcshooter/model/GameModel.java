package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.abstractFactory.DefaultGameObjectFactory;
import cz.fit.dpo.mvcshooter.abstractFactory.IGameObjectFactory;
import cz.fit.dpo.mvcshooter.command.AbsGameCommand;
import cz.fit.dpo.mvcshooter.command.PauseResumeGameCommand;
import cz.fit.dpo.mvcshooter.config.GameConfig;
import cz.fit.dpo.mvcshooter.model.entity.*;
import cz.fit.dpo.mvcshooter.observer.IObservable;
import cz.fit.dpo.mvcshooter.observer.IObserver;
import cz.fit.dpo.mvcshooter.proxy.IGameModel;
import cz.fit.dpo.mvcshooter.strategy.IMovementStrategy;
import cz.fit.dpo.mvcshooter.strategy.RandomMovementStrategy;
import cz.fit.dpo.mvcshooter.strategy.RealisticMovementStrategy;
import cz.fit.dpo.mvcshooter.strategy.SimpleMovementStrategy;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class GameModel implements IObservable, IGameModel {
    private final List<IMovementStrategy> movementStrategies = new ArrayList<>();
    private Cannon cannon;
    private int score = GameConfig.INIT_SCORE;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Missile> missiles = new ArrayList<>();
    private ArrayList<Collision> collisions = new ArrayList<>();
    private int activeMovementStrategyIndex = 0;
    private boolean pause = false;
    private ArrayList<IObserver> myObservers = new ArrayList<>();

    private Queue<AbsGameCommand> unexecutedCommands = new LinkedBlockingQueue<>();
    private Stack<AbsGameCommand> executedCommands = new Stack<>();

    private IGameObjectFactory goFact = new DefaultGameObjectFactory(this);
    private Timer timer;

    public GameModel() {
        movementStrategies.add(new SimpleMovementStrategy());
        movementStrategies.add(new RandomMovementStrategy());
        movementStrategies.add(new RealisticMovementStrategy());

        this.initTimer();
        this.initGameObjects();
    }

    private void initTimer() {
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                executeCommands();
                moveGameObjects();
            }
        }, 0, GameConfig.TIME_PERIOD);
    }

    private void executeCommands() {
        while (!this.unexecutedCommands.isEmpty()) {
            AbsGameCommand cmd = unexecutedCommands.poll();
            cmd.extExecute();
            this.executedCommands.push(cmd);
        }
    }

    private void moveGameObjects() {
        moveMissiles();
        moveEnemies();
        removeBadMissiles();
        handleCollisions();
        addEnemies();
        this.notifyObservers();
    }

    private void handleCollisions() {
        Set<Enemy> enemiesToRemove = new HashSet<>();
        Set<Missile> missilesToRemove = new HashSet<>();
        Set<Collision> collisionsToRemove = new HashSet<>();

        for (Missile m : missiles) {
            for (Enemy e : enemies) {
                if (m.collidesWith(e)) {
                    this.score++;

                    enemiesToRemove.add(e);
                    missilesToRemove.add(m);

                    this.collisions.add(goFact.createCollision(e.getPosX(), e.getPosY()));
                }
            }
        }

        for (Collision c : this.collisions) {
            c.incLifetime();
            if (c.getLifetime() > GameConfig.COLLISION_LIFETIME)
                collisionsToRemove.add(c);
        }

        for (Enemy e : enemiesToRemove) {
            this.enemies.remove(e);
        }

        for (Missile m : missilesToRemove) {
            this.missiles.remove(m);
        }

        for (Collision c : collisionsToRemove) {
            this.collisions.remove(c);
        }
    }

    private void moveMissiles() {
        for (Missile m : this.missiles) {
            m.move();
        }
    }

    private void moveEnemies() {
        // todo check score and if at certain point, increase speed of enemies
        for (Enemy e : this.enemies) {
            e.move();
        }
    }

    private void removeBadMissiles() {
        ArrayList<Missile> rem = new ArrayList<>();

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

    private void addEnemies() {
        for (int i = enemies.size(); i < GameConfig.MAX_ENEMIES; i++) {
            this.enemies.add(this.goFact.createEnemy());
        }
    }

    private void initGameObjects() {
        this.cannon = this.goFact.createCannon();

        this.enemies.clear();
        for (int i = 0; i < GameConfig.MAX_ENEMIES; i++) {
            this.enemies.add(this.goFact.createEnemy());
        }
    }

    public int getScore() {
        return score;
    }

    public boolean getPause() {
        return pause;
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

    @Override
    public void pauseResumeGame() {
        pause = !pause;
        if (pause) {
            timer.cancel();
        } else {
            initTimer();
        }
        notifyObservers();
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

    public void reloadCannon() {
        this.cannon.reload();
        this.notifyObservers();
    }

    public void switchMovementStrategy() {
        activeMovementStrategyIndex = (activeMovementStrategyIndex + 1) % this.movementStrategies.size();
    }

    public ArrayList<GameObject> getGameObjects() {
        ArrayList<GameObject> go = new ArrayList<>();

        go.addAll(this.missiles);
        go.addAll(this.enemies);
        go.addAll(this.collisions);
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

    @Override
    public void registerCmd(AbsGameCommand cmd) {
        if (cmd instanceof PauseResumeGameCommand) {
            cmd.extExecute();
            return;
        }
        if (pause) return;
        this.unexecutedCommands.add(cmd);
    }

    @Override
    public void undoLastCmd() {
        AbsGameCommand cmd = this.executedCommands.pop();
        cmd.unexecute();
    }

    @Override
    public void setMemento(Object memento) {
        Memento m = (Memento) memento;
        this.score = m.getScore();
        this.cannon = m.getCannon();
        this.activeMovementStrategyIndex = m.getActiveMovementStrategyIndex();
        this.enemies = m.getEnemies();
        this.missiles = m.getMissiles();
    }

    @Override
    public Object createMemento() {
        Memento m = new Memento();
        m.setActiveMovementStrategyIndex(this.activeMovementStrategyIndex);
        m.setCannon(this.cannon);
        m.setEnemies(this.enemies);
        m.setMissiles(this.missiles);
        m.setScore(this.score);

        return m;
    }
}
