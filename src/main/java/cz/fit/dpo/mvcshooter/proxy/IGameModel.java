package cz.fit.dpo.mvcshooter.proxy;

import cz.fit.dpo.mvcshooter.command.AbsGameCommand;
import cz.fit.dpo.mvcshooter.model.entity.*;
import cz.fit.dpo.mvcshooter.observer.IObserver;
import cz.fit.dpo.mvcshooter.strategy.IMovementStrategy;

import java.util.ArrayList;

public interface IGameModel {
    // all public methods of the game model
    int getScore();

    long getStopwatch();

    short getLevel();

    boolean getPause();

    boolean getRunGame();

    int getConfHeight();

    int getConfWidth();

    Cannon getCannon();

    ArrayList<Enemy> getEnemies();

    ArrayList<Missile> getMissiles();

    GameInfo getInfo();

    void stopGame();

    void startGame();

    void moveCannonDown();

    void moveCannonUp();

    void pauseResumeGame();

    void shootCanon();

    void reloadCannon();

    void attachObserver(IObserver observer);

    void detachObserver(IObserver observer);

    void notifyObservers();

    void aimCanonUp();

    void aimCanonDown();

    void incCanonPower();

    void decCannonPower();

    void switchMovementStrategy();

    ArrayList<GameObject> getGameObjects();

    IMovementStrategy getActiveMovementStrategy();

    void toggleShootingMode();

    void registerCmd(AbsGameCommand cmd);

    void undoLastCmd();

    void setMemento(Object memento);

    Object createMemento();
}
